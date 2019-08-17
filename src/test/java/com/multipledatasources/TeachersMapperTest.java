package com.multipledatasources;

import com.common.DynamicDataSource;
import com.common.DynamicDataSourceContextHolder;
import com.gzz.sys.user.UserSexEnum;
import com.multipledatasources.TeacherEntity;
import com.multipledatasources.TeacherMapper;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeachersMapperTest {

	@Autowired
	private TeacherMapper teacherMapper;

	@Autowired
	private DynamicDataSource dynamicDataSource;



	@Test
	public void testInsert() throws Exception {
		String url = "jdbc:mysql://192.168.80.117:3306/chenbin1?useUnicode=true&characterEncoding=utf-8";

		BasicDataSource r = new BasicDataSource();
		r.setDriverClassName("com.mysql.jdbc.Driver");
		r.setUrl(url);
		r.setUsername("kaifa");
		r.setPassword("fuzhaohui");
		dynamicDataSource.addDataSource(r);
		DynamicDataSourceContextHolder.setDataSourceKey(url);
		teacherMapper.insert(new TeacherEntity("aa", "a123456", UserSexEnum.MAN,url));
		teacherMapper.insert(new TeacherEntity("bb", "b123456", UserSexEnum.WOMAN,url));
		teacherMapper.insert(new TeacherEntity("cc", "b123456", UserSexEnum.WOMAN,url));
		System.out.println(teacherMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		String url = "jdbc:mysql://192.168.80.117:3306/chenbin2?useUnicode=true&characterEncoding=utf-8";

		BasicDataSource r = new BasicDataSource();
		r.setDriverClassName("com.mysql.jdbc.Driver");
		r.setUrl(url);
		r.setUsername("kaifa");
		r.setPassword("fuzhaohui");
		dynamicDataSource.addDataSource(r);
		DynamicDataSourceContextHolder.setDataSourceKey(url);

		List<TeacherEntity> users = teacherMapper.getAll();
		if (users == null || users.size() == 0) {
			System.out.println("is null");
		} else {
			System.out.println(users.toString());
		}
	}

	@Test
	public void testUpdate() throws Exception {
		TeacherEntity user = teacherMapper.getOne(1L);
		System.out.println(user.toString());
		user.setNickName("gzz");
		teacherMapper.update(user);
		System.out.println(teacherMapper.getOne(1L).getNickName());
	}


	@Test
	public void testdeleted() throws Exception {
		List<TeacherEntity> userss = teacherMapper.getAll();
		if(userss != null && userss.size()>0){
			TeacherEntity user = userss.get(0);
			System.out.println(user.toString());
			user.setNickName("gzz");
			System.out.println(user.getNickName());
			teacherMapper.delete(user.getId());
		}else{
			System.out.println("没有可操纵的数据");
		}


	}

}