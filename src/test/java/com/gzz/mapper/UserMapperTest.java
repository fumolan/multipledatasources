package com.gzz.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gzz.sys.user.UserEntity;
import com.gzz.sys.user.UserMapper;
import com.gzz.sys.user.UserSexEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper UserMapper;

	@Test
	public void testInsert() throws Exception {
		UserMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
		UserMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
		UserMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));
		System.out.println(UserMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<UserEntity> users = UserMapper.getAll();
		if (users == null || users.size() == 0) {
			System.out.println("is null");
		} else {
			System.out.println(users.toString());
		}
	}

	@Test
	public void testUpdate() throws Exception {
		UserEntity user = UserMapper.getOne(1L);
		System.out.println(user.toString());
		user.setNickName("gzz");
		UserMapper.update(user);
		System.out.println(UserMapper.getOne(1L).getNickName());
	}


	@Test
	public void testdeleted() throws Exception {
		List<UserEntity> userss = UserMapper.getAll();
		if(userss != null && userss.size()>0){
			UserEntity user = userss.get(0);
			System.out.println(user.toString());
			user.setNickName("gzz");
			System.out.println(user.getNickName());
			UserMapper.delete(user.getId());
		}else{
			System.out.println("没有可操纵的数据");
		}


	}

}