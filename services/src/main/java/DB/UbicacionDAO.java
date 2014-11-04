package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Ubicacion;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UbicacionDAO {
	public static final String TABLE_UBICACION = "ubicacion";
	public static final String ID = "idUbicacion";
	public static final String NOMBRE = "titulo";
	public static final String LONGITUD = "longitud";
	public static final String LATITUD = "latitud";
	
	public static Ubicacion createUbicacion(Ubicacion ubicacion){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		final String sql = "INSERT INTO "+TABLE_UBICACION 
				+ "("+NOMBRE+", "+LONGITUD+", "+LATITUD+") VALUES("
				+ "'"+ubicacion.getNombre() + "', "
				+ ubicacion.getLongitud() + ", "
				+ ubicacion.getLatitud()  
				+ ");";
		
		DataBaseHandler.getInstance().getTemplate().update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conection)
							throws SQLException {
						return conection.prepareStatement(sql);
					}
				}, keyHolder);
				
		int id = keyHolder.getKey().intValue();
		
		ubicacion.setId(id);
		return ubicacion;
	}
	
	public static List<Ubicacion> getRecorridoRuta(int idRuta){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ Ruta_UbicacionDAO.TABLE_RUTA_UBICACION 
						+ " JOIN " + UbicacionDAO.TABLE_UBICACION 
							+" USING ("+Ruta_UbicacionDAO.IDUBICACION+")"
							+ " WHERE "
							+ Ruta_UbicacionDAO.IDRUTA + " = "+idRuta+";",
						new UbicacionMapper());
		
	}
}

final class UbicacionMapper implements org.springframework.jdbc.core.RowMapper<Ubicacion> {

	@Override
	public Ubicacion mapRow(ResultSet res, int rowNum) throws SQLException {
		Ubicacion u = new Ubicacion();
		u.setId(res.getInt(UbicacionDAO.ID));
		u.setNombre(res.getString(UbicacionDAO.NOMBRE));
		u.setLatitud(res.getDouble(UbicacionDAO.LATITUD));
		u.setLongitud(res.getDouble(UbicacionDAO.LONGITUD));
		
		return u;
	}
}
