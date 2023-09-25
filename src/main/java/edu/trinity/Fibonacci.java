package edu.trinity;

import java.util.HashMap;
import java.util.Map;
public class Fibonacci {
    private final static Map<Integer, Integer> memo = new HashMap<>();
    public static int calculate(int i) {
        if (memo.containsKey(i))
            return memo.get(i);

        if (i <= 1) {
            memo.put(i, i);
            return i;
        }

        int res = calculate(i - 1) + calculate(i - 2);
        memo.put(i, res);

        return res;
    }
}
