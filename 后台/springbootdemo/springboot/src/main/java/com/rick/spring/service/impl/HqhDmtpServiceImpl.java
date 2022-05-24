package com.rick.spring.service.impl;

import com.rick.spring.entity.HqhDmtp;
import com.rick.spring.dao.HqhDmtpDao;
import com.rick.spring.service.HqhDmtpService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (HqhDmtp)表服务实现类
 *
 * @author makejava
 * @since 2022-05-18 15:03:47
 */
@Service("hqhDmtpService")
public class HqhDmtpServiceImpl implements HqhDmtpService {
    @Resource
    private HqhDmtpDao hqhDmtpDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqhDmtp queryById(Integer id) {
        return this.hqhDmtpDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hqhDmtp 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HqhDmtp> queryByPage(HqhDmtp hqhDmtp, PageRequest pageRequest) {
        long total = this.hqhDmtpDao.count(hqhDmtp);
        return new PageImpl<>(this.hqhDmtpDao.queryAllByLimit(hqhDmtp, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param hqhDmtp 实例对象
     * @return 实例对象
     */
    @Override
    public HqhDmtp insert(HqhDmtp hqhDmtp) {
        this.hqhDmtpDao.insert(hqhDmtp);
        return hqhDmtp;
    }

    /**
     * 修改数据
     *
     * @param hqhDmtp 实例对象
     * @return 实例对象
     */
    @Override
    public HqhDmtp update(HqhDmtp hqhDmtp) {
        this.hqhDmtpDao.update(hqhDmtp);
        return this.queryById(hqhDmtp.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.hqhDmtpDao.deleteById(id) > 0;
    }
}
