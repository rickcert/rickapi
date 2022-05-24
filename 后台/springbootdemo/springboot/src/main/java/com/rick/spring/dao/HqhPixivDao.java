package com.rick.spring.dao;

import com.rick.spring.entity.HqhPixiv;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (HqhPixiv)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-18 15:03:47
 */
public interface HqhPixivDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhPixiv queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param hqhPixiv 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<HqhPixiv> queryAllByLimit(HqhPixiv hqhPixiv, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param hqhPixiv 查询条件
     * @return 总行数
     */
    long count(HqhPixiv hqhPixiv);

    /**
     * 新增数据
     *
     * @param hqhPixiv 实例对象
     * @return 影响行数
     */
    int insert(HqhPixiv hqhPixiv);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqhPixiv> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqhPixiv> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqhPixiv> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HqhPixiv> entities);

    /**
     * 修改数据
     *
     * @param hqhPixiv 实例对象
     * @return 影响行数
     */
    int update(HqhPixiv hqhPixiv);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

