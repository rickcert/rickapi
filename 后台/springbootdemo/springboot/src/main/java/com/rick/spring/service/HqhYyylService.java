package com.rick.spring.service;

import com.rick.spring.entity.HqhYyyl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (HqhYyyl)表服务接口
 *
 * @author makejava
 * @since 2022-05-18 15:03:50
 */
public interface HqhYyylService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhYyyl queryById(Integer id);

    /**
     * 分页查询
     *
     * @param hqhYyyl 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HqhYyyl> queryByPage(HqhYyyl hqhYyyl, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hqhYyyl 实例对象
     * @return 实例对象
     */
    HqhYyyl insert(HqhYyyl hqhYyyl);

    /**
     * 修改数据
     *
     * @param hqhYyyl 实例对象
     * @return 实例对象
     */
    HqhYyyl update(HqhYyyl hqhYyyl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
