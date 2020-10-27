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

    @FXML
    private Label productoEliminar;

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

    private ObservableList<Historial> historiales;

    @FXML
    private TableView<Historial> tbHistorial;

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
    private AnchorPane paneLogo;
    
    ZoneId defaultZoneId;

    DaoProducto daoProducto;
   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        daoHistorial = new DaoHistorial();
        daoProducto = new DaoProducto();
        historiales = FXCollections.observableArrayList();
        this.colProducto.setCellValueFactory(new PropertyValueFactory("producto"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        tbHistorial.setItems(historiales);
        llenarTabla();
        defaultZoneId = ZoneId.systemDefault();
        paneLogo.setVisible(true);
        agregar.setVisible(false);
        eliminar.setVisible(false);
        
        boxProducto.getItems().addAll(daoProducto.getProducto());
        
        
    }

   //metodo de obtencion de datos

    @FXML
    void OnMouseClickedGetProduct(MouseEvent event) {
    	
    	if(radButtonEliminar.isSelected()) {
    		h = tbHistorial.getSelectionModel().getSelectedItem();
    		productoEliminar.setText(h.getProducto().getNombre());
    	}
    	if(radButtonModificar.isSelected()) {
    		h = tbHistorial.getSelectionModel().getSelectedItem();
    		boxProducto.getSelectionModel().select(h.getProducto());
    		boxProducto.setValue(h.getProducto());
    		dateSearch.setValue(h.getFecha().toInstant().atZone(defaultZoneId).toLocalDate());
    	}
       
        
    } //listo la obtencion de los productos

    
   

    //metodos para agregar y modificar
    @FXML
    void OnActionAgregar(ActionEvent event) {
        cleanSelect(1);
        cleanCamp();
    }
    
    @FXML
    void OnActionAddAndUpdate(ActionEvent event) {
        Date fecha;
        if (radButtonModificar.isSelected()) {
            fecha = Date.from(dateSearch.getValue().atStartOfDay(defaultZoneId).toInstant());
            h.setProducto(boxProducto.getValue());
            h.setFecha(fecha);
            daoHistorial.updateHistorial(h);
            llenarTabla();
        } else {
            Historial hA = new Historial();
            hA.setId(1);
            fecha = Date.from(dateSearch.getValue().atStartOfDay(defaultZoneId).toInstant());
            hA.setFecha(fecha);
            hA.setProducto(boxProducto.getValue());
            daoHistorial.addHistorial(hA);
            llenarTabla();
        }

    }

    @FXML
    void OnActionModificar(ActionEvent event) {
        cleanSelect(3);
        cleanCamp();
    }

    // metodo para eliminar
    @FXML
    void OnActionEliminar(ActionEvent event) {
        cleanSelect(2);
        cleanCamp();
    }

    @FXML
    void OnActionDelete(ActionEvent event) {
    	if(h != null) {
    		daoHistorial.deleteHistorial(h);
            llenarTabla();
    	}
       
    }
    
    // metodo para llenar la tabla con los datos
    private void llenarTabla() {

    	historiales.clear();
    	historiales.addAll(daoHistorial.getHistorial());


    }

   
    
    //metodos para limpiar
    //metodos de limpieza
    
    private void cleanSelect(int i) {
        switch (i) {
            case 1:
                radButtonEliminar.setSelected(false);
                radButtonModificar.setSelected(false);
                paneLogo.setVisible(false);
                eliminar.setVisible(false);
                agregar.setVisible(true);
                break;
            case 2:

                radButtonModificar.setSelected(false);
                radButtonAgregar.setSelected(false);
                paneLogo.setVisible(false);
                eliminar.setVisible(true);
                agregar.setVisible(false);
                break;
            case 3:

                radButtonEliminar.setSelected(false);
                radButtonAgregar.setSelected(false);
                paneLogo.setVisible(false);
                eliminar.setVisible(false);
                agregar.setVisible(true);
                break;

        }

    }
    
    private void cleanCamp() {
    	boxProducto.getSelectionModel().clearSelection();;
        dateSearch.getEditor().clear();
        productoEliminar.setText(null);
        h = null;
    }
    // metodos para cambiar de scene
    
    @FXML
    //listo
    void OMCgoFotografias(MouseEvent event) {
    	cambiarScene("Fotografias");
    }

    @FXML
    //liato
    void OMCgoProducts (MouseEvent event) {
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

    private void cambiarScene(String fxml) {
        try {
            Main.setFXML(fxml);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    

}
