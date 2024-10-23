package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidadeDebito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De tituloDivida: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 * 
 *   002192;BCB;true;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;1;PETROBRAS;2024-12-12;30.33;null;100000.0;2024-01-01 12:22:21 
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclusão deve adicionar uma nova linha ao arquivo. 
 * 
 * A busca deve retornar um array de transações cuja entidadeCredito tenha identificador igual ao
 * recebido como parâmetro.  
 */

public class RepositorioTransacao {

    public boolean incluir(Transacao transacao) {
        String dadoTransacao = montarLinhaTransacao(transacao);

        try (FileWriter writer = new FileWriter("Transacao.txt", true)) { 
            writer.append(dadoTransacao).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
        List<Transacao> transacoes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Transacao.txt"))) { 
            String line;

            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                if (Integer.parseInt(dados[0]) == identificadorEntidadeCredito) {
                    Transacao transacao = montarTransacao(dados);
                    transacoes.add(transacao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transacoes.toArray(new Transacao[0]);
    }
    
    public Transacao[] buscarPorEntidadeDebito(int identificadorEntidadeDebito) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Transacao.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                if (Integer.parseInt(dados[5]) == identificadorEntidadeDebito) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Transacao[0]; 
        }

        if (count == 0) {
            return new Transacao[0];
        }

        Transacao[] transacoes = new Transacao[count];
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Transacao.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                if (Integer.parseInt(dados[5]) == identificadorEntidadeDebito) {
                    Transacao transacao = montarTransacao(dados);
                    transacoes[index++] = transacao;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Transacao[0]; 
        }

        return transacoes;
    }


    private String montarLinhaTransacao(Transacao transacao) {
        StringBuilder sb = new StringBuilder();

        sb.append(transacao.getEntidadeCredito().getIdentificador()).append(";")
          .append(transacao.getEntidadeCredito().getNome()).append(";")
          .append(transacao.getEntidadeCredito().getAutorizadoAcao()).append(";")
          .append(transacao.getEntidadeCredito().getSaldoAcao()).append(";")
          .append(transacao.getEntidadeCredito().getSaldoTituloDivida()).append(";");

        sb.append(transacao.getEntidadeDebito().getIdentificador()).append(";")
          .append(transacao.getEntidadeDebito().getNome()).append(";")
          .append(transacao.getEntidadeDebito().getAutorizadoAcao()).append(";")
          .append(transacao.getEntidadeDebito().getSaldoAcao()).append(";")
          .append(transacao.getEntidadeDebito().getSaldoTituloDivida()).append(";");

        if (transacao.getAcao() != null) {
            sb.append(transacao.getAcao().getIdentificador()).append(";")
              .append(transacao.getAcao().getNome()).append(";")
              .append(transacao.getAcao().getDataDeValidade()).append(";")
              .append(transacao.getAcao().getValorUnitario()).append(";");
        } else {
            sb.append("null;");
        }

        if (transacao.getTituloDivida() != null) {
            sb.append(transacao.getTituloDivida().getIdentificador()).append(";")
              .append(transacao.getTituloDivida().getNome()).append(";")
              .append(transacao.getTituloDivida().getDataDeValidade()).append(";")
              .append(transacao.getTituloDivida().getTaxaJuros()).append(";");
        } else {
            sb.append("null;");
        }

        sb.append(transacao.getValorOperacao()).append(";")
          .append(transacao.getDataHoraOperacao());

        return sb.toString();
    }

    private Transacao montarTransacao(String[] dados) {
        EntidadeOperadora entidadeCredito = new EntidadeOperadora(
            Long.parseLong(dados[0]), dados[1], Boolean.parseBoolean(dados[2])
        );
        entidadeCredito.creditarSaldoAcao(Double.parseDouble(dados[3]));
        entidadeCredito.creditarSaldoTituloDivida(Double.parseDouble(dados[4]));

        EntidadeOperadora entidadeDebito = new EntidadeOperadora(
            Long.parseLong(dados[5]), dados[6], Boolean.parseBoolean(dados[7])
        );
        entidadeDebito.creditarSaldoAcao(Double.parseDouble(dados[8]));
        entidadeDebito.creditarSaldoTituloDivida(Double.parseDouble(dados[9]));

        Acao acao = null;
        if (!dados[10].equals("null")) {
            acao = new Acao(
                Integer.parseInt(dados[10]), dados[11], LocalDate.parse(dados[12]),
                Double.parseDouble(dados[13])
            );
        }

        TituloDivida tituloDivida = null;
        if (!dados[14].equals("null")) {
            tituloDivida = new TituloDivida(
                Integer.parseInt(dados[14]), dados[15], LocalDate.parse(dados[16]),
                Double.parseDouble(dados[17])
            );
        }

        double valorOperacao = Double.parseDouble(dados[15]);
        LocalDateTime dataHoraOperacao = LocalDateTime.parse(dados[16]);

        return new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
    }

}
