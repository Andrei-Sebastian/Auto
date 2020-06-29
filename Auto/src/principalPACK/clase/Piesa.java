package principalPACK.clase;

public class Piesa extends Masina {
    int idPiesa,cantitate;
    String denumire,brand,descriere;
    double pret;

    public Piesa(String marca, String model, String versiune) {
        super(marca, model, versiune);
    }

    public Piesa(int idPiesa,String marca, String model, String versiune, String brand, String denumire,  String descriere, double pret, int cantitate) {
        super(marca, model, versiune);
        this.idPiesa = idPiesa;
        this.cantitate = cantitate;
        this.denumire = denumire;
        this.brand = brand;
        this.descriere = descriere;
        this.pret = pret;
    }

    public int getIdPiesa() {
        return idPiesa;
    }

    public void setIdPiesa(int idPiesa) {
        this.idPiesa = idPiesa;
    }



    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getVersiune() {
        return versiune;
    }

    public void setVersiune(String versiune) {
        this.versiune = versiune;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "Piesa{" +
                "idPiesa=" + idPiesa +
                ", cantitate=" + cantitate +
                ", denumire='" + denumire + '\'' +
                ", brand='" + brand + '\'' +
                ", descriere='" + descriere + '\'' +
                ", pret=" + pret +
                ", marca='" + marca + '\'' +
                ", model='" + model + '\'' +
                ", versiune='" + versiune + '\'' +
                '}';
    }
}
