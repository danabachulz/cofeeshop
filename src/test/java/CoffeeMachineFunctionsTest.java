import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cofeeshop.CoffeeMachineFunctions;

class CoffeeMachineFunctionsTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Redirect System.out to the outputStreamCaptor
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        // Reset System.out to its original stream
        System.setOut(originalOut);
    }

    @Test
    void testCoffeeBrewing() {
        // Call the method to be tested
        CoffeeMachineFunctions.coffeeBrewing();

        // Expected output
        String expectedOutput = "\n Starting to make coffee" +
                "\n Grinding coffee beans" +
                "\n Boiling water" +
                "\n Mixing boiled water with crushed coffee beans" +
                "\n Pouring coffee into the cup" +
                "\n Pouring some milk into the cup" +
                "\n Coffee is ready!\n"; // Include the newline character at the end

        // Verify the output
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testBrewingEspresso() {
        // Call the method to be tested
        CoffeeMachineFunctions.brewingEspresso();

        // Expected output
        String expectedOutput = "\n Starting to make coffee" +
                "\n Grinding coffee beans" +
                "\n Boiling water" +
                "\n Mixing boiled water with crushed coffee beans" +
                "\n Pouring coffee into the cup" +
                "\n Coffee is ready!\n"; // Include the newline character at the end

        // Verify the output
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
