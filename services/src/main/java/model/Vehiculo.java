package model;

public class Vehiculo {

	private String placa;
	private Usuario owner;
	
	
	public Vehiculo(String placa, Usuario owner){
		this.placa = placa;
		this.owner = owner;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Usuario getUser() {
		return owner;
	}
	public void setUser(Usuario owner) {
		this.owner = owner;
	}
	
	
}
