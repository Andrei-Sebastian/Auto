package principalPACK.grafic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principalPACK.clase.persoane.Vanzator;

import java.io.IOException;


public class MeniuPrincipal {


    @FXML
    AnchorPane ancora;
    private static Vanzator angajat;
    private static final int ID=1;
    public void setAngajat(Vanzator angajat)
    {
        this.angajat=angajat;
    }

    public void setID(int id)
    {
        this.angajat=angajat;
    }

    public int getID()
    {
        return ID;
    }

    public void onActionComanda(ActionEvent actionEvent) throws Exception {
        System.out.println("am");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("comanda.fxml"));
        ancora.getChildren().setAll(pane);
    }



    public void btnNotiteOnAction(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("deFacut.fxml"));

        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        DeFacut alertBoxController = Loader.getController();
        //alertBoxController.setData("     ");
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void btnGarantieOnAction(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("garantie.fxml"));

        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(Loader.getRoot()));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void btnTerminaOnAction(ActionEvent actionEvent) {
        //System.out.println("Timp muncit: "+lblTimpMuncit.getText());
        System.exit(0);

    }


    public void btnAdaugaPieseOnAction(ActionEvent actionEvent)  {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("adaugaPiese.fxml"));

        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(Loader.getRoot()));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void btnProfilOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("profilAngajat.fxml"));
        Loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(Loader.getRoot()));
        ProfilAngajat profilAngajat=Loader.getController();
        profilAngajat.setAngajat(this.angajat);
        stage.show();
        
    }

    public void btnAdaugaMasinaOnAction(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("adaugaMasina.fxml"));

        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(Loader.getRoot()));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}


