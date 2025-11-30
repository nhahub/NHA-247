import java.util.ArrayList;
import java.util.List;
public class TestReports {
    static class TestCaseResult {
        String className;
        String name;
        String status;
        String expected;
        String actual;

        TestCaseResult(String className, String name, String status, String expected, String actual) {
            this.className = className;
            this.name = name;
            this.status = status;
            this.expected = expected;
            this.actual = actual;
        }
    }


    static List<TestCaseResult> results = new ArrayList<>();

    public static void add(String className, String name, String status, String expected, String actual) {
        results.add(new TestCaseResult(className, name, status, expected, actual));
    }


    public static void print() {

        List<String> classes = new ArrayList<>();
        for (TestCaseResult r : results) {
            if (!classes.contains(r.className)) {
                classes.add(r.className);
            }
        }

        for (String className : classes) {
            int n = 0; //test cases pass number
            int m = 0; //test cases fail number

            System.out.println("\n========================= REPORT FOR CLASS: " + className + " =========================\n");

            // Header
            System.out.printf("%-30s %-10s %-50s %-50s\n",
                    "Test Case", "Status", "Expected", "Actual");

            System.out.println("---------------------------------------------------------------------------------------------------------------");

            // Rows
            for (TestCaseResult r : results) {
                if (r.className.equals(className)) {
                    System.out.printf("%-30s %-10s %-50s %-50s\n",
                            trim(r.name, 30),
                            trim(r.status, 10),
                            trim(r.expected, 50),
                            trim(r.actual, 50));

                    if(r.status == "PASS"){
                        n++;
                    }
                    else {m++;}
                }
            }

            System.out.printf("Test Cases Passed: %d%nTest Cases Failed: %d%n", n ,m);
            System.out.println("===============================================================================================================\n");
        }
    }

    // Helper to prevent long text from breaking alignment
    private static String trim(String text, int max) {
        if (text == null) return "";
        return text.length() <= max ? text : text.substring(0, max - 3) + "...";
    }



}