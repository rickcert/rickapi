package com.rick.spring.service;

import com.rick.spring.entity.HqhDmtp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (HqhDmtp)表服务接口
 *
 * @author makejava
 * @since 2022-05-18 15:03:46
 */
public interface HqhDmtpService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhDmtp queryById(Integer id);

    /**
     * 分页查询
     *
     * @param hqhDmtp 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HqhDmtp> queryByPage(HqhDmtp hqhDmtp, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hqhDmtp 实例对象
     * @return 实例对象
     */
    HqhDmtp insert(HqhDmtp hqhDmtp);

    /**
     * 修改数据
     *
     * @param hqhDmtp 实例对象
     * @return 实例对象
     */
    HqhDmtp update(HqhDmtp hqhDmtp);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
