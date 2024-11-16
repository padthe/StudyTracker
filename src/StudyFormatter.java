import java.util.Map;

public class StudyFormatter {


    public static String formatAllCoursesSummary(Map<Courses, Integer> allTimes) {
        if (allTimes.isEmpty()) {
            return "No study time has been recorded";
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Courses, Integer> entry : allTimes.entrySet()) {
            result.append(entry.getKey().name())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" minutes\n");
        }
        return result.toString();
    }
}
