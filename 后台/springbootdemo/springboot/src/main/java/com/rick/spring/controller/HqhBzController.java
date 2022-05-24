package com.rick.spring.controller;

import com.rick.spring.annotation.Log;
import com.rick.spring.entity.HqhBz;
import com.rick.spring.service.HqhBzService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (HqhBz)表控制层
 *
 * @author makejava
 * @since 2022-05-18 15:03:43
 */
@RestController
@RequestMapping("hqhBz")
public class HqhBzController {
    /**
     * 服务对象
     */
    @Resource
    private HqhBzService hqhBzService;

    /**
     * 分页查询
     *
     * @param hqhBz 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @Log(title="查询壁纸", businessType="用户操作")
    public ResponseEntity<Page<HqhBz>> queryByPage(HqhBz hqhBz) {
        return ResponseEntity.ok(this.hqhBzService.queryByPage(hqhBz, PageRequest.of(hqhBz.getPage(), hqhBz.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HqhBz> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hqhBzService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hqhBz 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HqhBz> add(@RequestBody HqhBz hqhBz) {
        return ResponseEntity.ok(this.hqhBzService.insert(hqhBz));
    }

    /**
     * 编辑数据
     *
     * @param hqhBz 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HqhBz> edit(@RequestBody HqhBz hqhBz) {
        return ResponseEntity.ok(this.hqhBzService.update(hqhBz));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.hqhBzService.deleteById(id));
    }

}

