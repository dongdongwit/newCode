package nowcoder.com;

import java.util.*;

public class DoubleLink {
    /**
     * 给你一个字符串S,一个字符串T,请在字符串S里面找出：包含T所有字母的最小子串
     * 示例：
     * 输入：S = "ADOBECODEBANC", T="ABC"
     * 输出："BANC"
     */
    public static String minWindow(String s, String t) {
        char[] sList = s.toCharArray(), tList = t.toCharArray(), res;
        //记录滑动框中字符的情况
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        for (int i = 0; i != tList.length; i++) {
            needs.put(tList[i], needs.getOrDefault(tList[i], 0) + 1);
        }
        int right = 0, left = 0;
        int match = 0, minLen =Integer.MAX_VALUE;
        //开始滑动窗口
        while (right < sList.length) {
            char c1 = sList[right];
            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                //两者数量词相等，满足一次匹配
                if (window.get(c1).equals(needs.get(c1))) {
                    match++;
                }
            }
            right++;
            while (match == tList.length) {
                minLen = Math.min(minLen, right - left);
                char c2 = sList[left];
                if (needs.containsKey(c2)) {
                    window.put(c2, window.getOrDefault(c2, 0) - 1);
                    if (window.get(c2) < needs.get(c2)) {
                        match--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : String.copyValueOf(sList, left - 1, minLen);
    }

    public static void main(String[] arg) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println("result: " + DoubleLink.minWindow(s, t));
    }
}