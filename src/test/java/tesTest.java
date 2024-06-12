import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import cofeeshop.CoffeeMachineFunctions;

public class tesTest {
    CoffeeMachineFunctions cmf = new CoffeeMachineFunctions();

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,})
    public void coba_espresso(int cup){
        cmf.espresso(cup);
    }
}
