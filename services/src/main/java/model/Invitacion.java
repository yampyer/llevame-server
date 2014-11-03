package model;

public class Invitacion extends Evento {

	//PENDIENTE = false		ACEPTADO = true
	private boolean aceptado;
	private Integer tipo;
	private Integer idRef; //id de quien envia la invitacion
	private Integer idRef2; //id de la ruta en caso de invitacion a ruta

	public Invitacion(){
		super();
		esNotificacion=false;
	}
	
	public Invitacion(int id, String msj, int idUsr, boolean aceptado, int tipo, Integer idRef){
		super(id, msj, idUsr, false);
		this.aceptado = aceptado;
		this.setTipo(tipo);
		this.setIdRef(idRef);
		setEsNotificacion(false);
	}
	
	public Invitacion(int id, String msj, int idUsr, boolean aceptado, int tipo,
			Integer idRef, Integer idRef2){
		
		super(id, msj, idUsr, false);
		this.aceptado = aceptado;
		this.setTipo(tipo);
		this.setIdRef(idRef);
		this.setIdRef2(idRef2);
		setEsNotificacion(false);
	}
	
	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getIdRef() {
		return idRef;
	}

	public void setIdRef(Integer idRef) {
		this.idRef = idRef;
	}

	public Integer getIdRef2() {
		return idRef2;
	}

	public void setIdRef2(Integer idRef2) {
		this.idRef2 = idRef2;
	}
	
}

