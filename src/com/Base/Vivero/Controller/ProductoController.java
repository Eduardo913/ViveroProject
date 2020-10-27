package com.Base.Vivero.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;

import java.util.ResourceBundle;

import com.Base.Vivero.Main;
import com.Base.Vivero.Daos.DaoProducto;
import com.Base.Vivero.Entity.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProductoController implements Initializable{
	
	@FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableColumn<?, ?> colCondicion;

    @FXML
    private TableColumn<?, ?> colFecha;
    
    @FXML
    private AnchorPane paneEliminar;

    @FXML
    private Label productoEliminar;

    @FXML
    private Button buttonEliminar;

    @FXML
    private AnchorPane paneAgregarModificar;

    @FXML
    private TextField txtNombre;

    @FXML
    private ChoiceBox<String> boxTipo;

    @FXML
    private DatePicker dateIngreso;

    @FXML
    private ChoiceBox<String> boxCondicon;

    @FXML
    private Button buttonAgregarModificar;

    @FXML
    private AnchorPane paneLogo;

    @FXML
    private RadioButton radButtonAgregar;

    @FXML
    private RadioButton radButtonModificar;

    @FXML
    private RadioButton radButtonEliminar;
    
    @FXML
    private TableView<Producto> tbProductos;
    
    private ObservableList<Producto> productos;
    
    
    private DaoProducto daoProducto;
    
    private Producto temp;
    
    private ZoneId defaultZoneId;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productos = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.colCondicion.setCellValueFactory(new PropertyValueFactory("condicionActual"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory("fechaDeIngreso"));
        tbProductos.setItems(productos);
        daoProducto = new DaoProducto();
        defaultZoneId = ZoneId.systemDefault();
        
        llenarTabla();
        
        paneLogo.setVisible(true);
        paneAgregarModificar.setVisible(false);
        paneEliminar.setVisible(false);
        
        boxCondicon.getItems().addAll(daoProducto.getCondicon());
        boxTipo.getItems().addAll(daoProducto.getTipos());
        
	}
	

    @FXML
    void OnActionAddAndUpdate(ActionEvent event) {
    	String nombre = txtNombre.getText();
    	String tipo = boxTipo.getValue();
    	String condicionActual = boxCondicon.getValue();
    	Date fecha =  Date.from(dateIngreso.getValue().atStartOfDay(defaultZoneId).toInstant());
    	
    	if(radButtonModificar.isSelected()) {
    		if(temp != null) {
    			temp.setNombre(nombre);
    			temp.setCondicionActual(condicionActual);
    			temp.setFechaDeIngreso(fecha);
    			temp.setTipo(tipo);
    			daoProducto.updatePoducto(temp);
    	    	llenarTabla();
    	    	cleanCamp();
    		}
    	}else {
			Producto prod = new Producto(tipo, nombre, condicionActual, fecha);
			daoProducto.addProducto(prod);
	    	llenarTabla();
	    	cleanCamp();
		}
    }


    @FXML
    void OnActionDelete(ActionEvent event) {
    	if(temp != null) {
    		daoProducto.deleteProducto(temp);
        	llenarTabla();	
    	}
    }
    
    @FXML
    void OnActionAgregar(ActionEvent event) {
        cleanSelect(1);
        cleanCamp();
    }
    
    @FXML
    void OnActionModificar(ActionEvent event) {
        cleanSelect(2);
        cleanCamp();
    }
    
    @FXML
    void OnActionEliminar(ActionEvent event) {
        cleanSelect(3);
        cleanCamp();
    }


    @FXML
    void OnMouseClickedGetProduct(MouseEvent event) {
    	cleanCamp();
    	if(radButtonModificar.isSelected()) {
    		temp = tbProductos.getSelectionModel().getSelectedItem();
    		txtNombre.setText(temp.getNombre());
    		boxCondicon.setValue(temp.getCondicionActual());
    		boxTipo.setValue(temp.getTipo());
    		dateIngreso.setValue(temp.getFechaDeIngreso().toInstant().atZone(defaultZoneId).toLocalDate());
    	}else {
    		if(radButtonEliminar.isSelected()) {
    			temp = tbProductos.getSelectionModel().getSelectedItem();
        		productoEliminar.setText(temp.getNombre());
    		}
		}
    }

	
	private void llenarTabla() {
		productos.clear();
		productos.addAll(daoProducto.getProducto());
		
	}
	
	private void cleanCamp() {
		txtNombre.clear();
		boxCondicon.getSelectionModel().clearSelection();
		boxTipo.getSelectionModel().clearSelection();
		dateIngreso.setValue(null);
		productoEliminar.setText(null);
	}
	
	private void cleanSelect(int i) {
		
		switch (i) {
			case 1:
				paneLogo.setVisible(false);
		        paneAgregarModificar.setVisible(true);
		        paneEliminar.setVisible(false);
				radButtonModificar.setSelected(false);
				radButtonEliminar.setSelected(false);
				break;
			case 2:
				paneLogo.setVisible(false);
		        paneAgregarModificar.setVisible(true);
		        paneEliminar.setVisible(false);
				radButtonAgregar.setSelected(false);
				radButtonEliminar.setSelected(false);
				break;
			case 3:
				paneLogo.setVisible(false);
		        paneAgregarModificar.setVisible(false);
		        paneEliminar.setVisible(true);
				radButtonAgregar.setSelected(false);
				radButtonModificar.setSelected(false);
				break;
		}
	}
	
	  @FXML
	    void OnMouseClickedVIstaCalendario(MouseEvent event) {
		  cambiarScene("Calendario");
	    }

	   @FXML
	   void OnMouseClickedVIstaFotografia(MouseEvent event) {
		   cambiarScene("Fotografias");
	   }

	   @FXML
	   void OnMouseClickedVIstaProducto(MouseEvent event) {
		   cambiarScene("Productos");
	   }

	   @FXML
	   void OnMouseClickedVIstaReporte(MouseEvent event) {
		   cambiarScene("Historial");
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
