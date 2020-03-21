package principalPACK.grafic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import principalPACK.clase.work.AlertType;
import principalPACK.clase.preluate.Criptare;
import principalPACK.clase.work.WorkDataBsae;

import java.io.IOException;

public class PasswordPane {
    @FXML Pane panePassword;
    @FXML
    PasswordField txtPassword,txtConfirmare;
    String id;
    public void btnIesiOnAction(ActionEvent actionEvent) throws IOException {

            Pane paneLog= FXMLLoader.load(getClass().getResource("logPane.fxml"));
            panePassword.getChildren().setAll(paneLog);


    }

    public void btnIntraOnAction(ActionEvent actionEvent) throws IOException {
        if(txtPassword.getText().equals(txtConfirmare.getText())){
            WorkDataBsae db= new WorkDataBsae("AutoPrincipalBase", "angajati");
            db.update("update angajati set password='"+ Criptare.encrypt("parolaCriptare",txtPassword.getText())+"' where id='"+Integer.parseInt(id)+"'");
            db.stopConnection();
        Pane paneLog= FXMLLoader.load(getClass().getResource("logPane.fxml"));
        panePassword.getChildren().setAll(paneLog);
        }
        else
            AlertMsg.start(AlertType.WRONG);
    }

    public void code(String id) {
        this.id=id;
    }
}
