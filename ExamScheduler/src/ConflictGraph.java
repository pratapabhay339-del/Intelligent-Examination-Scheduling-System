import java.util.*;

/** Unit 2: exams are vertices, a shared student/faculty is an edge (adjacency list). */
public class ConflictGraph {
    final List<Exam> exams;
    final List<List<Integer>> adj = new ArrayList<>();

    ConflictGraph(List<Exam> exams) {
        this.exams = exams;
        for (int i = 0; i < exams.size(); i++) adj.add(new ArrayList<>());
        for (int i = 0; i < exams.size(); i++)
            for (int j = i + 1; j < exams.size(); j++)
                if (exams.get(i).clashesWith(exams.get(j))) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
    }

    int degree(int v) { return adj.get(v).size(); }

    int edgeCount() {
        int e = 0;
        for (List<Integer> l : adj) e += l.size();
        return e / 2;
    }

    List<List<Integer>> connectedComponentsDFS() {
        boolean[] seen = new boolean[exams.size()];
        List<List<Integer>> comps = new ArrayList<>();
        for (int s = 0; s < exams.size(); s++) {
            if (seen[s]) continue;
            List<Integer> comp = new ArrayList<>();
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(s);
            seen[s] = true;
            while (!stack.isEmpty()) {
                int u = stack.pop();
                comp.add(u);
                for (int v : adj.get(u))
                    if (!seen[v]) { seen[v] = true; stack.push(v); }
            }
            comps.add(comp);
        }
        return comps;
    }

    int[] bfsLevels(int src) {
        int[] level = new int[exams.size()];
        Arrays.fill(level, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        level[src] = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u))
                if (level[v] == -1) { level[v] = level[u] + 1; q.add(v); }
        }
        return level;
    }
}