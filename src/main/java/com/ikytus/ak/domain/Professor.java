package com.ikytus.ak.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.ikytus.ak.domain.enums.StatusProfessor;
import com.ikytus.ak.domain.enums.TipoUsuario;
import com.ikytus.ak.domain.enums.TituloProfessor;;

@Entity
public class Professor extends Usuario{
	private static final long serialVersionUID = 1L;
	
	@Column(length = 7)
	private String funcional;
        
	private Integer titulo;
	
	@Column(length = 80)
    private String emailsecundario;
   
    private Integer status;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date admissao;
    
    private boolean coordenador; 
   
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "curso_professor", 
    			joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"), 
    			inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"))
    private List<Curso> cursos;

	public Professor() {
		super();
	}

	public Professor(Long id, String nome, String cpf, Date dtnasc, TipoUsuario tipo, String senha, String endereco,
			String bairro, String email, String funcional, TituloProfessor titulo, String emailSecundario, 
			StatusProfessor status, Date admissao, boolean coord) {
		super(id,nome, cpf, dtnasc, tipo, senha, endereco, bairro, email);
		this.funcional = funcional;
		this.titulo = (titulo==null)?null: titulo.getCod();
		this.emailsecundario = emailSecundario;
		this.status = (status==null)?null: status.getCod();
		this.admissao = admissao;
		this.coordenador=coord;
	}

	public String getFuncional() {
		return funcional;
	}

	public void setFuncional(String funcional) {
		this.funcional = funcional;
	}

	public TituloProfessor getTitulo() {
		return TituloProfessor.toEnum(titulo);
	}

	public void setTitulo(TituloProfessor titulo) {
		this.titulo = titulo.getCod();
	}
	
	public StatusProfessor getStatus() {
		return StatusProfessor.toEnum(status);
	}

	public void setStatus(StatusProfessor status) {
		this.status = status.getCod();
	}
		
	public String getEmailsecundario() {
		return emailsecundario;
	}

	public void setEmailsecundario(String emailsecundario) {
		this.emailsecundario = emailsecundario;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public boolean isCoordenador() {
		return coordenador;
	}

	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
}
