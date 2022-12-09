package io.geekidea.springboot.assembly.demo.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test55_E_科学计数法 {


    public static void main(String[] args) throws IOException, ParseException {
//        java科学计数法转换成普通计数法:
//        String sjiachun = "12345E-10";
        String sjiachun = "4.390039658520765E-6";
        BigDecimal db = new BigDecimal(sjiachun);
        String ii = db.toPlainString();
        System.out.println(ii);

        DecimalFormat df = new DecimalFormat("0.00");
        //如果计算方式是：分子/分母方式的,并且展示方式是百分比的，如  *100%

        String str = df.format(Double.valueOf(ii) * 100) + "%";
        System.out.println(str);
        String str2 = df.format(Double.valueOf("0.0004390039658520765")) + "%";
        System.out.println(str2);

//        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
//        nf.setGroupingUsed(false);
//        System.out.println("d:="+nf.format(sjiachun));
    }
}
