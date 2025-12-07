package exercise;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
class StudentServiceTest {

    @Test
    void testAddStudentAndTopStudent() {
        StudentService service = new StudentService();
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.9);

        service.addStudent(s1);
        service.addStudent(s2);

        Student top = service.getTopStudent();
        assertNotNull(top);
        assertEquals("Bob", top.getName());
    }

    @Test
    void testTopStudentEmptyList() {
        StudentService service = new StudentService();
        assertNull(service.getTopStudent());
    }

    @Test
    void testCalculateAverageGpa() {
        StudentService service = new StudentService();
        service.addStudent(new Student("Alice", 20, 3.5));
        service.addStudent(new Student("Bob", 22, 3.5));

        double avg = service.calculateAverageGpa();
        assertEquals(3.5, avg, 0.001);
    }

    @Test
    void testCalculateAverageEmptyList() {
        StudentService service = new StudentService();
        assertEquals(0.0, service.calculateAverageGpa(), 0.001);
    }

    @Test
    void testRemoveStudentByNameFound() {
        StudentService service = new StudentService();
        service.addStudent(new Student("Alice", 20, 3.5));

        boolean removed = service.removeStudentByName("Alice");
        assertTrue(removed);
        assertNull(service.getTopStudent());
    }

    @Test
    void testRemoveStudentByNameNotFound() {
        StudentService service = new StudentService();
        service.addStudent(new Student("Alice", 20, 3.5));

        boolean removed = service.removeStudentByName("Bob");
        assertFalse(removed);
    }

    @Test
    void testGetTopStudentTie() {
        StudentService service = new StudentService();
        service.addStudent(new Student("Alice", 20, 3.9));
        service.addStudent(new Student("Bob", 22, 3.9)); // same GPA
        Student top = service.getTopStudent();
        assertNotNull(top);
        assertTrue(top.getGpa() == 3.9);
    }
    
    @Test
    void testRemoveStudentByNameEdge() {
        StudentService service = new StudentService();
        service.addStudent(new Student("Alice", 20, 3.5));
        assertFalse(service.removeStudentByName(null));
        assertFalse(service.removeStudentByName(""));
}

    @Test
    void testGetTopStudentEdgeCases() {
        StudentService service = new StudentService();
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.9);
        Student s3 = new Student("Charlie", 25, 3.9); // Tie with Bob

        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);
    
    // Ensure the top student is one of the tied students (Bob is added first, 
    // so he should remain 'top' if the comparison is strictly '>').
        Student top = service.getTopStudent();
        assertNotNull(top);
    
    // If your original code always favors the first one encountered (s2, Bob), 
    // then Bob should be the expected name.
        assertTrue(top.getGpa() == 3.9); 
    // Note: Use 'assertTrue' for ties since order might vary based on the loop.
}

    @Test
    void testCalculateAverageMultipleStudents() {
        StudentService service = new StudentService();
        service.addStudent(new Student("A", 20, 4.0));
        service.addStudent(new Student("B", 21, 3.0));
        double avg = service.calculateAverageGpa();
        assertEquals(3.5, avg, 0.001); 
}

    @Test
    void testCalculateAverageGpaWithMixedValues() {
        StudentService service = new StudentService();
    // Student 1: Average GPA (3.0)
        service.addStudent(new Student("Avg", 20, 3.0));
    // Student 2: High GPA (4.0)
        service.addStudent(new Student("High", 20, 4.0));
    // Student 3: Low GPA (2.0)
        service.addStudent(new Student("Low", 20, 2.0));

    // Average: (3.0 + 4.0 + 2.0) / 3 = 9.0 / 3 = 3.0
        double avg = service.calculateAverageGpa();
        assertEquals(3.0, avg, 0.001, "Average GPA should be 3.0");
}

    @Test
    void testGetTopStudentSortingKillMutant() {
        StudentService service = new StudentService();
    // 1. Add low GPA student first
        service.addStudent(new Student("Lowest", 20, 1.0));
    // 2. Add top student in the middle
        service.addStudent(new Student("Top", 20, 4.0));
    // 3. Add student with non-top GPA at the end
        service.addStudent(new Student("Mid", 20, 3.0));

    // This ensures the loop logic (s.getGpa() > top.getGpa()) is tested multiple times
    // and kills mutators that might change the comparison operator.
        Student top = service.getTopStudent();
        assertNotNull(top);
        assertEquals("Top", top.getName(), "Top student should be found regardless of position");
}

}