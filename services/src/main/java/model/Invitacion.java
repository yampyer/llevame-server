package model;

public class Invitacion extends Evento {

	public static final int RUTA = 0;
	public static final int USUARIO = 1;
	public static final int COMUNIDAD = 2;
	
	//PENDIENTE = false		ACEPTADO = true
	private boolean aceptado;
	private Integer tipo;
	private Integer idRef; //id de quien envia la invitacion
	private Integer idRef2; //id de la ruta en caso de invitacion a ruta
	private Integer idRef3; //id de la ubicacion en que se recoge al pasajero

	public Invitacion(){
		super();
		esNotificacion=false;
	}
	
	public Invitacion(int id, String msj, int idUsr, boolean aceptado, int tipo, Integer idRef){
		super(id, msj, idUsr, false);
		this.aceptado = aceptado;
		this.tipo = tipo;
		this.idRef = idRef;
		setEsNotificacion(false);
	}
	
	public Invitacion(int id, String msj, int idUsr, boolean aceptado, int tipo,
			Integer idRef, Integer idRef2, Integer idRef3){
		
		super(id, msj, idUsr, false);
		this.aceptado = aceptado;
		this.tipo = tipo;
		this.idRef = idRef;
		this.idRef2 = idRef2;
		this.setIdRef3(idRef3);
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

	public Integer getIdRef3() {
		return idRef3;
	}

	public void setIdRef3(Integer idRef3) {
		this.idRef3 = idRef3;
	}
	
}

