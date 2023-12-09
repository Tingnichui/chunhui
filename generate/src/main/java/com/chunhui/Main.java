package com.chunhui;

import com.chunhui.pojo.TableInfo;
import com.chunhui.pojo.TemplateConfig;
import com.chunhui.util.DbUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final List<String> tableNames = Arrays.asList("res_info");

    /**
     * 忽略表字段
     */
    public static final List<String> ignoreFields = Arrays.asList("id", "create_by", "create_time", "update_by", "update_time", "del_flag");
    public static final List<TemplateConfig> templateList = new ArrayList<>();

    static {
        // po 数据库实体类
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("po.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\pojo\\po")
                .setFilePrefix("")
                .setFileSuffix(".java"));

        // query 查询vo
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("query.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\pojo\\query")
                .setFilePrefix("Query")
                .setFileSuffix(".java"));

        // savevo 保存vo
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("savevo.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\pojo\\vo")
                .setFilePrefix("SaveVO")
                .setFileSuffix(".java"));

        // updatevo 更新vo
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("updatevo.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\pojo\\vo")
                .setFilePrefix("UpdateVO")
                .setFileSuffix(".java"));

        // outvo 接口vo
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("outvo.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\pojo\\vo")
                .setFilePrefix("OutVO")
                .setFileSuffix(".java"));

        // mapper
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("mapper.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\mapper")
                .setFilePrefix("Mapper")
                .setFileSuffix(".java"));

        // dao
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("dao.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\dao")
                .setFilePrefix("Dao")
                .setFileSuffix(".java"));


        // service
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("service.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\service")
                .setFilePrefix("Service")
                .setFileSuffix(".java"));

        // service
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("controller.ftl")
                .setGenateDir("D:\\project\\chunhui\\web-api\\src\\main\\java\\com\\chunhui\\web\\controller")
                .setFilePrefix("Controller")
                .setFileSuffix(".java"));

        // vue-list.ftl
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("vue-list.ftl")
                .setGenateDir("D:\\project\\chunhui\\admin-web\\src\\pages\\admin")
                .setFatherDir("tableName")
                .setFilePrefix("list")
                .setFileSuffix(".vue"));

        // vue-api
        templateList.add(new TemplateConfig()
                .setCoverFlag(true)
                .setName("vue-api.ftl")
                .setGenateDir("D:\\project\\chunhui\\admin-web\\src\\api")
                .setFileSuffix(".js"));

    }

    public static void main(String[] args) throws Exception {
        // 遍历生成代码
        for (String tableName : tableNames) {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName(tableName);
            tableInfo.setTableHump(DbUtil.lineToHump(tableName));
            tableInfo.setTableKebab(tableName.replaceAll("_", "-"));
            tableInfo.setTableBigHump(DbUtil.lineToBigHump(tableName));
            tableInfo.setTableComment(DbUtil.getTableComment(tableName));
            tableInfo.setFields(DbUtil.getColumnByTableName(tableName, ignoreFields));

            System.err.println(tableInfo);

            for (TemplateConfig templateConfig : templateList) {
                doGenerate(tableInfo, templateConfig);
            }

            String str = "    List<SysRoleOutVO> toSysRoleOutList(List<SysRole> records);\n" +
                    "\n" +
                    "    SysRoleOutVO toSysRoleListOut(SysRole byId);\n" +
                    "\n" +
                    "    SysRole toSysRole(SysRoleSaveVO saveVO);\n" +
                    "\n" +
                    "    SysRole updatetoSysRole(SysRoleUpdateVO saveVO);\n";

            System.err.println(str.replaceAll("SysRole", tableInfo.getTableBigHump()));

        }
    }

    private static void doGenerate(TableInfo tableInfo, TemplateConfig config) throws Exception {
        String baseDir = config.getGenateDir().endsWith("\\") ? config.getGenateDir() : config.getGenateDir() + "\\";
        if (StringUtils.isNotBlank(config.getFatherDir())) {
            baseDir = baseDir + tableInfo.getTableName().replaceAll("_", "-");
            if (!baseDir.endsWith("\\")) {
                baseDir = baseDir + "\\";
            }
            File file = new File(baseDir);
            if (!file.exists()) {
                file.mkdir();
            }
        }

        String filePath = null;
        if (config.getFileSuffix().contains("java")) {
            filePath = baseDir + tableInfo.getTableBigHump() + config.getFilePrefix() + config.getFileSuffix();
        }
        if (config.getFileSuffix().contains("vue")) {
            filePath = baseDir + config.getFilePrefix().replaceAll("_", "-") + config.getFileSuffix();
        }
        if (config.getFileSuffix().contains("js")) {
            filePath = baseDir + tableInfo.getTableName().replaceAll("_", "-") + config.getFileSuffix();
        }

        if (StringUtils.isBlank(filePath)) {
            return;
        }

        if (new File(filePath).exists() && !config.getCoverFlag()) {
            return;
        }


        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        configuration.setDefaultEncoding("utf-8");


        Template template = configuration.getTemplate(config.getName());
        Writer out = new FileWriter(filePath);
        //调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
        template.process(tableInfo, out);
        //关闭writer对象。
        out.flush();
        out.close();

    }

}