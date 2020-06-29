package principalPACK.clase;

public class Masina {
    String marca,model,versiune;
    int id;

    public Masina(int id,String marca, String model, String versiune) {
        this.marca = marca;
        this.model = model;
        this.versiune = versiune;
        this.id = id;
    }

    public Masina(String marca, String model, String versiune) {
        this.marca = marca;
        this.model = model;
        this.versiune = versiune;
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

    public String getVersiune() {
        return versiune;
    }

    public void setVersiune(String versiune) {
        this.versiune = versiune;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
