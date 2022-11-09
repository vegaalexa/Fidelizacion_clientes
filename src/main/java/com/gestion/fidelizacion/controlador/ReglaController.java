package com.gestion.fidelizacion.controlador;

import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestion.fidelizacion.entidades.Regla;
import com.gestion.fidelizacion.util.paginacion.PageRender;
import com.gestion.fidelizacion.servicio.ReglaService;

@Controller
public class ReglaController {

	@Autowired
	private ReglaService reglaService;
	
	@GetMapping("/reglaVer/{id}")
	public String verDetallesDeRegla(@PathVariable(value = "id") Long id,Map<String,Object> modelo,RedirectAttributes flash) {
		Regla regla = reglaService.findOne(id);
		if(regla == null) {
			flash.addFlashAttribute("error", "La regla no existe en la base de datos");
			return "redirect:/reglaListar";
		}
		
		modelo.put("regla",regla);
		modelo.put("titulo", "Detalles de las reglas ");
		return "reglaVer";
	}
	
        @GetMapping({"/reglaListar"})
	public String listarReglas(@RequestParam(name = "page",defaultValue = "0") int page,Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Regla> regla = reglaService.findAll(pageRequest);
		PageRender<Regla> pageRender = new PageRender<>("/reglaListar", regla);
		
		modelo.addAttribute("titulo","Listado de reglas");
		modelo.addAttribute("regla",regla);
		modelo.addAttribute("page", pageRender);
		
		return "reglaListar";
	}
	
	@GetMapping("/reglaForm")
	public String mostrarFormularioDeRegistrarRegla(Map<String,Object> modelo) {
		Regla regla = new Regla();
		modelo.put("regla", regla);
		modelo.put("titulo", "Registro de reglas");
		return "reglaForm";
	}
	
	@PostMapping("/reglaForm")
	public String guardarRegla(@Valid Regla regla,BindingResult result,Model modelo,RedirectAttributes flash,SessionStatus status) {
            System.out.println("------->LIMITE INFERIOR: "+ regla.getLimite_inferior());
            Integer limite_superior;
            Integer limite_inferior;
            Integer cant_puntos_calculados = (int)((regla.getLimite_superior() - regla.getLimite_inferior())/regla.getMonto_por_punto());
            
            System.out.println("------->Puntos por compra: "+ cant_puntos_calculados);
		if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de reglas");
			return "reglaForm";
		}
		
		String mensaje = (regla.getId() != null) ? "La regla ha sido editada con exito" : "Regla registrado con exito";
		regla.setPuntos_calculados(cant_puntos_calculados);
                
		reglaService.save(regla);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/reglaListar";
	}
	
	@GetMapping("/reglaForm/{id}")
	public String editarRegla(@PathVariable(value = "id") Long id,Map<String, Object> modelo,RedirectAttributes flash) {
		Regla regla = null;
		if(id > 0) {
			regla = reglaService.findOne(id);
			if(regla == null) {
				flash.addFlashAttribute("error", "El ID de la regla no existe en la base de datos");
				return "redirect:/reglaListar";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID de la regla no puede ser cero");
			return "redirect:/vencListar";
		}
		
		modelo.put("regla",regla);
		modelo.put("titulo", "Edición de regla");
		return "reglaForm";
	}
	
	@GetMapping("/reglaEliminar/{id}")
	public String eliminarRegla(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		if(id > 0) {
			reglaService.delete(id);
			flash.addFlashAttribute("success", "Regla eliminado con exito");
		}
		return "redirect:/reglaListar";
	}
	
}
