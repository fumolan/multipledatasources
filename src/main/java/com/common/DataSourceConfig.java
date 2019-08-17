package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.multipledatasources.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {

	@Primary
	@Bean(name = "datasource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource getDateSource1() {
		//return DataSourceBuilder.create().build();
			BasicDataSource r = new BasicDataSource();
			r.setDriverClassName("com.mysql.jdbc.Driver");
			r.setUrl("jdbc:mysql://192.168.80.117:3306/chenbin1?useUnicode=true&characterEncoding=utf-8");
			r.setUsername("kaifa");
			r.setPassword("fuzhaohui");
			return r;

	}

	@Bean(name = "dynanmicDataSource")
	public DataSource getDynanmicDataSource() {
		    DynamicDataSource dynamicRoutingDataSource = new DynamicDataSource();
			Map<Object, Object> dataSourceMap = new HashMap<>();
			dataSourceMap.put("jdbc:mysql://192.168.80.117:3306/chenbin1?useUnicode=true&characterEncoding=utf-8", getDateSource1());
			dynamicRoutingDataSource.setDefaultTargetDataSource(getDateSource1());// 设置默认数据源
			dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
			return dynamicRoutingDataSource;
	}



	@Bean(name = "SqlSessionFactory")
	public SqlSessionFactory test1SqlSessionFactory()
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		System.err.println("SqlSessionFactory使用的数据源为"+DynamicDataSourceContextHolder.getDataSourceKey());
        bean.setDataSource(getDynanmicDataSource());
		Resource[]  resources = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/muitiple/*.xml");
		Resource[]  resources2 = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml");
		//数组转化为集合
		List<Resource> resourceList = new ArrayList<Resource>();
		for(int i=0 ;i< resources.length ;i++){
			resourceList.add(resources[i]);
		}

		for(int i=0 ;i< resources2.length ;i++){
			resourceList.add(resources2[i]);
		}
		Resource[] resourcesArray = new Resource[resourceList.size()];
		for(int i=0 ;i< resourceList.size() ;i++){
			resourcesArray[i] = resourceList.get(i);
		}
		bean.setMapperLocations(resourcesArray);
		return bean.getObject();
	}

}
