import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cofeeshop.CoffeeMachineFunctions;
import cofeeshop.Main;

public class tes_CofeeBrewing {
    private final CoffeeMachineFunctions cmf = new CoffeeMachineFunctions();
    private final Main the_main = new Main();

    @Test
    public void coffeeBrewing_tes_Message(){
        cmf.coffeeBrewing();
    }

    @Test
    public void brewingEspresso_tes_Message(){
        cmf.brewingEspresso();
    }
    
}
