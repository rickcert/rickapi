package com.rick.spring.controller;

import com.rick.spring.annotation.Log;
import com.rick.spring.entity.HqhUser;
import com.rick.spring.service.HqhUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户信息表(HqhUser)表控制层
 *
 * @author makejava
 * @since 2022-04-28 00:05:57
 */
@RestController
@RequestMapping("hqhUser")
public class HqhUserController {
    /**
     * 服务对象
     */
    @Resource
    private HqhUserService hqhUserService;

    /**
     * 分页查询
     *
     * @param hqhUser 筛选条件
     * @param
     * @return 查询结果
     */
    @GetMapping
    @Log(title="查询用户", businessType="用户操作")
    public ResponseEntity<Page<HqhUser>> queryByPage(HqhUser hqhUser) {
        return ResponseEntity.ok(this.hqhUserService.queryByPage(hqhUser, PageRequest.of(hqhUser.getPage(),hqhUser.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HqhUser> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.hqhUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hqhUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HqhUser> add(@RequestBody HqhUser hqhUser) {
        hqhUser.setCreateTime(new Date());
        hqhUser.setCreateBy(hqhUser.getUserName());
        hqhUser.setStatus("0");
        hqhUser.setUpdateBy(hqhUser.getUserName());
        hqhUser.setUpdateTime(new Date());
        hqhUser.setPassword(hqhUser.getUserName());
        return ResponseEntity.ok(this.hqhUserService.insert(hqhUser));
    }

    /**
     * 编辑数据
     *
     * @param hqhUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HqhUser> edit(@RequestBody HqhUser hqhUser) {
        return ResponseEntity.ok(this.hqhUserService.update(hqhUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(this.hqhUserService.deleteById(id));
    }

}

