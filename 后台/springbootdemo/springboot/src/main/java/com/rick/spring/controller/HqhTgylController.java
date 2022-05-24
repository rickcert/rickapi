package com.rick.spring.controller;

import com.rick.spring.entity.HqhTgyl;
import com.rick.spring.service.HqhTgylService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (HqhTgyl)表控制层
 *
 * @author makejava
 * @since 2022-05-18 15:03:48
 */
@RestController
@RequestMapping("hqhTgyl")
public class HqhTgylController {
    /**
     * 服务对象
     */
    @Resource
    private HqhTgylService hqhTgylService;

    /**
     * 分页查询
     *
     * @param hqhTgyl 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<HqhTgyl>> queryByPage(HqhTgyl hqhTgyl) {
        return ResponseEntity.ok(this.hqhTgylService.queryByPage(hqhTgyl, PageRequest.of(hqhTgyl.getPage(),hqhTgyl.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HqhTgyl> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hqhTgylService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hqhTgyl 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HqhTgyl> add(@RequestBody HqhTgyl hqhTgyl) {
        return ResponseEntity.ok(this.hqhTgylService.insert(hqhTgyl));
    }

    /**
     * 编辑数据
     *
     * @param hqhTgyl 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HqhTgyl> edit(@RequestBody HqhTgyl hqhTgyl) {
        return ResponseEntity.ok(this.hqhTgylService.update(hqhTgyl));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.hqhTgylService.deleteById(id));
    }

}

