package DB;


public class Ruta_UbicacionDAO {
	public static final String TABLE_RUTA_UBICACION = "ruta_ubicacion";
	public static final String IDRUTA = "ruta";
	public static final String IDUBICACION = "idUbicacion";
	
	public static void agregarUbicacionARuta(int idUbicacion, int idRuta){
		DataBaseHandler.getInstance().getTemplate().update(""
			+ "INSERT INTO "+TABLE_RUTA_UBICACION+" VALUES("
			+ idRuta + ", "
			+ idUbicacion
			+ ");");
	}
	
	
	
	
}
