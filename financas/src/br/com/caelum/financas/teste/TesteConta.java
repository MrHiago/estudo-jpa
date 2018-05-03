package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {

		/**
		 * Estado Transient onde a entidade acabou de ser criada e ainda n�o �
		 * gerenciada pelo banco e se a aplica��o quebrar a entidade � perdida anes de
		 * ser persistida no banco
		 */
		Conta conta = new Conta();
		conta.setTitular("Danilo doidao");
		conta.setAgencia("123");
		conta.setBanco("Banco do Brasil");
		conta.setNumero("456");

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		/**
		 * a partir do momento que o metodo persist � chamado a entidade come�a a ser
		 * gerenciada pelo banco sendo assim transformando a conta de Transient pra
		 * Managed
		 */
		// em.persist(conta);

		conta = em.find(Conta.class, 1);
		em.remove(conta);

		conta.setBanco("Bradesco");

		em.getTransaction().commit();

		/**
		 * estado Managed de uma entidade vai durar at� o fechamento de um EntityManager
		 */
		em.close();
	}
}
