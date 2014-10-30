package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Evento;
import model.Invitacion;
import model.Notificacion;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class EventoDAO {

	public static String TABLA_EVENTOS = "Eventos";
	public static String ID = "idEvento";
	public static String MENSAJE = "mensaje";
	public static String ESTADO = "aceptado";
	public static String ID_USUARIO = "idUsuario";
	public static String ENUM = "enum"; //tipo de invitacion
	public static String ID_REF = "idRef"; //objeto al que hace referencia a la invitacion
	
	public static Evento crearEvento(Invitacion inv){
		String esquemaEvento = "INSERT INTO "+TABLA_EVENTOS
				+" ("+MENSAJE+", "
				+ ESTADO+", "
				+ ID_USUARIO+", "
				+ ENUM+", "
				+ ID_REF;
		
		int aceptado = inv.isAceptado()? 1 : ((inv.getTipo()==null)?  -1 : 0);
		final String sql = esquemaEvento
				+") VALUES ('"
				+ inv.getMensaje()+"', "
				+ aceptado+", "
				+ inv.getIdUsuario()+", "
				+ inv.getTipo()+", "
				+ inv.getIdRef()+");";
				
			
		KeyHolder keyHolder = new GeneratedKeyHolder();
			
		DataBaseHandler.getInstance().getTemplate().update(new PreparedStatementCreator() {
				
			@Override
			public PreparedStatement createPreparedStatement(Connection c)
					throws SQLException {
				return c.prepareStatement(sql);
			}
		}, keyHolder);
			
		int id = keyHolder.getKey().intValue();
		
		inv.setId(id);
		
		return inv;
		
	}
	
	
	
	public static List<Evento> fetchListaEventos(int id){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ EventoDAO.TABLA_EVENTOS + " "
						+ "WHERE "+ID_USUARIO+" = "+id+";",
						new SimpleEventoMapper());
	}
	
	public static void cambiarEstado(int id, boolean aceptado) throws Exception{
		if(!esInvitacion(id)){
			throw new Exception("evento con id:"+id+" no es una invitacion");
		} else {
			int estado = (aceptado)? 1 : 0;
			DataBaseHandler.getInstance().getTemplate()
				.update("UPDATE "+TABLA_EVENTOS+" SET "+ESTADO+" = "+estado+" WHERE "+ID+" = "+id);
		}
	}
	
	public static void eliminarEvento(int id){
		DataBaseHandler.getInstance().getTemplate()
			.execute("DELETE FROM "+TABLA_EVENTOS+" WHERE "+ID+" = "+id);
	}
	
	public static boolean esInvitacion(int id){
		Boolean b = getEstado(id);
		
		return (b!=null);
	}
	
	
	public static boolean getEstado(int id){
		return DataBaseHandler.getInstance().getTemplate()
			.query("SELECT "+ESTADO+" FROM "+TABLA_EVENTOS+" WHERE "+ID+"="+id,
					new BooleanMaper()).get(0);
	}
}


final class SimpleEventoMapper implements org.springframework.jdbc.core.RowMapper<Evento> {

	@Override
	public Evento mapRow(ResultSet res, int rowNum) throws SQLException {
		int estado = res.getInt(EventoDAO.ESTADO);
//		System.out.println(estado);
		Evento evento;
		if (estado!=1 && estado!=0){
			evento = new Notificacion(res.getInt(EventoDAO.ID), 
					res.getString(EventoDAO.MENSAJE), res.getInt(EventoDAO.ID_USUARIO));
		} else {
			boolean b = (estado==1);//0->false	1->true
			evento = new Invitacion(res.getInt(EventoDAO.ID), 
					res.getString(EventoDAO.MENSAJE), res.getInt(EventoDAO.ID_USUARIO), 
					b, res.getInt(EventoDAO.ENUM), res.getInt(EventoDAO.ID_REF));
		}
		
		
		return evento;
	}
}

final class BooleanMaper implements org.springframework.jdbc.core.RowMapper<Boolean> {

	@Override
	public Boolean mapRow(ResultSet res, int rowNum) throws SQLException {
		int estado = res.getInt(EventoDAO.ESTADO);
		if (estado!=1 && estado!=0){
			return null;
		} else {
			return (estado==1);//0->false	1->true
		}
	}
}


