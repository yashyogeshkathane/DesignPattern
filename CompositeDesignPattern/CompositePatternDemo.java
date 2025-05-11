package CompositeDesignPattern;

// CompositePatternDemo.java
import java.util.ArrayList;
import java.util.List;

/*
==============================================
WHAT IS COMPOSITE DESIGN PATTERN?
==============================================
The Composite Pattern is a structural design pattern that allows you to
treat individual objects (leaves) and compositions of objects (composites)
uniformly using a shared interface.

It is typically used when:
- You have a tree-like hierarchical structure.
- You want clients to treat both single objects and collections of objects the same way.
- You want to avoid writing conditional logic (e.g., "if isLeaf") everywhere.

Real-world examples:
- File systems (files and folders)
- GUI components (buttons, panels, windows)
- Organization hierarchies (employees, departments)

In this example, we're building a file system where:
- Files are leaves.
- Folders are composites that can contain both files and folders.
*/

/////////////////////////////////////////////////////
// COMPONENT - Abstract base for both Leaf and Composite
/////////////////////////////////////////////////////
abstract class FileComponent {
    protected String name;

    public FileComponent(String name) {
        this.name = name;
    }

    // Abstract method to display file/folder structure
    public abstract void display(String indent);
}

/////////////////////////////////////////////////////
// LEAF - Represents leaf nodes (e.g., files)
/////////////////////////////////////////////////////
class FileLeaf extends FileComponent {
    public FileLeaf(String name) {
        super(name);
    }

    @Override
    public void display(String indent) {
        // Display file with indentation
        System.out.println(indent + "- File: " + name);
    }
}

/////////////////////////////////////////////////////
// COMPOSITE - Represents folders containing files or subfolders
/////////////////////////////////////////////////////
class FolderComposite extends FileComponent {
    private List<FileComponent> children = new ArrayList<>();

    public FolderComposite(String name) {
        super(name);
    }

    // Add a file or folder to this folder
    public void add(FileComponent component) {
        children.add(component);
    }

    // Remove a child file or folder
    public void remove(FileComponent component) {
        children.remove(component);
    }

    @Override
    public void display(String indent) {
        // Display this folder's name
        System.out.println(indent + "+ Folder: " + name);

        // Recursively display its contents with extra indentation
        for (FileComponent component : children) {
            component.display(indent + "   ");
        }
    }
}

/////////////////////////////////////////////////////
// CLIENT - Builds and uses the composite structure
/////////////////////////////////////////////////////
public class CompositePatternDemo {
    public static void main(String[] args) {
        /*
         BENEFIT OF COMPOSITE PATTERN HERE:
         - We can treat both FileLeaf and FolderComposite objects using the same interface.
         - Adding new file types or folder behaviors is easy and doesn’t break existing code.
         - No need for complex if-else or type-checking logic.
        */

        // Create individual files
        FileComponent file1 = new FileLeaf("Resume.docx");
        FileComponent file2 = new FileLeaf("Photo.jpg");
        FileComponent file3 = new FileLeaf("Notes.txt");

        // Create folders (composites)
        FolderComposite rootFolder = new FolderComposite("Root");
        FolderComposite documentsFolder = new FolderComposite("Documents");
        FolderComposite picturesFolder = new FolderComposite("Pictures");

        // Build folder structure
        documentsFolder.add(file1); // Resume.docx -> Documents
        documentsFolder.add(file3); // Notes.txt -> Documents
        picturesFolder.add(file2);  // Photo.jpg -> Pictures

        rootFolder.add(documentsFolder); // Documents -> Root
        rootFolder.add(picturesFolder);  // Pictures -> Root

        // Display the full folder hierarchy
        rootFolder.display("");
    }
}

