package services;

import java.util.List;

import model.Evento;
import model.Ruta;
import model.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import DB.AmigosDAO;
import DB.EventoDAO;
import DB.PasajerosDAO;
import DB.RutaDAO;
import DB.UsuarioDAO;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Usuario createUsuario(@RequestBody Usuario usr){
		return UsuarioDAO.crearUsuario(usr);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Usuario getUsuario(@PathVariable int id){
		return UsuarioDAO.fetchUsuario(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/rutas")
	@ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public List<Ruta> getRutas(@PathVariable int id){
		return RutaDAO.getRutasAmigos(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/rutas/pasajero")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Ruta> listaRutasPasajeros(@PathVariable int id){
		return RutaDAO.fetchRutasListPasajero(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/eventos")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Evento> getListaEvento(@PathVariable int id){
		return EventoDAO.fetchListaEventos(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Usuario getUsuarioByNombre(@RequestParam(value = "usr", required=true) String username){
		return UsuarioDAO.fetchUsuario(username);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/like")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Usuario> getUsuarioByNombreLike(@RequestParam(value = "usr", required=true) String username,
			@RequestParam(value = "idUsr", required = false) Integer id){
		
		if(id == null){
			return UsuarioDAO.fetchUsuariosLike(username);
		} else {
			return UsuarioDAO.fetchUsuariosLike(username, id); 
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/amigos")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void setAmistad(@RequestParam(value = "usr1", required=true) int usr1,
						@RequestParam(value = "usr2", required=false) Integer usr2){
		
		if(usr2!=null){
			AmigosDAO.addAmistad(usr1, usr2);
			AmigosDAO.addAmistad(usr2, usr1);
		} else {
			AmigosDAO.getAmigos(usr1);
		}
	}
}
