package DB;

import java.util.List;

import model.Usuario;

public class AmigosDAO {
	public static final String TABLE_AMIGOS = "amigos";
	public static final String AMIGO1 = "idAmigo1";
	public static final String AMIGO2 = "idAmigo2";
	
	public static void addAmistad(int usr1, int usr2) {
		DataBaseHandler.getInstance().getTemplate().update(""
				+ "INSERT INTO "+TABLE_AMIGOS+" VALUES("
				+ usr1 + ", "
				+ usr2
				+ ");");
	}
	
	public static List<Usuario> getAmigos(int usr){
		return DataBaseHandler.getInstance().getTemplate()
				.query("SELECT * FROM "+ TABLE_AMIGOS + " "
						+ "WHERE "+AMIGO1+" = "+usr+";",
						new UsuarioMapper());
	}
}
