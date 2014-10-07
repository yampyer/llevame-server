package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Ruta;

public class RutaDAO {
	
	//esquema de la BDs
	public static final String TABLE_RUTA = "Ruta";
	public static final String ID = "idRuta";
	public static final String NOMBRE_RUTA = "nombre";
	public static final String FECHA_HORA = "fecha";
	public static final String CAPACIDAD = "capacidad";
	public static final String DESCRIPCION = "descripcion";
	
	public static List<Ruta> fetchRutasList(){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ RutaDAO.TABLE_RUTA + ";",
						new RutaMapper());
	}
	
	public static Ruta createRuta(Ruta ruta){
		int id = DataBaseHandler.getInstance().getTemplate().update("INSERT INTO "+RutaDAO.TABLE_RUTA
				+ " ("+RutaDAO.NOMBRE_RUTA + ", "
				+ RutaDAO.FECHA_HORA + ", "
				+ RutaDAO.CAPACIDAD + ", "
				+ RutaDAO.DESCRIPCION + ") VALUES ('"
				+ ruta.getNombre() + "', '"
				+ ruta.getFecha() + "', "
				+ ruta.getCapacidad() + ", '"
				+ ruta.getDescripcion() + "');");
		
		ruta.setId(id);
		
		return ruta;
	}
}



final class RutaMapper implements org.springframework.jdbc.core.RowMapper<Ruta> {

	@Override
	public Ruta mapRow(ResultSet res, int rowNum) throws SQLException {
		Ruta ruta = new Ruta(res.getInt(RutaDAO.ID), res.getString(RutaDAO.NOMBRE_RUTA),
				res.getString(RutaDAO.FECHA_HORA), res.getInt(RutaDAO.CAPACIDAD), 
				res.getString(RutaDAO.DESCRIPCION));
		return ruta;
	}
}
