package com.rick.spring.dao;

import com.rick.spring.entity.HqhDmtp;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (HqhDmtp)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-18 15:03:46
 */
public interface HqhDmtpDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhDmtp queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param hqhDmtp 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<HqhDmtp> queryAllByLimit(HqhDmtp hqhDmtp, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param hqhDmtp 查询条件
     * @return 总行数
     */
    long count(HqhDmtp hqhDmtp);

    /**
     * 新增数据
     *
     * @param hqhDmtp 实例对象
     * @return 影响行数
     */
    int insert(HqhDmtp hqhDmtp);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqhDmtp> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqhDmtp> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqhDmtp> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HqhDmtp> entities);

    /**
     * 修改数据
     *
     * @param hqhDmtp 实例对象
     * @return 影响行数
     */
    int update(HqhDmtp hqhDmtp);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

