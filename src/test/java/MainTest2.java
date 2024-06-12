import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cofeeshop.Main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest2 {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    @BeforeEach
    void setUpOutput() {
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testBuyMethod() {
        provideInput("1\n"); // Simulate user selecting option 1 (Espresso)
        provideInput("2\n"); // Simulate user selecting 2 cups
        provideInput("4\n"); // Simulate user returning to the main menu
        provideInput("5\n"); // Simulate user exiting the program
        Main.main(new String[]{});
        String output = testOut.toString();
        assertTrue(output.contains("How many cup(s) would you like?"));
        assertTrue(output.contains("Back to Main Menu"));
    }

    @Test
    void testRefill() {
        provideInput("100\n200\n300\n4\n5\n");
        Main.main(new String[]{});
        String output = testOut.toString();
        assertTrue(output.contains("Please enter refill amount for each item below"));
        assertTrue(output.contains("New remaining resources"));
    }

    @Test
    void testTakeMoney() {
        provideInput("4\n5\n");
        Main.main(new String[]{});
        String output = testOut.toString();
        assertTrue(output.contains("You took Rp0 From the Coffee Machine."));
    }

    @Test
    void testExit() {
        provideInput("5\n");
        Main.main(new String[]{});
        String output = testOut.toString();
        assertTrue(output.contains("EXIT"));
    }

    @Test
    void testRemaining() {
        provideInput("4\n5\n");
        Main.main(new String[]{});
        String output = testOut.toString();
        assertTrue(output.contains("Current Coffee Machine Resources"));
    }
}
