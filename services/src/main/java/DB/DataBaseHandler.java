package DB;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataBaseHandler {
	private static DataBaseHandler INSTANCE;
	
	public final static String SQLITE_DRIVER = "org.sqlite.JDBC";
	public final static String DATABASE_URL = "jdbc:sqlite:llevaMe.db";
	
	private JdbcTemplate jdbcTemplate;
	private DriverManagerDataSource dataSource;
	
	private DataBaseHandler(){
		//establecer conexion
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(SQLITE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		createTables();
	}
	
	public static DataBaseHandler getInstance(){
		if(INSTANCE == null){
			INSTANCE = new DataBaseHandler();
		}
		
		return INSTANCE;
	}
	
	private void createTables(){
		//rutas
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + RutaDAO.TABLE_RUTA + " ("
				+ RutaDAO.ID + " INTEGER PRIMARY KEY NOT NULL, "
				+ RutaDAO.NOMBRE_RUTA + " TEXT, "
				+ RutaDAO.FECHA_HORA + " DATETIME, "
				+ RutaDAO.CAPACIDAD + " INT, "
				+ RutaDAO.DESCRIPCION + " TEXT "
				+ ");");
		
		//pasajeros (usuarios-rutas)
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + PasajerosDAO.TABLE_PASAJEROS + " ("
				+ PasajerosDAO.ID_RUTA + " INTEGER NOT NULL, "
				+ PasajerosDAO.ID_USUARIO + " INTEGER NOT NULL, PRIMARY KEY (" + PasajerosDAO.ID_RUTA 
				+ ", " + PasajerosDAO.ID_USUARIO+"));");
		
		//ubicacion
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + UbicacionDAO.TABLE_UBICACION + " ("
				+ UbicacionDAO.ID + " INTEGER NOT NULL PRIMARY KEY, "
				+ UbicacionDAO.NOMBRE + " TEXT, "
				+ UbicacionDAO.LATITUD + " REAL NOT NULL, "
				+ UbicacionDAO.LONGITUD + " REAL NOT NULL"
				+ ");");
		
		//Ruta-Ubicacion
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS "+Ruta_UbicacionDAO.TABLE_RUTA_UBICACION+" ("
				+ Ruta_UbicacionDAO.IDRUTA + " INTEGER NOT NULL, "
				+ Ruta_UbicacionDAO.IDUBICACION + " INTEGER NOT NULL, "
				+ "PRIMARY KEY("+Ruta_UbicacionDAO.IDRUTA+", "+Ruta_UbicacionDAO.IDUBICACION+")"
				+ ");");
		
		
		
		//TODO: crear el resto de la BDs
	}
	
	public JdbcTemplate getTemplate(){
		return jdbcTemplate;
	}
}
