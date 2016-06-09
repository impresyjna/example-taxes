package contracts;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class BaseContractOfMandatoryTest extends TestCase {
    private BaseContractOfMandatory contract;

    @Before
    public void setUp() {
        double podstawa = 1000;
        contract = new BaseContractOfMandatory(podstawa);
    }

    @Test
    public void obliczKwoteZmniejszajacaIKosztUzyskania() {
        contract.podstawaSkladkiZdrowotnej = 100;

        contract.obliczKwoteZmniejszajacaIKosztUzyskania();
        assertEquals(0.0, contract.kwotaZmiejszajacaPodatek, 0.0);
        assertEquals(20.0, contract.kosztyUzyskaniaPrzychodu, 0.0);
    }
}