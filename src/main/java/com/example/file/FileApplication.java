package com.example.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);

        String getVersion = org.springframework.core.SpringVersion.getVersion();
        System.out.println("SpringVersion =============> " + getVersion);
    }
}
