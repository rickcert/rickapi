package com.rick.spring.controller;

import com.rick.spring.entity.HqhOperLog;
import com.rick.spring.service.HqhOperLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 操作日志(HqhOperLog)表控制层
 *
 * @author makejava
 * @since 2022-05-20 16:47:02
 */
@RestController
@RequestMapping("hqhOperLog")
public class HqhOperLogController {
    /**
     * 服务对象
     */
    @Resource
    private HqhOperLogService hqhOperLogService;

    /**
     * 分页查询
     *
     * @param hqhOperLog 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<HqhOperLog>> queryByPage(HqhOperLog hqhOperLog) {
        return ResponseEntity.ok(this.hqhOperLogService.queryByPage(hqhOperLog, PageRequest.of(hqhOperLog.getPage(), hqhOperLog.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HqhOperLog> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hqhOperLogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hqhOperLog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HqhOperLog> add(@RequestBody  HqhOperLog hqhOperLog) {
        return ResponseEntity.ok(this.hqhOperLogService.insert(hqhOperLog));
    }

    /**
     * 编辑数据
     *
     * @param hqhOperLog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HqhOperLog> edit(@RequestBody HqhOperLog hqhOperLog) {
        return ResponseEntity.ok(this.hqhOperLogService.update(hqhOperLog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hqhOperLogService.deleteById(id));
    }

}

