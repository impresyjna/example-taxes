package contracts;

import java.text.DecimalFormat;

public abstract class BaseContract {
    private double podstawa;

    private double procentSkladkiEmerytalnej = 9.76;
    private double procentSkladkiRentowej = 1.5;
    private double procentUbezpieczeniaChorobowego = 2.45;
    private double procentSkladkiZdrowotnej1 = 9.0;
    private double procentSkladkiZdrowotnej2 = 7.75;
    private double procentZaliczkiNaPodatekDochodowy = 18.0;

    private double skladkaEmerytalna = 0;
    private double skladkaRentowa = 0;
    private double ubezpieczenieChorobowe = 0;


    double kwotaZmiejszajacaPodatek;
    double kosztyUzyskaniaPrzychodu;

    private double skladkaZdrowotna1 = 0;
    private double skladkaZdrowotna2 = 0;

    private double zaliczkaNaPodatekDochodowy = 0;
    private double zaliczkaNaUS = 0;

    double podstawaSkladkiZdrowotnej;
    private double podstawaOpodatkowania;
    private double podatekPotracony;
    private double wynagrodzenie;

    private DecimalFormat dfCzescCalkowita;
    private DecimalFormat dfCzescDziesietna;

    BaseContract(double podstawa) {
        this.podstawa = podstawa;
        this.dfCzescCalkowita = new DecimalFormat("#");
        this.dfCzescDziesietna = new DecimalFormat("#.00");
    }

    public double obliczWynagrodzenie() {
        podstawaSkladkiZdrowotnej  = obliczPodstaweSkladkiZdrowotnej();
        obliczSkladkiZdrowotne();
        obliczKwoteZmniejszajacaIKosztUzyskania();
        zaliczkaNaUS = obliczZaliczkeNaUrzadSkarbowy();

        double sumaSkladekPodstawowych = skladkaEmerytalna + skladkaRentowa + ubezpieczenieChorobowe;
        wynagrodzenie = podstawa - (sumaSkladekPodstawowych + skladkaZdrowotna1 + Math.round(zaliczkaNaUS));

        return wynagrodzenie;
    }

    abstract void obliczKwoteZmniejszajacaIKosztUzyskania();

    public void zwrocWynik() {
        System.out.println("Podstawa wymiaru składek " + dfCzescDziesietna.format(podstawa));
        System.out.println("Składka na ubezpieczenie emerytalne "
                + dfCzescDziesietna.format(skladkaEmerytalna));
        System.out.println("Składka na ubezpieczenie rentowe    "
                + dfCzescDziesietna.format(skladkaRentowa));
        System.out.println("Składka na ubezpieczenie chorobowe  "
                + dfCzescDziesietna.format(ubezpieczenieChorobowe));
        System.out.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: "
                        + dfCzescDziesietna.format(podstawaSkladkiZdrowotnej));
        System.out.println("Składka na ubezpieczenie zdrowotne: " + dfCzescCalkowita.format(procentSkladkiZdrowotnej1) + " % = "
                + dfCzescDziesietna.format(skladkaZdrowotna1) + " "
                + dfCzescDziesietna.format(procentSkladkiZdrowotnej2) + " % = "
                + dfCzescDziesietna.format(skladkaZdrowotna2));
        System.out.println("Koszty uzyskania przychodu w stałej wysokości "
                + dfCzescDziesietna.format(kosztyUzyskaniaPrzychodu));
        System.out.println("Podstawa opodatkowania " + dfCzescDziesietna.format(podstawaOpodatkowania)
                + " zaokrąglona " + dfCzescCalkowita.format(podstawaOpodatkowania));
        System.out.println("Zaliczka na podatek dochodowy " + dfCzescCalkowita.format(procentZaliczkiNaPodatekDochodowy) + " % = "
                + zaliczkaNaPodatekDochodowy);
        System.out.println("Kwota wolna od podatku = " + dfCzescDziesietna.format(kwotaZmiejszajacaPodatek));
        System.out.println("Podatek potrącony = "
                + dfCzescDziesietna.format(podatekPotracony));
        System.out.println("Zaliczka do urzędu skarbowego = "
                + dfCzescDziesietna.format(zaliczkaNaUS) + " po zaokrągleniu = "
                + dfCzescCalkowita.format(zaliczkaNaUS));
        System.out.println("\nPracownik otrzyma wynagrodzenie netto w wysokości = "
                        + dfCzescDziesietna.format(wynagrodzenie));
    }

    private double obliczPodstaweSkladkiZdrowotnej() {
        skladkaEmerytalna = (podstawa * procentSkladkiEmerytalnej) / 100;
        skladkaRentowa = (podstawa * procentSkladkiRentowej) / 100;
        ubezpieczenieChorobowe = (podstawa * procentUbezpieczeniaChorobowego) / 100;
        return (podstawa - skladkaEmerytalna - skladkaRentowa - ubezpieczenieChorobowe);
    }

    private void obliczSkladkiZdrowotne() {
        skladkaZdrowotna1 = (podstawaSkladkiZdrowotnej  * procentSkladkiZdrowotnej1) / 100;
        skladkaZdrowotna2 = (podstawaSkladkiZdrowotnej  * procentSkladkiZdrowotnej2) / 100;
    }

    private double obliczZaliczkeNaUrzadSkarbowy() {
        podstawaOpodatkowania = podstawaSkladkiZdrowotnej  - kosztyUzyskaniaPrzychodu;

        zaliczkaNaPodatekDochodowy = (Math.round(podstawaOpodatkowania) * procentZaliczkiNaPodatekDochodowy) / 100;
        podatekPotracony = zaliczkaNaPodatekDochodowy - kwotaZmiejszajacaPodatek;

        return zaliczkaNaPodatekDochodowy - skladkaZdrowotna2 - kwotaZmiejszajacaPodatek;
    }
}
