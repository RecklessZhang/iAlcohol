package com.zhl.ialcohol.util;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zhl.ialcohol.entity.BaseEntity;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/8 15:32.
 */
public class MybatisFlexGen {

    public static void main(String[] args) {
        // 配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ialcohol?useInformationSchema=true&characterEncoding=utf-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        GlobalConfig globalConfig = createGlobalConfigUseStyle();

        // 通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        // 生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle() {
        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 包配置
        globalConfig.getPackageConfig()
                // 设置根包
                .setSourceDir(System.getProperty("user.dir") + "/src/main/java")
                .setBasePackage("com.zhl.ialcohol")
                // Entity包名
                .setEntityPackage(globalConfig.getBasePackage() + ".entity")
                // Mapper包名
                .setMapperPackage(globalConfig.getBasePackage() + ".mapper")
                // Service包名
                .setServicePackage(globalConfig.getBasePackage() + ".service")
                // ServiceImpl包名
                .setServiceImplPackage(globalConfig.getBasePackage() + ".service.impl")
                // Controller包名
                .setControllerPackage(globalConfig.getBasePackage() + ".controller")
                // Xml路径
                .setMapperXmlPath(System.getProperty("user.dir") + "/src/main/resources/mapper")
        ;

        // Entity生成配置
        globalConfig.enableEntity()
                // 启用Lombok
                .setWithLombok(true)
                // 是否覆盖之前生成的文件,默认false
                .setOverwriteEnable(true)
                // 父类工厂,对特定的Class设置父类,而非全部的Class,return null表示不需要设置父类
//                .setSuperClassFactory(table -> {
//                    if (table.containsColumn("record_name", "record_time", "update_name", "update_time")) {
//                        return BaseEntity.class;
//                    }
//                    return null;
//                })
                .setSuperClass(BaseEntity.class)
        // 设置项目的JDK版本,项目的JDK为14及以上时建议设置该项,小于14则可以不设置
//                .setJdkVersion(17);
        ;

        // Mapper生成配置
        globalConfig.enableMapper()
                // Mapper类的文件后缀
                .setClassSuffix("Mapper")
                // 是否覆盖之前生成的文件,默认false
                .setOverwriteEnable(true)
        ;

        // Service生成配置
        globalConfig.enableService()
                // Serivce接口的文件前缀
                .setClassPrefix("I")
                // Service类的文件后缀
                .setClassSuffix("Service")
                // 是否覆盖之前生成的文件,默认false
                .setOverwriteEnable(true)
        ;

        // ServiceImpl生成配置
        globalConfig.enableServiceImpl()
                // ServiceImpl类的文件后缀
                .setClassSuffix("ServiceImpl")
                // 是否覆盖之前生成的文件,默认false
                .setOverwriteEnable(true)
        ;

        // Controller生成配置
        globalConfig.disableController()
        // Controller类的文件后缀
//                .setClassSuffix("Controller")
        // 是否覆盖之前生成的文件,默认false
//                .setOverwriteEnable(true)
        ;

        // Xml生成配置
        globalConfig.enableMapperXml()
                // Xml文件后缀
                .setFileSuffix("Mapper")
                // 是否覆盖之前生成的文件,默认false
                .setOverwriteEnable(true)
        ;

        // 策略配置
        globalConfig.getStrategyConfig()
                // 表前缀
                .setTablePrefix("t_")
                // 逻辑删除默认字段名称
                .setLogicDeleteColumn("is_delete")
                // 需要忽略的列/父类定义的列
                .setIgnoreColumns("record_name", "record_time", "update_name", "update_time")
                // 生成的表名,未配置时生成所有表
                .setGenerateTable("t_tag");
        ;

        return globalConfig;
    }


}
