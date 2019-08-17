package com.multipledatasources;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TeacherMapper {
	
	List<TeacherEntity> getAll();

	TeacherEntity getOne(Long id);

	void insert(TeacherEntity teacher);

	void update(TeacherEntity teacher);

	void delete(Long id);

}