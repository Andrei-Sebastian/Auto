package principalPACK.clase.persoane;

public class Vanzator extends Persoana implements Angajat {
    int nrOreLucrate;
    String pathPoza;
    Cont cont;

    public Vanzator(String nume, String prenume, String dataNastere, String CNP, String numarTelefon, Gen gen, String adresaMail) {
        super(nume, prenume, dataNastere, CNP, numarTelefon, gen, adresaMail);
        cont=new Cont(setDefaultUser(),setDefaultPassword());
        setDefaultPassword();
        setDefaultUser();
    }

    public Vanzator(String nume, String prenume, String dataNastere, String CNP, String numarTelefon, Gen gen, String adresaMail, int nrOreLucrate, Cont cont,String pathPoza) {
        super(nume, prenume, dataNastere, CNP, numarTelefon, gen, adresaMail);
        this.nrOreLucrate = nrOreLucrate;
        this.cont=cont;
        this.pathPoza=pathPoza;
    }

    @Override
    public void setNrOreLucrate(int nrOreLucrate) {

    }

    @Override
    public double salariu() {
        return 0;
    }

    @Override
    public double leiPeOra() {
        return 0;
    }

    @Override
    public void setUser() {

    }

    @Override
    public void setPassword() {

    }

    private String setDefaultUser() {
         return  (getPrenume()+"."+getNume()).toLowerCase();
    }

    private String setDefaultPassword() {
        return  getNume()+CNP.substring(7);
    }

    public String getPathPoza() {
        return pathPoza;
    }

    @Override
    public String toString() {
        return "Vanzator{" +
                "nrOreLucrate=" + nrOreLucrate +
                ", pathPoza='" + pathPoza + '\'' +
                ", cont=" + cont.getUsser() +
                ", cont=" + cont.getParola() +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", dataNastere='" + dataNastere + '\'' +
                ", CNP='" + CNP + '\'' +
                ", numarTelefon='" + numarTelefon + '\'' +
                ", gen=" + gen +
                ", adresaMail='" + adresaMail + '\'' +
                '}';
    }
}
