package principalPACK.clase.persoane;

import principalPACK.clase.preluate.Criptare;

public class Cont {
    String usser;
    String parola;

    public Cont(String usser, String parola) {
        this.usser = usser;
        this.parola = Criptare.encrypt("parolaCriptare",parola);
    }

    public String getUsser() {
        return usser;
    }

    public void setUsser(String usser) {
        this.usser = usser;
    }

    public String getParola() {
        return Criptare.decrypt("parolaCriptare",parola);
    }

    public void setParola(String parola) {
        this.parola = Criptare.encrypt("parolaCriptare",parola);
    }
}
