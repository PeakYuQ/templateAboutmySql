package com.peakyu.application.mapper;

import com.peakyu.application.dao.note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface noteMapper {


    //分页查所有公告
    @Select("select * From note order by id limit #{start},#{pageSize}")
    List<note> selectAllNote(Integer start, Integer pageSize);

    //根据id查公告
    @Select("select * From note where id = #{id}")
    note selectNoteById(Integer id);
}
