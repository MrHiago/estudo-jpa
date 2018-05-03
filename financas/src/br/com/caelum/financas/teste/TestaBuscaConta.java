package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaBuscaConta {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		/**
		 * M�todo find retorna uma inst�ncia da classe que � considerada managed, que �
		 * um estado do jpa que automaticamente os dados da entidade s�o sincronizados
		 * com os dados do banco de dados.
		 */
		Conta conta = em.find(Conta.class, 1);

		conta.setId(1);
		conta.setTitular("Hiago");
		conta.setAgencia("456");
		System.out.println(conta);
		
		em.getTransaction().commit();
		em.close();

		/**
		 * Exemplo de um Estado Detached onde a entidade apos o fechamento do
		 * entitymanager anterior a entidade n�o � mais gerenciada pelo jpa, mas ela j�
		 * foi anteriormente, h� uma representa��o dela no banco mas a sincroniza��o
		 * automatica n�o esta sendo gerenciada pelo jpa
		 */
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();

		conta.setTitular("Hiagao");

		/**
		 * m�todo merge transforma uma entidade que ja foi portanto nao e gerenciada
		 * pelo jpa (Detached) em gerenciavel novamento pelo jpa (Managed) e a partir do
		 * momento que ela se torna managed ocorre a sincroniza��o com o banco de dados.
		 */
		em2.merge(conta);

		System.out.println(conta);

		em2.getTransaction().commit();
		em2.close();
	}
}
