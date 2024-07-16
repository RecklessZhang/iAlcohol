package com.zhl.ialcohol;

import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.ConsoleMessageCollector;
import com.mybatisflex.core.audit.MessageCollector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@MapperScan("com.zhl.ialcohol.mapper")
@ComponentScan(basePackages = {"com.zhl.ialcohol"})
@SpringBootApplication
public class IAlcoholApplication {

    public static void main(String[] args) {
        SpringApplication.run(IAlcoholApplication.class, args);
    }

    @PostConstruct
    public void enableMybatisFlexLog() {
        // 1.开启审计功能
        AuditManager.setAuditEnable(true);
        // 2.设置 SQL 审计收集器
        MessageCollector collector = new ConsoleMessageCollector();
        AuditManager.setMessageCollector(collector);
    }

}
