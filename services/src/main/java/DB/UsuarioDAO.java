package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UsuarioDAO {
	public static final String TABLE_USUARIO = "usuario";
	public static final String ID = "idUsuario";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String PUNTOS = "puntos";
	
	public static Usuario crearUsuario(Usuario usr){
		String esquemaUsuario = "INSERT INTO "+ TABLE_USUARIO
				+ " ("+ USERNAME + ", "
				+ PASSWORD + ", "
				+ PUNTOS;
		
		
		
		final String sql = esquemaUsuario
				+ ") VALUES ('"
				+ usr.getUsername() + "', '"
				+ usr.getPassword() + "', "
				+ "0);";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		DataBaseHandler.getInstance().getTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection c)
					throws SQLException {
				return c.prepareStatement(sql);
			}
		}, keyHolder);
		
		int id = keyHolder.getKey().intValue();
		
		usr.setId(id);
		usr.setPuntos(0);
		
		return usr;
	
	}
	
	public static Usuario fetchUsuario(int id){
		String sql = "SELECT * FROM " + UsuarioDAO.TABLE_USUARIO
				+ " WHERE "+ UsuarioDAO.ID+ " = " + id;
		
		List<Usuario> rs = DataBaseHandler.getInstance().getTemplate()
			.query(sql,new UsuarioMapper());
		
		return rs.get(0);
	}
	
	public static Usuario fetchUsuario(String username){
		String sql = "SELECT * FROM " + UsuarioDAO.TABLE_USUARIO
				+ " WHERE "+ UsuarioDAO.USERNAME+ " = '" + username + "'";
		
		List<Usuario> rs = DataBaseHandler.getInstance().getTemplate()
			.query(sql,new UsuarioMapper());
		
		if(rs.size()==0){
			return null;
		} 
		
		return rs.get(0);
	}

	public static List<Usuario> fetchUsuariosLike(String username) {
		String sql = "SELECT * FROM " + UsuarioDAO.TABLE_USUARIO
				+ " WHERE "+ UsuarioDAO.USERNAME+ " LIKE '%" + username + "%'";
		
		List<Usuario> rs = DataBaseHandler.getInstance().getTemplate()
			.query(sql,new UsuarioMapper());
		
		if(rs.size()==0){
			return null;
		} 
		
		return rs;
	}
	
	public static List<Usuario> fetchUsuariosLike(String username, int usr) {
		String usuariosLike = "SELECT * FROM " + UsuarioDAO.TABLE_USUARIO
				+ " WHERE "+ UsuarioDAO.USERNAME+ " LIKE '%" + username + "%'";
		
		String amigos = "SELECT "+AmigosDAO.AMIGO1+" FROM "+AmigosDAO.TABLE_AMIGOS
				+ " WHERE "+AmigosDAO.AMIGO2+" = "+usr;
		
		String sql = "SELECT *"
					+ " FROM ("+usuariosLike+") AS USUARIOS"
							+ " WHERE USUARIOS."+ID+" NOT IN ("+amigos+")"
									+ " AND USUARIOS."+ID+" != " +usr;
		
		List<Usuario> rs = DataBaseHandler.getInstance().getTemplate()
			.query(sql,new UsuarioMapper());
		
		if(rs.size()==0){
			return null;
		} 
		
		return rs;
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