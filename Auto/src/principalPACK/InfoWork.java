package principalPACK;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoWork {
    @FXML
    Text txtInfo;
    @FXML private Pane upBar;
    private Stage stage;
    public void imageExitClickEvent(MouseEvent mouseEvent) {
        stage = (Stage) txtInfo.getScene().getWindow();
        stage.close();
    }
    double x=0;
    double y=0;


    public void barUpDragged(MouseEvent mouseEvent) {
        stage=(Stage) upBar.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x);
        stage.setY(mouseEvent.getScreenY() - y);

    }

    public void barUpPresss(MouseEvent mouseEvent) {
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }
}
