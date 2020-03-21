package principalPACK.grafic;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import principalPACK.clase.persoane.Vanzator;
import principalPACK.clase.work.Conometru;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ProfilAngajat implements Initializable {
    @FXML
    private Label dateSys, lblOraStart, lblTimpMuncit;
    @FXML
    private Label lblNume, lblFunctie;
    final static Conometru conometru = new Conometru();
    private static Vanzator angajat;

    public void setAngajat(Vanzator angajat) {
        this.angajat = angajat;
        System.out.println(angajat.toString());
    }

    private void clock1() {
        //conometru.start();
        Thread clock = new Thread(() -> {
            try {
                while (true) {
                    Date d = new Date();
                    SimpleDateFormat x = new SimpleDateFormat("hh:mm:ss");
                    Platform.runLater(() -> {
                        dateSys.setText(x.format(d));
                        lblTimpMuncit.setText(conometru.afisTimp());
                        if ((new Date().getHours() < 12) || (new Date().getHours() > 14)) ;
                        // btnPauza.setDisable(true);
                    });
                    Thread.sleep(1000);

                }
            } catch (InterruptedException ex) {
                //...
            }
            //Period.between(dataNaster,LocalDate.now()).getYears();
        });
        clock.setDaemon(true);
        clock.start();
    }


    private String dataStart() {
        Date d = new Date();
        SimpleDateFormat x = new SimpleDateFormat("hh:mm:ss");
        return x.format(d);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblOraStart.setText(dataStart());
        lblNume.setText(angajat.getNume()+" "+angajat.getPrenume());
        clock1();

    }

}
