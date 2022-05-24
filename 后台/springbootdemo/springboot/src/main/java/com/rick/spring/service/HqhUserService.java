package com.rick.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rick.spring.entity.HqhLoginUser;
import com.rick.spring.entity.HqhUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户信息表(HqhUser)表服务接口
 *
 * @author makejava
 * @since 2022-04-28 00:05:58
 */
public interface HqhUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    HqhUser queryById(Long userId);

    /**
     * 分页查询
     *
     * @param hqhUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HqhUser> queryByPage(HqhUser hqhUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hqhUser 实例对象
     * @return 实例对象
     */
    HqhUser insert(HqhUser hqhUser);

    /**
     * 修改数据
     *
     * @param hqhUser 实例对象
     * @return 实例对象
     */
    HqhUser update(HqhUser hqhUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */

    HqhLoginUser login(String userName, String password) throws JsonProcessingException;

    void logout();
}
