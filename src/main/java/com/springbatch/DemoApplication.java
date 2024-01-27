package com.springbatch;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("firstJob")
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("This is a Test");
	}

	/**
	 * Trigger the job using CommandLineRunner interface provided by Spring Boot that allows you to execute code after the application context is loaded and before the application starts. It has a single method, run, which is called with the command-line arguments that were passed to the application.
	 * */

	@Bean
	@Primary
	public CommandLineRunner demoJobRunner() {
		return args -> {
			//jobLauncher.run(job, new JobParameters());
			JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters();
			jobLauncher.run(job, jobParameters);
		};
	}
}

