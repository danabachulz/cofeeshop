import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import cofeeshop.CoffeeMachineFunctions;

public class tesTest {
    CoffeeMachineFunctions cmf = new CoffeeMachineFunctions();
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

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,})
    public void coba_espresso(int cup){
        
        CoffeeMachineFunctions.espresso(cup);;

        String expectedOutput = "\n Starting to make coffee" +
                        "\n Grinding coffee beans" +
                        "\n Boiling water" +
                        "\n Mixing boiled water with crushed coffee beans" +
                        "\n Pouring coffee into the cup" +
                        "\n Coffee is ready!\n";
        // Verify the output
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
