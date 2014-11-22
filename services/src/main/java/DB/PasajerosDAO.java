package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.omg.PortableServer.ID_UNIQUENESS_POLICY_ID;

import model.Pasajero;
import model.Ruta;
import model.Usuario;

public class PasajerosDAO {

	public static final String TABLE_PASAJEROS = "pasajeros";
	public static final String ID_USUARIO = "idUsuario";
	public static final String ID_RUTA = "idRuta";
	public static final String ID_UBICACION = "idUbicacion";
	
	
	public static void crearPasajero(int idRuta, int idUsuario, int idUbicacion) {
		DataBaseHandler.getInstance().getTemplate().update("INSERT INTO "+PasajerosDAO.TABLE_PASAJEROS
				+" VALUES ( "
				+ idRuta + ", "
				+ idUsuario + ", "
				+ idUbicacion
				+" );"
				);
	}
	
	public static void borrarPasajero(int idRuta, int idUsuario){
		DataBaseHandler.getInstance().getTemplate().execute("DELETE FROM "+TABLE_PASAJEROS+
				" WHERE "+ID_RUTA+" = "+idRuta+" AND "+ID_USUARIO+" = "+idUsuario);
	}
	
	public static List<Pasajero> fetchPasajeros(int idRuta){
		String sql = "SELECT * "
				+ "FROM " + TABLE_PASAJEROS + " JOIN " + UsuarioDAO.TABLE_USUARIO 
					+ " USING ("+ID_USUARIO+")"
				+ " WHERE " + ID_RUTA + " = " + idRuta;
		
		return DataBaseHandler.getInstance().getTemplate().query(sql, new PasajeroMapper());
	}
	
	public static int fetchNumberPasajeros(int idRuta){
		return fetchPasajeros(idRuta).size();
	}
	
	
}

final class PasajeroMapper implements org.springframework.jdbc.core.RowMapper<Pasajero> {

	@Override
	public Pasajero mapRow(ResultSet res, int rowNum) throws SQLException {
		Pasajero p = new Pasajero(res.getInt(UsuarioDAO.ID), 
				res.getString(UsuarioDAO.USERNAME), 
				res.getString(UsuarioDAO.PASSWORD), 
				res.getInt(UsuarioDAO.PUNTOS),
				res.getInt(PasajerosDAO.ID_UBICACION));
		
		return p;
	}
}
