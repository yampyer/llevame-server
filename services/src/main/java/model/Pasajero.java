package model;

public class Pasajero extends Usuario {
	
	private int idUbicacion;

	public Pasajero(){
		super();
	}
	
	public Pasajero(int id, String username, String password, int puntos, int idUbicacion){
		super(id, username, password, puntos);
		this.idUbicacion = idUbicacion;
	}
	
	public int getPickUp() {
		return idUbicacion;
	}

	public void setPickUp(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	
	
	
}
