package exercise;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
class UtilsTest {

    @Test
    void testCheckNameValid() {
        assertTrue(Utils.checkName("Alice"));
    }

    @Test
    void testCheckNameInvalid() {
        assertFalse(Utils.checkName(""));
        assertFalse(Utils.checkName("   "));
        assertFalse(Utils.checkName(null));
    }

    @Test
    void testIsValidAge() {
        assertTrue(Utils.isValidAge(0));
        assertTrue(Utils.isValidAge(30));
        assertTrue(Utils.isValidAge(120));
    }

    @Test
    void testIsValidAgeInvalid() {
        assertFalse(Utils.isValidAge(-1));
        assertFalse(Utils.isValidAge(121));
    }
    @Test
    void testCheckNameEdgeCases() {
        assertFalse(Utils.checkName("   "));
        assertFalse(Utils.checkName("\n\t"));
        assertFalse(Utils.checkName(null));
}

}
