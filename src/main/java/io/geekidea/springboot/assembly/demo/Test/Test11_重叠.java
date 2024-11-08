package io.geekidea.springboot.assembly.demo.Test;

public class Test11_重叠 {
    public static void main(String[] args) {
        System.out.println(judge(new int[]{1, 3}, new int[]{2, 5}));
        System.out.println(judge(new int[]{1, 3}, new int[]{4, 5}));
        System.out.println(judge(new int[]{3, 5}, new int[]{4, 8}));
    }

    // 判断A、B两个区间是否有重合
    public static boolean judge(int[] A, int[] B) {
        return Math.max(A[0], B[0]) <= Math.min(A[1], B[1]);
    }


}
