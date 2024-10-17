package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

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
		BufferedReader reader = null;
		boolean existingDataFlag = false;
		
		try {
            reader = new BufferedReader(new FileReader("Acao.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
            	
            	String identificador = String.valueOf(acao.getIdentificador());
            	int delimitador = line.indexOf(';');
            	String lineId = line.substring(0, delimitador);
            	
                if(identificador.equals(lineId)) {
                	existingDataFlag = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
		
		finally {
			
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
		
		if(existingDataFlag == true) {
			return false;
		}
		
		try {
			FileWriter writer = new FileWriter("Acao.txt");
			writer.append(dadoAcao + "\n");
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public boolean alterar(Acao acao) {
		BufferedReader reader = null;
		boolean existingDataFlag = false;
		int lineCount = 0, lineIndex = 0;
		
		try {
            reader = new BufferedReader(new FileReader("Acao.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
            	lineCount++;
            	
            	String identificador = String.valueOf(acao.getIdentificador());
            	int delimitador = line.indexOf(';');
            	String lineId = line.substring(0, delimitador);
            	
                if(identificador.equals(lineId)) {
                	existingDataFlag = true;
                	lineIndex = lineCount - 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
		
		finally {
			
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
		
		if(existingDataFlag == false) {
			return false;
		}
		
		String [] lines = new String[lineCount];
		
		try {
            reader = new BufferedReader(new FileReader("Acao.txt"));
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {
            	lines[i] = line;
            	i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
		
		finally {
			
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
		
		String dadoAcao = acao.getIdentificador()+";"+acao.getNome()+";"+acao.getDataDeValidade()+";"+acao.getValorUnitario();
		
		lines[lineIndex] = dadoAcao;
		
		try {
			FileWriter writer = new FileWriter("Acao.txt");
			writer.write("");
			
			for(int i = 0; i < lineCount; i++) {
				writer.append(lines[i] + "\n");
			}
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public boolean excluir(int identificador) {
		return false;
	}
	public Acao buscar(int identificador) {
		return null;
	}
}
