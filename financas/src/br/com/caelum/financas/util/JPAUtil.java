package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manter o código de criação do EntityManager isolado em uma classe
 * especialista. Dessa forma podemos, na nossa aplicação, focar apenas em regras
 * de negócio em vez de nos preocupar com a criação de um EntityManager toda vez
 * que houver a necessidade de trabalhar com o banco.
 * 
 * 
 * Apesar de ainda precisarmos fechar o EntityManager, dessa forma cada classe
 * será mais coesa tendo seu papel único na aplicação.
 *
 */
public class JPAUtil {

	/**
	 * O método createEntityManagerFactory irá gerar um EntityManager baseado nas
	 * configurações do persistence.xml. Baseado nisso, é fundamental que este
	 * método receba como argumento o nome de alguma unidade de persistência
	 * existente no arquivo.
	 * 
	 * 
	 * As configurações relacionadas ao acesso banco de dados ficam dentro da sessão
	 * persistence-unit. A JPA não limita o número de unidades de persistência (o
	 * que é útil quando precisamos de mais de um banco por aplicação, como veremos
	 * no próximo exercício) e por isso precisamos escolher um para usar no método
	 * createEntityManagerFactory.
	 */
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
