package br.com.devmedia.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.devmedia.beans.Funcionario;
import br.com.devmedia.beans.Grupo;
import br.com.devmedia.jpa.EntityManagerUtil;
import br.com.devmedia.util.UtilErros;
import br.com.devmedia.util.UtilMessagens;

public class FuncionarioDAO {
	
	private EntityManager em;
	
	public FuncionarioDAO() {
		em = EntityManagerUtil.getEntityManager();
	}
	
	public List<Funcionario> listarTodos(){
		return em.createQuery("from Funcionario order by nome").getResultList();
	}
	
	public boolean gravar(Funcionario obj) {
		try {
			em.getTransaction().begin();
			if (obj.getId() == null) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMessagens.mensagemInformacao("Objeto persistido com sucesso");
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			
			em.getTransaction().rollback();
			UtilMessagens.mensagemErro("Erro ao persistir objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean excluir(Funcionario obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMessagens.mensagemInformacao("Objeto removido com sucesso");
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			
			em.getTransaction().rollback();
			UtilMessagens.mensagemErro("Erro ao remover objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public Funcionario localizar(Integer id) {
		return em.find(Funcionario.class, id);
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
}
