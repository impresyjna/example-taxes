package main;

import contracts.BaseContract;
import contracts.FullTimeBaseContract;
import contracts.BaseContractOfMandatory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaxCalculator {

    private static double podstawa = 0;
    private static char umowa = ' ';

    public static void main(String[] args) {
        getInputData();
        contractInformation();
    }

    private static void getInputData() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {


            System.out.print("Podaj kwotę dochodu: ");
            podstawa = Double.parseDouble(br.readLine());
        } catch (Exception ex) {
            System.out.println("Błędna kwota");
            System.err.println(ex);
            return;
        }
        System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");
        try {
            umowa = br.readLine().charAt(0);
        } catch (IOException e) {
            System.out.println("Błędny znak umowy");
            e.printStackTrace();
        }


    }

    private static void contractInformation() {
        BaseContract baseContract;
        try {
            if (umowa == 'P') {
                baseContract = new FullTimeBaseContract(podstawa);
            } else if (umowa == 'Z') {
                baseContract = new BaseContractOfMandatory(podstawa);
            } else {
                throw new IllegalArgumentException();
            }
            baseContract.obliczWynagrodzenie();
            baseContract.zwrocWynik();
        } catch (IllegalArgumentException e) {
            System.out.println("Nieznany typ umowy!");
        }
    }
}
