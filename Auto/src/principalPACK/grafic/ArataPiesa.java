package principalPACK.grafic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ArataPiesa implements Initializable {
    @FXML
    Label txtTitlu,txt1,txt2,txt3,txt4,txt5,txt6,txt7;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtTitlu.setText("TRW Disc frana");
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");
        txt7.setText("");

    }
}
