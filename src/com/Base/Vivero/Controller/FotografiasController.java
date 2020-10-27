package com.Base.Vivero.Controller;

import com.Base.Vivero.Daos.DaoFotografia;
import com.Base.Vivero.Daos.DaoHistorial;
import com.Base.Vivero.Entity.*;
import com.Base.Vivero.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FotografiasController implements Initializable {

    private DaoFotografia daoFotografia;

    private File imgFile;

    private ObservableList<Fotografia> fotos;

    private Fotografia h;


    @FXML
    private TextField urlPath;

    @FXML
    private Label modT;

    @FXML
    private Label modProd;

    @FXML
    private TableView<Fotografia> tbFotos;

    @FXML
    private ChoiceBox<Historial> productoN;

    @FXML
    private TableColumn<Fotografia, ?> colProducto;

    @FXML
    private TableColumn<Fotografia, String> colIMG;


    @FXML
    private AnchorPane agregar;

    @FXML
    private Button buttonAgregarModificar;


    @FXML
    private TextField idFotografia;


    @FXML
    private AnchorPane eliminar;

    @FXML
    private Button buttonEliminar;

    @FXML
    private TextField IdEliminar;

    @FXML
    private Label idFotografiaEliminar;

    @FXML
    private RadioButton radButtonAgregar;

    @FXML
    private RadioButton radButtonModificar;

    @FXML
    private RadioButton radButtonEliminar;

    @FXML
//listo
    void OMCProductos(MouseEvent event) {


    }

    @FXML
//listo
    void OMCagregarUrlfoto(ActionEvent event) {

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        imgFile = fileChooser.showOpenDialog(stage);
        urlPath.setText(imgFile.getAbsolutePath());
        System.out.println(imgFile);

    }

    @FXML
//listo
    void OMCgoFotografias(MouseEvent event) {
        cambiarScene("Fotografias");
    }

    @FXML
        //listo
    void OMCgoProducts(MouseEvent event) {
        cambiarScene("Productos");
    }

    @FXML
//listo
    void OMNgoReporte(MouseEvent event) {
        cambiarScene("Historial");
    }

    @FXML
//listo
    void ONMgoCalendar(MouseEvent event) {
        cambiarScene("Calendario");
    }

    @FXML//listo
    void OnActionDelete(ActionEvent event) {
        daoFotografia.deleteFotografia(h);
        llenarTabla();
        llenarbuton();


    }

    @FXML
    void OnActionAddAndUpdate(ActionEvent event) {
        if (radButtonAgregar.isSelected()) {

            h.setHistorial(productoN.getValue());
            h.setFoto(imgFile.getAbsolutePath());
            daoFotografia.addFotografia(h);
            llenarTabla();
            llenarbuton();
        } else {

            h.setHistorial(productoN.getValue());
            h.setFoto(imgFile.getAbsolutePath());
            daoFotografia.modifiedFotografia(h);
            llenarTabla();
            llenarbuton();

        }

    }

    @FXML//listo
    void OnMouseClickedGetProduct(MouseEvent event) {
        h = tbFotos.getSelectionModel().getSelectedItem();
        idFotografiaEliminar.setText(String.valueOf(h.getHistorial()));
        idFotografiaEliminar.setText(String.valueOf(h.getHistorial()));;
        modProd.setText(String.valueOf(h.getHistorial()));
        System.out.println(h);
    }

    @FXML
//listo
    void OnActionAgregar(ActionEvent event) {
        cleanSelect(1);

    }

    @FXML
//listo
    void OnActionEliminar(ActionEvent event) {
        cleanSelect(2);
    }

    @FXML
//listo
    void OnActionModificar(ActionEvent event) {
        cleanSelect(3);
    }

    //listo
    private void cambiarScene(String fxml) {
        try {
            Main.setFXML(fxml);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //listo
    private void cleanSelect(int i) {
        switch (i) {
            case 1:
                modProd.setVisible(false);
                modT.setVisible(false);
                radButtonEliminar.setSelected(false);
                radButtonModificar.setSelected(false);
                eliminar.setVisible(false);
                agregar.setVisible(true);
                break;
            case 2:
                modProd.setVisible(false);
                modT.setVisible(false);
                radButtonModificar.setSelected(false);
                radButtonAgregar.setSelected(false);
                eliminar.setVisible(true);
                agregar.setVisible(false);
                break;
            case 3:
                modProd.setVisible(true);
                modT.setVisible(true);
                radButtonEliminar.setSelected(false);
                radButtonAgregar.setSelected(false);
                eliminar.setVisible(false);
                agregar.setVisible(true);
                break;

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        daoFotografia = new DaoFotografia();
        fotos = FXCollections.observableArrayList();
        this.colProducto.setCellValueFactory(new PropertyValueFactory("historial"));
        this.colIMG.setCellValueFactory(new PropertyValueFactory("foto"));
        tbFotos.setItems(fotos);
        llenarTabla();
        llenarbuton();
    }

    private void llenarTabla() {
        urlPath.setText(null);
        fotos.clear();
        List<Fotografia> f = daoFotografia.getFotografia();
        fotos.addAll(f);


    }

    public void llenarbuton() {
        DaoHistorial daoHistorial = new DaoHistorial();
        productoN.getItems().clear();

        productoN.getItems().addAll(daoHistorial.getHistorial());


    }
}
