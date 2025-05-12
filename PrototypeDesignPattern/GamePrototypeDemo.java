/*
 * -----------------------------------------------------------
 * PROTOTYPE DESIGN PATTERN - EXPLANATION
 * -----------------------------------------------------------
 *
 * Definition:
 * The Prototype Pattern is a creational design pattern used when 
 * the type of objects to create is determined by a prototypical instance,
 * which is cloned to produce new objects.
 *
 * Use Case:
 * - When object creation is expensive (e.g., deep initialization).
 * - When objects are similar and need only slight modifications.
 * - When instantiating directly with 'new' is costly or unnecessary.
 *
 * Benefits:
 * - Speeds up object creation.
 * - Reduces duplication of initialization code.
 * - Simplifies code for creating complex or pre-configured instances.
 *
 * -----------------------------------------------------------
 * EXAMPLE: GAME CHARACTER CLONING USING PROTOTYPE PATTERN
 * -----------------------------------------------------------
 * We are creating a game with characters like Orcs and Trolls.
 * Instead of creating new objects from scratch each time,
 * we use cloning to quickly spawn new characters.
 */

package PrototypeDesignPattern;
import java.util.HashMap;

// STEP 1: Define a common interface for all game characters.
// This interface extends Cloneable and declares clone() and display().
interface GameCharacter extends Cloneable {
    GameCharacter clone();  // Clone the current object
    void display();         // Display the character's info
}

// STEP 2: Create a concrete prototype: Orc
class Orc implements GameCharacter {
    private String weapon;
    private int health;

    // Default constructor with preset values
    public Orc() {
        this.weapon = "Axe";
        this.health = 100;
    }

    // Parameterized constructor (used internally for cloning)
    public Orc(String weapon, int health) {
        this.weapon = weapon;
        this.health = health;
    }

    // Implements clone() by returning a new instance with the same state
    @Override
    public GameCharacter clone() {
        return new Orc(this.weapon, this.health);
    }

    // Display Orc details
    @Override
    public void display() {
        System.out.println("Orc with " + weapon + ", Health: " + health);
    }
}

// STEP 3: Create another concrete prototype: Troll
class Troll implements GameCharacter {
    private String weapon;
    private int health;

    // Default constructor
    public Troll() {
        this.weapon = "Club";
        this.health = 150;
    }

    // Parameterized constructor for cloning
    public Troll(String weapon, int health) {
        this.weapon = weapon;
        this.health = health;
    }

    // Clone method to return a new Troll with the same state
    @Override
    public GameCharacter clone() {
        return new Troll(this.weapon, this.health);
    }

    // Display Troll details
    @Override
    public void display() {
        System.out.println("Troll with " + weapon + ", Health: " + health);
    }
}

// STEP 4: Registry class that holds prototypes
// This simulates a "factory" from which we get clones instead of new objects.
class CharacterRegistry {
    // A map to store prototypes by name
    private static HashMap<String, GameCharacter> prototypes = new HashMap<>();

    // Adds a prototype to the registry
    public static void addPrototype(String key, GameCharacter prototype) {
        prototypes.put(key, prototype);
    }

    // Retrieves a clone of the prototype by key
    public static GameCharacter getPrototype(String key) {
        GameCharacter prototype = prototypes.get(key);
        return prototype != null ? prototype.clone() : null;
    }
}

// STEP 5: Demo class to show usage of the Prototype Pattern
public class GamePrototypeDemo {
    public static void main(String[] args) {
        // STEP 5.1: Register prototype instances into the registry
        CharacterRegistry.addPrototype("orc", new Orc());
        CharacterRegistry.addPrototype("troll", new Troll());

        // STEP 5.2: Clone characters from registry instead of creating new ones
        GameCharacter orc1 = CharacterRegistry.getPrototype("orc");
        GameCharacter orc2 = CharacterRegistry.getPrototype("orc");
        GameCharacter troll1 = CharacterRegistry.getPrototype("troll");

        // STEP 5.3: Use cloned objects
        orc1.display();    // Output: Orc with Axe, Health: 100
        orc2.display();    // Output: Orc with Axe, Health: 100
        troll1.display();  // Output: Troll with Club, Health: 150

        // Each object is a separate instance (deep copy behavior)
    }
}
