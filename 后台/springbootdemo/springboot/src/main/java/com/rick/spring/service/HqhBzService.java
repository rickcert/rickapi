package com.rick.spring.service;

import com.rick.spring.entity.HqhBz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (HqhBz)表服务接口
 *
 * @author makejava
 * @since 2022-05-18 15:03:45
 */
public interface HqhBzService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhBz queryById(Integer id);

    /**
     * 分页查询
     *
     * @param hqhBz 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HqhBz> queryByPage(HqhBz hqhBz, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hqhBz 实例对象
     * @return 实例对象
     */
    HqhBz insert(HqhBz hqhBz);

    /**
     * 修改数据
     *
     * @param hqhBz 实例对象
     * @return 实例对象
     */
    HqhBz update(HqhBz hqhBz);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
