package contracts;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class FullTimeBaseContractTest extends TestCase {
    private FullTimeBaseContract contract;

    @Before
    public void setUp() {
        double podstawa = 1000;
        contract = new FullTimeBaseContract(podstawa);
    }

    @Test
    public void obliczKwoteZmniejszajacaIKosztUzyskania() {
        contract.obliczKwoteZmniejszajacaIKosztUzyskania();
        assertEquals(46.33, contract.kwotaZmiejszajacaPodatek, 0.0);
        assertEquals(111.25, contract.kosztyUzyskaniaPrzychodu, 0.0);
    }
}