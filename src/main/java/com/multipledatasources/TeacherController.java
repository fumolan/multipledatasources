package com.multipledatasources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	@RequestMapping("/getUsers")
	public List<TeacherEntity> getUsers() {
		List<TeacherEntity> users=teacherMapper.getAll();
		return users;
	}
	
    @RequestMapping("/getUser")
    public TeacherEntity getUser(Long id) {
        TeacherEntity user=teacherMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(TeacherEntity user) {
        teacherMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(TeacherEntity user) {
        teacherMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        teacherMapper.delete(id);
    }
    
    
}