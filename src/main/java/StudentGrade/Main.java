package StudentGrade;

public class Main {
	public static void main(String[] args) {
    
        StudentGrade student = new StudentGrade();

        System.out.println("Searching Student : ");
        student.search("Jesse Singh");
        
        System.out.println("Searching Student not it list");
        student.search("Hazelnut");
    }
}
