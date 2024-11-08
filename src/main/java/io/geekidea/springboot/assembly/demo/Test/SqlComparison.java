package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.geekidea.springboot.assembly.demo.model.DimensionOrMeasure;
import io.geekidea.springboot.assembly.demo.model.KeyVal;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Pattern;

public class SqlComparison {

    public static void main(String[] args) {
        String sql1 = "\n\n\n\n \n\n \n \n\n \n \n\n SELECT\n tb_0_detail.`sale_ord_id` AS primary_key\n , MAX(`sale_ord_tm`)\n AS label_value\n FROM (\n SELECT\n `sale_ord_id`,\n `sale_ord_tm`\n \n FROM `adm`.`adm_d04_trade_ord_det_sku_snapshot`\n WHERE\n `dt` >= FROM_UNIXTIME(UNIX_TIMESTAMP('2024-09-02','yyyy-MM-dd'), 'yyyy-MM-dd') AND `dt` <= FROM_UNIXTIME(UNIX_TIMESTAMP('2024-09-10','yyyy-MM-dd'), 'yyyy-MM-dd')\n ) AS tb_0_detail\n GROUP BY tb_0_detail.`sale_ord_id`\n\n\n\n \n";

        String sql2 = "\n\n\n\n \n\n \n \n\n \n \n\n SELECT\n tb_0_detail.sale_ord_id AS primary_key\n , MAX(sale_ord_tm)\n AS label_value\n FROM (\n SELECT\n sale_ord_id,\n sale_ord_tm\n \n FROM adm.adm_d04_trade_ord_det_sku_snapshot\n WHERE\n dt >= FROM_UNIXTIME(UNIX_TIMESTAMP('2024-09-02','yyyy-MM-dd'), 'yyyy-MM-dd') AND dt <= FROM_UNIXTIME(UNIX_TIMESTAMP('2024-09-10','yyyy-MM-dd'), 'yyyy-MM-dd')\n ) AS tb_0_detail\n GROUP BY tb_0_detail.sale_ord_id\n\n\n\n \n";

        boolean areEqual = areSqlStatementsEqual(sql1, sql2);
        System.out.println("Are the SQL statements equal? " + areEqual);
        Integer a=1;
        System.out.println(Objects.equals(1,1));
    }

    public static boolean areSqlStatementsEqual(String sql1, String sql2) {
        // 去除空白字符
        String trimmedSql1 = removeWhitespace(sql1);
        String trimmedSql2 = removeWhitespace(sql2);

        // 比较处理后的字符串
        return trimmedSql1.equals(trimmedSql2);
    }

    private static String removeWhitespace(String sql) {
        // 去除首尾空白字符
        sql = sql.trim();

        // 去除内部空白字符
        sql = sql.replaceAll("\\s+", " ").trim(); // 替换多个空白字符为单个空格
        sql = sql.replaceAll("`", ""); // 去除反引号

        return sql;
    }
}
