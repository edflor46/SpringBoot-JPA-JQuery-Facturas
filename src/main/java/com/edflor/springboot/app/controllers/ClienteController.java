package com.edflor.springboot.app.controllers;

//import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.Map;
//import java.util.UUID;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.edflor.springboot.app.models.dao.IClienteDao;
import com.edflor.springboot.app.models.entity.Cliente;
import com.edflor.springboot.app.models.service.IClienteService;
import com.edflor.springboot.app.models.service.IUploadFileService;
import com.edflor.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
@Qualifier("clienteDaoJPA")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUploadFileService uploadFileService;

	/*
	 * private Logger log = LoggerFactory.getLogger(getClass());
	 * 
	 * private final static String UPLOADS_FOLDER = "uploads";
	 */

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		/*
		 * //Path pathFoto =
		 * Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
		 * //log.info("pathFoto: " + pathFoto);
		 * 
		 * Resource recurso = null;
		 * 
		 * try { recurso = new UrlResource(pathFoto.toUri()); if (!recurso.exists() ||
		 * !recurso.isReadable()) { throw new
		 * RuntimeException("Error: No se puede cargar la imagen: " +
		 * pathFoto.toString()); } } catch (MalformedURLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedInputException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		/* Cliente cliente = clienteService.findOne(id); */
		
		Cliente cliente = clienteService.fetchByIdWhitFacturas(id);
		if (cliente == null) {
			flash.addAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());

		return "ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}

		if (!foto.isEmpty()) {
			// Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
			// String rootPath = directorioRecursos.toFile().getAbsolutePath();
			// String rootPath = "C://Temp//uploads";
			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {

				uploadFileService.delete(cliente.getFoto());

			}

			/*
			 * String uniqueFileName = UUID.randomUUID().toString() + "_" +
			 * foto.getOriginalFilename();
			 * 
			 * Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFileName); Path
			 * rootAbsolutPath = rootPath.toAbsolutePath(); log.info("rootPath: " +
			 * rootPath); log.info("rootAbsolutPath: " + rootAbsolutPath);
			 * 
			 * try {
			 * 
			 * byte[] bytes = foto.getBytes(); Path rutaCompleta = Paths.get(rootPath + "//"
			 * + foto.getOriginalFilename()); Files.write(rutaCompleta, bytes);
			 * 
			 * Files.copy(foto.getInputStream(), rootAbsolutPath);
			 * flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFileName
			 * + "'");
			 * 
			 * cliente.setFoto(uniqueFileName); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			cliente.setFoto(uniqueFilename);
		}

		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con exito" : "Cliente creado con exito";

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del Cliente no existe");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del Cliente no puede ser 0 ");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito");

			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + cliente.getFoto() + " eliminada con exito!");
			}
		}

		return "redirect:/listar";
	}

}
