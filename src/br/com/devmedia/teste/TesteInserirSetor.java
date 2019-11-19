package br.com.devmedia.teste;

import javax.persistence.EntityManager;

import br.com.devmedia.beans.Setor;
import br.com.devmedia.jpa.EntityManagerUtil;

public class TesteInserirSetor {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		Setor s = new Setor();
		s.setNome("administrativo");
		
		Setor s2 = new Setor();
		s2.setNome("Operacional");
		
		em.getTransaction().begin();
		em.persist(s);
		em.persist(s2);
		em.getTransaction().commit();
		
		System.out.println("OK Inseriu");
	}

}
