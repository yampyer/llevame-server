package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {
	public static final String TABLE_USUARIO = "usuario";
	public static final String ID = "idUsuario";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String PUNTOS = "puntos";
	
	public static Usuario fetchUsuario(int id){
		String sql = "SELECT * FROM " + UsuarioDAO.TABLE_USUARIO
				+ " WHERE "+ UsuarioDAO.ID+ " = " + id;
		
		List<Usuario> rs = DataBaseHandler.getInstance().getTemplate()
			.query(sql,new UsuarioMapper());
		
		return rs.get(0);
	}
}

final class UsuarioMapper implements org.springframework.jdbc.core.RowMapper<Usuario>  {

	@Override
	public Usuario mapRow(ResultSet res, int rowNum) throws SQLException {
		Usuario usr = new Usuario(res.getInt(UsuarioDAO.ID), 
				res.getString(UsuarioDAO.USERNAME), 
				res.getString(UsuarioDAO.PASSWORD), 
				res.getInt(UsuarioDAO.PUNTOS));
		return usr;
	}
	
}