package services;

import java.util.List;

import model.Ruta;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import DB.RutaDAO;


@Controller
@RequestMapping("/rutas")
public class RutaController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public List<Ruta> getRutas(){
		return RutaDAO.fetchRutasList();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Ruta crearRuta(@RequestBody Ruta r){
		r = RutaDAO.createRuta(r);
		
		return r;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Ruta getRuta(@PathVariable int id){
		return RutaDAO.fetchRuta(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void eliminarRuta(@PathVariable int id){
		RutaDAO.eliminarRuta(id);
	} 
}