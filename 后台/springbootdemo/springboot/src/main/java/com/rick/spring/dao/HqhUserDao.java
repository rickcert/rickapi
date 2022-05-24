package com.rick.spring.dao;

import com.rick.spring.entity.HqhUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 用户信息表(HqhUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-28 00:05:58
 */
public interface HqhUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    HqhUser queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param hqhUser 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<HqhUser> queryAllByLimit(HqhUser hqhUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param hqhUser 查询条件
     * @return 总行数
     */
    long count(HqhUser hqhUser);

    /**
     * 新增数据
     *
     * @param hqhUser 实例对象
     * @return 影响行数
     */
    int insert(HqhUser hqhUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqhUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqhUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqhUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HqhUser> entities);

    /**
     * 修改数据
     *
     * @param hqhUser 实例对象
     * @return 影响行数
     */
    int update(HqhUser hqhUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

    HqhUser queryByUserName(String userName);
}

