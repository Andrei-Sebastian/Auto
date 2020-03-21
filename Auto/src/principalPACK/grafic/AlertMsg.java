package principalPACK.grafic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principalPACK.clase.work.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertMsg implements Initializable {
    @FXML
    Text txtInfo;
    @FXML
    Label txtPrompt;
    @FXML private Pane upBar;
    AlertType alertType;
    static String msgError[]=new String[2];
    Stage stage;
    double x=0;
    double y=0;
    private void setTxtInfo()
    {
         txtInfo.setText(msgError[1]);
    }
    private void setTxtPrompt()
    {
        txtPrompt.setText(msgError[0]);
    }

    public void imageExitClickEvent(MouseEvent mouseEvent) {
        stage = (Stage) txtInfo.getScene().getWindow();
        stage.close();
    }

    public  void  setValue(String x)
    {
        txtInfo.setText(x);
    }

    public void barUpDragged(MouseEvent mouseEvent) {
        stage=(Stage) upBar.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x);
        stage.setY(mouseEvent.getScreenY() - y);

    }

    public void barUpPresss(MouseEvent mouseEvent) {
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPrompt.setText(msgError[0]);
        txtInfo.setText(msgError[1]);
    }
    public static void start(AlertType alertType) {
        switch(alertType) {
            case EMPTY:
                msgError[0]="Eroare";
                msgError[1]="Completati toate campurile pentru a va putea conecta!!";
                break;
            case WRONG_LOGIN:
                msgError[0]="Conectare esuata";
                msgError[1]="User sau parola gresita! Completeaza inca o data cu atentie!!";
                break;
            case WRONG:
                msgError[0]="Eroare";
                msgError[1]="Datele introduse sunt gresite! Completeaza inca o data cu atentie!!";
                break;
            case INFO:
                msgError[0]="Cum lucreaza aplicatia?";
                break;

        }
        getForm();
    }
    public static void start(String prompt,String continut) {
        msgError[0]=prompt;
        msgError[1]=continut;
        getForm();
    }
    private static void getForm()  {
        Stage stage=new Stage();
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(AlertMsg.class.getResource("alertMsg.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void btnOkOnAction(ActionEvent actionEvent) {
        stage = (Stage) txtInfo.getScene().getWindow();
        stage.close();
    }
}
