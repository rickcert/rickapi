package com.rick.spring.service;

import com.rick.spring.entity.HqhOperLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 操作日志(HqhOperLog)表服务接口
 *
 * @author makejava
 * @since 2022-05-20 16:47:02
 */
public interface HqhOperLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    HqhOperLog queryById(Integer operId);

    /**
     * 分页查询
     *
     * @param hqhOperLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HqhOperLog> queryByPage(HqhOperLog hqhOperLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hqhOperLog 实例对象
     * @return 实例对象
     */
    HqhOperLog insert(HqhOperLog hqhOperLog);

    /**
     * 修改数据
     *
     * @param hqhOperLog 实例对象
     * @return 实例对象
     */
    HqhOperLog update(HqhOperLog hqhOperLog);

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer operId);

}
