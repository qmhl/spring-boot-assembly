package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

@Slf4j
public class Test16_最长回文字符串 {


    public static void main(String[] args) {
        String ss = "abbba";
        String ss1 = "abxbbac";
        String ss2 = "babxbbacbnanbca";

        System.out.println(longestPalindrome(ss));
        System.out.println(longestPalindrome(ss1));
        System.out.println(longestPalindrome(ss2));

    }


    public static String longestPalindrome(String s) {
        int begin = 0;
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int left = maxStringLength(s, i, i);
            int right = maxStringLength(s, i, i + 1);
            int curMaxLen = Math.max(left, right);
            if (maxLen < curMaxLen) {
                maxLen = curMaxLen;
                begin = i - (maxLen - 1) / 2;
            }

        }
        return s.substring(begin, begin + maxLen);
    }

    public static int maxStringLength(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }
}