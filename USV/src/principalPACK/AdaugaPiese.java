package principalPACK;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principalPACK.Class.Piesa;
import principalPACK.Class.WorkDataBsae;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdaugaPiese implements Initializable {

    @FXML
    private TableView<Piesa> tbPiese;
    @FXML
    private TableColumn<Piesa, Integer> tbIDPiesa, tbAnPiesa;
    @FXML
    private TableColumn<Piesa, Double> tbPretPiesa;
    @FXML
    private TableColumn<Piesa, String> tbDenumirePiesa, tbMarcaPiesa, tbModelPiesa;
    @FXML
    private TextField txtID,txtMarca,txtModel,txtAn,txtDenumire,txtPret;

    private static WorkDataBsae work;
    private ObservableList<Piesa> getListPieste() {
        work = new WorkDataBsae("AutoPrincipalBase", "PieseAuto");
        System.out.println("I am here");
        List<Piesa> listPiese = new ArrayList<>();

        ResultSet rs = work.getDB();
        try {
            while (rs.next()) {
                Piesa piesa = new Piesa();
                piesa.setId(Integer.parseInt(rs.getString(1)));
                piesa.setMarca(rs.getString(2));
                piesa.setModel(rs.getString(3));
                piesa.setAn(Integer.parseInt(rs.getString(4)));
                piesa.setDenumire(rs.getString(5));
                piesa.setPret(Double.parseDouble(rs.getString(6)));
                listPiese.add(piesa);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return FXCollections.observableArrayList(listPiese);
    }
    private void setTableCell() {
        //Piese
        tbIDPiesa.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbMarcaPiesa.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbModelPiesa.setCellValueFactory(new PropertyValueFactory<>("Model"));
        tbAnPiesa.setCellValueFactory(new PropertyValueFactory<>("An"));
        tbDenumirePiesa.setCellValueFactory(new PropertyValueFactory<>("Denumire"));
        tbPretPiesa.setCellValueFactory(new PropertyValueFactory<>("Pret"));
    }

    public void btnAdaugaOnAction(ActionEvent actionEvent) {
        work.insert(txtID.getText(),"'"+txtMarca.getText()+"'","'"+txtModel.getText()+"'",txtAn.getText(),"'"+txtDenumire.getText()+"'",txtPret.getText());
        tbPiese.setItems( getListPieste());

    }
@FXML AnchorPane anchorAddPiese;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableCell();
        tbPiese.setItems(getListPieste());
    }

    public void imageAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) anchorAddPiese.getScene().getWindow();
        stage.close();
    }

    public void listPieseEvent(MouseEvent mouseEvent) {
            Piesa selectedItem = tbPiese.getSelectionModel().getSelectedItem();
            txtID.setText(String.valueOf(selectedItem.getId()));
            txtPret.setText(String.valueOf(selectedItem.getPret()));
            txtAn.setText(String.valueOf(selectedItem.getAn()));
            txtModel.setText(selectedItem.getModel());
            txtMarca.setText(selectedItem.getMarca());
            txtDenumire.setText(selectedItem.getDenumire());

    }
    @FXML
    Button btnActualizeaza, btnSterge,btnAdauga;
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnAdauga) {
            btnAdauga.setStyle("-fx-background-color : #1620A1");
            btnAdauga.toFront();
        }
        if (actionEvent.getSource() == btnSterge) {
            btnSterge.setStyle("-fx-background-color : #53639F");
            btnSterge.toFront();
        }
        if (actionEvent.getSource() == btnActualizeaza) {
            btnActualizeaza.setStyle("-fx-background-color : #02030A");
            btnActualizeaza.toFront();
        }
    }

    public void btnStergeOnAction(ActionEvent actionEvent) {
        work.removeID(Integer.parseInt(txtID.getText()));
        tbPiese.setItems(getListPieste());
    }

    public void btnActualizeazaOnAction(ActionEvent actionEvent) {
    }

    public void handleClickss(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == btnAdauga) {
            btnAdauga.setStyle("-fx-background-color : #1620A1");
            btnAdauga.toFront();
        }
        if (mouseEvent.getSource() == btnSterge) {
            btnSterge.setStyle("-fx-background-color : #53639F");
            btnSterge.toFront();
        }
        if (mouseEvent.getSource() == btnActualizeaza) {
            btnActualizeaza.setStyle("-fx-background-color : #02030A");
            btnActualizeaza.toFront();
        }

    }

    public void handleExited(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() == btnAdauga) {
            btnAdauga.setStyle("-fx-background-color : #0b824e");
            btnAdauga.toFront();
        }
        if (mouseEvent.getSource() == btnSterge) {
            btnSterge.setStyle("-fx-background-color : #0b824e");
            btnSterge.toFront();
        }
        if (mouseEvent.getSource() == btnActualizeaza) {
            btnActualizeaza.setStyle("-fx-background-color : #0b824e");
            btnActualizeaza.toFront();
        }
    }

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
}
