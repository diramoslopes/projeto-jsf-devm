package br.com.devmedia.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.devmedia.beans.Projeto;
import br.com.devmedia.conversores.ConverterFuncionario;
import br.com.devmedia.conversores.ConverterSetor;
import br.com.devmedia.modelo.FuncionarioDAO;
import br.com.devmedia.modelo.ProjetoDAO;
import br.com.devmedia.modelo.SetorDAO;

@ManagedBean(name = "controleProjeto")
@SessionScoped
public class ControleProjeto implements Serializable{
	
	private ProjetoDAO dao;
	private Projeto objeto;
	private FuncionarioDAO daoFuncionario;
	private ConverterFuncionario converterFuncionario;
	private SetorDAO daoSetor;
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
		return "form";
	}
	
	public String cancelar() {
		return "listar";
	}
	
	public String gravar() {
		if(dao.gravar(objeto)) {
			return "listar";
		} else {
			return "form";
		}
	}
	
	public String alterar(Projeto obj) {
		objeto = obj;
		return "form";
	}
	
	public String excluir(Projeto obj) {
		dao.excluir(obj);
		return "listar";
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
}