package principalPACK.Class;

public class Piesa extends Masina{
    String denumire;
    Double pret;
    //private static  int id=0;
   // private int thisID;
    int id;
    public Piesa(){super();}
    public Piesa(String marca, String model, int an, String denumire, Double pret) {
        super(marca, model, an);
        this.denumire = denumire;
        this.pret = pret;
        //thisID=id++;

    }


    public String getDenumire() {
        return denumire;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPret() {
        System.out.println("Apelll");
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public int getId() {
        //System.out.println(thisID);
        return id;
    }


    @Override
    public String toString() {
        return  id+denumire + pret + id + an;
    }
}
