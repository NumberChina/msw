package com.saic.msw.conf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author: zzz
 * @date 2021/7/21 21:22
 * @description：
 * @version: 1.0
 */
@Slf4j
public class CodeGenerator {
    public static String[] TABLES = new String[]{"t_demo"};
    public static String[] TABLES_PREFIX = new String[]{"t_"};

    public static String API_PATH = "/msw-api/src/main/java/com/saic/msw/api/";
    public static String COMMON_PATH = "/msw-common";
    public static String DAO_PATH = "/msw-dao/src/main/java/com/saic/msw/mapper/";
    public static String DAO_PATH_XML = "/msw-dao/src/main/resources/com/saic/msw/mapper/";
    public static String MODEL_PATH = "/msw-model/src/main/java/com/saic/msw/model/";
    public static String SERVICE_PATH = "/msw-service/src/main/java/com/saic/msw/service/";
    public static String WEB_PATH = "/msw-web/src/main/java/com/saic/msw/controller/";

    public static void main(String[] args) throws InterruptedException {
        //项目根目录  不可修改
        String projectPath = System.getProperty("user.dir");
        log.info("项目根路径"+projectPath);
        //用来获取Mybatis-Plus.properties文件的配置信息
        ResourceBundle rb = ResourceBundle.getBundle("genreator");

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("/GeneratorTemp");
        gc.setFileOverride(false);
        // 开启 activeRecord 模式
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap 映射图
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);
        gc.setAuthor(rb.getString("author"));
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        //注意Mysql 驱动版本问题 我这为8版本
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(rb.getString("jdbc.username"));
        dsc.setPassword(rb.getString("jdbc.password"));
        dsc.setUrl(rb.getString("jdbc.url"));
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        /** 此处可以修改为您的表前缀，如果没有，注释掉即可*/
        strategy.setTablePrefix(TABLES_PREFIX);
        /** 表名生成策略*/
        strategy.setNaming(NamingStrategy.underline_to_camel);
        /** 需要生成的表*/
        strategy.setInclude(TABLES);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(rb.getString("parent"));
        pc.setController("controller");
        pc.setService("api");
        pc.setServiceImpl("service");
        pc.setEntity("model");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            //如果是Entity则直接返回true表示写文件
            if (fileType == FileType.ENTITY) {
                return true;
            }
            //否则先判断文件是否存在
            File file = new File(filePath);
            boolean exist = file.exists();
            if (!exist) {
                file.getParentFile().mkdirs();
            }
            //文件不存在或者全局配置的fileOverride为true才写文件
            return !exist || configBuilder.getGlobalConfig().isFileOverride();
        });


        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        //调整 controller java 生成目录
        focList.add(new FileOutConfig("/templates/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath+ WEB_PATH + tableInfo.getEntityName() + "Controller.java";
            }
        });

        //调整 entity 生成目录
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath+ MODEL_PATH + tableInfo.getEntityName() + ".java";
            }
        });

        // 调整 service-interface 生成目录演示
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath+ API_PATH + "I" +tableInfo.getEntityName() + "Service.java";
            }
        });

        // 调整 serviceImpl 生成目录演示
        focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath+ SERVICE_PATH + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });


        //调整 mapper java 生成目录
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath+ DAO_PATH + tableInfo.getEntityName() + "Mapper.java";
            }
        });

        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + DAO_PATH_XML/* + tableInfo.getEntityName()
                        + "/" */+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });


        // 调整 query 生成目录
/*        focList.add(new FileOutConfig("/templates/query.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDomainDir")+ "/cn/leilei/query/" + tableInfo.getEntityName() + "Query.java";
            }
        });*/



        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        //tc.setController("/templates/controller.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        tc.setEntity(null);
        tc.setXml(null);
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }
}
