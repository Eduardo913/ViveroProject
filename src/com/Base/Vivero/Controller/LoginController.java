package com.Base.Vivero.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.Base.Vivero.Daos.DaoUsuario;
import com.Base.Vivero.Entity.Usuario;
import com.Base.Vivero.Model.ConexionHibernate;
import com.Base.Vivero.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

    @FXML
    private RadioButton radButtonMariadb;

    @FXML
    private RadioButton radButtonPostgresql;

    @FXML
    private RadioButton radButtonServer;

    @FXML
    private TextField txtUsuario;

    @FXML
    private Button buttonIniciar;

    @FXML
    private PasswordField txtContraseña;
    
    private String nameFile;
    
    private DaoUsuario daoUsuario;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

    @FXML
    void OnActionIniciar(ActionEvent event) {
    	if(radButtonMariadb.isSelected() || radButtonPostgresql.isSelected() || radButtonServer.isSelected()) {
    		ConexionHibernate.setDriver(nameFile);
    		daoUsuario = new DaoUsuario();
    		String usuario = txtUsuario.getText();
    		String contraseña = txtContraseña.getText();
    		
    		if(daoUsuario.authUser(usuario, contraseña)) {
    			cambiarScene("Productos");
    		}
    	}else {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Se debe seleccionar un Manejador");
			alert.showAndWait();
		}
    }
    
    @FXML
    void OnActionMariadb(ActionEvent event) {
    	cleanSelect(1);
    	nameFile = "mariadb";
    }

    @FXML
    void OnActionPostgresql(ActionEvent event) {
    	cleanSelect(3);
    	nameFile = "postgresql";
    }

    @FXML
    void OnActionServer(ActionEvent event) {
    	cleanSelect(2);
    	nameFile = "sqlserver";
    }

    
    private void cleanSelect(int i) {
		
		switch (i) {
			case 1:
				
				radButtonPostgresql.setSelected(false);
				radButtonServer.setSelected(false);
				break;
			case 2:
				radButtonMariadb.setSelected(false);
				radButtonPostgresql.setSelected(false);
				break;
			case 3:
				radButtonMariadb.setSelected(false);
				radButtonServer.setSelected(false);
				break;
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

}
