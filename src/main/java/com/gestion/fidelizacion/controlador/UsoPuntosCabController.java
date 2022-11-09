package com.gestion.fidelizacion.controlador;

import com.gestion.fidelizacion.entidades.Cliente;
import com.gestion.fidelizacion.entidades.Premio;
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

import com.gestion.fidelizacion.entidades.UsoPuntosCab;
import com.gestion.fidelizacion.servicio.ClienteService;
import com.gestion.fidelizacion.util.paginacion.PageRender;
import com.gestion.fidelizacion.servicio.UsoPuntosCabService;
import com.gestion.fidelizacion.servicio.PremioService;
import java.util.List;

@Controller
public class UsoPuntosCabController {

	@Autowired
	private UsoPuntosCabService usopuntoscabService;
        
        @Autowired
	private PremioService premioService;
        
        @Autowired
	private ClienteService clienteService;
	
	@GetMapping("/usopuntoscabVer/{id}")
	public String verDetallesDeUsopuntoscab(@PathVariable(value = "id") Long id,Map<String,Object> modelo,RedirectAttributes flash) {
		UsoPuntosCab usopuntoscab = usopuntoscabService.findOne(id);
		if(usopuntoscab == null) {
			flash.addFlashAttribute("error", "No existe en la base de datos");
			return "redirect:/usopuntoscabListar";
		}
		
		modelo.put("usopuntoscab",usopuntoscab);
		modelo.put("titulo", "Detalles de uso de puntos ");
		return "usopuntoscabVer";
	}
	
        @GetMapping({"/usopuntoscabListar"})
	public String listarUsopuntosCab(@RequestParam(name = "page",defaultValue = "0") int page,Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 4);
                List<Cliente> listadoClientes = clienteService.findAll(); 
                modelo.addAttribute("listadoClientes",listadoClientes);
		Page<UsoPuntosCab> usopuntoscab = usopuntoscabService.findAll(pageRequest);
              
		PageRender<UsoPuntosCab> pageRender = new PageRender<>("/usopuntoscabListar", usopuntoscab);
		          
		modelo.addAttribute("titulo","Listado de uso de puntos");
		modelo.addAttribute("usopuntoscab",usopuntoscab);
		modelo.addAttribute("page", pageRender);
		
		return "usopuntoscabListar";
	}
        
        
        @GetMapping("/usopuntoscabForm")
	public String mostrarFormularioDeRegistrarUsoPuntos(Model modelo, Map<String,Object> modelomap) {
            //Para listar y seleccionar premios
            List<Premio> listadoPremios = premioService.findAll(); 
            modelo.addAttribute("listadoPremios",listadoPremios);
            //Para listar y seleccionar clientes
            List<Cliente> listadoClientes = clienteService.findAll(); 
            modelo.addAttribute("listadoClientes",listadoClientes);
            
            UsoPuntosCab usopuntos = new UsoPuntosCab();
 
            modelo.addAttribute("usopuntoscab",usopuntos);
            modelo.addAttribute("titulo", "Registro de uso de puntajes");
            return "usopuntoscabForm";
	}
        
	
	@PostMapping("/usopuntoscabForm")
	public String guardarUsopuntoscab(@Valid UsoPuntosCab usopuntoscab,BindingResult result,Model modelo,RedirectAttributes flash,SessionStatus status) {
                System.out.println("---->ID DEL CLIENTE usopuntoscab: " + usopuntoscab.getCliente().getId());
                if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de uso de puntos");
			return "usopuntoscabForm";
		}
		
		String mensaje = (usopuntoscab.getId() != null) ? "Editato con exito" : "Registrado con exito";
		
		usopuntoscabService.save(usopuntoscab);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/usopuntoscabListar";
	}
	
	@GetMapping("/usopuntoscabForm/{id}")
	public String editarUsopuntoscab(@PathVariable(value = "id") Long id,Map<String, Object> modelo,RedirectAttributes flash) {
		UsoPuntosCab usopuntoscab = null;
		if(id > 0) {
			usopuntoscab = usopuntoscabService.findOne(id);
			if(usopuntoscab == null) {
				flash.addFlashAttribute("error", "El ID del usopuntoscab no existe en la base de datos");
				return "redirect:/usopuntoscabListar";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del usopuntoscab no puede ser cero");
			return "redirect:/usopuntoscabListar";
		}
		
		modelo.put("usopuntoscab",usopuntoscab);
		modelo.put("titulo", "Edición de usopuntoscab");
		return "usopuntoscabForm";
	}
	
	@GetMapping("/usopuntoscabEliminar/{id}")
	public String eliminarUsopuntoscab(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		if(id > 0) {
			usopuntoscabService.delete(id);
			flash.addFlashAttribute("success", "Usopuntoscab eliminado con exito");
		}
		return "redirect:/usopuntoscabListar";
	}
	
}
