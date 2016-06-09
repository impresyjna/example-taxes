package contracts;

public class BaseContractOfMandatory extends BaseContract {
    public BaseContractOfMandatory(double podstawa) {
        super(podstawa);
    }

    @Override
    void obliczKwoteZmniejszajacaIKosztUzyskania() {
        kwotaZmiejszajacaPodatek = 0;
        kosztyUzyskaniaPrzychodu = (podstawaSkladkiZdrowotnej * 20) / 100;
    }

    @Override
    public void zwrocWynik() {
        System.out.println("UMOWA-ZLECENIE");
        super.zwrocWynik();
    }
}
