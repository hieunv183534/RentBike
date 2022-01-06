import controllers.calculate.CalculateMoney1;
import exception.InvalidCalculateInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author HieuNV183534
 */
public class CalculatorMoneyTest {

    @BeforeEach
    void setUp() throws Exception {

    }


    @ParameterizedTest
    @CsvSource({
            "-10000, 30, 3000, 15, 20, -100 ",
            "10000, -30, 3000, 15, 20, -100 ",
            "10000, 30, -3000, 15, 20, -100 ",
            "10000, 30, 3000, -15, 20, -100 ",
            "10000, 30, 3000, 15, -20, -100 ",
            "10000, 30, 3000, 15, 20, 10000 ",
            "10000, 30, 3000, 15, 35, 13000 ",
            "10000, 30, 3000, 15, 55, 16000",
    })


    /**
     * kiểm thử hàm tính tiền thuê xe từ số phút
     * @author HieuNV183534
     */
    @Test
    public void test(long baseCost, long baseTime, long progressiveCost,
                     long progressiveTime , long time ,long expected) {
        long money;
        try {
            money = new CalculateMoney1(baseCost,baseTime,progressiveCost,progressiveTime).calculateMoney(time);
        } catch (InvalidCalculateInputException e) {
            e.printStackTrace();
            money = -100;
        }
        assertEquals(expected, money);
    }
}
