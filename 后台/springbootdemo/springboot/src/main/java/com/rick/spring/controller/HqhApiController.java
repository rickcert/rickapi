package com.rick.spring.controller;

import com.rick.spring.annotation.Log;
import com.rick.spring.entity.HqhApi;
import com.rick.spring.service.HqhApiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (HqhApi)表控制层
 *
 * @author makejava
 * @since 2022-05-18 19:20:26
 */
@RestController
@RequestMapping("hqhApi")
public class HqhApiController {
    /**
     * 服务对象
     */
    @Resource
    private HqhApiService hqhApiService;

    /**
     * 分页查询
     *
     * @param hqhApi 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @Log(title="查询ap列表", businessType="用户操作")
    public ResponseEntity<Page<HqhApi>> queryByPage(HqhApi hqhApi) {
        return ResponseEntity.ok(this.hqhApiService.queryByPage(hqhApi, PageRequest.of(hqhApi.getPage(), hqhApi.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HqhApi> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hqhApiService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hqhApi 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HqhApi> add(@RequestBody  HqhApi hqhApi) {
        return ResponseEntity.ok(this.hqhApiService.insert(hqhApi));
    }

    /**
     * 编辑数据
     *
     * @param hqhApi 实体
     * @return 编辑结果
     */
    @PutMapping
    @Log(title="更新了api", businessType="更新操作")
    public ResponseEntity<HqhApi> edit(@RequestBody  HqhApi hqhApi) {
        return ResponseEntity.ok(this.hqhApiService.update(hqhApi));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.hqhApiService.deleteById(id));
    }

}

