package services;

import java.util.List;

import model.Vehiculo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import DB.VehiculoDAO;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Vehiculo crearVehiculo(@RequestBody Vehiculo v){
		v = VehiculoDAO.CrearVehiculo(v);
		
		return v;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public List<Vehiculo> getVehiculos(){
		return VehiculoDAO.fetchListaVehiculos();
	}
}
