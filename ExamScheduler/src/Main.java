import java.util.*;

public class Main {
    static List<String> range(String prefix, int from, int to) {
        List<String> l = new ArrayList<>();
        for (int i = from; i <= to; i++) l.add(prefix + i);
        return l;
    }

    static List<Exam> sampleExams() {
        List<Exam> e = new ArrayList<>();
        e.add(new Exam("CS101", "Data Structures", "Dr. Rao", range("S", 1, 50)));
        e.add(new Exam("CS102", "Algorithms", "Dr. Mehta", range("S", 30, 80)));
        e.add(new Exam("CS103", "DBMS", "Dr. Khan", range("S", 70, 110)));
        e.add(new Exam("CS104", "Operating Systems", "Dr. Rao", range("S", 120, 150)));
        e.add(new Exam("CS105", "Computer Networks", "Dr. Iyer", range("S", 100, 135)));
        e.add(new Exam("ME201", "Thermodynamics", "Dr. Singh", range("M", 1, 45)));
        e.add(new Exam("ME202", "Fluid Mechanics", "Dr. Verma", range("M", 30, 70)));
        e.add(new Exam("ME203", "Machine Design", "Dr. Singh", range("M", 80, 100)));
        e.add(new Exam("HU301", "Economics", "Dr. Das", range("H", 1, 40)));
        e.add(new Exam("HU302", "Sociology", "Dr. Paul", range("H", 25, 60)));
        return e;
    }

    public static void main(String[] args) {
        List<Exam> exams = sampleExams();
        int seatsPerSlot = 120, invigilatorsPerSlot = 6;
        ConflictGraph g = new ConflictGraph(exams);

        System.out.println("== Conflict graph ==");
        System.out.println("Exams (vertices): " + exams.size() + ", conflicts (edges): " + g.edgeCount());
        for (int v = 0; v < exams.size(); v++) {
            StringBuilder sb = new StringBuilder();
            for (int n : g.adj.get(v)) sb.append(exams.get(n).code).append(' ');
            System.out.printf("  %s (deg %d) -> %s%n", exams.get(v).code, g.degree(v), sb);
        }

        List<List<Integer>> comps = g.connectedComponentsDFS();
        System.out.println("\n== Connected components (DFS) ==");
        for (int i = 0; i < comps.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int v : comps.get(i)) sb.append(exams.get(v).code).append(' ');
            System.out.println("  Component " + (i + 1) + ": " + sb);
        }

        int[] lv = g.bfsLevels(0);
        System.out.println("BFS conflict-hops from " + exams.get(0).code + ": " + Arrays.toString(lv));

        int[] ff = SlotColoring.firstFit(g);
        int[] slot = SlotColoring.colour(g, comps);
        System.out.println("\n== Slot assignment (graph colouring) ==");
        System.out.println("Plain First Fit slots used     : " + SlotColoring.slotsUsed(ff));
        System.out.println("Degree-ordered + components    : " + SlotColoring.slotsUsed(slot));

        System.out.println("\n== Capacity check per slot (0/1 Knapsack, " + seatsPerSlot + " seats) ==");
        List<List<Exam>> timetable = new ArrayList<>();
        int slots = SlotColoring.slotsUsed(slot);
        for (int s = 0; s < slots; s++) {
            List<Exam> group = new ArrayList<>();
            for (int v = 0; v < exams.size(); v++) if (slot[v] == s) group.add(exams.get(v));
            Set<Integer> chosen = new HashSet<>(CapacityKnapsack.select(group, seatsPerSlot));
            List<Exam> run = new ArrayList<>(), deferred = new ArrayList<>();
            for (int i = 0; i < group.size(); i++)
                if (chosen.contains(i)) run.add(group.get(i)); else deferred.add(group.get(i));
            timetable.add(run);
            for (Exam e : deferred) {
                int t = s + 1;
                while (true) {
                    if (t == timetable.size()) timetable.add(new ArrayList<>());
                    List<Exam> target = timetable.get(t);
                    int seats = e.size();
                    boolean clash = false;
                    for (Exam o : target) {
                        seats += o.size();
                        if (o.clashesWith(e)) clash = true;
                    }
                    if (!clash && seats <= seatsPerSlot) { target.add(e); break; }
                    t++;
                }
            }
            int seated = 0;
            for (Exam e : run) seated += e.size();
            System.out.printf("  Slot %d: %s | seated %d/%d | deferred: %s%n", s + 1, run, seated, seatsPerSlot, deferred);
        }

        System.out.println("\n== Invigilator allocation (DP, " + invigilatorsPerSlot + " per slot) ==");
        for (int s = 0; s < timetable.size(); s++) {
            List<Exam> run = timetable.get(s);
            if (run.isEmpty()) continue;
            int[] a = InvigilatorAllocation.allocate(run, invigilatorsPerSlot);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < run.size(); i++) sb.append(run.get(i).code).append('=').append(a[i]).append(' ');
            System.out.println("  Slot " + (s + 1) + ": " + sb);
        }

        boolean ok = true;
        for (List<Exam> run : timetable)
            for (int i = 0; i < run.size(); i++)
                for (int j = i + 1; j < run.size(); j++)
                    if (run.get(i).clashesWith(run.get(j))) ok = false;
        System.out.println("\nClash-free timetable verified: " + ok);
    }
}