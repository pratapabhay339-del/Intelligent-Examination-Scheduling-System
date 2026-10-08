import java.util.*;

/**
 * Unit 2: graph colouring (colour = exam slot). Review 1 improvement over plain First Fit:
 * colour each connected component separately and in decreasing order of degree.
 */
public class SlotColoring {
    static int[] colour(ConflictGraph g, List<List<Integer>> components) {
        int[] slot = new int[g.exams.size()];
        int[] mark = new int[g.exams.size() + 1];
        Arrays.fill(slot, -1);
        for (List<Integer> comp : components) {
            List<Integer> order = new ArrayList<>(comp);
            order.sort((a, b) -> g.degree(b) - g.degree(a));
            for (int v : order) {
                for (int n : g.adj.get(v))
                    if (slot[n] >= 0) mark[slot[n]] = v + 1;
                int c = 0;
                while (mark[c] == v + 1) c++;
                slot[v] = c;
            }
        }
        return slot;
    }

    static int[] firstFit(ConflictGraph g) {
        int[] slot = new int[g.exams.size()];
        Arrays.fill(slot, -1);
        int[] mark = new int[g.exams.size() + 1];
        for (int v = 0; v < slot.length; v++) {
            for (int n : g.adj.get(v))
                if (slot[n] >= 0) mark[slot[n]] = v + 1;
            int c = 0;
            while (mark[c] == v + 1) c++;
            slot[v] = c;
        }
        return slot;
    }

    static int slotsUsed(int[] slot) {
        int m = -1;
        for (int s : slot) m = Math.max(m, s);
        return m + 1;
    }
}