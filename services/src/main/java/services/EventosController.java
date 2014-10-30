package services;

import model.Evento;
import model.Invitacion;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import DB.EventoDAO;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Evento crearEvento(@RequestBody Invitacion evento) throws Exception{
		return EventoDAO.crearEvento(evento);
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void AceptarInvitacion(@PathVariable int id) throws Exception{
		
		EventoDAO.cambiarEstado(id, true);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarEvento(@PathVariable int id){
		EventoDAO.eliminarEvento(id);
	}
}
