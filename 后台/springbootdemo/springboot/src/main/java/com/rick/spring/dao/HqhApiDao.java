package com.rick.spring.dao;

import com.rick.spring.entity.HqhApi;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (HqhApi)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-18 19:20:26
 */
public interface HqhApiDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqhApi queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param hqhApi 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<HqhApi> queryAllByLimit(HqhApi hqhApi, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param hqhApi 查询条件
     * @return 总行数
     */
    long count(HqhApi hqhApi);

    /**
     * 新增数据
     *
     * @param hqhApi 实例对象
     * @return 影响行数
     */
    int insert(HqhApi hqhApi);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqhApi> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqhApi> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqhApi> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HqhApi> entities);

    /**
     * 修改数据
     *
     * @param hqhApi 实例对象
     * @return 影响行数
     */
    int update(HqhApi hqhApi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

