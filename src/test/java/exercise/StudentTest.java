package exercise;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void testSetAgeInvalidHigh() {
        Student s = new Student("Alice", 20, 3.5);
        s.setAge(150); 
    // The fixed bug logic sets invalid ages to 0. 
        assertEquals(0, s.age); // <--- CORRECTED: Expect 0
}

    @Test
    void testSetAgeInvalidLow() {
        Student s = new Student("Alice", 20, 3.5);
        s.setAge(-5);
        assertEquals(0, s.age);
    }

    // ---------------------------
    // Added tests for coverage
    // ---------------------------

    @Test
    void testSetGpa() {
        Student s = new Student("Bob", 19, 2.0);
        s.setGpa(3.8);
        assertEquals(3.8, s.getGpa(), 0.0001);
    }

    @Test
    void testPrintStudentInfo() {
        Student s = new Student("Alice", 20, 3.5);

        // Capture System.out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        s.printStudentInfo();

        System.setOut(original); // restore stdout

        String printed = out.toString().trim();

        assertTrue(printed.contains("Alice"));
        assertTrue(printed.contains("20"));
        assertTrue(printed.contains("3.5"));
    }

    @Test
    void testSetAgeFixedBounds() {
        Student s = new Student("Charlie", 25, 3.0);
        s.setAge(121);
        assertEquals(0, s.age, "Age should be 0 for > 120");
        s.setAge(-10);
        assertEquals(0, s.age, "Age should be 0 for < 0");
        s.setAge(60);
        assertEquals(60, s.age, "Age should be set to 60");
    }
}
