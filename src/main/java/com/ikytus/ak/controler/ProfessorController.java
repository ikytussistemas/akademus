package com.ikytus.ak.controler;

import java.util.Arrays;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.ak.domain.Professor;
import com.ikytus.ak.domain.enums.StatusProfessor;
import com.ikytus.ak.domain.enums.TituloProfessor;
import com.ikytus.ak.dto.Filter;
import com.ikytus.ak.services.ProfessorService;
import com.ikytus.ak.util.image.ImageService;
import com.ikytus.ak.util.pageable.pageConfig;

@Controller
@RequestMapping("/professores")
public class ProfessorController{
	
	@Autowired
	private ProfessorService service;
	
	@Autowired
	private ImageService is;
	
	@Autowired
	private pageConfig pconfig;
		
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		return pconfig.montarPagina("geral/consultaProfessor", filter, service.findByName(filter.getNome(), pageSize, page, pageable, ordem));
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Professor professor){
		ModelAndView mv = new ModelAndView("geral/cadastroProfessor");
		if(professor.getId()!=null) {
			mv.addObject("tel1", professor.getTelefones().get(0));
			mv.addObject("tel2", professor.getTelefones().get(1));
			mv.addObject("tel3", professor.getTelefones().get(2));		
		}
		
		mv.addObject(professor);
		mv.addObject("cursosCadastrados",service.getCursos());
		mv.addObject("titulos",TituloProfessor.values());
		mv.addObject("statuss",StatusProfessor.values());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file,
								@RequestParam(name="tel1", required=false, defaultValue="(00)00000-0000") String tel1,
								@RequestParam(name="tel2", required=false, defaultValue="(00)00000-0000") String tel2,
								@RequestParam(name="tel3", required=false, defaultValue="(00)00000-0000") String tel3,
								@Valid Professor professor, BindingResult result, RedirectAttributes atributos){
		if(result.hasErrors()){
			return novo(professor);
		}
		professor.getTelefones().addAll(Arrays.asList(tel1,tel2,tel3));	
		is.gravaImagemBase64Service(file,service,professor);
		atributos.addFlashAttribute("mensagem","Professor salvo com sucesso!");
		return new ModelAndView("redirect:/professores/novo");
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		return novo(service.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		service.delete(codigo);
		atributos.addFlashAttribute("mensagem","Professor removido com sucesso!");
		return new ModelAndView("redirect:/professores");
	}
}
