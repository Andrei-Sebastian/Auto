package principalPACK.grafic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login extends Application implements Initializable {
    @FXML
    Pane panePrincipal;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        System.out.println("Am ajuns");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pane mailPane= null;
        try {
            mailPane = FXMLLoader.load(getClass().getResource("logPane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panePrincipal.getChildren().setAll(mailPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
