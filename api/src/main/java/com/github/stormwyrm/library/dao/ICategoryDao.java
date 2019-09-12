package com.github.stormwyrm.library.dao;

import com.github.stormwyrm.library.model.Category;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("catecoryDao")
public interface ICatecoryDao extends Mapper<Category> {

    @Select("select * from category where id = #{id}")
    @Results({
            @Result(
                    column = "id",
                    property = "id",
                    id = true
            ),
            @Result(
                    column = "name",
                    property = "name"
                    ),
            @Result(
                    column = "books",
                    property = "id",
                    javaType = List.class,
                    many = @Many(select = "com.github.stormwyrm.library.dao.ILibraryDao.findBookByCatecory")
            )
    })
    Category selectWithStudentInfo(int id);
}
