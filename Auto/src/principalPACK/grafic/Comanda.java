package principalPACK.grafic;


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
import principalPACK.clase.Piesa;
import principalPACK.clase.work.WorkDataBsae;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Comanda implements Initializable {


    @FXML
    private ComboBox<String> chMarca, chModel,chCategorie,chSubcategorie;
    @FXML
    private ComboBox chAnMic, chAnMare;
    @FXML
    private AnchorPane anchorPiese;
    @FXML
    private TableView<Piesa> tbPiese, tbComanda;
    @FXML
    private TableColumn<Piesa, Double>  tbComandaPret;
    @FXML
    private TableColumn<Piesa, String>  tbComandaDenumire;
    @FXML
    private TableColumn<Piesa, Integer> tbIDPiesa,tbCantitatePiesa;
    @FXML
    private TableColumn<Piesa, Double> tbPretPiesa;
    @FXML
    private TableColumn<Piesa, String> tbDenumirePiesa, tbMarcaPiesa, tbModelPiesa,tbBrandPiesa,tbDescrierePiesa,tbVersiunePiesa;
    @FXML
    private Label lblPret;
    @FXML
    private TextField txtCauta;
    @FXML
    Button btnAnuleaza, btnSterge, btnTermina;
    private List<Piesa> lstComanda = new ArrayList<>();
    private Double pretComanda = 0.00;
    private ObservableList<Piesa> listPiese = FXCollections.observableArrayList(getListPieste());
    private static WorkDataBsae work,workT2;
    private FilteredList<Piesa> filtru = new FilteredList(listPiese, p -> true);
    private static Map<String,List<String>> mapCategoriiPiese=populareCategorii();
    //private static final Angajat=setAngajat();
    private static List<String> citireFisier(String path)
    {
        List<String> list=new ArrayList<>();
        try {
            Scanner scanner=new Scanner(new File(path));
            while (scanner.hasNext())
                list.add(scanner.nextLine());
            return list;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Map<String,Map<String,List<String>>> populareMasini()
    {
        Map<String,Map<String,List<String>>> map=new TreeMap<>();
        List<String> lstMarca=citireFisier("./src/principalPACK/masiniTXT/marca.txt");
        List<String> lstModel=citireFisier("./src/principalPACK/masiniTXT/model.txt");
        List<String> lstVersiune=citireFisier("./src/principalPACK/masiniTXT/versiune.txt");
      for(int i=0;i<=lstMarca.size();i++){
            Map<String,List<String>> list=map.get(lstMarca.get(i));
            for(int j=0;j<=lstModel.size();j++)
            {
                
            }

        }
        return  null;
    }

    private static Map<String,List<String>> populareCategorii()
    {
        Map<String,List<String>> map=new TreeMap<>();
        List<String> categorii=citireFisier("./src/principalPACK/categorii.txt");
        List<String> subcategorii=citireFisier("./src/principalPACK/subcategorii.txt");
        System.out.println(subcategorii.size());
        for (int i=0;i<subcategorii.size();i++)
        {
           List<String> list=map.get(categorii.get(Integer.parseInt(subcategorii.get(i).split("-")[0])));
            if(list==null)
            {
                list = new ArrayList <> ();
                map.put (categorii.get(Integer.parseInt(subcategorii.get(i).split("-")[0])), list);
            }
            list.add(subcategorii.get(i).split("-")[1]);
    }
        return map;
    }

    public Comanda() throws SQLException {
    }

    public void handleClicks(MouseEvent actionEvent) {
        if (actionEvent.getSource() == btnTermina) {
            btnTermina.setStyle("-fx-background-color : #1620A1");
            btnTermina.toFront();
        }
        if (actionEvent.getSource() == btnSterge) {
            btnSterge.setStyle("-fx-background-color : #53639F");
            btnSterge.toFront();
        }
        if (actionEvent.getSource() == btnAnuleaza) {
            btnAnuleaza.setStyle("-fx-background-color : #02030A");
            btnAnuleaza.toFront();
        }
    }

    public void handleExited(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() == btnTermina) {
            btnTermina.setStyle("-fx-background-color : #0b824e");
            btnTermina.toFront();
        }
        if (mouseEvent.getSource() == btnSterge) {
            btnSterge.setStyle("-fx-background-color : #0b824e");
            btnSterge.toFront();
        }
        if (mouseEvent.getSource() == btnAnuleaza) {
            btnAnuleaza.setStyle("-fx-background-color : #0b824e");
            btnAnuleaza.toFront();
        }
    }

    private List<Piesa> getListPieste() throws SQLException {
        work = new WorkDataBsae("AutoPrincipalBase", "piese");
        //System.out.println("I am here");
        List<Piesa> listPiese = new ArrayList<>();
        ResultSet rs = work.getTable();
        workT2=new WorkDataBsae("AutoPrincipalBase", "masini");
        try {
            while (rs.next()) {

                ResultSet rs1=workT2.getCommand("SELECT * FROM masini WHERE id_masina ="+Integer.parseInt(rs.getString(2))+"");
                while (rs1.next()) {
                    Piesa piesa = new Piesa(Integer.parseInt(rs.getString(1)), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs.getString(3), rs.getString(4), rs.getString(5), Double.parseDouble(rs.getString(6)), Integer.parseInt(rs.getString(7)));
                    System.out.println(piesa.toString());
                    if(piesa.getCantitate()>0)
                        listPiese.add(piesa);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return listPiese;
    }

    private ObservableList<Piesa> getListPieste1() throws SQLException {
        work = new WorkDataBsae("AutoPrincipalBase", "piese");
        //System.out.println("I am here");
        List<Piesa> listPiese = new ArrayList<>();
        ResultSet rs = work.getTable();
        workT2=new WorkDataBsae("AutoPrincipalBase", "masini");
        try {
            while (rs.next()) {

                ResultSet rs1=workT2.getCommand("SELECT * FROM masini WHERE id_masina ="+Integer.parseInt(rs.getString(2))+"");
                while (rs1.next()) {
                    Piesa piesa = new Piesa(Integer.parseInt(rs.getString(1)), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs.getString(3), rs.getString(4), rs.getString(5), Double.parseDouble(rs.getString(6)), Integer.parseInt(rs.getString(7)));
                    System.out.println(piesa.toString());
                    if(piesa.getCantitate()>0)
                        listPiese.add(piesa);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return FXCollections.observableArrayList(listPiese);
    }
    public void onActionT(ActionEvent actionEvent) throws Exception {
        System.out.println("Apasat");
        for (Piesa x : lstComanda)
            work.removeID("id",x.getId());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("meniuPrincipal.fxml"));
        anchorPiese.getChildren().setAll(pane);

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

        tbMarcaPiesa.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbModelPiesa.setCellValueFactory(new PropertyValueFactory<>("Model"));
        tbVersiunePiesa.setCellValueFactory(new PropertyValueFactory<>("Versiune"));
        tbDenumirePiesa.setCellValueFactory(new PropertyValueFactory<>("Denumire"));
        tbPretPiesa.setCellValueFactory(new PropertyValueFactory<>("Pret"));
        tbBrandPiesa.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        tbDescrierePiesa.setCellValueFactory(new PropertyValueFactory<>("Descriere"));
        tbCantitatePiesa.setCellValueFactory(new PropertyValueFactory<>("Cantitate"));
        //Comanda
        tbComandaDenumire.setCellValueFactory(new PropertyValueFactory<>("Denumire"));
        tbComandaPret.setCellValueFactory(new PropertyValueFactory<>("Pret"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableCell();
        chMarca.setItems(initializareChoiceBox(citireFisier("C:\\Users\\andre\\OneDrive\\Desktop\\marca.txt")));
        chCategorie.setItems(initializareChoiceBox(new ArrayList<>(mapCategoriiPiese.keySet())));
        tbPiese.setItems(filtru);//Set the table's items using the filtered list
        txtCauta.textProperty().addListener((observable, oldValue, newValue) -> {
          //  if(txtCauta.getText().contains())

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

    public void listPieseEvent(MouseEvent mouseEvent) throws SQLException {



        if (mouseEvent.getClickCount() == 2) {
            Piesa selectedItem = tbPiese.getSelectionModel().getSelectedItem();
            int cantitate=selectedItem.getCantitate();
            if(cantitate>0){
                lstComanda.add(selectedItem);
                ObservableList<Piesa> list = FXCollections.observableList(lstComanda);
                tbComanda.setItems(list);
                pretComanda += selectedItem.getPret();
                lblPret.setText(String.format("%.2f", pretComanda) + " LEI");

                cantitate--;
                work.update("UPDATE piese SET cantitate_disponibila= "+cantitate +" WHERE id="+selectedItem.getIdPiesa());

            System.out.println(cantitate+" "+selectedItem.getIdPiesa());

                 tbPiese.setItems( getListPieste1());
        }}

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
           // filtru.setPredicate(p -> (p.getAn() <= Integer.parseInt(chAnMare.getValue().toString())) && (p.getAn() >= Integer.parseInt(chAnMic.getValue().toString())));


        } catch (Exception e) {
        }

    }

    public void chMarcaOnAction(ActionEvent actionEvent) {
        filtru.setPredicate(p -> p.getMarca().toLowerCase().contains(chMarca.getValue().equals("-Toate-")?"":chMarca.getValue().toLowerCase().trim()));//filter table by first name

    }

    public void chModelOnAction(MouseEvent mouseEvent) {
        filtru.setPredicate(p -> p.getModel().toLowerCase().contains(chModel.getValue().toLowerCase().trim()));//filter table by first name

    }

    public void btnCautaOnAction(ActionEvent actionEvent) {
    }

    public void chCategorieOnAction(ActionEvent actionEvent) {
        chSubcategorie.setItems(initializareChoiceBox(mapCategoriiPiese.get(chCategorie.getValue())));
    }
}