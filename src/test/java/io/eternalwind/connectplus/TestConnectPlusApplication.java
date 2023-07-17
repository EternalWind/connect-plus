package io.eternalwind.connectplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestConnectPlusApplication {

	public static void main(String[] args) {
		SpringApplication.from(ConnectPlusApplication::main).with(TestConnectPlusApplication.class).run(args);
	}

}
