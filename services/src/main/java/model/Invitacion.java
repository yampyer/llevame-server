package model;

public class Invitacion extends Evento {

	//PENDIENTE = false		ACEPTADO = true
	private boolean aceptado;

	public Invitacion(){
		super();
	}
	
	public Invitacion(int id, String msj, int idUsr, boolean aceptado){
		super(id, msj, idUsr);
		this.aceptado = aceptado;
	}
	
	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}
	
	
	
}

