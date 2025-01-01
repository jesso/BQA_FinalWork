package StudentGrade;

import java.util.HashMap;

public class StudentGrade {
	public void search(String studentName) {
        // Create a HashMap to store student names and their percentage scores
        HashMap<String, Double> studentGrades = new HashMap<>();
        
        // Adding 10 students and their percentage scores to the HashMap
        studentGrades.put("Jesse Singh", 80.0);
        studentGrades.put("Simon Katich", 51.5);
        studentGrades.put("Salman khan", 65.2);
        studentGrades.put("Angelina Joli", 88.2);
        studentGrades.put("Preety Zinta", 68.8);
        studentGrades.put("Kahif Kumar", 90.7);
        studentGrades.put("Superman kumar", 99.2);
        studentGrades.put("Spiderman Singh", 59.5);
        studentGrades.put("Gocinda Saini", 75.8);
        studentGrades.put("Srikant", 95.5);
        
        // printing all the element
        System.out.println("All students and their percentage scores:");
        System.out.println(studentGrades);
        
        // printing grades based on sudents
        if (studentGrades.containsKey(studentName)) {
            System.out.println("\nGrade of " + studentName + ": " + studentGrades.get(studentName) + "%");
        } else {
            System.out.println("\nStudent " + studentName + " is missing from the list.");
        }

        //remove
        System.out.print("\nRemoving student " + studentName + " from the list...\n");
        studentGrades.remove(studentName);

        //printing the updated list after removal
        System.out.println("All students and their percentage scores:");
        System.out.println(studentGrades);
        
    }
}
