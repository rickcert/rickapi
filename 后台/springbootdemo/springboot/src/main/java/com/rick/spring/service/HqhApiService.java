package com.rick.spring.service;

import com.rick.spring.entity.HqhApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (HqhApi)表服务接口
 *
 * @author makejava
 * @since 2022-05-18 19:20:27
 */
public interface HqhApiService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhApi queryById(Integer id);

    /**
     * 分页查询
     *
     * @param hqhApi 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HqhApi> queryByPage(HqhApi hqhApi, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hqhApi 实例对象
     * @return 实例对象
     */
    HqhApi insert(HqhApi hqhApi);

    /**
     * 修改数据
     *
     * @param hqhApi 实例对象
     * @return 实例对象
     */
    HqhApi update(HqhApi hqhApi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
