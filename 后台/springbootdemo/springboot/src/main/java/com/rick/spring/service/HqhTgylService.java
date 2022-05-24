package com.rick.spring.service;

import com.rick.spring.entity.HqhTgyl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (HqhTgyl)表服务接口
 *
 * @author makejava
 * @since 2022-05-18 15:03:49
 */
public interface HqhTgylService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhTgyl queryById(Integer id);

    /**
     * 分页查询
     *
     * @param hqhTgyl 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HqhTgyl> queryByPage(HqhTgyl hqhTgyl, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hqhTgyl 实例对象
     * @return 实例对象
     */
    HqhTgyl insert(HqhTgyl hqhTgyl);

    /**
     * 修改数据
     *
     * @param hqhTgyl 实例对象
     * @return 实例对象
     */
    HqhTgyl update(HqhTgyl hqhTgyl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
