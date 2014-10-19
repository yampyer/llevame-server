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
	public static final String VEHICULO = "vehiculo";
	
	public static List<Ruta> fetchRutasList(){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ RutaDAO.TABLE_RUTA + ";",
						new SimpleRutaMapper());
	}
	
	public static Ruta createRuta(Ruta ruta){
		String esquemaRuta = "INSERT INTO "+RutaDAO.TABLE_RUTA
				+ " ("+RutaDAO.NOMBRE_RUTA + ", "
				+ RutaDAO.FECHA_HORA + ", "
				+ RutaDAO.CAPACIDAD + ", "
				+ RutaDAO.DESCRIPCION;
		
		
		
		final String sql = esquemaRuta
				+ ") VALUES ('"
				+ ruta.getNombre() + "', '"
				+ ruta.getFecha() + "', "
				+ ruta.getCapacidad() + ", '"
				+ ruta.getDescripcion() + "');";
		
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
		return ruta;
	}
	
	public static Ruta fetchRuta(int id){
		String rutaSql = "SELECT * FROM " + RutaDAO.TABLE_RUTA
						+ " WHERE "+ RutaDAO.ID+ " = " + id;
		
		String sql = "SELECT * "
				+ "FROM "+VehiculoDAO.TABLE_VEHICULO+" JOIN (" + rutaSql + ") "
						+ "ON " + VEHICULO + " == " + VehiculoDAO.ID; 
		
		List<Ruta> rs = DataBaseHandler.getInstance().getTemplate()
			.query(sql,new DetailedRutaMapper());
		
		return rs.get(0);
	}
	
	public static void eliminarRuta(int id){
		DataBaseHandler.getInstance().getTemplate()
			.execute("DELETE FROM " + RutaDAO.TABLE_RUTA 
					+ " WHERE " + RutaDAO.ID + " = " + id + ";");
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
		ruta.setPlaca(res.getString(VehiculoDAO.PLACA));
		return ruta;
	}
}