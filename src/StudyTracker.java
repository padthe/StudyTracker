import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * StudyTracker klassen ansvarar för att lagra tiden för
 * diverse kurserna. En del av funktionaliteten:
 * - Lägga till och ta fram tid per kurs
 * - Spara och ladda statistik till/från fil
 * - Nollställa datan och arkivera den i extern fil
 */

public class StudyTracker {


    /**
     * Hashmap för att spara tid per kurs
     * Key = course(ENUM) och nyckel är total tid i minuter.
     */
    private HashMap<Courses,Integer> studyTracker;

    public StudyTracker(){
        studyTracker = new HashMap<>();
    }

    /**
     * Genererar filnamnet för denna veckans statistisk
     * beroende på vilket år och veckonummer det är
     * @return En sträng som representerar veckans filnamn.
     */
    public String getWeeklyFileName() {
        LocalDate today = LocalDate.now();
        int week = today.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        int year = today.getYear();
        return "weekly_statistics_" + year + "_week_" + week + ".txt";
    }

    /**
     * Adderar tiden till specifik kurs, finns kursen redan i HashMap
     * så adderas tiden och sparas på nytt, annars
     * skapas en ny entry i HashMap
     * @param course Kursen som vi vill addera tiden till
     * @param time Tiden vi vill lägga till, i minuter
     */
    public void addTime(Courses course, int time){
            if(studyTracker.containsKey(course)){
                int currentTime = studyTracker.get(course);
                studyTracker.put(course, currentTime + time);
            } else {
                studyTracker.put(course, time);
            }
    }

    public int getTotalTime(Courses course){
        return studyTracker.getOrDefault(course, 0);
    }

    public Map<Courses, Integer> getAllTimes() {
        return new HashMap<>(studyTracker); // Returnerar kopia av datan
    }

    public void resetAndArchive(String filename) {
        // Spara aktuell statistik till fil
        saveToFile(filename);

        // Nollställ studietid
        studyTracker.clear();
    }


    /**
     * Sparar tiden till de olika kursena i en fil
     * Varje rad innehåller kursnamn samt tiden.
     * @param filename Namnet på filen vi sparar till
     */
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Map.Entry<Courses, Integer> entry : getAllTimes().entrySet()) {
                writer.println(entry.getKey().name() + ": " + entry.getValue() + " minutes");
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    /**
     * Laddar de föregående sparade tiderna till vårt program
     * Finns det inte redan en fil eller den är korrupt, så laddas inget
     *
     * @param filename Namnet på filen vi vill ladda från
     */
    public void loadFromFile(String filename){
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                studyTracker.clear(); // Rensa aktuell data
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    Courses course = Courses.valueOf(parts[0].trim());
                    int minutes = Integer.parseInt(parts[1].trim().replace(" minutes", ""));
                    studyTracker.put(course, minutes);
                }
                System.out.println("Data loaded from " + filename);
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("No data loaded. Starting fresh for this week.");
            }
        }

    }
