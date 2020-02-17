package principalPACK.Class;

public class Masina {
    String marca, model;
    int an;
public Masina(){}
    public Masina(String marca, String model, int an) {
        this.marca = marca;
        this.model = model;
        this.an = an;
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

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }
}
