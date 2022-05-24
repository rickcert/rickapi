package com.rick.spring.controller;

import com.rick.spring.annotation.Log;
import com.rick.spring.entity.HqhYyyl;
import com.rick.spring.service.HqhYyylService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (HqhYyyl)表控制层
 *
 * @author makejava
 * @since 2022-05-18 15:03:49
 */
@RestController
@RequestMapping("hqhYyyl")
public class HqhYyylController {
    /**
     * 服务对象
     */
    @Resource
    private HqhYyylService hqhYyylService;

    /**
     * 分页查询
     *
     * @param hqhYyyl 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @Log(title="查询一言", businessType="用户操作")
    public ResponseEntity<Page<HqhYyyl>> queryByPage(HqhYyyl hqhYyyl) {
        return ResponseEntity.ok(this.hqhYyylService.queryByPage(hqhYyyl, PageRequest.of(hqhYyyl.getPage(), hqhYyyl.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HqhYyyl> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hqhYyylService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hqhYyyl 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HqhYyyl> add(@RequestBody  HqhYyyl hqhYyyl) {
        return ResponseEntity.ok(this.hqhYyylService.insert(hqhYyyl));
    }

    /**
     * 编辑数据
     *
     * @param hqhYyyl 实体
     * @return 编辑结果
     */
    @PutMapping
    @Log(title="更新了一言", businessType="更新操作")
    public ResponseEntity<HqhYyyl> edit(@RequestBody  HqhYyyl hqhYyyl) {
        return ResponseEntity.ok(this.hqhYyylService.update(hqhYyyl));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.hqhYyylService.deleteById(id));
    }

}

