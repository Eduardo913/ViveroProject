package com.Base.Vivero.Entity;

import java.util.Date;
import java.util.List;

public class Historial {

	private Integer id;
	private Producto producto;
	private Date fecha;
	private List<Fotografia> fotografiaList;
	
	
	

	public Historial() {
	}
	
	public Historial(Integer id, Producto producto, Date fecha) {
		super();
		this.id = id;
		this.producto = producto;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
	}

	public Producto getProducto() {
		return producto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return producto+"";
	}
	
	public List<Fotografia> getFotografiaList() {
		return fotografiaList;
	}

	public void setFotografiaList(List<Fotografia> fotografiaList) {
		this.fotografiaList = fotografiaList;
	}
	
}
