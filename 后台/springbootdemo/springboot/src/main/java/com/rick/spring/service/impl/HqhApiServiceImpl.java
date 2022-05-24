package com.rick.spring.service.impl;

import com.rick.spring.entity.HqhApi;
import com.rick.spring.dao.HqhApiDao;
import com.rick.spring.service.HqhApiService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (HqhApi)表服务实现类
 *
 * @author makejava
 * @since 2022-05-18 19:20:28
 */
@Service("hqhApiService")
public class HqhApiServiceImpl implements HqhApiService {
    @Resource
    private HqhApiDao hqhApiDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqhApi queryById(Integer id) {
        return this.hqhApiDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hqhApi 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HqhApi> queryByPage(HqhApi hqhApi, PageRequest pageRequest) {
        long total = this.hqhApiDao.count(hqhApi);
        return new PageImpl<>(this.hqhApiDao.queryAllByLimit(hqhApi, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param hqhApi 实例对象
     * @return 实例对象
     */
    @Override
    public HqhApi insert(HqhApi hqhApi) {
        this.hqhApiDao.insert(hqhApi);
        return hqhApi;
    }

    /**
     * 修改数据
     *
     * @param hqhApi 实例对象
     * @return 实例对象
     */
    @Override
    public HqhApi update(HqhApi hqhApi) {
        this.hqhApiDao.update(hqhApi);
        return this.queryById(hqhApi.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.hqhApiDao.deleteById(id) > 0;
    }
}
