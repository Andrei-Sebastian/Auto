package principalPACK.grafic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principalPACK.clase.Piesa;
import principalPACK.clase.work.WorkDataBsae;

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
    private TableColumn<Piesa, Integer> tbIDPiesa,tbCantitatePiesa;
    @FXML
    private TableColumn<Piesa, Double> tbPretPiesa;
    @FXML
    private TableColumn<Piesa, String> tbDenumirePiesa, tbMarcaPiesa, tbModelPiesa,tbBrandPiesa,tbDescrierePiesa,tbVersiunePiesa;
    @FXML
    private TextField txtID,txtMarca,txtModel,txtVersiune,txtBrand,txtDenumire,txtPret,txtCantitate,txtAdaugaCantitate;
    @FXML
    private TextArea txtDescriere;
    @FXML
    Button btnActualizeaza, btnSterge,btnAdauga;
    private static WorkDataBsae work,workT2;
    private ObservableList<Piesa> getListPieste() throws SQLException {
        work = new WorkDataBsae("AutoPrincipalBase", "piese");
        //System.out.println("I am here");
        List<Piesa> listPiese = new ArrayList<>();
        ResultSet rs = work.getTable();
        workT2=new WorkDataBsae("AutoPrincipalBase", "masini");
        try {
            while (rs.next()) {
               System.out.println("Aici sunt");
                ResultSet rs1=workT2.getCommand("SELECT * FROM masini WHERE id_masina ="+Integer.parseInt(rs.getString(2))+"");
                while (rs1.next()) {
                    Piesa piesa = new Piesa(Integer.parseInt(rs.getString(1)), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs.getString(3), rs.getString(4), rs.getString(5), Double.parseDouble(rs.getString(6)), Integer.parseInt(rs.getString(7)));
                    System.out.println(piesa.toString());
                    listPiese.add(piesa);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return FXCollections.observableArrayList(listPiese);
    }
    private void setTableCell() {
        //Piese

        tbMarcaPiesa.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbModelPiesa.setCellValueFactory(new PropertyValueFactory<>("Model"));
        tbVersiunePiesa.setCellValueFactory(new PropertyValueFactory<>("Versiune"));
        tbDenumirePiesa.setCellValueFactory(new PropertyValueFactory<>("Denumire"));
        tbPretPiesa.setCellValueFactory(new PropertyValueFactory<>("Pret"));
        tbBrandPiesa.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        tbDescrierePiesa.setCellValueFactory(new PropertyValueFactory<>("Descriere"));
        tbCantitatePiesa.setCellValueFactory(new PropertyValueFactory<>("Cantitate"));

    }
    public void btnAdaugaOnAction(ActionEvent actionEvent) throws SQLException {
        ResultSet rs=work.getCommand("SELECT id_masina FROM piese WHERE id="+txtID.getText());
        int id_masina=1;
        if(rs.next())
            id_masina=Integer.parseInt(rs.getString(1));
        work.insert(txtID.getText(),String.valueOf(id_masina),"'"+txtBrand.getText()+"'","'"+txtDenumire.getText()+"'","'"+txtDescriere.getText()+"'",txtPret.getText(),txtCantitate.getText());
        tbPiese.setItems( getListPieste());

    }
@FXML AnchorPane anchorAddPiese;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableCell();
        try {
            tbPiese.setItems(getListPieste());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void listPieseEvent(MouseEvent mouseEvent) {
            Piesa selectedItem = tbPiese.getSelectionModel().getSelectedItem();
            txtID.setText(String.valueOf(selectedItem.getIdPiesa()));
            txtModel.setText(selectedItem.getModel());
            txtMarca.setText(selectedItem.getMarca());
            txtVersiune.setText(selectedItem.getVersiune());
            txtBrand.setText(selectedItem.getBrand());
            txtDenumire.setText(selectedItem.getDenumire());
            txtDescriere.setText(selectedItem.getDescriere());
            txtPret.setText(String.valueOf(selectedItem.getPret()));
            txtCantitate.setText(String.valueOf(selectedItem.getCantitate()));

    }


    public void btnStergeOnAction(ActionEvent actionEvent) throws SQLException {
        work.removeID("id",Integer.parseInt(txtID.getText()));
        tbPiese.setItems(getListPieste());
    }

    public void btnActualizeazaOnAction(ActionEvent actionEvent) throws SQLException {
        int cantitete=Integer.parseInt(txtCantitate.getText())+Integer.parseInt(txtAdaugaCantitate.getText());

        work.update("UPDATE piese SET cantitate_disponibila= "+cantitete+" WHERE id="+txtID.getText());
        tbPiese.setItems(getListPieste());
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
        txtCantitate.clear();
        txtBrand.clear();
        txtDescriere.clear();
        txtPret.clear();
        txtDenumire.clear();
        txtDescriere.clear();
    }
    private String next() throws SQLException {
        return String.valueOf(work.nextID("id"));
    }
}
