package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
public class Test_99 {
    public static void main(String[] args) {
        List<Double> labelValue = Arrays.asList(0.0, 1.0, 4.0, 3.0, 13.0, 11.0, 5.0, 12.0, 7.0, 2.0, 6.0, 10.0, 14.0, 18.0, 15.0, 19.0, 8.0, 21.0, 20.0, 9.0);
        List<Integer> coverCnt = Arrays.asList(86884736, 84099325, 83911255, 82659666, 82092485, 80993580, 80810230, 79856648, 78330933, 77952341, 77390293, 76196956, 75807446, 75588274, 75073741, 74605170, 74351449, 72135507, 71295651, 69702046);

        // 计算标签值的最大值和最小值
        double min = Collections.min(labelValue);
        double max = Collections.max(labelValue);

        // 计算每个桶的大小
        double bucketSize = (max - min) / 20;

        // 初始化桶的数量统计
        int[] bucketCounts = new int[20];

        // 遍历标签值列表，统计每个桶内的覆盖数量
        for (int i = 0; i < labelValue.size(); i++) {
            double label = labelValue.get(i);
            int count = coverCnt.get(i);
            int bucketIndex = (int) ((label - min) / bucketSize);
            if (bucketIndex >= 20) {
                bucketIndex = 19; // 确保不会超出最后一个桶
            }
            bucketCounts[bucketIndex] += count;
        }

        // 输出每个桶的范围和覆盖数量
        for (int i = 0; i < 20; i++) {
            double start = min + i * bucketSize;
            double end = min + (i + 1) * bucketSize;
            if (i == 19) {
                end = max; // 最后一个桶的结束值为最大值
            }
            start = roundToActualDecimals(start);
            end = roundToActualDecimals(end);
            System.out.printf("Bucket %d: [%f, %f) - Count: %d\n", i, start, end, bucketCounts[i]);
//            log.info("i- {} Bucket {}-{} - Count: {}", i, start, end, bucketCounts[i]);
        }

        // 计算总覆盖数量
        long sum = coverCnt.stream().mapToLong(Integer::longValue).sum();
        System.out.println("Sum: " + sum);
    }

    private static double roundToActualDecimals(double value) {
        String valueStr = String.valueOf(value);
        // 如果是小数
        if (valueStr.contains(".")) {
            String[] parts = valueStr.split("\\.");
            if (parts[1].length() > 6) {
                BigDecimal bd = new BigDecimal(value);
                valueStr = bd.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
            }
        }
        return Double.parseDouble(valueStr);
    }


    /**
     * 获取小数点后的位数
     *
     * @param valueStr
     * @return
     */
    private static int getDecimalPointLength(String valueStr) {
        // 检查字符串是否包含小数点
        if (valueStr.contains(".")) {
            // 分割字符串
            String[] parts = valueStr.split("\\.");
            // 返回小数部分的长度
            return parts[1].length();
        }
        return 0;
    }
}