package io.geekidea.springboot.assembly.demo.Test;

import freemarker.template.TemplateException;
import io.geekidea.springboot.assembly.demo.utils.FreeMarkerTemplateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 参考链接：  https://blog.csdn.net/weixin_40686603/article/details/105528874
 */
@Slf4j
public class Test46_freemarker_邮件 {
    public static void main(String[] args) throws IOException, TemplateException {
// 创建模板数据
        Map<String, Object> templateData = new HashMap<>(16);
        templateData.put("taskName", "任务名称001");
        templateData.put("name", "张三");
//        String template = FreeMarkerTemplateUtil.getTemplate(templateData);
//        System.out.println(template);
    }
}


