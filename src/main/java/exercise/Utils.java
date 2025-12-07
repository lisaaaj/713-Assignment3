package exercise;
public class Utils {

    // Poor naming, duplicate logic, code smell
    public static boolean checkName(String name) {
        boolean result = name != null && !name.trim().isEmpty();
        return result;
    }
    
    public static boolean isValidAge(int age) {
        boolean result = age >= 0 && age <= 120;
        return result;
    }
    
}
