package cn.yunlingfly.springbootapolloclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 如果都是使用默认的application命名空间可以直接在启动类使用下面的注解
//@EnableApolloConfig
public class SpringbootApolloClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApolloClientApplication.class, args);
	}
}
