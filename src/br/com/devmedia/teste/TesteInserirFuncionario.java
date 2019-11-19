package br.com.devmedia.teste;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.devmedia.beans.Funcionario;
import br.com.devmedia.beans.Grupo;
import br.com.devmedia.beans.Setor;
import br.com.devmedia.jpa.EntityManagerUtil;

public class TesteInserirFuncionario {

	public static void main(String[] args) {
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		Grupo grupo = em.find(Grupo.class, 1);
		Setor setor = em.find(Setor.class, 1);
		Funcionario f = new Funcionario();
		f.setAtivo(true);
		f.setCpf("519.554.775-07");
		f.setEmail("jlbavaresco@gmail.com");
		f.setGrupo(grupo);
		f.setNascimento(Calendar.getInstance());
		f.setNome("João");
		f.setNomeUsuario("joao1");
		f.setSalario(5000.00);
		f.setSenha("12345");
		f.setSetor(setor);
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		
	}

}
