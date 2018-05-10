package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorOperacionMatematica {

    @RequestMapping(path = "/operacion-matematica/{operacion}/{valor1}/{valor2}")
    public ModelAndView saludar(@PathVariable String operacion,
                                @PathVariable double valor1,
                                @PathVariable double valor2){
    	
        ModelMap modelo = new ModelMap();
        ModelAndView respuesta;

        operacion = operacion.toUpperCase();
        
        if(operacion.equals("SUMAR") || operacion.equals("RESTAR") || operacion.equals("MULTIPLICAR") || (operacion.equals("DIVIDIR") && valor2!=0)) {

        	modelo.put("operacion", operacion.toLowerCase());
            modelo.put("valor1", valor1);
            modelo.put("valor2", valor2);

            if(operacion.equals("SUMAR")) {
            	modelo.put("resultado", valor1 + valor2);
            }else if(operacion.equals("RESTAR")){
            	modelo.put("resultado", valor1 - valor2);
            }else if(operacion.equals("MULTIPLICAR")){
            	modelo.put("resultado", valor1 * valor2);
            }else if(operacion.equals("DIVIDIR")){
            	modelo.put("resultado", valor1 / valor2);
            }
            
            respuesta = new ModelAndView("operacion-exitosa", modelo);
        } else {
        	if(operacion.equals("DIVIDIR") && valor2 == 0) {
            	modelo.put("mensaje", "no se puede dividir por 0");
        	} else {
        		modelo.put("mensaje", "operación inválida, los valores esperados son: sumar, restar, multiplicar o dividir");
        	}
        	
        	respuesta = new ModelAndView("operacion-fallida", modelo);
        }
        
        return respuesta;
    }
}
