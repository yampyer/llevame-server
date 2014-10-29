package model;

public class Invitacion extends Evento {

	//PENDIENTE = false		ACEPTADO = true
	private boolean aceptado;
	private int tipo;
	private int id_ref;

	public Invitacion(){
		super();
	}
	
	public Invitacion(int id, String msj, int idUsr, boolean aceptado, int tipo, int id_ref){
		super(id, msj, idUsr);
		this.aceptado = aceptado;
		this.setTipo(tipo);
		this.setId_ref(id_ref);
	}
	
	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getId_ref() {
		return id_ref;
	}

	public void setId_ref(int id_ref) {
		this.id_ref = id_ref;
	}
	
	
	
}

