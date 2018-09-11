package com.meroTokenization.config;

@Configuration
@PropertySource({"classpath:/sqlQUeries","classpath:/application.properties"})
@Import(SecretConfig.class)

public class CommonConfig {

	@Value("${"BAGE_DRIVER_CLASS"}) 
	private String bage	DriverClassName;
	
	@Value("${"BAGE_JDBC_URL"}) 
	private String bageJdbUrl;		


	@Value("${"BAGE_USERNAME"}) 
	private String bageDBUserId;
	
	
	@Value("${"BAGE_PASSWORD"}) 
	private String bageDBPassword;ß		

	@Value("{turingClient.trustStorePassword.path:changeit}")
	private String duringClientpassword;
	
	
	@Value("{turing.oauth.clientId.path}")
	private String turingClientId;
	
	
	@Value("{turing.oauth.clientSecrtet.path}")
	private String turingClientSecret;
	
	@Value("{turing3.api.npi.url}")
	private String turingAPIURL;
	
	
	@Value("{turing3.api.npi.scope}")
	private String turingAPIScope;
	
	
	@Value("{turing3.api.version}")
	private String turingStorePath;
	
	
	@Inject
	private BaseDataSource baseDataSource;
	
	
	@PostConstruct
	public void init() {
		setProperty(DEFAULT_CORE_THREAD_POOL_SIZE, String.valueOf(3));
		setProperty(DEFAULT_TIMEOUT_MILLIS, "30000");

	}
	
	@Bean
	public TuringClientConfig turingClientConfig() {
		TuringClientConfig  config = new  TuringClientConfig();
		config.setNpiOauthClientId("enterpriseapi-1tryuweqtrygygg");
		config.setNpiOauthClientSecret("7642856786378547865786");
		config.setTrustStorePassword("hbsjadfgjkhsjah");
		config.setTrustorepath("Library/Java/JavaVirtualMachines/jdk1.8.0.181.jdk/COntents/Home/jre/Lib/security/cacerts");
		config.setNpiUrl("https://api-it.mlouds.mertotokenization.com.1333/");
		config.setNpiScope("tokenize:BAN");
		config.setApiVersion("3.1");
		return config;
	
		}
	
	@Bean
	@DependsOn("turingClientConfig")
	public TuringServiceImpl getTuringService(TuringCLientConfig config) {
		return new TuringServiceImpl(config);
	}
	
	@Bean
	Public BaseDataSource getbaseDataSource() {
		return new BaseDataSource();
	}
	
//	Comment the following wile deploying in the cloud
	
	@Bean(name= "sageDataSOurce", derstroytMethod = "close")
	public BasicDataSource getSageDataSOurce() {
		return baseDataSource.getBasicDataSource(sageDriverClassName,sageJdbcUrl,sageDBUserId,sageDBPassword);
	}
	
	@Bean(name = "sageJdbcTemplate")
	public JdbcTemplate getSageJdbcTemplate() {
		return new JdbcTemplate(getSageDataSource());
	}
	
	
}




