package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import model.Ubicacion;

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
	
	
}
