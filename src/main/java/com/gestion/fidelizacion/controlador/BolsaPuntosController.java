package com.gestion.fidelizacion.controlador;

import com.gestion.fidelizacion.entidades.BolsaPuntos;
import com.gestion.fidelizacion.servicio.BolsaPuntosService;
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

import com.gestion.fidelizacion.util.paginacion.PageRender;

@Controller
public class BolsaPuntosController {

	@Autowired
	private BolsaPuntosService bolsapuntosService;
	
	@GetMapping("/bolsaPuntosVer/{id}")
	public String verDetallesDelaBolsaPuntos(@PathVariable(value = "id") Long id,Map<String,Object> modelo,RedirectAttributes flash) {
		BolsaPuntos bolsapuntos = bolsapuntosService.findOne(id);
		if(bolsapuntos == null) {
			flash.addFlashAttribute("error", "La bolsa de puntos no existe en la base de datos");
			return "redirect:/bolsaPuntosListar";
		}
		
		modelo.put("bolsapuntos",bolsapuntos);
		modelo.put("titulo", "Detalles de la bolsa de puntos " + bolsapuntos.getId());
		return "bolsaPuntosVer";
	}
	
	@GetMapping("/bolsaPuntosListar")
        //@GetMapping({"/","/listar",""})
	public String listarBolsaPuntos(@RequestParam(name = "page",defaultValue = "0") int page,Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<BolsaPuntos> bolsapuntos = bolsapuntosService.findAll(pageRequest);
		PageRender<BolsaPuntos> pageRender = new PageRender<>("/bolsaPuntosListar", bolsapuntos);
		
		modelo.addAttribute("titulo","Listado de bolsa puntos");
		modelo.addAttribute("bolsapuntos",bolsapuntos);
		modelo.addAttribute("page", pageRender);
		
		return "bolsaPuntosListar";
	}
	
	@GetMapping("/bolsaPuntosForm")
	public String mostrarFormularioDeRegistrarBolsaPuntos(Map<String,Object> modelo) {
		BolsaPuntos bolsapuntos = new BolsaPuntos();
		modelo.put("bolsapuntos", bolsapuntos);
		modelo.put("titulo", "Registro de bolsa de puntos");
		return "bolsaPuntosForm";
	}
	
	@PostMapping("/bolsaPuntosForm")
	public String guardarBolsaPuntos(@Valid BolsaPuntos bolsapuntos,BindingResult result,Model modelo,RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de bolsa de puntos");
			return "bolsaPuntosForm";
		}
		
		String mensaje = (bolsapuntos.getId() != null) ? "La bolsa de puntos ha sido editato con exito" : "Bolsa de puntos registrado con exito";
		
		bolsapuntosService.save(bolsapuntos);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/bolsaPuntosListar";
	}
	
	@GetMapping("/bolsaPuntosForm/{id}")
	public String editarBolsaPuntos(@PathVariable(value = "id") Long id,Map<String, Object> modelo,RedirectAttributes flash) {
		BolsaPuntos bolsapuntos = null;
		if(id > 0) {
			bolsapuntos = bolsapuntosService.findOne(id);
			if(bolsapuntos == null) {
				flash.addFlashAttribute("error", "El ID de bolsa puntos no existe en la base de datos");
				return "redirect:/bolsaPuntosListar";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID de bolsa puntos no puede ser cero");
			return "redirect:/bolsaPuntosListar";
		}
		
		modelo.put("bolsapuntos",bolsapuntos);
		modelo.put("titulo", "Edición de bolsa puntos");
		return "bolsaPuntosForm";
	}
	
	@GetMapping("/bolsaPuntosEliminar/{id}")
	public String bolsaPuntosEliminar(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		if(id > 0) {
			bolsapuntosService.delete(id);
			flash.addFlashAttribute("success", "Bolsa de puntos eliminado con exito");
		}
		return "redirect:/bolsaPuntosListar";
	}
	
        /*
	@GetMapping("/exportarPDF")
	public void exportarListadoDeEmpleadosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Empleados_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Empleado> empleados = clienteService.findAll();
		
		EmpleadoExporterPDF exporter = new EmpleadoExporterPDF(empleados);
		exporter.exportar(response);
	}
	
	@GetMapping("/exportarExcel")
	public void exportarListadoDeEmpleadosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Empleados_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Empleado> empleados = clienteService.findAll();
		
		EmpleadoExporterExcel exporter = new EmpleadoExporterExcel(empleados);
		exporter.exportar(response);
	}
        */
}
