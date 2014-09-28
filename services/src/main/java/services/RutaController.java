package services;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping("/rutas")
public class RutaController {

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Ruta> obtenerRuta(@PathVariable int id){
		return new ResponseEntity<Ruta>(new Ruta(id, "poblado", "feb 2", 3), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public ArrayList<Ruta> getRutas(){
		ArrayList<Ruta> rutas = new ArrayList<Ruta>();
		
		rutas.add(new Ruta(1, "poblado", "hoy", 3));
		rutas.add(new Ruta(2, "envigado", "martes", 2, "llego tarde"));
		
		return rutas;
	}
	
	
}
