package principalPACK.grafic;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import principalPACK.clase.work.AlertType;
import principalPACK.clase.work.GenerareCod;
import principalPACK.clase.preluate.SendEmail;
import principalPACK.clase.work.WorkDataBsae;

import java.io.IOException;
import java.sql.ResultSet;

public class MailPane {
    @FXML
    Pane paneEmail;
    @FXML
    Text txtTrimite;
    @FXML
    JFXTextField txtMail;
    String cod;


    public void btnIntraOnAction(ActionEvent actionEvent) throws IOException {
        WorkDataBsae db = new WorkDataBsae("AutoPrincipalBase", "angajati");
        if (txtMail.getText().equals(""))
            AlertMsg.start(AlertType.EMPTY);
        else {
            try {
                ResultSet result = db.getCommand("SELECT id FROM angajati WHERE email='" + txtMail.getText() + "'");


                if (result.next()) {
                    cod= GenerareCod.getCode();
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                SendEmail.sendEmail(cod,txtMail.getText());
                                }catch(Exception e) {
                            }
                        }
                    };
                    thread.setDaemon(true);
                    thread.start();
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("codePane.fxml"));
                    Pane mailPane = loader.load();
                    CodePane codePane=loader.getController();
                    codePane.code(cod,result.getString(1));
                    paneEmail.getChildren().setAll(mailPane);
                } else {
                    AlertMsg.start(AlertType.WRONG);
                }
                db.stopConnection();

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public void btnIesiOnAction(ActionEvent actionEvent) throws IOException {
        Pane mailPane = FXMLLoader.load(getClass().getResource("logPane.fxml"));
        paneEmail.getChildren().setAll(mailPane);
    }
}
