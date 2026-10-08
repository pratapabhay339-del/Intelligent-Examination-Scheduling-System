import java.util.*;

/** One examination (course) with its enrolled students and invigilation need. */
public class Exam {
    final String code;
    final String name;
    final Set<String> students;
    final String faculty;

    Exam(String code, String name, String faculty, Collection<String> students) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.students = new HashSet<>(students);
    }

    int size() { return students.size(); }

    boolean clashesWith(Exam o) {
        if (faculty.equals(o.faculty)) return true;
        Set<String> small = size() <= o.size() ? students : o.students;
        Set<String> big = small == students ? o.students : students;
        for (String s : small) if (big.contains(s)) return true;
        return false;
    }

    @Override public String toString() { return code + "(" + size() + ")"; }
}