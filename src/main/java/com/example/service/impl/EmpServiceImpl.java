package com.example.service.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpExpr;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult(p.getTotal(),p.getResult());
    }

    @Transactional//事务管理 spring封装了
    @Override
    public void save(Emp emp) {
        //补全信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //保存基本信息
        empMapper.insert(emp);

        //批量保存员工工作经历
        Integer empId = emp.getId();//Mapper部分用Mybatis获取了主键，赋值给empId
        List<EmpExpr> exprlist = emp.getExprList();//获取员工工作经历集合
        if(!CollectionUtils.isEmpty(exprlist)){//如果不为空或者null
            exprlist.forEach(empExpr -> empExpr.setEmpId(empId));//则遍历每一个员工经历，并将员工id设置为主键
            //这样emp表（主键id）就可以和empExpr表（主键id，外键empid）关联起来
            empExprMapper.insertBatch(exprlist);//批量加入
        }


    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void updateInfo(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateInfo(emp);
    }

}
