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

import com.gestion.fidelizacion.entidades.Vencimiento;
import com.gestion.fidelizacion.util.paginacion.PageRender;
import com.gestion.fidelizacion.servicio.VencimientoService;

@Controller
public class VencimientoController {

	@Autowired
	private VencimientoService vencimientoService;
	
	@GetMapping("/vencVer/{id}")
	public String verDetallesDelVencimiento(@PathVariable(value = "id") Long id,Map<String,Object> modelo,RedirectAttributes flash) {
		Vencimiento vencimiento = vencimientoService.findOne(id);
		if(vencimiento == null) {
			flash.addFlashAttribute("error", "El vencimiento no existe en la base de datos");
			return "redirect:/vencListar";
		}
		
		modelo.put("vencimiento",vencimiento);
		modelo.put("titulo", "Detalles del vencimiento ");
		return "vencVer";
	}
	
	//@GetMapping({"/","/vencListar",""})
        @GetMapping({"/vencListar"})
	public String listarVencimientos(@RequestParam(name = "page",defaultValue = "0") int page,Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Vencimiento> vencimiento = vencimientoService.findAll(pageRequest);
		PageRender<Vencimiento> pageRender = new PageRender<>("/vencListar", vencimiento);
		
		modelo.addAttribute("titulo","Listado de vencimientos");
		modelo.addAttribute("vencimiento",vencimiento);
		modelo.addAttribute("page", pageRender);
		
		return "vencListar";
	}
	
	@GetMapping("/vencForm")
	public String mostrarFormularioDeRegistrarVencimiento(Map<String,Object> modelo) {
		Vencimiento vencimiento = new Vencimiento();
		modelo.put("vencimiento", vencimiento);
		modelo.put("titulo", "Registro de vencimiento");
		return "vencForm";
	}
	
	@PostMapping("/vencForm")
	public String guardarVencimiento(@Valid Vencimiento vencimiento,BindingResult result,Model modelo,RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de vencimiento");
			return "vencForm";
		}
		
		String mensaje = (vencimiento.getId() != null) ? "El vencimiento ha sido editato con exito" : "Vencimiento registrado con exito";
		
		vencimientoService.save(vencimiento);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/vencListar";
	}
	
	@GetMapping("/vencForm/{id}")
	public String editarVencimiento(@PathVariable(value = "id") Long id,Map<String, Object> modelo,RedirectAttributes flash) {
		Vencimiento vencimiento = null;
		if(id > 0) {
			vencimiento = vencimientoService.findOne(id);
			if(vencimiento == null) {
				flash.addFlashAttribute("error", "El ID del vencimiento no existe en la base de datos");
				return "redirect:/vencListar";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del vencimiento no puede ser cero");
			return "redirect:/vencListar";
		}
		
		modelo.put("vencimiento",vencimiento);
		modelo.put("titulo", "Edición de vencimiento");
		return "vencForm";
	}
	
	@GetMapping("/vencEliminar/{id}")
	public String eliminarVencimiento(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		if(id > 0) {
			vencimientoService.delete(id);
			flash.addFlashAttribute("success", "Vencimiento eliminado con exito");
		}
		return "redirect:/vencListar";
	}
	
}
