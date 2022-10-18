package io.geekidea.springboot.assembly.demo.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class FreeMarkerTemplateUtil {

    public String getEmailHtml(Map map, String templateName) {

        String htmlText = "";
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        try {
            //加载模板路径
            configuration.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(),"ftl");
            //获取对应名称的模板
            Template template = configuration.getTemplate(templateName);
            //渲染模板为html
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return htmlText;
    }

    /**
     * 输出到控制台
     */
    public void print(String name, Map<String, Object> root) throws TemplateException, IOException {
        //通过Template可以将模板文件输出到相应的流
        Template template = this.getTemplate(name);
        template.process(root, new PrintWriter(System.out));
    }

    /**
     * 获取模板信息
     *
     * @param name 模板名
     * @return
     */
    public Template getTemplate(String name) {
        //通过freemarkerd Configuration读取相应的ftl
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        //设定去哪里读取相应的ftl模板文件，指定模板路径
        cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "ftl");
        try {
            //在模板文件目录中找到名称为name的文件
            Template template = cfg.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}