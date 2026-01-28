package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    public List<Emp> list(EmpQueryParam empQueryParam);//当 MyBatis 只有一个参数对象时，会自动把对象字段当作参数名传入 XML
    @Options(useGeneratedKeys = true, keyProperty = "id")//useGeneratedKeys=true表示Mybatis主动获取数据库生成的主键，并将主键赋给xx --> keyProperty=“xx”
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
@Select("select * from emp where id = #{id}")
    Emp getById(Integer id);


    void updateInfo(Emp emp);
}
