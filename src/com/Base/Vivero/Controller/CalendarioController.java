package com.Base.Vivero.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.Base.Vivero.Main;
import com.Base.Vivero.Daos.DaoCalendario;
import com.Base.Vivero.Daos.DaoProducto;
import com.Base.Vivero.Entity.Calendario;
import com.Base.Vivero.Entity.Historial;
import com.Base.Vivero.Entity.Producto;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CalendarioController implements Initializable{

    @FXML
    private Label LabelMes;

    @FXML
    private ImageView buttonArrowRigth;

    @FXML
    private ImageView buttonArrowLeft;

    @FXML
    private GridPane GridPaneFechas;

    @FXML
    private AnchorPane paneEliminar;

    @FXML
    private Label calendarioEliminar; 

    @FXML
    private Button buttonEliminar; //

    @FXML
    private AnchorPane paneAgregar;

    @FXML
    private ChoiceBox<Producto> boxProducto;

    @FXML
    private DatePicker dateRiego;

    @FXML
    private Button buttonAgregar;

    @FXML
    private AnchorPane paneLogo;

    @FXML
    private RadioButton radButtonAgregar;

    @FXML
    private RadioButton radButtonModificar;

    @FXML
    private RadioButton radButtonEliminar;
    
    @FXML
    private ListView<Calendario> listViewProductoRiegoModificar;
    
    @FXML
    private ListView<Calendario> listViewProductoRiegoEliminar;
    
    @FXML
    private ChoiceBox<Producto> boxProductoMod;

    @FXML
    private DatePicker DateFechaRiegoMod;
    
    @FXML
    private AnchorPane paneModificar;
    
    private DaoCalendario daoCalendario;
    
    private DaoProducto daoProducto;
    
    private VBox[][] diasCalendario = new VBox[7][5];
    
    private String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    
    private int mes;
    
    private int diaFinal;
    
    private int diaSemana;
    
    private int año;
    
    private ZoneId defaultZoneId;
    
    private Calendario calendario;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		daoCalendario = new DaoCalendario();
		daoProducto = new DaoProducto();
		
		mes=LocalDate.now().getMonthValue()-1;
		año = LocalDate.now().getYear();
		LabelMes.setText(meses[mes] +" "+ año);
		setCalendar();
		
		paneLogo.setVisible(true);
        paneAgregar.setVisible(false);
        paneEliminar.setVisible(false);
        paneModificar.setVisible(false);
        
        boxProducto.getItems().addAll(daoProducto.getProducto());
        boxProductoMod.getItems().addAll(daoProducto.getProducto());
        defaultZoneId = ZoneId.systemDefault();
		
	}
    
    //Agregar fecha riego
    @FXML
    void OnActionAdd(ActionEvent event) {
    	if(boxProducto.getValue() != null && dateRiego.getValue() != null) {
    		Calendario cal = new Calendario();
        	Producto prod = (Producto) boxProducto.getValue();
        	Date date = Date.from(dateRiego.getValue().atStartOfDay(defaultZoneId).toInstant());
        	cal.setFecha(date);
        	cal.setProducto(prod);
        	daoCalendario.addCalendario(cal);
        	cleanCamp();
        	setCalendar();
    	}
    	
    }

    @FXML
    void OnActionAgregar(ActionEvent event) {
    	 cleanSelect(1);
    	 cleanCamp();
    }

   
    // modificar objeto calendario
    @FXML
    void OnActionModificar(ActionEvent event) {
    	cleanSelect(2);
    	listViewProductoRiegoModificar.setDisable(true);
    	cleanCamp();
    }
    
    @FXML
    void OnMouseClickedRemplazar(MouseEvent event) {
    	if(calendario != null) {
    		calendario.setProducto(boxProductoMod.getValue());
    		calendario.setFecha(Date.from(DateFechaRiegoMod.getValue().atStartOfDay(defaultZoneId).toInstant()));
    		daoCalendario.updateCalendario(calendario);
    		cleanCamp();
        	setCalendar();
    	}
    }
    
    
    // eliminar feha de calendario
    @FXML
    void OnActionEliminar(ActionEvent event) {
    	 cleanSelect(3);
    	 listViewProductoRiegoEliminar.setDisable(true);
    	 cleanCamp();
    }
   
    @FXML
    void OnActionDelete(ActionEvent event) {
    	if(calendario != null) {
    		daoCalendario.deleteCalendario(calendario);
    		setCalendar();
    	}
    }
    
    
    
    // metodos de seleccion de listView and grid pane
    @FXML
    void ClickedGridPane(MouseEvent event) {
    	Node clickedNode = event.getPickResult().getIntersectedNode();
    	Integer colIndex = GridPaneFechas.getColumnIndex(clickedNode);
        Integer rowIndex = GridPaneFechas.getRowIndex(clickedNode);
    	if(colIndex != null && rowIndex != null) {
            cleanCamp();
            
            if(radButtonEliminar.isSelected() || radButtonModificar.isSelected()) {
            	listViewProductoRiegoEliminar.setDisable(false);
            	listViewProductoRiegoModificar.setDisable(false);
            	VBox auxBox = (VBox) getNodeByRowColumnIndex(rowIndex, colIndex, GridPaneFechas);
            	if(auxBox.getChildren().size()== 2) {
            		ListView<Calendario> auxListView= (ListView<Calendario>) auxBox.getChildren().get(1);
            		listViewProductoRiegoEliminar.getItems().addAll(auxListView.getItems());
            		listViewProductoRiegoModificar.getItems().addAll(auxListView.getItems());
            	}
            	
            }
    	}
    	
    }
    
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) { 
    	Node result = null; 
    	ObservableList<Node> childrens = gridPane.getChildren(); 
    	for (Node node : childrens) { 
    		if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) { 
    			result = node; 
    			break; }
    		} 
    	return result; 
    }
    
    @FXML
    void OnMouseClickedListRiego(MouseEvent event) {
    	if(radButtonEliminar.isSelected()) {
    		calendario = listViewProductoRiegoEliminar.getSelectionModel().getSelectedItem();
    		calendarioEliminar.setText(calendario.toString());
    	}else {
    		calendario = listViewProductoRiegoModificar.getSelectionModel().getSelectedItem();
    		Producto producto = calendario.getProducto();
    		boxProductoMod.setValue(producto);
    		DateFechaRiegoMod.setValue(calendario.getFecha().toInstant().atZone(defaultZoneId).toLocalDate());
    	}
    }
    
    
    //Botones para cambiar de mes en el calendario
    @FXML
    void OnMouseclickesDerecha(MouseEvent event) {
    	comprobarMes(1);
    	LabelMes.setText(meses[mes] +" "+ año);
    	limpiarGrid();
    	setCalendar();
    	resetLine();
    }

    @FXML
    void OnMouseclickesIzquierda(MouseEvent event) {
    	comprobarMes(2);
    	LabelMes.setText(meses[mes] +" "+ año);
    	limpiarGrid();
    	setCalendar();
    	resetLine();
    }

    
    
    // metodos para crear el calendario segun el mes
	private void setCalendar() {
		llenarCalenadrio();
		GridPaneFechas.getChildren().clear();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				GridPaneFechas.add(diasCalendario[i][j], i,j );
			}
		}
		resetLine();
		cleanCamp();
	}
	
	private void comprobarMes(int op) {
		
		if(op == 1) {
			mes++;
			if(mes>11) {
				mes =0;
				año++;
			}
		}else {
			mes--;
			if(mes<0) {
				mes=11;
				año--;
			}
		}
	}
	
	private void comprobarDia() {
		Calendar calendario = Calendar.getInstance();
		calendario.set(año, mes, 1);
		diaFinal=calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
		diaSemana = calendario.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	private void llenarCalenadrio() {
		comprobarDia();
		int j=diaSemana;
		int numero=1;
		diaFinal = diaFinal + (j-1);
		
		List calendarioList = daoCalendario.getCalendario();
		
		int aux= 0;
		
		for (int k = 0; k < 5; k++) {
			for (int i = 0; i < 7; i++) {
				if(aux >=j && aux <= diaFinal ) {
					VBox vBox = new VBox();
					vBox.setPrefSize(82, 83);
					Label diaNumero = new Label(""+numero++);
					vBox.getChildren().add(diaNumero);
					ListView<Calendario> auxList = recorrerLista(calendarioList, (numero-1));
					
					if(!auxList.getItems().isEmpty()) {
						
						vBox.getChildren().add(auxList);
					}
					diasCalendario[i][k]=vBox;
				}else {
					diasCalendario[i][k]=new VBox();
				}
				aux ++;
			}
		}
	
	}
	
	private ListView<Calendario> recorrerLista(List aux,int dianumero) {
		
		ListView<Calendario> productosListView = new ListView<Calendario>();
		productosListView.setPrefSize(82d, 83d);
		productosListView.setDisable(true);
		
		for (int i = 0; i < aux.size() ; i++) {
			Calendario calendario = (Calendario) aux.get(i);
			if( calendario.getFecha().getDate() == dianumero &&  calendario.getFecha().getMonth() == mes && (calendario.getFecha().getYear()+1900) == año) {
				productosListView.getItems().add(calendario);
			}
		}
		return productosListView;
	}
	
	
	
	//metodos de cada boton del Menu
	
	@FXML
  void OnMouseClickedVIstaCalendario(MouseEvent event) {
	  cambiarScene("Calendario");
    }

   @FXML
   void OnMouseClickedVIstaFotografia(MouseEvent event) {
	   cambiarScene("");
   }

   @FXML
   void OnMouseClickedVIstaProducto(MouseEvent event) {
	   cambiarScene("Productos");
   }

   @FXML
   void OnMouseClickedVIstaReporte(MouseEvent event) {
	   cambiarScene("");
   }
   
   // metodo para cambiar de scene
   private void cambiarScene(String fxml) {
    	try {
			Main.setFXML(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	

   // metodos de limpieza incluye, campos, listas , grid pane, etc
	private void limpiarGrid() {
		GridPaneFechas.getChildren().clear();
	}

	private void resetLine(){
		GridPaneFechas.setGridLinesVisible(false);
    	GridPaneFechas.setGridLinesVisible(true);
	}
	
   private void cleanSelect(int i) {
		
		switch (i) {
			case 1:
				paneLogo.setVisible(false);
		        paneAgregar.setVisible(true);
		        paneEliminar.setVisible(false);
		        paneModificar.setVisible(false);
				radButtonModificar.setSelected(false);
				radButtonEliminar.setSelected(false);
				break;
			case 2:
				paneLogo.setVisible(false);
		        paneAgregar.setVisible(false);
		        paneEliminar.setVisible(false);
		        paneModificar.setVisible(true);
				radButtonAgregar.setSelected(false);
				radButtonEliminar.setSelected(false);
				break;
			case 3:
				paneLogo.setVisible(false);
		        paneAgregar.setVisible(false);
		        paneEliminar.setVisible(true);
		        paneModificar.setVisible(false);
				radButtonAgregar.setSelected(false);
				radButtonModificar.setSelected(false);
				break;
		}
	}

   private void cleanCamp() {
		boxProducto.getSelectionModel().clearSelection();
		dateRiego.setValue(null);
		listViewProductoRiegoEliminar.getItems().clear();
		listViewProductoRiegoModificar.getItems().clear();
		calendarioEliminar.setText(null);
		boxProductoMod.getSelectionModel().clearSelection();
		DateFechaRiegoMod.setValue(null);
		calendario = null;
		//productoEliminar.setText(null);
	}
   
}
