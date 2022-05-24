package com.rick.spring.service.impl;

import com.rick.spring.entity.HqhBz;
import com.rick.spring.dao.HqhBzDao;
import com.rick.spring.service.HqhBzService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (HqhBz)表服务实现类
 *
 * @author makejava
 * @since 2022-05-18 15:03:45
 */
@Service("hqhBzService")
public class HqhBzServiceImpl implements HqhBzService {
    @Resource
    private HqhBzDao hqhBzDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqhBz queryById(Integer id) {
        return this.hqhBzDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hqhBz 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HqhBz> queryByPage(HqhBz hqhBz, PageRequest pageRequest) {
        long total = this.hqhBzDao.count(hqhBz);
        return new PageImpl<>(this.hqhBzDao.queryAllByLimit(hqhBz, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param hqhBz 实例对象
     * @return 实例对象
     */
    @Override
    public HqhBz insert(HqhBz hqhBz) {
        this.hqhBzDao.insert(hqhBz);
        return hqhBz;
    }

    /**
     * 修改数据
     *
     * @param hqhBz 实例对象
     * @return 实例对象
     */
    @Override
    public HqhBz update(HqhBz hqhBz) {
        this.hqhBzDao.update(hqhBz);
        return this.queryById(hqhBz.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.hqhBzDao.deleteById(id) > 0;
    }
}
