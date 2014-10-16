package model;

public class Vehiculo {

	private String placa;
	private Usuario user;
	
	public Vehiculo(String placa, Usuario user){
		this.placa = placa;
		this.user = user;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	
	
}
