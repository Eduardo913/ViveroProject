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
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HistorialController implements Initializable {


    private DaoHistorial daoHistorial;

    private Historial h;

    private Producto seleccion;


    @FXML
    private Label textModificar;

    @FXML
    private Label prodcutoModificar;


    @FXML
    private Label idEliminar;

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
    private ChoiceBox<Producto> boxProducto;

    @FXML
        //listo
    void OMCgoFotografias(MouseEvent event) {
        cambiarScene("Fotografias");
    }

    @FXML
        //liato
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

    @FXML
    void OMCProductos(MouseEvent event) {
        cambiarScene("Productos");
    }

    @FXML
    void OnActionDelete(ActionEvent event) {
        daoHistorial.deleteHistorial(h);
        llenarTabla();

    }//listo el borrar historial

    @FXML
    void OnMouseClickedGetProduct(MouseEvent event) {
        h = tbProductos.getSelectionModel().getSelectedItem();
        idEliminar.setText(h.getProducto().getNombre());
        prodcutoModificar.setText(h.getProducto().getNombre());
        System.out.println(h);

    } //listo la obtencion de los productos

    @FXML
    void OnActionAddAndUpdate(ActionEvent event) {
        Date fecha;
        ZoneId defaultZoneId = ZoneId.systemDefault();
        if (radButtonModificar.isSelected()) {
            System.out.println("modificar");
            fecha = Date.from(dateSearch.getValue().atStartOfDay(defaultZoneId).toInstant());
            h.setProducto(boxProducto.getValue());
            h.setFecha(fecha);
            daoHistorial.modifiedHistorial(h);
            llenarTabla();
            llenarbuton();
        } else {
            Historial hA = new Historial();

            System.out.println("agregar");
            hA.setId(1);
            fecha = Date.from(dateSearch.getValue().atStartOfDay(defaultZoneId).toInstant());
            hA.setFecha(fecha);
            hA.setProducto(boxProducto.getValue());
            daoHistorial.addHistorial(hA);
            llenarTabla();
            llenarbuton();
        }

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
        tbProductos.setItems(productos);
        llenarTabla();
        llenarbuton();
    }

    private void llenarTabla() {

        productos.clear();
        productos.addAll(daoHistorial.getHistorial());


    }

    public void llenarbuton() {
        boxProducto.getItems().clear();
        dateSearch.getEditor().clear();
        List<Historial> h = daoHistorial.getHistorial();
        for (Historial hx : h) {
            boxProducto.getItems().add(hx.getProducto());
        }
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

                textModificar.setVisible(false);
                prodcutoModificar.setVisible(false);
                radButtonEliminar.setSelected(false);
                radButtonModificar.setSelected(false);
                eliminar.setVisible(false);
                agregar.setVisible(true);
                break;
            case 2:

                textModificar.setVisible(false);
                prodcutoModificar.setVisible(false);
                radButtonModificar.setSelected(false);
                radButtonAgregar.setSelected(false);
                eliminar.setVisible(true);
                agregar.setVisible(false);
                break;
            case 3:

                textModificar.setVisible(true);
                prodcutoModificar.setVisible(true);
                radButtonEliminar.setSelected(false);
                radButtonAgregar.setSelected(false);
                eliminar.setVisible(false);
                agregar.setVisible(true);
                break;

        }

    }

}
