package com.rick.spring.service.impl;

import com.rick.spring.entity.HqhPixiv;
import com.rick.spring.dao.HqhPixivDao;
import com.rick.spring.service.HqhPixivService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (HqhPixiv)表服务实现类
 *
 * @author makejava
 * @since 2022-05-18 15:03:48
 */
@Service("hqhPixivService")
public class HqhPixivServiceImpl implements HqhPixivService {
    @Resource
    private HqhPixivDao hqhPixivDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqhPixiv queryById(Integer id) {
        return this.hqhPixivDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hqhPixiv 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HqhPixiv> queryByPage(HqhPixiv hqhPixiv, PageRequest pageRequest) {
        long total = this.hqhPixivDao.count(hqhPixiv);
        return new PageImpl<>(this.hqhPixivDao.queryAllByLimit(hqhPixiv, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param hqhPixiv 实例对象
     * @return 实例对象
     */
    @Override
    public HqhPixiv insert(HqhPixiv hqhPixiv) {
        this.hqhPixivDao.insert(hqhPixiv);
        return hqhPixiv;
    }

    /**
     * 修改数据
     *
     * @param hqhPixiv 实例对象
     * @return 实例对象
     */
    @Override
    public HqhPixiv update(HqhPixiv hqhPixiv) {
        this.hqhPixivDao.update(hqhPixiv);
        return this.queryById(hqhPixiv.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.hqhPixivDao.deleteById(id) > 0;
    }
}
