package com.rick.spring.service.impl;

import com.rick.spring.entity.HqhYyyl;
import com.rick.spring.dao.HqhYyylDao;
import com.rick.spring.service.HqhYyylService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (HqhYyyl)表服务实现类
 *
 * @author makejava
 * @since 2022-05-18 15:03:50
 */
@Service("hqhYyylService")
public class HqhYyylServiceImpl implements HqhYyylService {
    @Resource
    private HqhYyylDao hqhYyylDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqhYyyl queryById(Integer id) {
        return this.hqhYyylDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hqhYyyl 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HqhYyyl> queryByPage(HqhYyyl hqhYyyl, PageRequest pageRequest) {
        long total = this.hqhYyylDao.count(hqhYyyl);
        return new PageImpl<>(this.hqhYyylDao.queryAllByLimit(hqhYyyl, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param hqhYyyl 实例对象
     * @return 实例对象
     */
    @Override
    public HqhYyyl insert(HqhYyyl hqhYyyl) {
        this.hqhYyylDao.insert(hqhYyyl);
        return hqhYyyl;
    }

    /**
     * 修改数据
     *
     * @param hqhYyyl 实例对象
     * @return 实例对象
     */
    @Override
    public HqhYyyl update(HqhYyyl hqhYyyl) {
        this.hqhYyylDao.update(hqhYyyl);
        return this.queryById(hqhYyyl.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.hqhYyylDao.deleteById(id) > 0;
    }
}
