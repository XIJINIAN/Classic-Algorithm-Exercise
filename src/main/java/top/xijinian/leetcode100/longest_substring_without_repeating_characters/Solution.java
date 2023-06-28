package top.xijinian.leetcode100.longest_substring_without_repeating_characters;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int[] countArray = new int[128];
        char[] charArray = s.toCharArray();
        int begin = 0;
        int end = 0;
        int max = 1;
        countArray[charArray[end]]++;
        while (end < charArray.length - 1) {
            end++;
            countArray[charArray[end]]++;
            if (countArray[charArray[end]] == 2) {
                while (true) {
                    countArray[charArray[begin]]--;
                    if (charArray[begin] == charArray[end]) {
                        begin++;
                        break;
                    } else {
                        begin++;
                    }
                }
            }
            max = Math.max(max, (end - begin + 1));
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
    }
}
