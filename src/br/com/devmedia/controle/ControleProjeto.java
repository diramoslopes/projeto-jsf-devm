package br.com.devmedia.controle;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.devmedia.beans.Funcionario;
import br.com.devmedia.beans.Projeto;
import br.com.devmedia.beans.ProjetoFuncionario;
import br.com.devmedia.conversores.ConverterFuncionario;
import br.com.devmedia.conversores.ConverterSetor;
import br.com.devmedia.modelo.FuncionarioDAO;
import br.com.devmedia.modelo.ProjetoDAO;
import br.com.devmedia.modelo.SetorDAO;
import br.com.devmedia.util.UtilRelatorios;

@ManagedBean(name = "controleProjeto")
@SessionScoped
public class ControleProjeto implements Serializable{
	
	private ProjetoDAO dao;
	private Projeto objeto;
	private FuncionarioDAO daoFuncionario;
	private ConverterFuncionario converterFuncionario;
	private SetorDAO daoSetor;
	private Funcionario funcionario;
	private Integer cargaHorario;
	private Boolean gestor;
	private Calendar inicioParticipacao;
	private Calendar fimParticipacao;
	private Boolean addFunc = false;
	
	private ConverterSetor converterSetor;
	
	public ControleProjeto() {
		dao = new ProjetoDAO();
		daoFuncionario = new FuncionarioDAO();
		converterFuncionario = new ConverterFuncionario();
		daoSetor = new SetorDAO();
		converterSetor = new ConverterSetor();
	}
	
	public String listar() {
		return "/privado/projeto/listar?faces-redirect=true";
	}
	
	public String novo() {
		objeto = new Projeto();
		addFunc = false;
		return "form";
	}
	
	public String cancelar() {
		addFunc = false;
		dao.roolback();
		return "listar";
	}
	
	public String gravar() {
		if(dao.gravar(objeto)) {
			addFunc = false;
			return "listar";
		} else {
			return "form";
		}
	}
	
	public String alterar(Projeto obj) {
		objeto = obj;
		addFunc = false;
		return "form";
	}
	
	public String excluir(Projeto obj) {
		dao.excluir(obj);
		return "listar";
	}
	
	public void relatorio() {
		HashMap parametros = new HashMap();
		UtilRelatorios.imprimeRelatorio("projetos", parametros, dao.listarTodos());
	}
	
	public void removerFuncionario(ProjetoFuncionario obj) {
		objeto.removerFuncionario(obj);
	}
	
	public void adicionarFuncionario() {
		addFunc = true;
	}
	
	public void cancelarFuncionario() {
		addFunc = false;
	}
	
	public void salvarFuncionario() {
		ProjetoFuncionario obj = new ProjetoFuncionario();
		obj.setCargaHorario(cargaHorario);
		obj.setFuncionario(funcionario);
		obj.setInicioParticipacao(inicioParticipacao);
		obj.setFimParticipacao(fimParticipacao);
		obj.setGestor(gestor);
		objeto.adicionarFuncionario(obj);
		addFunc = false;
		cargaHorario = null;
		funcionario = null;
		inicioParticipacao = null;
		fimParticipacao = null;
		gestor = null;
	}
	
	public ProjetoDAO getDao() {
		return dao;
	}
	
	public void setDao(ProjetoDAO dao) {
		this.dao = dao;
	}
	
	public Projeto getObjeto() {
		return objeto;
	}
	
	public void setObjeto(Projeto obj) {
		this.objeto = obj;
	}

	public FuncionarioDAO getDaoFuncionario() {
		return daoFuncionario;
	}

	public void setDaoFuncionario(FuncionarioDAO daoFuncionario) {
		this.daoFuncionario = daoFuncionario;
	}

	public ConverterFuncionario getConverterFuncionario() {
		return converterFuncionario;
	}

	public void setConverterFuncionario(ConverterFuncionario converterFuncionario) {
		this.converterFuncionario = converterFuncionario;
	}

	public SetorDAO getDaoSetor() {
		return daoSetor;
	}

	public void setDaoSetor(SetorDAO daoSetor) {
		this.daoSetor = daoSetor;
	}

	public ConverterSetor getConverterSetor() {
		return converterSetor;
	}

	public void setConverterSetor(ConverterSetor converterSetor) {
		this.converterSetor = converterSetor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getCargaHorario() {
		return cargaHorario;
	}

	public void setCargaHorario(Integer cargaHorario) {
		this.cargaHorario = cargaHorario;
	}

	public Boolean getGestor() {
		return gestor;
	}

	public void setGestor(Boolean gestor) {
		this.gestor = gestor;
	}

	public Calendar getInicioParticipacao() {
		return inicioParticipacao;
	}

	public void setInicioParticipacao(Calendar inicioParticipacao) {
		this.inicioParticipacao = inicioParticipacao;
	}

	public Calendar getFimParticipacao() {
		return fimParticipacao;
	}

	public void setFimParticipacao(Calendar fimParticipacao) {
		this.fimParticipacao = fimParticipacao;
	}

	public Boolean getAddFunc() {
		return addFunc;
	}

	public void setAddFunc(Boolean addFunc) {
		this.addFunc = addFunc;
	}
}