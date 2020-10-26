package com.Base.Vivero.Controller;

import com.Base.Vivero.Daos.DaoFotografia;
import com.Base.Vivero.Entity.Fotografia;
import com.Base.Vivero.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class FotografiasController implements Initializable {

    private DaoFotografia daoFotografia;
    @FXML
    private TableView<?> tbProductos;

    @FXML
    private TableColumn<?, ?> colProducto;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private AnchorPane agregar;

    @FXML
    private Button buttonAgregarModificar;

    @FXML
    private TextField urlFotografia;

    @FXML
    private TextField IdHistoriall;

    @FXML
    private TextField idFotografia;


    @FXML
    private AnchorPane eliminar;

    @FXML
    private Button buttonEliminar;

    @FXML
    private TextField IdEliminar;

    @FXML
    private TextField idFotografiaEliminar;

    @FXML
    private RadioButton radButtonAgregar;

    @FXML
    private RadioButton radButtonModificar;

    @FXML
    private RadioButton radButtonEliminar;

    @FXML
    void OMCProductos(MouseEvent event) {



    }

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
    void OnActionAddAndUpdate(ActionEvent event) {
        if (radButtonAgregar.isSelected()){
            Fotografia fotografia = new Fotografia();
            fotografia.setFoto(urlFotografia.getText());
            fotografia.setId(Integer.parseInt(idFotografia.getText()));
        }else {

        }

    }

    @FXML
    void OnActionAgregar(ActionEvent event) {
        cleanSelect(1);

    }

    @FXML
    void OnActionDelete(ActionEvent event) {


    }

    @FXML
    void OnActionEliminar(ActionEvent event) {
        cleanSelect(2);
    }

    @FXML
    void OnActionModificar(ActionEvent event) {
        cleanSelect(3);
    }

    @FXML
    void OnMouseClickedGetProduct(MouseEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        daoFotografia = new DaoFotografia();


    }
}
