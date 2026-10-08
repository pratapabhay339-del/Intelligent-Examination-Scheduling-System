import java.util.*;

/**
 * Unit 3: Resource Allocation (DP). Distribute R invigilators among the exams of a slot so
 * that total "coverage gain" is maximum. gain(exam, k) = benefit of giving k invigilators,
 * computed from the exam's batch size (1 invigilator per 30 students is ideal).
 * dp[i][r] = best gain using first i exams and r invigilators. Time O(n*R^2).
 */
public class InvigilatorAllocation {
    static final int STUDENTS_PER_INVIGILATOR = 30;

    static int gain(Exam e, int k) {
        int need = (e.size() + STUDENTS_PER_INVIGILATOR - 1) / STUDENTS_PER_INVIGILATOR;
        int covered = Math.min(k, need) * STUDENTS_PER_INVIGILATOR;
        return Math.min(covered, e.size());
    }

    static int[] allocate(List<Exam> exams, int total) {
        int n = exams.size();
        int[][] dp = new int[n + 1][total + 1];
        int[][] pick = new int[n + 1][total + 1];
        for (int i = 1; i <= n; i++)
            for (int r = 0; r <= total; r++)
                for (int k = 0; k <= r; k++) {
                    int val = dp[i - 1][r - k] + gain(exams.get(i - 1), k);
                    if (val > dp[i][r]) { dp[i][r] = val; pick[i][r] = k; }
                }
        int[] res = new int[n];
        int r = total;
        for (int i = n; i >= 1; i--) { res[i - 1] = pick[i][r]; r -= res[i - 1]; }
        return res;
    }
}