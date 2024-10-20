package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas.
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */
public class RepositorioEntidadeOperadora {
	
	public boolean incluir(EntidadeOperadora entidadeOperadora) {

		String dadoAcao = entidadeOperadora.getIdentificador()+";"+entidadeOperadora.getNome()+";"+entidadeOperadora.getAutorizadoAcao()+";"+entidadeOperadora.getSaldoAcao()+";"+entidadeOperadora.getSaldoTituloDivida();
		boolean existingDataFlag = false;

		try (BufferedReader reader = new BufferedReader(new FileReader("EntidadeOperadora.txt"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String identificador = String.valueOf(entidadeOperadora.getIdentificador());
				String lineId = line.substring(0, line.indexOf(';'));

				if(identificador.equals(lineId)) {
					existingDataFlag = true;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (existingDataFlag) {
			return false;
		}

		try (FileWriter writer = new FileWriter("EntidadeOperadora.txt", true)) {
			writer.append(dadoAcao + "\n");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean alterar(EntidadeOperadora entidadeOperadora) {
		boolean existingDataFlag = false;
		StringBuilder content = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader("EntidadeOperadora.txt"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String identificador = String.valueOf(entidadeOperadora.getIdentificador());
				String lineId = line.substring(0, line.indexOf(';'));

				if(identificador.equals(lineId)) {
					existingDataFlag = true;
					content.append(entidadeOperadora.getIdentificador()+";"+entidadeOperadora.getNome()+";"+entidadeOperadora.getAutorizadoAcao()+";"+entidadeOperadora.getSaldoAcao()+";"+entidadeOperadora.getSaldoTituloDivida()).append("\n");
				} else {
					content.append(line).append("\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (!existingDataFlag) {
			return false;
		}

		try (FileWriter writer = new FileWriter("EntidadeOperadora.txt")) {
			writer.write(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean excluir(int identificador) {
		boolean existingDataFlag = false;
		StringBuilder content = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader("EntidadeOperadora.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String lineId = line.substring(0, line.indexOf(';'));

				if(!lineId.equals(String.valueOf(identificador))) {
					content.append(line).append("\n");
				} else {
					existingDataFlag = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (!existingDataFlag) {
			return false;
		}

		try (FileWriter writer = new FileWriter("EntidadeOperadora.txt")) {
			writer.write(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public EntidadeOperadora buscar(int identificador) {
		try (BufferedReader reader = new BufferedReader(new FileReader("EntidadeOperadora.txt"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String lineId = line.substring(0, line.indexOf(';'));

				if(lineId.equals(String.valueOf(identificador))) {
					
					EntidadeOperadora entidadeOperadora = null;
					
					String[] dados = line.split(";");
					String nome = dados[1];
					boolean autorizadoAcao = Boolean.parseBoolean(dados[2]);
					double saldoAcao = Double.parseDouble(dados[3]);
					double saldoTituloDivida = Double.parseDouble(dados[4]);
					
					entidadeOperadora = new EntidadeOperadora(identificador, nome, autorizadoAcao);
					
					entidadeOperadora.creditarSaldoAcao(saldoAcao);
					entidadeOperadora.creditarSaldoTituloDivida(saldoTituloDivida);

					return entidadeOperadora;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
