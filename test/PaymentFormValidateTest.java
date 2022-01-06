import controllers.ReturnBikeController;
import controllers.calculate.CalculateMoney1;
import exception.InvalidCalculateInputException;
import org.junit.jupiter.api.BeforeEach;

import controllers.calculate.CalculateMoney1;
import exception.InvalidCalculateInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;

import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author HieuNV183534
 */
public class PaymentFormValidateTest {
    private ReturnBikeController returnBikeController;

    @BeforeEach
    void setUp() throws Exception {
        returnBikeController = new ReturnBikeController();
    }


    @ParameterizedTest
    @CsvSource({
            "-10000,30,3000,15,20, ",
            "10000,-30,3000,15,20, ",
            "10000,30,-3000,15,20, ",
            "10000,30,3000,-15,20, ",
            "10000,30,3000,15,-20, ",
            "10000,30,3000,15,20,  ",
            "10000,30,3000,15,35,  ",
            "10000,30,3000,15,50,  ",

    })


    @Test
    public void test(String cardCode, String owner, String cvvCode, String dateExpired, boolean expected) {

        Map<String, String> creditCardInput = new Hashtable<String, String>();
        creditCardInput.put("cardCode", cardCode);
        creditCardInput.put("owner", owner);
        creditCardInput.put("cvvCode", cvvCode);
        creditCardInput.put("dateExpired", dateExpired);
        boolean result = returnBikeController.validatePaymentForm(creditCardInput);
        assertEquals(expected, result);
    }
}
