package com.ikytus.ak.controler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.ak.domain.Aluno;
import com.ikytus.ak.domain.Curso;
import com.ikytus.ak.domain.Professor;
import com.ikytus.ak.domain.enums.Perfil;
import com.ikytus.ak.domain.enums.StatusAluno;
import com.ikytus.ak.domain.enums.StatusProfessor;
import com.ikytus.ak.domain.enums.TipoUsuario;
import com.ikytus.ak.domain.enums.TituloProfessor;
import com.ikytus.ak.repositories.AlunoRepository;
import com.ikytus.ak.repositories.CursoRepository;
import com.ikytus.ak.repositories.ProfessorRepository;
import com.ikytus.ak.repositories.UsuarioRepository;

@Controller
@RequestMapping("testes")
public class TestController {
	
	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private CursoRepository rep;
	@Autowired
	private AlunoRepository repal;
	
	@Autowired
	private ProfessorRepository repprf;
	
	@Autowired
	private UsuarioRepository repuser;
	
	@GetMapping()
	public ModelAndView testar() {
		return new ModelAndView("administrador/testes");
	}
		
	
	@PostMapping("/gravarcsv")
	public ModelAndView gravaCSV(@RequestParam("file") Part arquivo, RedirectAttributes atributos) throws IOException {
		
		Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		scanner.useDelimiter(";");

		while(scanner.hasNext()) {
			Curso curso = new Curso();
			String linha = scanner.nextLine();
			if(linha !=null && !linha.trim().isEmpty()) {
				linha = linha.replace("\"", "");
				String[] dados = linha.split("\\,");
				String fac = dados[4];
				if(fac.equals("FAECE")) {
					curso = rep.findOne(1L);
				}else {
					curso = rep.findOne(2L);
				}
				if(repuser.findByEmail(dados[2])==null) {
					Aluno al = new Aluno(null, dados[7], null, null, TipoUsuario.ALUNO, 
							pe.encode(dados[6]), null, null, dados[2], dados[6], null, curso,"8º", StatusAluno.ATIVO);
					al.getTelefones().addAll(Arrays.asList("(00)00000-0000","(00)00000-0000","(00)00000-0000"));
					al.addPerfil(Perfil.ALUNO);
					repal.save(al);
				};
			
				/*System.out.println("código: "+ dados[0]+" nome: " + dados[7] + " e o RA: " + dados[6] +" email: " + dados[2] + " Curso: " + curso.getNome() +" Faculdade: " + curso.getFaculdade().getNome() + dados[4]);*/
			}
		}
		scanner.close();
		atributos.addFlashAttribute("mensagem","Upload completo!");				
		return new ModelAndView("redirect:/testes");
	}
	
	@PostMapping("/gravarcsvprof")
	public ModelAndView gravaCSVProf(@RequestParam("file") Part arquivo, RedirectAttributes atributos) throws IOException {
		
		Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		scanner.useDelimiter(";");

		while(scanner.hasNext()) {
			StatusProfessor sp = null;
			TituloProfessor titulo = null;
			
			String linha = scanner.nextLine();
			if(linha !=null && !linha.trim().isEmpty()) {
				linha = linha.replace("\"", "");
				String[] dados = linha.split("\\,");
				
				String tit = dados[3];
				String st = dados[12];
				
				if(st.isEmpty()) {
					sp=StatusProfessor.DESLIGADO;
				}
				if(st.equals("Ativo")) {
					sp = StatusProfessor.ATIVO;
				}
				if(st.equals("Desligado")) {
					sp = StatusProfessor.DESLIGADO;
				}
				if(st.equals("Licença")) {
					sp = StatusProfessor.LICENCA;
				}
				
				if(tit.isEmpty()) {
					titulo = TituloProfessor.ESPECIALISTA;
				}
				if(tit.equals("Mestrado")) {
					titulo = TituloProfessor.MESTRE;
				}
				if(tit.equals("Especialização")) {
					titulo = TituloProfessor.ESPECIALISTA;
				}
				if(tit.equals("Doutorado")) {
					titulo = TituloProfessor.DOUTOR;
				}
				
				
				/*if(repuser.findByEmail(dados[2])==null) {
			
					Professor prf = new Professor(null, dados[2], dados[4], null, TipoUsuario.PROFESSOR, 
							pe.encode(dados[1]), dados[6], null, dados[10], dados[1], titulo, 
							dados[11], sp, null, false);
					
					String tel1;
					String tel2;
					String tel3;
					
					if(dados[7]==null) {
						tel1="(00)00000-0000";
					}else {
						tel1=dados[7];
					}
					if(dados[8]==null) {
						tel2="(00)00000-0000";
					}else {
						tel2=dados[8];
					}
					if(dados[9]==null) {
						tel3="(00)00000-0000";
					}else {
						tel3=dados[9];
					}
							
					prf.getTelefones().addAll(Arrays.asList(tel1,tel2,tel3));
					prf.addPerfil(Perfil.PROFESSOR);
					repprf.save(prf);
				};*/
			
				System.out.println("campo0: "+ dados[0]+"campo1: "+ dados[1]+
				  "campo2: "+ dados[2]+"Status: "+ sp.getDescricao()+"campo4: "+ dados[4]+
				  "campo5: "+ dados[5]+"campo6: "+ dados[6]+"campo7: "+ dados[7]+
				  "campo8: "+ dados[8]+"campo9: "+ dados[9]+"campo10: "+ dados[10]+
				  "campo11: "+ dados[11]+"Titulo: "+ titulo.getDescricao()+"campo13: "+ dados[13]+
				  "campo14: "+ dados[14]);
			}
		}
		scanner.close();
		atributos.addFlashAttribute("mensagem","Upload completo!");				
		return new ModelAndView("redirect:/testes");
	}

}
