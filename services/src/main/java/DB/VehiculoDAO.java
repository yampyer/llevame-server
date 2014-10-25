package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Vehiculo;

public class VehiculoDAO {
	public static final String TABLE_VEHICULO = "Vehiculo";
	public static final String PLACA = "placa";
	public static final String OWNER = "owner";
	
	public static Vehiculo CrearVehiculo(Vehiculo v){
		String esquemaVehiculo = "INSERT INTO "+VehiculoDAO.TABLE_VEHICULO
				+ " ("
				+ VehiculoDAO.PLACA + ", "
				+ VehiculoDAO.OWNER;
		
		
		
		final String sql = esquemaVehiculo
				+ ") VALUES ("
				+ "'"+v.getPlaca()+"', "
				+ v.getOwner()+");";
		
		DataBaseHandler.getInstance().getTemplate().update(sql);
		
		return v;
	}
	
	public static List<Vehiculo> fetchListaVehiculos(){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ VehiculoDAO.TABLE_VEHICULO + ";",
						new SimpleVehiculoMapper());
	}
}


final class SimpleVehiculoMapper implements org.springframework.jdbc.core.RowMapper<Vehiculo> {

	@Override
	public Vehiculo mapRow(ResultSet res, int rowNum) throws SQLException {
		Vehiculo v = new Vehiculo(res.getString(VehiculoDAO.PLACA),
				res.getInt(VehiculoDAO.OWNER));
		return v;
	}
}
