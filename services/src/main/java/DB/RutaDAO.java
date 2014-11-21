package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import model.Ruta;

public class RutaDAO {
	
	//esquema de la BDs
	public static final String TABLE_RUTA = "Ruta";
	public static final String ID = "idRuta";
	public static final String NOMBRE_RUTA = "nombre";
	public static final String FECHA_HORA = "fecha";
	public static final String CAPACIDAD = "capacidad";
	public static final String DESCRIPCION = "descripcion";
	
	public static final String CONDUCTOR = "conductor";
	public static final String VEHICULO = "placaVehiculo";
	
	public static final String ESTADO = "estado";//0->pendiente 1->en progreso
	
	public static List<Ruta> fetchRutasList(){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ RutaDAO.TABLE_RUTA + ";",
						new DetailedRutaMapper());
	}
	
	public static List<Ruta> fetchRutasListPasajero(int idUsr){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ TABLE_RUTA 
							+ " JOIN "+PasajerosDAO.TABLE_PASAJEROS+" USING ("+ID+")"
							+ " WHERE "
							+ PasajerosDAO.TABLE_PASAJEROS+"."+PasajerosDAO.ID_USUARIO+" = "+idUsr+";",
						new DetailedRutaMapper());
	}
	
	public static List<Ruta> fetchRutasListConductor(int idUsr){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ TABLE_RUTA + " WHERE "
						+ CONDUCTOR + " = "+idUsr,
						new DetailedRutaMapper());
	}
	
	public static Ruta createRuta(Ruta ruta){
		String esquemaRuta = "INSERT INTO "+RutaDAO.TABLE_RUTA
				+ " ("+RutaDAO.NOMBRE_RUTA + ", "
				+ RutaDAO.FECHA_HORA + ", "
				+ RutaDAO.CAPACIDAD + ", "
				+ RutaDAO.DESCRIPCION + ", "
				+ RutaDAO.CONDUCTOR + ", "
				+ RutaDAO.VEHICULO + ", "
				+ RutaDAO.ESTADO;
		
		
		
		final String sql = esquemaRuta
				+ ") VALUES ('"
				+ ruta.getNombre() + "', '"
				+ ruta.getFecha() + "', "
				+ ruta.getCapacidad() + ", '"
				+ ruta.getDescripcion() + "', "
				+ ruta.getConductor()+", "
				+ "'"+ruta.getPlaca()+"',"
				+ "0);";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		DataBaseHandler.getInstance().getTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection c)
					throws SQLException {
				return c.prepareStatement(sql);
			}
		}, keyHolder);
		
		int id = keyHolder.getKey().intValue();
		
		ruta.setId(id);
		
		ruta.setEstado(false);
		return ruta;
	}
	
	public static Ruta fetchRuta(int id){
		String rutaSql = "SELECT * FROM " + RutaDAO.TABLE_RUTA
						+ " WHERE "+ RutaDAO.ID+ " = " + id;
		
		List<Ruta> rs = DataBaseHandler.getInstance().getTemplate()
			.query(rutaSql,new DetailedRutaMapper());
		
		if(rs.isEmpty()){
			System.err.println("ERROR: Nothing found in sql: " + rutaSql);
		}
		
		return rs.get(0);
	}
	
	public static void eliminarRuta(int id){
		DataBaseHandler.getInstance().getTemplate()
			.execute("DELETE FROM " + RutaDAO.TABLE_RUTA 
					+ " WHERE " + RutaDAO.ID + " = " + id + ";");
	}
	
	public static void cambiarEstadoRuta(boolean estado, int idRuta){
		int estadoNum = estado? 1 : 0;//true->1 false->0
		
		DataBaseHandler.getInstance().getTemplate()
			.update("UPDATE "+TABLE_RUTA+" SET "+RutaDAO.ESTADO+" = "+estadoNum+""
					+ " WHERE "+RutaDAO.ID+" = "+idRuta);
	}

	public static List<Ruta> getRutasAmigos(int usr) {
		//TODO: que no traiga rutas en las que soy pasajero
		
		String rutasAmigos = "SELECT * FROM "+ TABLE_RUTA + " JOIN "+ AmigosDAO.TABLE_AMIGOS
				+ " ON "+CONDUCTOR+" = "+AmigosDAO.AMIGO1
				+" AND "+AmigosDAO.AMIGO2+" = "+usr;
		
		String rutasComoPasajero = "SELECT "+ID+" FROM "+ TABLE_RUTA 
				+ " JOIN "+PasajerosDAO.TABLE_PASAJEROS+" USING ("+ID+")"
				+ " WHERE "
				+ PasajerosDAO.TABLE_PASAJEROS+"."+PasajerosDAO.ID_USUARIO+" = "+usr;
		
//		return DataBaseHandler.getInstance().getTemplate()
//				.query("SELECT *"
//					+ " FROM ("+rutasAmigos+") LEFT OUTER JOIN ("+(rutasComoPasajero)+")"
//							+ " USING ("+ID+") ",
//						new DetailedRutaMapper());
		
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT *"
					+ " FROM ("+rutasAmigos+") AS AMIGOS JOIN ("+(rutasComoPasajero)+") AS PASAJEROS"
							+ " WHERE AMIGOS."+ID+" != PASAJEROS."+ID+"",
						new DetailedRutaMapper());
	}
}



final class SimpleRutaMapper implements org.springframework.jdbc.core.RowMapper<Ruta> {

	@Override
	public Ruta mapRow(ResultSet res, int rowNum) throws SQLException {
		Ruta ruta = new Ruta(res.getInt(RutaDAO.ID), res.getString(RutaDAO.NOMBRE_RUTA),
				res.getString(RutaDAO.FECHA_HORA), res.getInt(RutaDAO.CAPACIDAD), 
				res.getString(RutaDAO.DESCRIPCION));
		
		
		return ruta;
	}
}

//SimpleRutaMaper + placa + idConductor
final class DetailedRutaMapper implements org.springframework.jdbc.core.RowMapper<Ruta> {

	@Override
	public Ruta mapRow(ResultSet res, int rowNum) throws SQLException {
		Ruta ruta = new Ruta(res.getInt(RutaDAO.ID), res.getString(RutaDAO.NOMBRE_RUTA),
				res.getString(RutaDAO.FECHA_HORA), res.getInt(RutaDAO.CAPACIDAD), 
				res.getString(RutaDAO.DESCRIPCION));
		
		ruta.setConductor(res.getInt(RutaDAO.CONDUCTOR));
		ruta.setPlaca(res.getString(RutaDAO.VEHICULO));
		ruta.setEstado(res.getBoolean(RutaDAO.ESTADO));
		return ruta;
	}
}