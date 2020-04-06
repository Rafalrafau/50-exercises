import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArmstrongNumberTest {

    private ArmstrongNumber an;
    private int input;
    private boolean output;

    public ArmstrongNumberTest(int input, boolean output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup(){
        an = new ArmstrongNumber();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> coll() {
        return Arrays.asList(new Object[][]{
                {3, true},
                {371, true},
                {407, true},
                {1634, true},
        });
    }

    @Test
    public void isArmstrong() {
        assertEquals(output, an.isArmstrong(input));
    }
}