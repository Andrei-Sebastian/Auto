package principalPACK.Grafic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class Garantie {
    @FXML
    AnchorPane anchorP;
    public void imageInfoAction(MouseEvent mouseEvent) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("infoWork.fxml"));

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

    public void imageExitAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) anchorP.getScene().getWindow();
        stage.close();
    }
}
