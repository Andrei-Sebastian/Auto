package principalPACK.Grafic;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import principalPACK.Class.Piesa;
import principalPACK.Class.WorkDataBsae;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Comanda implements Initializable {


    @FXML
    private ComboBox<String> chMarca, chModel;
    @FXML
    private ComboBox chAnMic, chAnMare;
    @FXML
    private AnchorPane anchorPiese;
    @FXML
    private Button btnTermina;
    @FXML
    private TableView<Piesa> tbPiese, tbComanda;
    @FXML
    private TableColumn<Piesa, Integer> tbIDPiesa, tbAnPiesa;
    @FXML
    private TableColumn<Piesa, Double> tbPretPiesa, tbComandaPret;
    @FXML
    private TableColumn<Piesa, String> tbDenumirePiesa, tbComandaDenumire, tbMarcaPiesa, tbModelPiesa;
    @FXML
    private Label lblPret;
    @FXML
    private ComboBox<String> chCautaDupa;
    @FXML
    private TextField txtCauta;
    private List<Piesa> lstComanda = new ArrayList<>();
    private Double pretComanda = 0.00;
    private ObservableList<Piesa> listPiese =FXCollections.observableArrayList( getListPieste());
    private static WorkDataBsae work;
    private FilteredList<Piesa> filtru = new FilteredList(listPiese, p -> true);
    private List<Piesa> getListPieste() {
         work = new WorkDataBsae("AutoPrincipalBase", "PieseAuto");
        System.out.println("I am here");
        List<Piesa> listPiesee = new ArrayList<>();

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
                listPiesee.add(piesa);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
       /* for(int i=2000;i<=2020;i++) {
            listPiesee.add(new Piesa("Audi", "A4", i, "Bara spate", 300.0*i/1500));
            listPiesee.add(new Piesa("Audi", "A4", i, "Bara fata", 300.0*i/1500));
            listPiesee.add(new Piesa("BMW", "x6", i, "Bara spate", 500.0*i/1500));
            listPiesee.add(new Piesa("BMW", "x6", i, "Bara fata", 500.00*i/1500));
            listPiesee.add(new Piesa("Honda", "Civic", i, "Bara spate", 300.0*i/1500));
            listPiesee.add(new Piesa("Honda", "Civic", i, "Bara fata", 300.0*i/1500));

        }
        listPiesee.stream().map(a->{return a.getId();}).forEach(System.out::println);*/
        return listPiesee;
    }



    public void onActionT(ActionEvent actionEvent) throws Exception {
        System.out.println("Apasat");
        for(Piesa x:lstComanda)
            work.removeID(x.getId());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("meniuPrincipal.fxml"));
        anchorPiese.getChildren().setAll(pane);

    }

    private List<String> citireF() {
        List<String> l = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\andre\\OneDrive\\Desktop\\marca.txt"));
            while (scanner.hasNext())
                l.add(scanner.nextLine());
        } catch (Exception x) {
        }
        return l;
    }

    private List<String> listAni() {
        List<String> l = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDate localDate = LocalDate.now();
        int dataSys = Integer.parseInt(dtf.format(localDate));
        for (int i = dataSys; i >= 1980; i--) {
            l.add(String.valueOf(i));
        }
        return l;
    }

    private ObservableList initializareChoiceBox(List<String> l) {

        ObservableList<String> data = FXCollections.observableList(l);

        return data;

    }

    private void setTableCell() {
        //Piese
        tbIDPiesa.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbMarcaPiesa.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbModelPiesa.setCellValueFactory(new PropertyValueFactory<>("Model"));
        tbAnPiesa.setCellValueFactory(new PropertyValueFactory<>("An"));
        tbDenumirePiesa.setCellValueFactory(new PropertyValueFactory<>("Denumire"));
        tbPretPiesa.setCellValueFactory(new PropertyValueFactory<>("Pret"));
        //Comanda
        tbComandaDenumire.setCellValueFactory(new PropertyValueFactory<>("Denumire"));
        tbComandaPret.setCellValueFactory(new PropertyValueFactory<>("Pret"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableCell();
        chCautaDupa.setItems(FXCollections.observableArrayList("ID", "Denumire"));
        chAnMare.setItems(initializareChoiceBox(listAni()));
        chAnMic.setItems(initializareChoiceBox(listAni()));
        chMarca.setItems(initializareChoiceBox(citireF()));
        tbPiese.setItems(filtru);//Set the table's items using the filtered list
        txtCauta.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (chCautaDupa.getValue()) {
                case "ID":
                    filtru.setPredicate(p -> String.valueOf(p.getId()).contains(txtCauta.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Denumire":
                    filtru.setPredicate(p -> p.getDenumire().toLowerCase().contains(txtCauta.getText().toLowerCase().trim()));//filter table by first name
                    break;
                default:
                    filtru.setPredicate(p -> true);//filter table by first name

            }

        });

    }

    private void getScena(ActionEvent actionEvent, String name) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(name));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }

    public void onActionAnuleaza(ActionEvent actionEvent) {
        tbComanda.getItems().clear();
        pretComanda = 0.00;
        lblPret.setText("0.00 LEI");

    }

    public void listPieseEvent(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            Piesa selectedItem = tbPiese.getSelectionModel().getSelectedItem();
            lstComanda.add(selectedItem);
            ObservableList<Piesa> list = FXCollections.observableList(lstComanda);
            tbComanda.setItems(list);
            pretComanda += selectedItem.getPret();
            lblPret.setText(String.format("%.2f", pretComanda) + " LEI");
        }

    }

    public void btnStergeOnAction(ActionEvent actionEvent) {
        try {
            Piesa selectedItem = tbComanda.getSelectionModel().getSelectedItem();
            tbComanda.getItems().remove(selectedItem);
            pretComanda -= selectedItem.getPret();
            lblPret.setText(String.format("%.2f", pretComanda) + " LEI");
        } catch (Exception e) {

        }


    }


    public void chOnAction(ActionEvent actionEvent) {
        try {
            filtru.setPredicate(p -> (p.getAn() <= Integer.parseInt(chAnMare.getValue().toString())) && (p.getAn() >= Integer.parseInt(chAnMic.getValue().toString())));


        } catch (Exception e) {
        }

    }

    public void chMarcaOnAction(ActionEvent actionEvent) {
        filtru.setPredicate(p -> p.getMarca().toLowerCase().contains(chMarca.getValue().toLowerCase().trim()));//filter table by first name

    }

    public void chModelOnAction(MouseEvent mouseEvent) {
        filtru.setPredicate(p -> p.getModel().toLowerCase().contains(chModel.getValue().toLowerCase().trim()));//filter table by first name

    }
}