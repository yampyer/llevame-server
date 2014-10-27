package services;

import java.util.List;

import model.Evento;
import model.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import DB.EventoDAO;
import DB.UsuarioDAO;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Usuario getUsuario(@PathVariable int id){
		return UsuarioDAO.fetchUsuario(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/eventos")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Evento> getListaElemento(@PathVariable int id){
		return EventoDAO.fetchListaEventos(id);
	}
}
