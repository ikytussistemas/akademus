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

import com.ikytus.ak.domain.Funcionario;
import com.ikytus.ak.domain.enums.Perfil;
import com.ikytus.ak.domain.enums.Setores;
import com.ikytus.ak.dto.Filter;
import com.ikytus.ak.services.FuncionarioService;
import com.ikytus.ak.util.image.ImageService;
import com.ikytus.ak.util.pageable.pageConfig;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
			
	@Autowired
	private FuncionarioService service;
				
	@Autowired
	private ImageService is;
		
	@Autowired
	private pageConfig pconfig;
	
			
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
							   @RequestParam("page") Optional<Integer> page,
							   @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		
		return pconfig.montarPagina("geral/consultaFuncionario", filter,service.findByName(filter.getNome(), pageSize, page, pageable, ordem));
	}
						
	@GetMapping("/novo")
	public ModelAndView novo(Funcionario funcionario){
		ModelAndView mv = new ModelAndView("geral/cadastroFuncionario");
		if(funcionario.getId()!=null) {
			mv.addObject("tel1", funcionario.getTelefones().get(0));
			mv.addObject("tel2", funcionario.getTelefones().get(1));
			mv.addObject("tel3", funcionario.getTelefones().get(2));
		}
		System.out.println(funcionario.getPerfis().size());
		System.out.println(funcionario.getPerfis());
		mv.addObject("setores",Setores.values());
		mv.addObject("perfis",Perfil.values());
		mv.addObject(funcionario);
		System.out.println(funcionario.getPerfis().size());
		System.out.println(funcionario.getPerfis());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file,
								@RequestParam(name="tel1", required=false, defaultValue="(00)00000-0000") String tel1,
								@RequestParam(name="tel2", required=false, defaultValue="(00)00000-0000") String tel2,
								@RequestParam(name="tel3", required=false, defaultValue="(00)00000-0000") String tel3,
								@Valid Funcionario funcionario, BindingResult result, RedirectAttributes atributos){
		if(result.hasErrors()){
			return novo(funcionario);
		}
		
		System.out.println(funcionario.getPerfis().size());
		System.out.println(funcionario.getPerfis());
		funcionario.getTelefones().addAll(Arrays.asList(tel1,tel2,tel3));
		is.gravaImagemBase64Service(file,service,funcionario);
		atributos.addFlashAttribute("mensagem","Funcionario(a) salvo(a) com sucesso!");
		return new ModelAndView("redirect:/funcionarios/novo").addObject(funcionario);
	}
		
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		return novo(service.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public String deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		service.delete(codigo);
		atributos.addFlashAttribute("mensagem","Funcionario(a) removido(a) com sucesso!");
		return "redirect:/funcionarios";
	}
}