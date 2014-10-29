package services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import DB.EventoDAO;

@Controller
@RequestMapping("/eventos")
public class Eventos {
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void AceptarInvitacion(@PathVariable int id){
		try {
			EventoDAO.cambiarEstado(id, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
