package DB;

import java.util.List;

import model.Usuario;

public class PasajerosDAO {

	public static final String TABLE_PASAJEROS = "pasajeros";
	public static final String ID_USUARIO = "idUsuario";
	public static final String ID_RUTA = "idRuta";
	
	
	public static void crearPasajero(int idRuta, int idUsuario) {
		DataBaseHandler.getInstance().getTemplate().update("INSERT INTO "+PasajerosDAO.TABLE_PASAJEROS
				+" VALUES ( "
				+ idRuta + ", "
				+ idUsuario
				+" );"
				);
	}
	
	public static List<Usuario> fetchPasajeros(int idRuta){
		String sql = "SELECT * "
				+ "FROM " + TABLE_PASAJEROS + " JOIN " + UsuarioDAO.TABLE_USUARIO 
					+ " USING ("+ID_USUARIO+")"
				+ " WHERE " + ID_RUTA + " = " + idRuta;
		
		return DataBaseHandler.getInstance().getTemplate().query(sql, new UsuarioMapper());
	}
	
	public static int fetchNumberPasajeros(int idRuta){
		return fetchPasajeros(idRuta).size();
	}
}
