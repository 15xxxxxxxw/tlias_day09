package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result findAll() {
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result deleteById(Integer id) {
        log.info("根据id删除部门, id=" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        log.info("添加新的部门");
        deptService.addDept(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("点击获得id，id关联显示name");
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result updateInfo(@RequestBody Dept dept){
        log.info("修改信息ℹ");
        deptService.updateInfo(dept);
        return Result.success();
    }
}
