package principalPACK.grafic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import principalPACK.clase.Masina;
import principalPACK.clase.work.WorkDataBsae;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdaugaMasina implements Initializable {

    @FXML
    private TableView<Masina> tbPiese;
    @FXML
    private TableColumn<Masina, Integer> tbIDPiesa;
    @FXML
    private TableColumn<Masina, String>  tbMarcaPiesa, tbModelPiesa,tbVersiunePiesa;
    @FXML
    private TextField txtID,txtMarca,txtModel,txtVersiune;
    @FXML
    Button btnActualizeaza, btnSterge,btnAdauga;
    private static WorkDataBsae work;
    private ObservableList<Masina> getListMasini() throws SQLException {
        work = new WorkDataBsae("AutoPrincipalBase", "masini");
        List<Masina> listMasini = new ArrayList<>();
        ResultSet rs = work.getTable();
        try {
            while (rs.next()) {

                    Masina masina = new Masina(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4));
                    System.out.println(masina.toString());
                    listMasini.add(masina);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return FXCollections.observableArrayList(listMasini);
    }
    private void setTableCell() {
        //Piese
        tbIDPiesa.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbMarcaPiesa.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbModelPiesa.setCellValueFactory(new PropertyValueFactory<>("Model"));
        tbVersiunePiesa.setCellValueFactory(new PropertyValueFactory<>("Versiune"));

    }
    public void btnAdaugaOnAction(ActionEvent actionEvent) throws SQLException {
        System.out.println(txtID.getText());
        work.insert(txtID.getText(),"'"+txtMarca.getText()+"'","'"+txtModel.getText()+"'","'"+txtVersiune.getText()+"'");
        tbPiese.setItems( getListMasini());
        clear();

    }
@FXML AnchorPane anchorAddPiese;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableCell();
        try {
            tbPiese.setItems(getListMasini());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void listPieseEvent(MouseEvent mouseEvent) {
            Masina selectedItem = tbPiese.getSelectionModel().getSelectedItem();
            txtID.setText(String.valueOf(selectedItem.getId()));
            txtModel.setText(selectedItem.getModel());
            txtMarca.setText(selectedItem.getMarca());
            txtVersiune.setText(selectedItem.getVersiune());


    }


    public void btnStergeOnAction(ActionEvent actionEvent) throws SQLException {
        work.removeID("id_masina",Integer.parseInt(txtID.getText()));
        tbPiese.setItems(getListMasini());
        clear();
    }

    public void btnActualizeazaOnAction(ActionEvent actionEvent) throws SQLException {
        work.update("UPDATE masini SET marca= '"+txtMarca.getText()+"',model= '"+txtModel.getText()+"', versiune= '"+txtVersiune.getText()+"' WHERE id_masina="+txtID.getText());
        tbPiese.setItems(getListMasini());
        clear();
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
            Loader.setLocation(getClass().getResource("alertMsg.fxml"));

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
    public void imageAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) anchorAddPiese.getScene().getWindow();
        stage.close();
    }

    public void btnIdNouOnAction(ActionEvent actionEvent) throws SQLException {
        txtID.setText(next());
    }
    private void clear() throws SQLException {
        txtID.setText(next());
        txtMarca.clear();
        txtModel.clear();
        txtVersiune.clear();
    }
    private String next() throws SQLException {
        return String.valueOf(work.nextID("id_masina"));
    }
}
