package com.gestion.fidelizacion.controlador;

import com.gestion.fidelizacion.entidades.BolsaPuntos;
import com.gestion.fidelizacion.entidades.Cliente;
import com.gestion.fidelizacion.entidades.Premio;
import com.gestion.fidelizacion.entidades.UsoPuntosCab;
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

import com.gestion.fidelizacion.entidades.UsoPuntosDet;
import com.gestion.fidelizacion.entidades.UsoPuntosCab;
import com.gestion.fidelizacion.servicio.BolsaPuntosService;
import com.gestion.fidelizacion.servicio.UsoPuntosCabService;
import com.gestion.fidelizacion.util.paginacion.PageRender;
import com.gestion.fidelizacion.servicio.UsoPuntosDetService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;

@Controller
public class UsoPuntosDetController {

	@Autowired
	private UsoPuntosDetService usopuntosdetService;
        
        @Autowired
	private UsoPuntosCabService usopuntoscabService;
        
        @Autowired
	private BolsaPuntosService bolsapuntosService;
	
	@GetMapping("/usopuntosdetVer/{id}")
	public String verDetallesDeUsopuntosDet(@PathVariable(value = "id") Long id,Map<String,Object> modelo,RedirectAttributes flash) {
		UsoPuntosDet usopuntosdet = usopuntosdetService.findOne(id);
		if(usopuntosdet == null) {
			flash.addFlashAttribute("error", "No existe en la base de datos");
			return "redirect:/usopuntosdetListar";
		}
		
		modelo.put("usopuntosdet",usopuntosdet);
		modelo.put("titulo", "Detalles de uso de puntos ");
		return "usopuntosdetVer";
	}
	
        @GetMapping({"/usopuntosdetListar"})
	public String listarUsopuntosDet(@RequestParam(name = "page",defaultValue = "0") int page,Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<UsoPuntosDet> usopuntosdet = usopuntosdetService.findAll(pageRequest);
              
		PageRender<UsoPuntosDet> pageRender = new PageRender<>("/usopuntosdetListar", usopuntosdet);
		
		modelo.addAttribute("titulo","Listado de uso de puntos detalle");
		modelo.addAttribute("usopuntosdet",usopuntosdet);
		modelo.addAttribute("page", pageRender);
		
		return "usopuntosdetListar";
	}
        
        
        /*@GetMapping("/usopuntosdetForm")
	public String mostrarFormularioDeRegistrarUsoPuntosDet(Model modelo, Map<String,Object> modelomap) {
            //Para listar y seleccionar premios
            //List<UsoPuntosDet> listadoUsopuntoscab = usopuntoscabService.findAll(); //no es asi!!!
            //modelo.addAttribute("listadoPremios",listadoPremios);
            //Para listar y seleccionar clientes
            //List<Cliente> listadoClientes = clienteService.findAll(); 
            //modelo.addAttribute("listadoClientes",listadoClientes);
            
            UsoPuntosDet usopuntosdet = new UsoPuntosDet();
 
            modelomap.put("usopuntosdet",usopuntosdet);
            modelo.addAttribute("titulo", "Registro de uso de puntajes detalle");
            return "usopuntosdetForm";
	}*/
        @GetMapping("/cargardetForm/{id}")
        public String mostrarFormularioDeRegistroDet(Model modelo, Map<String,Object> modelomap) {
            //usopuntoscab.getId();
            //System.err.println("--->LLEGA ID DE CAB:"+usopuntoscab.getId());
            List<BolsaPuntos> listBolsapuntos = bolsapuntosService.findAll(); 
                modelo.addAttribute("listBolsapuntos",listBolsapuntos);
            
		UsoPuntosDet usopuntosdet = new UsoPuntosDet();
		modelomap.put("usopuntosdet", usopuntosdet);
		modelomap.put("titulo", "Registro de detalles");
		return "cargardetForm";
	}
	
	@PostMapping("/cargardetForm")
	public String guardarUsopuntosDet(@Valid UsoPuntosDet usopuntosdet,BindingResult result,Model modelo,RedirectAttributes flash,SessionStatus status) {
              
                if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de uso de puntos detalle");
			return "usopuntosdetForm";
		}
		
		String mensaje = (usopuntosdet.getId() != null) ? "Editato con exito" : "Registrado con exito";
		
		usopuntosdetService.save(usopuntosdet);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/usopuntoscabListar";
	}
	
	@GetMapping("/usopuntosdetForm/{id}")
	public String editarUsopuntosdet(@PathVariable(value = "id") Long id,Map<String, Object> modelo,RedirectAttributes flash) {
		UsoPuntosDet usopuntosdet = null;
		if(id > 0) {
			usopuntosdet = usopuntosdetService.findOne(id);
			if(usopuntosdet == null) {
				flash.addFlashAttribute("error", "El ID del usopuntosdet no existe en la base de datos");
				return "redirect:/usopuntosdetListar";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del usopuntosdet no puede ser cero");
			return "redirect:/usopuntosdetListar";
		}
		
		modelo.put("usopuntosdet",usopuntosdet);
		modelo.put("titulo", "Edición de usopuntosdet");
		return "usopuntosdetForm";
	}
	
	@DeleteMapping("/usopuntosdetEliminar/{id}")
	public String eliminarUsopuntosdet(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		if(id > 0) {
			usopuntosdetService.delete(id);
			flash.addFlashAttribute("success", "Usopuntosdet eliminado con exito");
		}
		return "redirect:/usopuntosdetListar";
	}
	
}
