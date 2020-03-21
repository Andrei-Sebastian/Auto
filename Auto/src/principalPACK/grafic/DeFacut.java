package principalPACK.grafic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class DeFacut implements Initializable {
    @FXML
    TextArea txtArea;
    public void salveazaOnAction(ActionEvent actionEvent) {
        try {
            Files.write( Paths.get("./src/principalPACK/deFacut.txt"), txtArea.getText().getBytes());
        }catch (Exception e){}
    }

    public void inchideOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) txtArea.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtArea.setText(Files.readString(Path.of("./src/principalPACK/deFacut.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
