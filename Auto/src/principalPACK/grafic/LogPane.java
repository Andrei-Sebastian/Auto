package principalPACK.grafic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import principalPACK.clase.persoane.Vanzator;
import principalPACK.clase.preluate.Criptare;
import principalPACK.clase.work.AlertType;
import principalPACK.clase.work.WorkDataBsae;

import java.io.IOException;
import java.sql.ResultSet;

public class LogPane {
    @FXML
    Pane paneLogare;
    @FXML
    TextField txtUser;
    @FXML
    PasswordField txtPassword;
    Vanzator angajat=null;

    public void btnIntraOnAction(ActionEvent actionEvent) throws Exception {
        if (txtUser.getText().equals("") || txtPassword.getText().equals("")) {
            AlertMsg.start(AlertType.EMPTY);
        } else {
            try {
                WorkDataBsae db = new WorkDataBsae("AutoPrincipalBase", "conturi");
                ResultSet result = null;
                try {
                    result = db.getCommand("SELECT * FROM conturi WHERE user='" + txtUser.getText() + "' && password='" + Criptare.encrypt("parolaCriptare", txtPassword.getText()) + "'");
                } catch (Exception e) {
                    AlertMsg.start("Eroare baza de date", "Asigurati-va ca aveti baza de date in PC-ul dumneavoastra!");
                }

                if (result.next()) {
                    //angajat = new Vanzator(result.getString(2), result.getString(3), "", "1234567890123", "", Gen.M, result.getString(5), 0, new Cont(txtUser.getText(), txtPassword.getText()), result.getString(8));

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("meniuPrincipal.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    primaryStage.setScene(scene);
                    //MeniuPrincipal meniuPrincipal = loader.getController();
                    //meniuPrincipal.setAngajat(angajat);
                    primaryStage.show();
                    primaryStage.centerOnScreen();
                } else {
                    AlertMsg.start(AlertType.WRONG_LOGIN);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

    public void btnIesiOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

   /* private void getScena(ActionEvent actionEvent, String name) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(name));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        // primaryStage.setFullScreen(true);
    }
*/

    public void hyPassOnAction(ActionEvent actionEvent) throws IOException {
        // titleLbl.setText("Resetare parola");
        Pane mailPane = FXMLLoader.load(getClass().getResource("mailPane.fxml"));
        paneLogare.getChildren().setAll(mailPane);

    }
}
