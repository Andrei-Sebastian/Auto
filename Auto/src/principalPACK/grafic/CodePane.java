package principalPACK.grafic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import principalPACK.clase.work.AlertType;

import java.io.IOException;

public class CodePane {
    @FXML
    Pane paneEmail;
    @FXML
    TextField txtCod;
    String cod,id;


    public void btnIntraOnAction(ActionEvent actionEvent) throws IOException {

        if (txtCod.getText().equals(cod))
        {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("passwordPane.fxml"));
            Pane mailPane = loader.load();
            PasswordPane passwordPane=loader.getController();
            passwordPane.code(id);
            paneEmail.getChildren().setAll(mailPane);
        }else AlertMsg.start(AlertType.WRONG);

    }

    public void btnIesiOnAction(ActionEvent actionEvent) throws IOException {
        Pane mailPane= FXMLLoader.load(getClass().getResource("logPane.fxml"));
        paneEmail.getChildren().setAll(mailPane);
    }
    public void code(String cod,String id)
    {
        this.cod=cod;
        this.id=id;
    }

}
