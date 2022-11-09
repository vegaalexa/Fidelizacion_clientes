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

import com.gestion.fidelizacion.entidades.Premio;
import com.gestion.fidelizacion.util.paginacion.PageRender;
import com.gestion.fidelizacion.servicio.PremioService;

@Controller
public class PremioController {

	@Autowired
	private PremioService premioService;
	
	@GetMapping("/premioVer/{id}")
	public String verDetallesDelPremio(@PathVariable(value = "id") Long id,Map<String,Object> modelo,RedirectAttributes flash) {
		Premio premio = premioService.findOne(id);
		if(premio == null) {
			flash.addFlashAttribute("error", "El premio no existe en la base de datos");
			return "redirect:/premioListar";
		}
		
		modelo.put("premio",premio);
		modelo.put("titulo", "Detalles del premio ");
		return "premioVer";
	}
	
        @GetMapping({"/premioListar"})
	public String listarPremios(@RequestParam(name = "page",defaultValue = "0") int page,Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Premio> premio = premioService.findAll(pageRequest);
		PageRender<Premio> pageRender = new PageRender<>("/premioListar", premio);
		
		modelo.addAttribute("titulo","Listado de premios");
		modelo.addAttribute("premio",premio);
		modelo.addAttribute("page", pageRender);
		
		return "premioListar";
	}
	
	@GetMapping("/premioForm")
	public String mostrarFormularioDeRegistrarPremio(Map<String,Object> modelo) {
		Premio premio = new Premio();
		modelo.put("premio", premio);
		modelo.put("titulo", "Registro de premio");
		return "premioForm";
	}
	
	@PostMapping("/premioForm")
	public String guardarPremio(@Valid Premio premio,BindingResult result,Model modelo,RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de premio");
			return "premioForm";
		}
		
		String mensaje = (premio.getId() != null) ? "El premio ha sido editato con exito" : "Premio registrado con exito";
		
		premioService.save(premio);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/premioListar";
	}
	
	@GetMapping("/premioForm/{id}")
	public String editarPremio(@PathVariable(value = "id") Long id,Map<String, Object> modelo,RedirectAttributes flash) {
		Premio premio = null;
		if(id > 0) {
			premio = premioService.findOne(id);
			if(premio == null) {
				flash.addFlashAttribute("error", "El ID del premio no existe en la base de datos");
				return "redirect:/premioListar";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del premio no puede ser cero");
			return "redirect:/premioListar";
		}
		
		modelo.put("premio",premio);
		modelo.put("titulo", "Edición de premio");
		return "premioForm";
	}
	
	@GetMapping("/premioEliminar/{id}")
	public String eliminarPremio(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		if(id > 0) {
			premioService.delete(id);
			flash.addFlashAttribute("success", "Premio eliminado con exito");
		}
		return "redirect:/premioListar";
	}
	
}
