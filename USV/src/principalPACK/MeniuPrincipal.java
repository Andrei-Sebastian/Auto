package principalPACK;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principalPACK.Class.Conometru;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class MeniuPrincipal  implements Initializable {

    @FXML
    private Label dateSys, lblOraStart, lblTimpMuncit;
    final static Conometru conometru =new Conometru();
    private void clock1() throws InterruptedException {
        //conometru.start();
        Thread clock = new Thread() {
            public void run() {
                try {
                    for (; ; ) {
                        Date d = new Date();
                        SimpleDateFormat x = new SimpleDateFormat("hh:mm:ss");
                        Platform.runLater(() -> {
                            dateSys.setText(x.format(d));
                            lblTimpMuncit.setText(conometru.afisTimp());
                            if ((new Date().getHours() < 12) || (new Date().getHours() > 14))
                                btnPauza.setDisable(true);
                        });
                        sleep(1000);

                    }
                } catch (InterruptedException ex) {
                    //...
                }
                //Period.between(dataNaster,LocalDate.now()).getYears();
            }
        };
        clock.setDaemon(true);
        clock.start();
    }


    private String dataStart() {
        Date d = new Date();
        SimpleDateFormat x = new SimpleDateFormat("hh:mm:ss");
        return x.format(d);
    }

    @FXML
    AnchorPane ancora;

    public void onActionComanda(ActionEvent actionEvent) throws Exception {
        System.out.println("am");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("comanda.fxml"));
        ancora.getChildren().setAll(pane);
    }

    

    @FXML
    private Button btnPauza,btnTerminaP;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblOraStart.setText(dataStart());
       try {
            clock1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void btnPauzaOnAction(ActionEvent actionEvent) {
        btnTerminaP.setDisable(false);
        btnPauza.setDisable(true);
        conometru.stop();


    }

    public void btnNotiteOnAction(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("infoWork.fxml"));

        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        InfoWork alertBoxController = Loader.getController();
        //alertBoxController.setData("     ");
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void btnGarantieOnAction(ActionEvent actionEvent) {
    }

    public void btnTerminaOnAction(ActionEvent actionEvent) {
        System.out.println("Timp muncit: "+lblTimpMuncit.getText());
        System.exit(0);

    }

    public void btnTerminaPOnAction(ActionEvent actionEvent) {
        btnPauza.setDisable(true);
        conometru.continua();
    }

    public void btnAdaugaPieseOnAction(ActionEvent actionEvent) throws IOException {
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
}


