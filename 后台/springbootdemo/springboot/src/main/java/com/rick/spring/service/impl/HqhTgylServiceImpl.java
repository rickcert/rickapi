package com.rick.spring.service.impl;

import com.rick.spring.entity.HqhTgyl;
import com.rick.spring.dao.HqhTgylDao;
import com.rick.spring.service.HqhTgylService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (HqhTgyl)表服务实现类
 *
 * @author makejava
 * @since 2022-05-18 15:03:49
 */
@Service("hqhTgylService")
public class HqhTgylServiceImpl implements HqhTgylService {
    @Resource
    private HqhTgylDao hqhTgylDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqhTgyl queryById(Integer id) {
        return this.hqhTgylDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hqhTgyl 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HqhTgyl> queryByPage(HqhTgyl hqhTgyl, PageRequest pageRequest) {
        long total = this.hqhTgylDao.count(hqhTgyl);
        return new PageImpl<>(this.hqhTgylDao.queryAllByLimit(hqhTgyl, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param hqhTgyl 实例对象
     * @return 实例对象
     */
    @Override
    public HqhTgyl insert(HqhTgyl hqhTgyl) {
        this.hqhTgylDao.insert(hqhTgyl);
        return hqhTgyl;
    }

    /**
     * 修改数据
     *
     * @param hqhTgyl 实例对象
     * @return 实例对象
     */
    @Override
    public HqhTgyl update(HqhTgyl hqhTgyl) {
        this.hqhTgylDao.update(hqhTgyl);
        return this.queryById(hqhTgyl.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.hqhTgylDao.deleteById(id) > 0;
    }
}
