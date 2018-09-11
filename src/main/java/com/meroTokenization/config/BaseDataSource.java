package com.meroTokenization.config;

@Named
public class BaseDataSource {
	public BasicDataASource getBasicDataASource(String driverClassName, String jdbcUrl, String userId, String password) {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driverClassName);
		basicDataSource.setUrl(jdbcUrl);
		basicDataSource.setUserName(userId);
		basicDataSource.setPassword(password);
		return basicDataSource;
	}
}
