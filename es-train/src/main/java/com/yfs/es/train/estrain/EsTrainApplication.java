package com.yfs.es.train.estrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EsTrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsTrainApplication.class, args);
	}

}
