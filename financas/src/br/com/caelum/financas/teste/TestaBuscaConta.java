package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaBuscaConta {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		/**
		 * Método find retorna uma instância da classe que é considerada managed, que é
		 * um estado do jpa que automaticamente os dados da entidade são sincronizados
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
		 * entitymanager anterior a entidade não é mais gerenciada pelo jpa, mas ela já
		 * foi anteriormente, há uma representação dela no banco mas a sincronização
		 * automatica não esta sendo gerenciada pelo jpa
		 */
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();

		conta.setTitular("Hiagao");

		/**
		 * método merge transforma uma entidade que ja foi portanto nao e gerenciada
		 * pelo jpa (Detached) em gerenciavel novamento pelo jpa (Managed) e a partir do
		 * momento que ela se torna managed ocorre a sincronização com o banco de dados.
		 */
		em2.merge(conta);

		System.out.println(conta);

		em2.getTransaction().commit();
		em2.close();
	}
}
