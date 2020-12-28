package com.cpg.Springwebdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.cpg*" })
@EntityScan("com.cpg*")
@EnableJpaRepositories("com.cpg*")
@EnableJpaAuditing
public class SpringWebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebDemoApplication.class, args);
	}

}
