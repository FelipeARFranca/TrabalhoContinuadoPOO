package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public abstract class RepositorioGeral {
	private DAOSerializadorObjetos dao;
	
	public DAOSerializadorObjetos getDao() {
		return dao;
	}

	public abstract Class<?> getClasseEntidade();
	
	public RepositorioGeral() {
		this.dao = new DAOSerializadorObjetos(getClasseEntidade());
	}
}
