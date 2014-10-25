package model;

public class Vehiculo {

	private String placa;
	private int owner; //id del dueño
	
	public Vehiculo(){
		
	}
	
	public Vehiculo(String placa, int owner){
		this.placa = placa;
		this.owner = owner;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	
}
