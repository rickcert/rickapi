package com.rick.spring.controller;

import com.rick.spring.annotation.Log;
import com.rick.spring.entity.HqhPixiv;
import com.rick.spring.service.HqhPixivService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (HqhPixiv)表控制层
 *
 * @author makejava
 * @since 2022-05-18 15:03:47
 */
@RestController
@RequestMapping("hqhPixiv")
public class HqhPixivController {
    /**
     * 服务对象
     */
    @Resource
    private HqhPixivService hqhPixivService;

    /**
     * 分页查询
     *
     * @param hqhPixiv 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @Log(title="查询pixiv", businessType="用户操作")
    public ResponseEntity<Page<HqhPixiv>> queryByPage(HqhPixiv hqhPixiv) {
        return ResponseEntity.ok(this.hqhPixivService.queryByPage(hqhPixiv, PageRequest.of(hqhPixiv.getPage(),hqhPixiv.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HqhPixiv> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hqhPixivService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hqhPixiv 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HqhPixiv> add(@RequestBody HqhPixiv hqhPixiv) {
        return ResponseEntity.ok(this.hqhPixivService.insert(hqhPixiv));
    }

    /**
     * 编辑数据
     *
     * @param hqhPixiv 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HqhPixiv> edit(@RequestBody  HqhPixiv hqhPixiv) {
        return ResponseEntity.ok(this.hqhPixivService.update(hqhPixiv));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.hqhPixivService.deleteById(id));
    }

}

