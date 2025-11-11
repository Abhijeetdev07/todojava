import java.util.ArrayList;
import java.util.Scanner;

/**
 * To-Do List Manager
 * A simple console-based application to manage tasks
 */
public class ToDoListManager {
    
    // Task class to represent individual tasks
    static class Task {
        private String description;
        private int id;
        
        public Task(int id, String description) {
            this.id = id;
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
        
        public int getId() {
            return id;
        }
        
        public String toString() {
            return id + ". " + description;
        }
    }
    
    // Main application
    private ArrayList<Task> tasks;
    private int nextId;
    private Scanner scanner;
    
    public ToDoListManager() {
        tasks = new ArrayList<>();
        nextId = 1;
        scanner = new Scanner(System.in);
    }
    
    public void run() {
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    running = false;
                    System.out.println("\nGoodbye! Your tasks have been saved.");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("TO-DO LIST MANAGER");
        System.out.println("========================================");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Delete Task");
        System.out.println("4. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice (1-4): ");
    }
    
    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void addTask() {
        System.out.print("\nEnter task description: ");
        String description = scanner.nextLine().trim();
        
        if (description.isEmpty()) {
            System.out.println("Error: Task description cannot be empty!");
            return;
        }
        
        Task task = new Task(nextId, description);
        nextId = nextId + 1;
        tasks.add(task);
        System.out.println("Task added successfully!");
    }
    
    private void viewTasks() {
        System.out.println("\n========================================");
        System.out.println("YOUR TASKS");
        System.out.println("========================================");
        
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet. Add some tasks to get started!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(task);
            }
            
            System.out.println("========================================");
            System.out.println("Total Tasks: " + tasks.size());
        }
        System.out.println("========================================");
    }
    
    private void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to delete!");
            return;
        }
        
        viewTasks();
        System.out.print("\nEnter task ID to delete: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Task task = findTaskById(id);
            
            if (task == null) {
                System.out.println("Task not found!");
            } else {
                tasks.remove(task);
                System.out.println("Task deleted successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task ID.");
        }
    }
    
    private Task findTaskById(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    
    // Main method
    public static void main(String[] args) {
        ToDoListManager manager = new ToDoListManager();
        System.out.println("\nWelcome to To-Do List Manager!");
        manager.run();
    }
}

