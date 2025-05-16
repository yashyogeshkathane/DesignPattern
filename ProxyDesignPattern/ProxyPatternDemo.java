package ProxyDesignPattern;

// ProxyPatternDemo.java

// Main class to demonstrate the Proxy Design Pattern
public class ProxyPatternDemo {

    /**
     * ================================
     * What is Proxy Design Pattern?
     * ================================
     * The Proxy Design Pattern provides a surrogate or placeholder for another object
     * to control access to it. It acts as an intermediary, adding an extra level of control.
     * 
     * Use cases:
     * - Lazy initialization (Virtual Proxy)
     * - Access control (Protection Proxy)
     * - Logging, caching, or resource management (Smart Proxy)
     * 
     * In this example, we use a Virtual Proxy to delay loading of a heavy resource (report).
     */

    // 1. Common interface for both RealReport and Proxy
    interface Report {
        void display();
    }

    // 2. Real object which is expensive to create or load
    static class RealReport implements Report {
        private String reportName;

        // Constructor simulates a heavy operation (like loading a big file)
        public RealReport(String reportName) {
            this.reportName = reportName;
            loadFromDisk();  // simulate an expensive task
        }

        // Simulate slow loading (e.g., reading from disk or network)
        private void loadFromDisk() {
            System.out.println("Loading report: " + reportName);
            try {
                Thread.sleep(2000); // Simulate delay (2 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Show the report
        public void display() {
            System.out.println("Displaying report: " + reportName);
        }
    }

    // 3. Proxy class to control access to the RealReport
    static class ReportProxy implements Report {
        private String reportName;
        private RealReport realReport; // Reference to the real object

        public ReportProxy(String reportName) {
            this.reportName = reportName;
        }

        // On first display, RealReport is created (lazy initialization)
        public void display() {
            if (realReport == null) {
                realReport = new RealReport(reportName); // Load only when needed
            }
            realReport.display(); // Then delegate to real object
        }
    }

    // 4. Client code
    public static void main(String[] args) {
        // Create a proxy for a report, but don't load it yet
        Report report = new ReportProxy("Annual_Report_2024.pdf");

        System.out.println("Report created, but not loaded yet...\n");

        System.out.println("Now displaying the report:\n");
        report.display(); // First access: loads the report

        System.out.println("\nDisplaying again (should be fast):\n");
        report.display(); // Already loaded, so no delay this time
    }
}

