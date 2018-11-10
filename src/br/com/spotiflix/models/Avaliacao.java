package br.com.spotiflix.models;

public class Avaliacao {
	
	private long id;
	private long id_usuario;
	private long id_banda;
	private float nota;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public long getId_banda() {
		return id_banda;
	}
	public void setId_banda(long id_bandas) {
		this.id_banda = id_bandas;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}

}
