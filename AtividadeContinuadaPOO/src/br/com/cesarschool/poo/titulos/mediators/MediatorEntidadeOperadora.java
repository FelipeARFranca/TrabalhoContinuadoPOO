package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

/*
 * Deve ser um singleton.
 * 
 * Deve ter um atributo repositorioEntidadeOperadora, do tipo RepositorioEntidadeOperadora, que deve 
 * ser inicializado na sua declaração. Este atributo será usado exclusivamente
 * pela classe, não tendo, portanto, métodos set e get.
 * 
 * Métodos: 
 * 
 * pivate String validar(EntidadeOperadora): deve validar os dados do objeto recebido nos seguintes termos: 
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 5 e 60 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 180 dias (4). 
 * valorUnitario: deve ser maior que zero (5). 
 * O método validar deve retornar null se o objeto estiver válido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inválido.
 * 
 * (1) - Identificador deve estar entre 100 e 1000000.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 *
 * public String incluir(EntidadeOperadora entidade): deve chamar o método validar. Se ele 
 * retornar null, deve incluir entidade no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do 
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Entidade já existente", se o retorno do validar for null
 * e o retorno do repositório for false. 
 *
 * public String alterar(EntidadeOperadora entidade): deve chamar o método validar. Se ele 
 * retornar null, deve alterar entidade no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do 
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Entidade inexistente", se o retorno do validar for null
 * e o retorno do repositório for false.
 * 
 * public String excluir(int identificador): deve validar o identificador. 
 * Se este for válido, deve chamar o excluir do repositório. Retornos possíveis:
 * (1) null, se o retorno do excluir do repositório for true.
 * (2) A mensagem "Entidade inexistente", se o retorno do repositório for false
 * ou se o identificador for inválido.
 * 
 * public EntidadeOperadora buscar(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o buscar do repositório, retornando o 
 * que ele retornar. Se o identificador for inválido, retornar null. 
 */
public class MediatorEntidadeOperadora {
	private RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();
	
	private String validar(EntidadeOperadora entidadeOperadora) {
		if(entidadeOperadora.getIdentificador() < 1 || entidadeOperadora.getIdentificador() > 99999) {
			return "Identificador deve estar entre 1 e 99999.";
		}
		
		if(entidadeOperadora.getNome().isBlank() || entidadeOperadora.getNome() == null) {
			return "Nome deve ser preenchido.";
		}
		
		if(entidadeOperadora.getNome().length() < 5 || entidadeOperadora.getNome().length() > 60) {
			return "Nome deve ter entre 5 e 60 caracteres.";
		}
		
		return null;
	}
	
	public String incluir(EntidadeOperadora entidadeOperadora) {
		if(validar(entidadeOperadora) != null) {
			return validar(entidadeOperadora);
		}
		else {
			boolean retornoRepo = repositorioEntidadeOperadora.incluir(entidadeOperadora);
			
			if(!retornoRepo) {
				return "Entidade já existente";
			}
		}
		
		return null;
	}
	
	public String alterar(EntidadeOperadora entidadeOperadora) {
		if(validar(entidadeOperadora) != null) {
			return validar(entidadeOperadora);
		}
		else {
			boolean retornoRepo = repositorioEntidadeOperadora.incluir(entidadeOperadora);
			
			if(!retornoRepo) {
				return "Entidade inexistente";
			}
		}
		
		return null;
	}
	
	public String excluir(int identificador) {
		boolean validarIdentificador = true;
		
		if(identificador < 1 || identificador > 99999) {
			validarIdentificador = false;
		}
		
		if(!validarIdentificador) {
			return "Ação inexistente";
		}
		else {
			boolean retornoRepo = repositorioEntidadeOperadora.excluir(identificador);
			
			if (!retornoRepo) {
				return "Ação inexistente";
			}
		}
		
		return null;
	}
	
	public EntidadeOperadora buscar(int identificador) {
		boolean validarIdentificador = true;
		
		if(identificador < 1 || identificador > 99999) {
			validarIdentificador = false;
		}
		
		if(!validarIdentificador) {
			return null;
		}
		else {
			return repositorioEntidadeOperadora.buscar(identificador);
		}
	}

}