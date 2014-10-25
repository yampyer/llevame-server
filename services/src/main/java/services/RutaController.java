package services;

import java.util.List;

import model.Ruta;
import model.Ubicacion;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import DB.PasajerosDAO;
import DB.RutaDAO;
import DB.Ruta_UbicacionDAO;
import DB.UbicacionDAO;


@Controller
@RequestMapping("/rutas")
public class RutaController {

	//ruta----------------------------------------------
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
	
	
	//pasajeros----------------------------------------------
	@RequestMapping(method = RequestMethod.POST, value = "/pasajeros")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void agregarPasajero(@RequestParam(value = "ruta", required = true) Integer ruta,
			@RequestParam(value = "usuario", required = true) Integer usuario) {
		PasajerosDAO.crearPasajero(ruta,usuario);
	}
	
	//ubicacion----------------------------------------------
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/ubicaciones")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<Ubicacion> IngresarRecorrido(@RequestBody List<Ubicacion> recorrido, 
												@PathVariable int id){
		//foreach ubicacion en recorrido
		for(Ubicacion u : recorrido){
			u = UbicacionDAO.createUbicacion(u);
			Ruta_UbicacionDAO.agregarUbicacionARuta(u.getId(), id);
		}
		
		return recorrido;
	}
}
