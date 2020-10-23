package com.Base.Vivero.Entity;

import java.util.Date;

public class Calendario {

	private Integer id;
	private Producto producto;
	private Date fecha;
	
	public Calendario() {
	}
	
	public Calendario(Integer id, Producto producto, Date fecha) {
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
		return "Calendario [id=" + id + ", producto=" + producto + ", fecha=" + fecha + "]";
	}
	
	
	
}
