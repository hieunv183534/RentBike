import controllers.BikeParkController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateInfoTest {


    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    public void test() {
        boolean isValided = new BikeParkController().validateInfoBikePark("10","10","0");
        System.out.println(isValided);
        assertEquals(true, isValided);
    }
}
