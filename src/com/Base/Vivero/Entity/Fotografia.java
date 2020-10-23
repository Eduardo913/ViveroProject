package com.Base.Vivero.Entity;

public class Fotografia {

	private Integer id;
	private String foto;
	private Historial historial;
	
	public Fotografia() {
	}
	
	public Fotografia(Integer id, String foto, Historial historial) {
		super();
		this.id = id;
		this.foto = foto;
		this.historial = historial;
	}

	public Integer getId() {
		return id;
	}

	public String getFoto() {
		return foto;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}

	@Override
	public String toString() {
		return "Fotografia [id=" + id + ", foto=" + foto + ", historial=" + historial + "]";
	}
	
	
}
