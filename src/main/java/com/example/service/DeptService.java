package com.example.service;

import com.example.pojo.Dept;
import com.example.pojo.Result;

import java.util.List;

public interface DeptService {
     List<Dept> findAll();

     void deleteById(Integer id);

    void addDept(Dept dept);

    Dept getInfo(Integer id);

    void updateInfo(Dept dept);
}
