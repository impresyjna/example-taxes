package contracts;

public class FullTimeBaseContract extends BaseContract {
    public FullTimeBaseContract(double podstawa) {
        super(podstawa);
    }

    @Override
    void obliczKwoteZmniejszajacaIKosztUzyskania() {
        kwotaZmiejszajacaPodatek = 46.33;
        kosztyUzyskaniaPrzychodu = 111.25;
    }

    @Override
    public void zwrocWynik() {
        System.out.println("UMOWA O PRACĘ");
        super.zwrocWynik();
    }
}
