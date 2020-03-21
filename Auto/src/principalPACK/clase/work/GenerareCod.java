package principalPACK.clase.work;

import java.util.Random;

public class GenerareCod {
    private static Random random=new Random();
    private static String[] sir=creareSir();
    private static String[] creareSir()
    {
        String[] sirr=new String[26];
        for(int i=65;i<=90;i++)
            sirr[i-65]= String.valueOf((char)i);

        return sirr;
    }
    public static String getCode()
    {
        String cod="";
       for(int i=0;i<6;i++)
       {
           if(i==3)
               cod+="-";
            cod+= sir[random.nextInt(sir.length)];
       }

        return cod;
    }
}
