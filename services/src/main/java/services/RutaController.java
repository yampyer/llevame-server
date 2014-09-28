package services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RutaController {

	@RequestMapping("/rutas")
	public Ruta obtenerRuta(@RequestParam(value="id", required=true) int id){
		return new Ruta(id, "poblado", "feb 2", 3);
	}
}
