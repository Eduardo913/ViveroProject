package com.Base.Vivero.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.Base.Vivero.Daos.DaoCalendario;
import com.Base.Vivero.Daos.DaoFotografia;
import com.Base.Vivero.Daos.DaoHistorial;
import com.Base.Vivero.Daos.DaoProducto;
import com.Base.Vivero.Entity.Producto;

import javafx.fxml.Initializable;

public class RootController implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//DaoFotografia dao = new DaoFotografia();
		//DaoHistorial dao1 = new DaoHistorial();
		//DaoCalendario dao2 = new DaoCalendario();
		
		DaoProducto dao = new DaoProducto();
		
		//System.out.println(dao1.getHistorial());
		//System.out.println(dao2.getCalendario());
		//System.out.println(dao3.getProducto());
		//System.out.println(dao.getFotografia()); 
		//dao3.addProducto();
		//dao1.addHistorial();
		//dao.addFotografia();
		//dao2.addCalendario();
		
		/*Producto prod = new Producto();
		prod.setId(11);
		prod.setTipo("Arbustos");
		prod.setCondicionActual("Con Plaga");*/
		
		//dao.deleteProducto(prod);
		//dao.updatePoducto(prod);
	}
	
}
