package com.rick.spring.controller;

import com.rick.spring.annotation.Log;
import com.rick.spring.entity.HqhDmtp;
import com.rick.spring.service.HqhDmtpService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (HqhDmtp)表控制层
 *
 * @author makejava
 * @since 2022-05-18 15:03:45
 */
@RestController
@RequestMapping("hqhDmtp")
public class HqhDmtpController {
    /**
     * 服务对象
     */
    @Resource
    private HqhDmtpService hqhDmtpService;

    /**
     * 分页查询
     *
     * @param hqhDmtp 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @Log(title="查询动漫壁纸", businessType="用户操作")
    public ResponseEntity<Page<HqhDmtp>> queryByPage(HqhDmtp hqhDmtp) {
        return ResponseEntity.ok(this.hqhDmtpService.queryByPage(hqhDmtp, PageRequest.of(hqhDmtp.getPage(),hqhDmtp.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HqhDmtp> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hqhDmtpService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hqhDmtp 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HqhDmtp> add(@RequestBody HqhDmtp hqhDmtp) {
        return ResponseEntity.ok(this.hqhDmtpService.insert(hqhDmtp));
    }

    /**
     * 编辑数据
     *
     * @param hqhDmtp 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HqhDmtp> edit(@RequestBody HqhDmtp hqhDmtp) {
        return ResponseEntity.ok(this.hqhDmtpService.update(hqhDmtp));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.hqhDmtpService.deleteById(id));
    }

}

