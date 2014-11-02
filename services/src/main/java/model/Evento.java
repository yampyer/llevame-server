package model;

public abstract class Evento {

	private String mensaje;
	private int id;
	private int idUsuario;
	protected boolean esNotificacion;

	public Evento(){
		
	}
	
	public Evento(int id, String msj, int idUsr, boolean notificacion){
		this.id = id;
		mensaje = msj;
		idUsuario = idUsr;
		esNotificacion = notificacion;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public boolean getEsNotificacion(){
		return esNotificacion;
	}
	
	public void setEsNotificacion(Boolean esNotificacion){
		this.esNotificacion = esNotificacion;
	}
	
}
