package model;

public class Usuario {

	private String username;
	private String password;
	private int puntos;
	
	public Usuario(String username, String password){
		this.username = username;
		this.password = password;
		puntos = 0;
	}
	
	public Usuario(String username, String password, int puntos){
		this.username = username;
		this.password = password;
		this.puntos = puntos;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}
