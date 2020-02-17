package principalPACK.Grafic;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application {
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtUser;
    public void btnIntraOnAction(ActionEvent actionEvent) throws Exception {
        //System.out.println(txtPassword.getText());
        if(txtPassword.getText().equals("123")&&txtUser.getText().equals("123"))

            getScena(actionEvent,"meniuPrincipal.fxml");

    }

    public void btnIesiOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
    private void getScena(ActionEvent actionEvent, String name) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(name));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
       // primaryStage.setFullScreen(true);
    }
    public void getScena()
    {

    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
