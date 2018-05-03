package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manter o c�digo de cria��o do EntityManager isolado em uma classe
 * especialista. Dessa forma podemos, na nossa aplica��o, focar apenas em regras
 * de neg�cio em vez de nos preocupar com a cria��o de um EntityManager toda vez
 * que houver a necessidade de trabalhar com o banco.
 * 
 * 
 * Apesar de ainda precisarmos fechar o EntityManager, dessa forma cada classe
 * ser� mais coesa tendo seu papel �nico na aplica��o.
 *
 */
public class JPAUtil {

	/**
	 * O m�todo createEntityManagerFactory ir� gerar um EntityManager baseado nas
	 * configura��es do persistence.xml. Baseado nisso, � fundamental que este
	 * m�todo receba como argumento o nome de alguma unidade de persist�ncia
	 * existente no arquivo.
	 * 
	 * 
	 * As configura��es relacionadas ao acesso banco de dados ficam dentro da sess�o
	 * persistence-unit. A JPA n�o limita o n�mero de unidades de persist�ncia (o
	 * que � �til quando precisamos de mais de um banco por aplica��o, como veremos
	 * no pr�ximo exerc�cio) e por isso precisamos escolher um para usar no m�todo
	 * createEntityManagerFactory.
	 */
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
