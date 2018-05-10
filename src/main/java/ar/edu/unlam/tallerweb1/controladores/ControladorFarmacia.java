package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Farmacia;
import ar.edu.unlam.tallerweb1.servicios.ServicioFarmacia;

@Controller
public class ControladorFarmacia {
	
	@Inject ServicioFarmacia servicioFarmacia;

    @RequestMapping(path = "/farmacias/listar")
    public ModelAndView listarFarmacias(){
    	List<Farmacia> farmacias = servicioFarmacia.obtenerFarmacias(); 
    	
    	ModelAndView respuesta = new ModelAndView("listar-farmacias");
    	respuesta.addObject("farmacias", farmacias);
    	
    	return respuesta;
    }
}