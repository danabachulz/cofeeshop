package cofeeshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        Resources.totalWater = 1000;
        Resources.totalMilk = 500;
        Resources.totalCoffeeBeans = 200;
        Resources.totalCups = 10;
        Resources.totalMoney = 0;
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testRemaining() {
        Main.remaining();
        String expectedOutput = "\n [ Current Coffee Machine Resources ] \n" +
                "1000 ml of water \n" +
                "500 ml of milk \n" +
                "200 grams of coffee beans \n" +
                "10 of disposable cups \n" +
                "Rp0 \n";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testRefill() {
        String input = "1000\n500\n500\n10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.sc = new Scanner(System.in);
        Main.refill();

        assertEquals(2000, Resources.totalWater);
        assertEquals(1000, Resources.totalMilk);
        assertEquals(700, Resources.totalCoffeeBeans);
        assertEquals(20, Resources.totalCups);

        String expectedOutput = "Please enter refill amount for each item below: \n" +
            " How many ml of water: \n" +  // Perbaikan pada spasi atau newline
            " How many ml of milk: \n" + 
            " How many grams of Coffee Beans: \n" + 
            " How many cup(s): \n" + 
            "\n [ New remaining resources ] \n" + 
            " [ Current Coffee Machine Resources ] \n" + 
            "2000 ml of water \n" + 
            "1000 ml of milk \n" + 
            "700 grams of coffee beans \n" + 
            "20 of disposable cups \n" + 
            "Rp0 \n";

                
        String actualOutput = outContent.toString().trim();
        System.out.println("Expected Output:\n" + expectedOutput);
        System.out.println("Actual Output:\n" + actualOutput);
            
        assertEquals(expectedOutput.trim(), actualOutput);
    }

    @Test
    public void testTakeMoney() {
        Resources.totalMoney = 500;
        Main.takeMoney();
        String expectedOutput = "You took Rp500 From the Coffee Machine. The new Coffee Machine balance Rp0";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
        assertEquals(0, Resources.totalMoney);
    }
}
