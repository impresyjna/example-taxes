package contracts;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class BaseContractTest extends TestCase {
    private BaseContract baseContract;

    @Before
    public void setUp() {
        double podstawa = 1000;
        baseContract = new FullTimeBaseContract(podstawa);
    }

    @Test
    public void obliczWynagrodzenie() throws Exception {
        assertEquals(763.24, Math.round(100.0 * baseContract.obliczWynagrodzenie()) / 100.0, 0.0);
    }

}