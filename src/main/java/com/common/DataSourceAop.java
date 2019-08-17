package com.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DataSourceAop {
	@Before("execution(* com.gzz.sys.user.UserMapper.*(..))")
	public void setDataSource2test01() {
		System.err.println("啥也不干");
	}

	@Before("execution(* com.multipledatasources.TeacherMapper.*(..))")
	public void setDataSource2test02(JoinPoint point) {
//		Object[] params = point.getArgs();
//		String param = (String) params[0];
//		if(DynamicDataSource.isExistDataSource(param) && !param.equals(DynamicDataSourceContextHolder.getDataSourceKey()) ) {
//			DynamicDataSourceContextHolder.setDataSourceKey(param);
//		}else{
//
//		}
		//DynamicDataSourceContextHolder.setDataSourceKey("jdbc:mysql://192.168.80.117:3306/chenbin2?useUnicode=true&characterEncoding=utf-8");
		System.err.println("切面数据源"+DynamicDataSourceContextHolder.getDataSourceKey()+" in database ="+   point.getSignature());

	}
}
