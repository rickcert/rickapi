package com.rick.spring.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rick.spring.constant.Constants;
import com.rick.spring.entity.HqhLoginUser;
import com.rick.spring.entity.HqhUser;
import com.rick.spring.dao.HqhUserDao;
import com.rick.spring.exception.PasswordIncorrectException;
import com.rick.spring.exception.UserNotFoundException;
import com.rick.spring.service.HqhUserService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户信息表(HqhUser)表服务实现类
 *
 * @author makejava
 * @since 2022-04-28 00:05:59
 */
@Slf4j
@Service("hqhUserService")
public class HqhUserServiceImpl implements HqhUserService {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private HqhUserDao hqhUserDao;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public HqhUser queryById(Long userId) {
        return this.hqhUserDao.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param hqhUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<HqhUser> queryByPage(HqhUser hqhUser, PageRequest pageRequest) {
        long total = this.hqhUserDao.count(hqhUser);
        return new PageImpl<>(this.hqhUserDao.queryAllByLimit(hqhUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param hqhUser 实例对象
     * @return 实例对象
     */
    @Override
    public HqhUser insert(HqhUser hqhUser) {
        this.hqhUserDao.insert(hqhUser);
        return hqhUser;
    }

    /**
     * 修改数据
     *
     * @param hqhUser 实例对象
     * @return 实例对象
     */
    @Override
    public HqhUser update(HqhUser hqhUser) {
        this.hqhUserDao.update(hqhUser);
        return this.queryById(hqhUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.hqhUserDao.deleteById(userId) > 0;
    }

    @Override
    public HqhLoginUser login(String userName, String password) {

        ValueOperations ops = redisTemplate.opsForValue();


        // 1、登陆，使用用户名查询用户，没有查询到，说明没有该账户
        HqhUser hqhUser = hqhUserDao.queryByUserName(userName);


        if (hqhUser == null) {
            log.info("执行登陆操作：【" + userName + "】该用户不存在");
            throw new UserNotFoundException("执行登陆操作：【"+ userName +"】该用户不存在");
        }

        // 2、如果查到了，比较比较密码，密码如果不正确，登陆失败
        if (!password.equals(hqhUser.getPassword())) {
            log.info("执行登陆操作：【" + userName + "】该用户密码输入错误");
            throw new PasswordIncorrectException("执行登陆操作：【"+ userName +"】该用户密码输入错误");
        }

        // (1) 生成token
        String token = UUID.randomUUID().toString();

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));

        // 通过ip获取其所属的地址
        ResponseEntity<String> result = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip=" + request.getRemoteHost() + "&json=true", String.class);
        String body = result.getBody();
        JSONObject jsonObject= JSONUtil.parseObj(body);

        String location = jsonObject.getStr("addr") + jsonObject.getStr("pro") + jsonObject.getStr("city") + jsonObject.getStr("region");

        // (2) 封装一个YdlLoginUser，保存在redis
        HqhLoginUser hqhLoginUser = HqhLoginUser.builder()
                .userId(hqhUser.getUserId())
                .token(token)
                .ipaddr(request.getRemoteAddr())
                .loginTime(new Date())
                .os(userAgent.getOperatingSystem().getName())
                .browser(userAgent.getBrowser().getName())
                .loginLocation(location)
                .hqhUser(hqhUser)
                .build();


        String keyPrefix = Constants.TOKEN_PREFIX + userName + ":";
        // 2、查询token:username:前缀的数据
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        System.out.println(keys);
        // 3、删除原来的数据
        for (String key : keys) {
            redisTemplate.delete(key);
        }
//         4、把新的数据加入redis
        ops.set(keyPrefix + token, hqhLoginUser, Constants.TOKEN_TIME, TimeUnit.SECONDS);

        return hqhLoginUser;
    }
    @Override
    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取首部信息的token
        String token = request.getHeader(Constants.HEAD_AUTHORIZATION);

        // 删除redis中的token user这些数据
        redisTemplate.delete(Constants.TOKEN_PREFIX + token);

    }

}

