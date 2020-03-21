package principalPACK.clase.persoane;

public abstract class Persoana {
    final static int LUNGIME_CNP = 13;
    final static String[] CORECT_MAIL = {"@gmail.com", "@yahoo.com"};
    String nume, prenume;
    String dataNastere;
    String CNP = new String();
    String numarTelefon;
    Gen gen;
    String adresaMail;

    public Persoana(String nume, String prenume, String dataNastere, String CNP, String numarTelefon, Gen gen, String adresaMail) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataNastere = dataNastere;
        setCNP(CNP);
        setAdresaMail(adresaMail);
        this.numarTelefon = numarTelefon;
        this.gen = gen;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(String dataNastere) {
        this.dataNastere = dataNastere;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        if (CNP.length() == LUNGIME_CNP) {
            this.CNP=CNP;
        } else try {
            throw new Exception("Lungime CNP gresita");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public Gen getGen() {
        return gen;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }

    public String getAdresaMail() {
        return adresaMail;
    }

    public void setAdresaMail(String adresaMail) {
        boolean contine = false;
        for (int i = 1; i < CORECT_MAIL.length; i++) {
            if (adresaMail.contains(CORECT_MAIL[i])) {
                contine = true;
            }
        }
        if (contine)
            this.adresaMail=adresaMail;
        else
            try {
                throw new Exception("Adresa de mail gresita");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
