package edu.icet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"edu.icet.entity.event", "edu.icet.entity.supplier","edu.icet.entity.customer","edu.icet.entity.admin","edu.icet.entity.system"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
