package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.geekidea.springboot.assembly.demo.model.Entity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
public class Test_98 {

    // 测试标签 ：el_2_7566  l2_sale_amount_rank_32823
    public static void main(String[] args) {
        // 测试标签 ：  l2_sale_amount_rank_32823
//        List<Integer> labelValue = Arrays.asList(1423139, 1532150, 1779837, 2048587, 2096922, 2096971, 2163518, 2227202, 2533540, 2681425, 2953487, 329247, 3402373, 3692059, 3741991, 474355, 599456, 643375, 725931, 966509);
//        List<Integer> coverCnt = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
//        // 测试标签 ：el_2_7566

        List<Double> labelValue = Arrays.asList(0.0, 1.0, 4.0, 3.0, 13.0, 11.0, 5.0, 12.0, 7.0, 2.0, 6.0, 10.0, 14.0, 18.0, 15.0, 19.0, 8.0, 21.0, 20.0, 9.0);
        List<Integer> coverCnt = Arrays.asList(86884736, 84099325, 83911255, 82659666, 82092485, 80993580, 80810230, 79856648, 78330933, 77952341, 77390293, 76196956, 75807446, 75588274, 75073741, 74605170, 74351449, 72135507, 71295651, 69702046);

        // 计算标签值的最大值和最小值
        double min = Collections.min(labelValue);
        double max = Collections.max(labelValue);

        // 计算每个桶的大小
        double bucketSize = (double) (max - min) / 20;

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
            double start = (int) (min + i * bucketSize);
            double end = (int) (min + (i + 1) * bucketSize);
            if (i == 19) {
                end = max; // 最后一个桶的结束值为最大值
            }
//            System.out.printf("Bucket %d: [%d, %d) - Count: %d\n", i, start, end, bucketCounts[i]);
            log.info("i- {} Bucket {}-{} - Count: {}", i, start, end, bucketCounts[i]);
        }

            long sum = coverCnt.stream().mapToInt(Integer::intValue).sum();
            System.out.println("Sum: " + sum);
    }
}





