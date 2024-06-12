package cofeeshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoffeeMachineFunctionsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));

        // Initialize resources before each test
        Resources.totalWater = 1000;
        Resources.totalCoffeeBeans = 500;
        Resources.totalMilk = 1000;
        Resources.totalCups = 10;
        Resources.espressoWater = 220;
        Resources.espressoBeans = 20;
        Resources.espressoCost = 5;
        Resources.latteWater = 400;
        Resources.latteMilk = 150;
        Resources.latteBeans = 20;
        Resources.latteCost = 7;
        Resources.cappuccinoWater = 400;
        Resources.cappuccinoMilk = 100;
        Resources.cappuccinoBeans = 20;
        Resources.cappuccinoCost = 6;
        Resources.totalMoney = 0;
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    static Stream<Integer> userCupsProvider() {
        return Stream.of(1, 5, 10, 20, 30);
    }

    @ParameterizedTest
    @MethodSource("userCupsProvider")
    void testEspresso(int userCups) {
        CoffeeMachineFunctions.espresso(userCups);
        if (Resources.espressoWater * userCups > Resources.totalWater) {
            assertTrue(outContent.toString().contains("Not enough ml of Water. Please refill."));
        } else if (Resources.espressoBeans * userCups > Resources.totalCoffeeBeans) {
            assertTrue(outContent.toString().contains("Not enough Grams of Coffee Beans. Please refill."));
        } else if (userCups > Resources.totalCups) {
            assertTrue(outContent.toString().contains("Not enough cups. Please refill"));
        } else {
            assertEquals(1000 - (Resources.espressoWater * userCups), Resources.totalWater);
            assertEquals(500 - (Resources.espressoBeans * userCups), Resources.totalCoffeeBeans);
            assertEquals(10 - userCups, Resources.totalCups);
            assertEquals(5 * userCups, Resources.totalMoney);
            assertTrue(outContent.toString().contains("Starting to make coffee"));
        }
        outContent.reset();
    }

    @ParameterizedTest
    @MethodSource("userCupsProvider")
    void testLatte(int userCups) {
        CoffeeMachineFunctions.latte(userCups);
        if (Resources.latteWater * userCups > Resources.totalWater) {
            assertTrue(outContent.toString().contains("Not enough ml of Water. Please refill."));
        } else if (Resources.latteMilk * userCups > Resources.totalMilk) {
            assertTrue(outContent.toString().contains("Not enough ml of Milk. Please refill."));
        } else if (Resources.latteBeans * userCups > Resources.totalCoffeeBeans) {
            assertTrue(outContent.toString().contains("Not enough Grams of Coffee Beans. Please refill."));
        } else if (userCups > Resources.totalCups) {
            assertTrue(outContent.toString().contains("Not enough cups. Please refill."));
        } else {
            assertEquals(1000 - (Resources.latteWater * userCups), Resources.totalWater);
            assertEquals(1000 - (Resources.latteMilk * userCups), Resources.totalMilk);
            assertEquals(500 - (Resources.latteBeans * userCups), Resources.totalCoffeeBeans);
            assertEquals(10 - userCups, Resources.totalCups);
            assertEquals(7 * userCups, Resources.totalMoney);
            assertTrue(outContent.toString().contains("Starting to make coffee"));
        }
        outContent.reset();
    }

    @ParameterizedTest
    @MethodSource("userCupsProvider")
    void testCappuccino(int userCups) {
        CoffeeMachineFunctions.cappuccino(userCups);
        if (Resources.cappuccinoWater * userCups > Resources.totalWater) {
            assertTrue(outContent.toString().contains("Not enough ml of Water. Please refill."));
        } else if (Resources.cappuccinoMilk * userCups > Resources.totalMilk) {
            assertTrue(outContent.toString().contains("Not enough ml of Milk. Please refill."));
        } else if (Resources.cappuccinoBeans * userCups > Resources.totalCoffeeBeans) {
            assertTrue(outContent.toString().contains("Not enough Grams of Coffee Beans. Please refill."));
        } else if (userCups > Resources.totalCups) {
            assertTrue(outContent.toString().contains("Not enough cups. Please refill."));
        } else {
            assertEquals(1000 - (Resources.cappuccinoWater * userCups), Resources.totalWater);
            assertEquals(1000 - (Resources.cappuccinoMilk * userCups), Resources.totalMilk);
            assertEquals(500 - (Resources.cappuccinoBeans * userCups), Resources.totalCoffeeBeans);
            assertEquals(10 - userCups, Resources.totalCups);
            assertEquals(6 * userCups, Resources.totalMoney);
            assertTrue(outContent.toString().contains("Starting to make coffee"));
        }
        outContent.reset();
    }
}