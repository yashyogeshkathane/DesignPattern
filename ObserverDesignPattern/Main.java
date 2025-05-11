import java.util.ArrayList;
import java.util.List;

/*
=======================================================================================
🧠 What is the Observer Design Pattern?

- Observer Pattern is a Behavioral Design Pattern.
- It defines a **one-to-many** dependency between objects so that 
  when one object (called **Subject**) changes state, 
  all its dependents (called **Observers**) are automatically notified and updated.

✅ Real-Life Example:
  - You subscribe to a YouTube channel (Subject).
  - When a new video is uploaded, you (Observer) get a notification automatically!

=======================================================================================
🧠 How Observer Pattern Works in This Code?

- `IplMatch` is the **Subject**. It maintains a list of viewers (observers).
- `TVDisplay`, `MobileApp`, and `GoogleSearch` are the **Observers**.
- When the match score updates, `IplMatch` notifies all registered observers.
- Observers automatically print the new match status without needing to ask for it manually.

=======================================================================================
🧠 Step-by-Step Execution Flow:

1. Create an IplMatch (Subject).
2. Create Observers: TVDisplay, MobileApp, GoogleSearch.
3. Register Observers with IplMatch using `addObserver()`.
4. Update match score in IplMatch using `updateMatchScore()`.
5. IplMatch internally calls `notifyObservers()`:
    - Loops through each observer and calls its `update()` method.
6. Observers react individually and print match updates.
7. We can also remove an observer anytime using `removeObserver()`.

=======================================================================================
*/

// Subject Interface
interface Subject {
    void addObserver(Observer observer);   // To add observer
    void removeObserver(Observer observer); // To remove observer
    void notifyObservers(); // Notify all observers about changes
}

// Observer Interface
interface Observer {
    void update(String matchStatus); // Called when subject updates
}

// Concrete Subject Class - IplMatch
class IplMatch implements Subject {
    private List<Observer> viewers = new ArrayList<>(); // List to store registered observers
    private String matchStatus; // State of the match

    @Override
    public void addObserver(Observer observer) {
        viewers.add(observer); // Add observer to list
    }

    @Override
    public void removeObserver(Observer observer) {
        viewers.remove(observer); // Remove observer from list
    }

    @Override
    public void notifyObservers() {
        // Notify all observers by calling their update method
        for (Observer observer : viewers) {
            observer.update(matchStatus);
        }
    }

    // Updates match status and notifies all observers
    public void updateMatchScore(String status) {
        this.matchStatus = status;
        notifyObservers();
    }
}

// Concrete Observer - TVDisplay
class TVDisplay implements Observer {
    private String viewerName;

    public TVDisplay(String viewerName) {
        this.viewerName = viewerName;
    }

    @Override
    public void update(String matchStatus) {
        // Display update on TV screen
        System.out.println(viewerName + " on TV: Match Update - " + matchStatus);
    }
}

// Concrete Observer - MobileApp
class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String matchStatus) {
        // Display update in Mobile App
        System.out.println(appName + " Mobile App: Match Update - " + matchStatus);
    }
}

// Concrete Observer - GoogleSearch
class GoogleSearch implements Observer {
    @Override
    public void update(String matchStatus) {
        // Display update on Google Search
        System.out.println("GoogleSearch: Match Update - " + matchStatus);
    }
}

// Driver/Main Class
class Main {
    public static void main(String[] args) {
        // Create the Subject
        IplMatch match = new IplMatch();

        // Create Observers
        Observer tvViewer = new TVDisplay("Star Sports");
        Observer mobileApp = new MobileApp("JioCinema");
        Observer google = new GoogleSearch();

        // Register observers with the match
        match.addObserver(tvViewer);
        match.addObserver(mobileApp);
        match.addObserver(google);

        // First match update - all observers notified
        System.out.println("First Match Update");
        match.updateMatchScore("CSK: 150/3 IN 18 OVERS");

        // Second match update - all observers notified
        System.out.println("Second Match Update");
        match.updateMatchScore("CSK: 180/4 IN 20 OVERS");

        // Removing Google Search observer
        match.removeObserver(google);

        // Final match update - only TVDisplay and MobileApp notified
        System.out.println("Final Match Update");
        match.updateMatchScore("CSK WON BY 20 RUNS");
    }
}
