package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
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

public class RepositorioAcao { 

	public boolean incluir(Acao acao) {

		String dadoAcao = acao.getIdentificador()+";"+acao.getNome()+";"+acao.getDataDeValidade()+";"+acao.getValorUnitario();
		boolean existingDataFlag = false;

		try (BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String identificador = String.valueOf(acao.getIdentificador());
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

		try (FileWriter writer = new FileWriter("Acao.txt", true)) {
			writer.append(dadoAcao + "\n");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean alterar(Acao acao) {
		boolean existingDataFlag = false;
		StringBuilder content = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String identificador = String.valueOf(acao.getIdentificador());
				String lineId = line.substring(0, line.indexOf(';'));

				if(identificador.equals(lineId)) {
					existingDataFlag = true;
					content.append(acao.getIdentificador()+";"+acao.getNome()+";"+acao.getDataDeValidade()+";"+acao.getValorUnitario()).append("\n");
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

		try (FileWriter writer = new FileWriter("Acao.txt")) {
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

		try (BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"))) {
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

		try (FileWriter writer = new FileWriter("Acao.txt")) {
			writer.write(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Acao buscar(int identificador) {
		try (BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String lineId = line.substring(0, line.indexOf(';'));

				if(lineId.equals(String.valueOf(identificador))) {
					String[] dados = line.split(";");
					String nome = dados[1];
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate dataDeValidade = LocalDate.parse(dados[2], formatter);
					double valorUnitario = Double.parseDouble(dados[3]);

					return new Acao(identificador, nome, dataDeValidade, valorUnitario);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
