package DB;

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
	
}
