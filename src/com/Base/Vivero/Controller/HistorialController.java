package com.Base.Vivero.Controller;

import com.Base.Vivero.Daos.DaoHistorial;
import com.Base.Vivero.Daos.DaoProducto;
import com.Base.Vivero.Entity.Historial;
import com.Base.Vivero.Entity.Producto;
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

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class HistorialController implements Initializable {

    private DaoProducto daoProducto;

    private DaoHistorial daoHistorial;

    private Historial h;

    private Producto seleccion;

    @FXML
    private TextField IdEliminar;

    @FXML
    private RadioButton radButtonAgregar;

    @FXML
    private RadioButton radButtonModificar;

    @FXML
    private RadioButton radButtonEliminar;

    @FXML
    private AnchorPane agregar;

    @FXML
    private AnchorPane eliminar;

    private ObservableList<Historial> productos;

    @FXML
    private TableView<Historial> tbProductos;

    @FXML
    private TableColumn<Historial, Producto> colProducto;

    @FXML
    private TableColumn<Historial, Date> colFecha;

    @FXML
    private Button buttonAgregarModificar;

    @FXML
    private DatePicker dateSearch;

    @FXML
    private ChoiceBox<String> boxProducto;

    @FXML
    void OMCgoFotografias(MouseEvent event) {
        cambiarScene("Fotografias");
    }

    @FXML
    void OMCgoProducts(MouseEvent event) {
        cambiarScene("Productos");
    }

    @FXML
    void OMNgoReporte(MouseEvent event) {
        cambiarScene("Historial");
    }

    @FXML
    void ONMgoCalendar(MouseEvent event) {
        cambiarScene("Calendario");
    }

    @FXML
    void OnActionDelete(ActionEvent event) {

    }

    @FXML
    void OnActionAddAndUpdate(ActionEvent event) {
        if (radButtonModificar.isSelected()) {

        } else {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            h.setFecha(Date.from(dateSearch.getValue().atStartOfDay(defaultZoneId).toInstant()));
        }

    }

    @FXML
    void OnMouseClickedGetProduct(MouseEvent event) {

    }

    @FXML
    void OMCProductos(MouseEvent event) {
        cambiarScene("Productos");
    }

    @FXML
    void OnActionAgregar(ActionEvent event) {
        cleanSelect(1);


    }


    @FXML
    void OnActionEliminar(ActionEvent event) {
        cleanSelect(2);

    }


    @FXML
    void OnActionModificar(ActionEvent event) {
        cleanSelect(3);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        daoHistorial = new DaoHistorial();
        productos = FXCollections.observableArrayList();
        this.colProducto.setCellValueFactory(new PropertyValueFactory("producto"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
//        boxProducto.getItems().addAll(daoProducto.getProductoName());
        tbProductos.setItems(productos);
        llenarTabla();
    }

    private void llenarTabla() {
        productos.clear();
        productos.addAll(daoHistorial.getHistorial());

    }

    private void cambiarScene(String fxml) {
        try {
            Main.setFXML(fxml);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void cleanSelect(int i) {
        switch (i) {
            case 1:
                radButtonEliminar.setSelected(false);
                radButtonModificar.setSelected(false);
                eliminar.setVisible(false);
                agregar.setVisible(true);
                break;
            case 2:
                radButtonModificar.setSelected(false);
                radButtonAgregar.setSelected(false);
                eliminar.setVisible(true);
                agregar.setVisible(false);
                break;
            case 3:
                radButtonEliminar.setSelected(false);
                radButtonAgregar.setSelected(false);
                eliminar.setVisible(false);
                agregar.setVisible(true);
                break;

        }

    }

    private void cleanCamp() {
    }
}
