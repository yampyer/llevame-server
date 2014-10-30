package services;

import java.util.List;

import model.Ruta;
import model.Ubicacion;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/pasajero/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Ruta> listaRutasPasajeros(@PathVariable int id){
		return RutaDAO.fetchRutasListPasajero(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/conductor/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Ruta> listaRutasConductor(@PathVariable int id){
		return RutaDAO.fetchRutasListConductor(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void cambiarEstadoRuta(@PathVariable int id,
			@RequestParam(value = "estado", required = true) boolean estado){
		
		RutaDAO.cambiarEstadoRuta(estado, id);
		
	} 
	
	//pasajeros----------------------------------------------
	
	@RequestMapping(method = RequestMethod.GET, value = "/pasajeros")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Usuario> getPasajeros(@RequestParam(value = "ruta", required = true) Integer ruta) {
		return PasajerosDAO.fetchPasajeros(ruta);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pasajeros")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void agregarPasajero(@RequestParam(value = "ruta", required = true) Integer ruta,
			@RequestParam(value = "usuario", required = true) Integer usuario) throws Throwable {
		int capacidadMax = RutaDAO.fetchRuta(ruta).getCapacidad();
		
		int cuposDisp = capacidadMax - PasajerosDAO.fetchNumberPasajeros(ruta);
		
		if(cuposDisp <= 0){
			//error, no hay cupos
			throw new Throwable("No hay mas cupos disponibles");
		} else {
			PasajerosDAO.crearPasajero(ruta,usuario);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/pasajeros")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void retirarPasajero(@RequestParam(value = "ruta", required = true) Integer ruta,
			@RequestParam(value = "usuario", required = true) Integer usuario) throws Throwable {
		
		PasajerosDAO.borrarPasajero(ruta, usuario);
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
