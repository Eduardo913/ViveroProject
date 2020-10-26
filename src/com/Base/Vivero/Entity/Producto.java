package com.Base.Vivero.Entity;


import java.util.Date;
import java.util.List;

public class Producto{
	
	private Integer id;
	private String tipo;
	private String nombre;
	private String condicionActual;
	private Date fechaDeIngreso;
	
	private List<Calendario> calendarioList;
	private List<Historial> historialList;
	


	public Producto() {
		
	}
	
	
	public Producto(String tipo, String nombre,String condicionActual, Date fechaDeIngreso) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.fechaDeIngreso = fechaDeIngreso;
		this.condicionActual = condicionActual;
	}


	public int getId() {
		return id;
	}
	public String getTipo() {
		return tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}
	public String getCondicionActual() {
		return condicionActual;
	}
	public void setCondicionActual(String condicionActual) {
		this.condicionActual = condicionActual;
	}

	public List<Calendario> getCalendarioList() {
		return calendarioList;
	}


	public List<Historial> getHistorialList() {
		return historialList;
	}


	public void setCalendarioList(List<Calendario> calendarioList) {
		this.calendarioList = calendarioList;
	}


	public void setHistorialList(List<Historial> historialList) {
		this.historialList = historialList;
	}

	@Override
	public String toString() {

		return nombre;
	}
	
	
	
}
