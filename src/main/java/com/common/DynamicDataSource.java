package com.common;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

	private static Map<Object, Object> targetDataSources = new HashMap<>();

	@Override
	protected Object determineCurrentLookupKey() {
		System.err.println(DynamicDataSourceContextHolder.getDataSourceKey());
		return DynamicDataSourceContextHolder.getDataSourceKey();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		DynamicDataSource.targetDataSources = targetDataSources;
	}



	/**
	 * 是否存在当前key的 DataSource
	 *
	 * @param key
	 * @return 存在返回 true, 不存在返回 false
	 */
	public static boolean isExistDataSource(String key) {
		return targetDataSources.containsKey(key);
	}

	/**
	 * 动态增加数据源
	 *
	 * @return
	 */
	public synchronized boolean addDataSource(BasicDataSource basicDataSource ) {
		try {
			Connection connection = null;
			// 排除连接不上的错误
			try {

				Class.forName(basicDataSource.getDriverClassName());
				connection = DriverManager.getConnection(basicDataSource.getUrl(),
						basicDataSource.getUsername(),
						basicDataSource.getPassword());
				System.out.println("测试完成关闭连接"+connection.isClosed());
			} catch (Exception e) {
				return false;
			} finally {
				if (connection != null && !connection.isClosed())
					connection.close();
			}
			String database = basicDataSource.getUrl();//获取要添加的数据库名
			if (database == null || "".equals(database)) return false;
			if (DynamicDataSource.isExistDataSource(database)) return true;

			Map<Object, Object> targetMap = DynamicDataSource.targetDataSources;
			targetMap.put(database, basicDataSource);
			// 当前 targetDataSources 与 父类 targetDataSources 为同一对象 所以不需要set
//			this.setTargetDataSources(targetMap);
			this.afterPropertiesSet();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}


}
