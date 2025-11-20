import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

        // Find all unique class names
        List<String> classes = results.stream()
                .map(r -> r.className)
                .distinct()
                .collect(Collectors.toList());

        for (String className : classes) {

            System.out.println("\n============== REPORT FOR CLASS: " + className + " ==============\n");

            System.out.printf("%-10s %-6s %-50s %-50s\n",
                    "TestCase", "Status", "Expected URL", "Actual URL");
            System.out.println("---------------------------------------------------------------------------------------------");

            for (TestCaseResult r : results) {
                if (r.className.equals(className)) {
                    System.out.printf("%-10s %-6s %-50s %-50s\n",
                            r.name, r.status, r.expected, r.actual);
                }
            }

            System.out.println("\n====================================================\n");
        }
    }

}

