package br.gov.cesarschool.poo.daogenerico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Esta classe representa um DAO genérico, que inclui, exclui, altera, busca por identificador 
 * único e busca todos, qualquer objeto(s) cujo tipo é subtipo de Entidade.
 * 
 * Sugerimos o uso da API de serialização do JAVA, que grava e lê objetos em arquvos. 
 * Lembrar sempre de fechar (em qualquer circunstância) streams JAVA abertas.
 * 
 * As nuances mais detalhadas do funcionamento desta classe está especificada na classe de testes
 * automatizados br.gov.cesarschool.poo.testes.TestesDAOSerializador.    
 * 
 * A classe deve ter a estrutura (métodos e construtores) dada abaixo.
 */
public class DAOSerializadorObjetos {
	private String nomeDiretorio; 
	public DAOSerializadorObjetos(Class<?> tipoEntidade) {
		this.nomeDiretorio = "." + File.separator + tipoEntidade.getSimpleName();
	}
	public boolean incluir(Entidade entidade) {
		File diretorio = new File(nomeDiretorio + File.separator);
		
		if(!diretorio.exists()) {
			diretorio.mkdirs();
		}
		
		File arquivo = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
		
		if (arquivo.exists()) {
			return false;
		}
		else {
			entidade.setDataHoraInclusao(LocalDateTime.now());
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
	            oos.writeObject(entidade);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
			
			return true;
		}
	}
	public boolean alterar(Entidade entidade) {
		
		File diretorio = new File(nomeDiretorio + File.separator);
		
		if(!diretorio.exists()) {
			diretorio.mkdirs();
		}
		
		File arquivo = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
		
		if (!arquivo.exists()) {
			return false;
		}
		else {
			arquivo.delete();
			
			arquivo = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
			entidade.setDataHoraUltimaAlteracao(LocalDateTime.now());
			
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
	            oos.writeObject(entidade);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
			
			return true;
		}
	}
	public boolean excluir(String idUnico) {
		
		File diretorio = new File(nomeDiretorio + File.separator);
		
		if(!diretorio.exists()) {
			diretorio.mkdirs();
		}
		
		File arquivo = new File(nomeDiretorio + File.separator + idUnico);
		
		if (!arquivo.exists()) {
			return false;
		}
		else {
			arquivo.delete();
			return true;
		}
	}
	public Entidade buscar(String idUnico) {
		
		File diretorio = new File(nomeDiretorio + File.separator);
		
		if(!diretorio.exists()) {
			diretorio.mkdirs();
		}
		
		File arquivo = new File(nomeDiretorio + File.separator + idUnico);
		
		if (!arquivo.exists()) {
			return null;
		}
		else {
		
			Entidade entidade = null;
			
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
	            entidade = (Entidade) ois.readObject();
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return null;
	        }
			
			return entidade;
		}
	}
	public Entidade[] buscarTodos() {
		
		File diretorio = new File(nomeDiretorio + File.separator);
		
		if(!diretorio.exists()) {
			diretorio.mkdirs();
		}
		
		List<Entidade> entidadesList = new ArrayList<>();
		
		Entidade [] entidades = new Entidade[0];
		Entidade entidade = null;
		
		File [] arquivos = diretorio.listFiles();
		
		if (arquivos != null) {
			for (File arquivo : arquivos) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
		            entidade = (Entidade) ois.readObject();
		        } catch (IOException | ClassNotFoundException e) {
		            e.printStackTrace();
		        }
				entidadesList.add(entidade);
			}
			
			return entidadesList.toArray(new Entidade[0]);
		}
		else {
			return entidades;
		}
		
	}
}
