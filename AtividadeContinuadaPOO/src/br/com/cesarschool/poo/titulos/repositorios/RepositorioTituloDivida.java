package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5 
 * 
 * A inclusão deve adicionar uma nova linha ao arquivo. Não é permitido incluir 
 * identificador repetido. Neste caso, o método deve retornar false. Inclusão com 
 * sucesso, retorno true.
 * 
 * A alteração deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando não encontrado, enseja retorno false. 
 * Alteração com sucesso, retorno true.  
 *   
 * A exclusão deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando não encontrado, enseja retorno false. 
 * Exclusão com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador não seja encontrado no arquivo, retornar null.   
 */

public class RepositorioTituloDivida {

    public boolean incluir(TituloDivida tituloDivida) {

        String dadoTituloDivida = tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" 
                                  + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros();
        boolean existingDataFlag = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("TituloDivida.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String identificador = String.valueOf(tituloDivida.getIdentificador());
                String lineId = line.substring(0, line.indexOf(';'));

                if (identificador.equals(lineId)) {
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

        try (FileWriter writer = new FileWriter("TituloDivida.txt", true)) {
            writer.append(dadoTituloDivida + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean alterar(TituloDivida tituloDivida) {

        boolean existingDataFlag = false;
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("TituloDivida.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String identificador = String.valueOf(tituloDivida.getIdentificador());
                String lineId = line.substring(0, line.indexOf(';'));

                if (identificador.equals(lineId)) {
                    existingDataFlag = true;
                    content.append(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" 
                                   + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros())
                           .append("\n");
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

        try (FileWriter writer = new FileWriter("TituloDivida.txt")) {
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

        try (BufferedReader reader = new BufferedReader(new FileReader("TituloDivida.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String lineId = line.substring(0, line.indexOf(';'));

                if (!lineId.equals(String.valueOf(identificador))) {
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

        try (FileWriter writer = new FileWriter("TituloDivida.txt")) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public TituloDivida buscar(int identificador) {

        try (BufferedReader reader = new BufferedReader(new FileReader("TituloDivida.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String lineId = line.substring(0, line.indexOf(';'));

                if (lineId.equals(String.valueOf(identificador))) {
                    String[] dados = line.split(";");
                    String nome = dados[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dataValidade = LocalDate.parse(dados[2], formatter);
                    double taxaJuros = Double.parseDouble(dados[3]);

                    return new TituloDivida(identificador, nome, dataValidade, taxaJuros);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
