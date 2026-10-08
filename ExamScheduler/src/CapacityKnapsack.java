import java.util.*;

/**
 * Unit 3: 0/1 Knapsack. In one slot the available seats (capacity) are limited.
 * weight = value = number of students of an exam.
 * Time O(n*C), space O(n*C).
 */
public class CapacityKnapsack {
    static List<Integer> select(List<Exam> items, int capacity) {
        int n = items.size();
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            int w = items.get(i - 1).size();
            for (int c = 0; c <= capacity; c++) {
                dp[i][c] = dp[i - 1][c];
                if (w <= c) dp[i][c] = Math.max(dp[i][c], dp[i - 1][c - w] + w);
            }
        }
        List<Integer> chosen = new ArrayList<>();
        int c = capacity;
        for (int i = n; i >= 1; i--)
            if (dp[i][c] != dp[i - 1][c]) {
                chosen.add(i - 1);
                c -= items.get(i - 1).size();
            }
        Collections.reverse(chosen);
        return chosen;
    }
}