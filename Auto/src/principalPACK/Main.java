package principalPACK;


import principalPACK.clase.persoane.Angajat;
import principalPACK.clase.persoane.Gen;
import principalPACK.clase.persoane.Vanzator;
import principalPACK.grafic.Login;

public class Main {

    public static void main(String[] args) throws Exception {
        Login.main(args);
        Angajat angajat=new Vanzator("Popescu", "Vasile","", "1980803330219", "0l45954056", Gen.M, "andrei@yahoo.com");
        System.out.println(angajat.toString());
    }

}

