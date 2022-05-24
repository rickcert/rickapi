package com.rick.spring.service.impl;

import com.rick.spring.entity.HqhOperLog;
import com.rick.spring.dao.HqhOperLogDao;
import com.rick.spring.service.HqhOperLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 操作日志(HqhOperLog)表服务实现类
 *
 * @author makejava
 * @since 2022-05-20 16:47:03
 */
@Service("hqhOperLogService")
public class HqhOperLogServiceImpl implements HqhOperLogService {
    @Resource
    private HqhOperLogDao hqhOperLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    @Override
    public HqhOperLog queryById(Integer operId) {
        return this.hqhOperLogDao.queryById(operId);
    }

    /**
     * 分页查询
     *
     * @param hqhOperLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HqhOperLog> queryByPage(HqhOperLog hqhOperLog, PageRequest pageRequest) {
        long total = this.hqhOperLogDao.count(hqhOperLog);
        return new PageImpl<>(this.hqhOperLogDao.queryAllByLimit(hqhOperLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param hqhOperLog 实例对象
     * @return 实例对象
     */
    @Override
    public HqhOperLog insert(HqhOperLog hqhOperLog) {
        this.hqhOperLogDao.insert(hqhOperLog);
        return hqhOperLog;
    }

    /**
     * 修改数据
     *
     * @param hqhOperLog 实例对象
     * @return 实例对象
     */
    @Override
    public HqhOperLog update(HqhOperLog hqhOperLog) {
        this.hqhOperLogDao.update(hqhOperLog);
        return this.queryById(hqhOperLog.getOperId());
    }

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer operId) {
        return this.hqhOperLogDao.deleteById(operId) > 0;
    }
}
