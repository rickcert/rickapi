package com.rick.spring.service;

import com.rick.spring.entity.HqhPixiv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (HqhPixiv)表服务接口
 *
 * @author makejava
 * @since 2022-05-18 15:03:48
 */
public interface HqhPixivService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhPixiv queryById(Integer id);

    /**
     * 分页查询
     *
     * @param hqhPixiv 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HqhPixiv> queryByPage(HqhPixiv hqhPixiv, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hqhPixiv 实例对象
     * @return 实例对象
     */
    HqhPixiv insert(HqhPixiv hqhPixiv);

    /**
     * 修改数据
     *
     * @param hqhPixiv 实例对象
     * @return 实例对象
     */
    HqhPixiv update(HqhPixiv hqhPixiv);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
