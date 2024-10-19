package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Esta classe deve ter os seguintes atributos:
 * entidadeCredito, do tipo EntidadeOperadora
 * entidadeDebito, do tipo EntidadeOperadora
 * acao, do tipo Acao
 * tituloDivida, do tipo TituloDivida
 * valorOperacao, do tipo double
 * dataHoraOperacao, do tipo LocalDateTime
 *  
 * Deve ter um construtor p�blico que inicializa os atributos.
 * Deve ter m�todos get/set p�blicos para todos os atributos, que 
 * s�o read-only fora da classe.
 * 
 *  
 */ 
public class Transacao {
	private EntidadeOperadora entidadeCredito;
	private EntidadeOperadora entidadeDebito;
	private Acao acao;
	private TituloDivida tituloDivida;
	private double valorOperacao;
	private LocalDateTime dataHoraOperacao;
	
	public Transacao (EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao, TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
		this.entidadeCredito = entidadeCredito;
		this.entidadeDebito = entidadeDebito;
		this.acao = acao;
		this.tituloDivida = tituloDivida;
		this.valorOperacao = valorOperacao;
		this.dataHoraOperacao = dataHoraOperacao;
	}

	public EntidadeOperadora getEntidadeCredito() {
		return entidadeCredito;
	}

	private void setEntidadeCredito(EntidadeOperadora entidadeCredito) {
		this.entidadeCredito = entidadeCredito;
	}

	public EntidadeOperadora getEntidadeDebito() {
		return entidadeDebito;
	}

	private void setEntidadeDebito(EntidadeOperadora entidadeDebito) {
		this.entidadeDebito = entidadeDebito;
	}

	public Acao getAcao() {
		return acao;
	}

	private void setAcao(Acao acao) {
		this.acao = acao;
	}

	public TituloDivida getTituloDivida() {
		return tituloDivida;
	}

	private void setTituloDivida(TituloDivida tituloDivida) {
		this.tituloDivida = tituloDivida;
	}

	public double getValorOperacao() {
		return valorOperacao;
	}

	private void setValorOperacao(double valorOperacao) {
		this.valorOperacao = valorOperacao;
	}

	public LocalDateTime getDataHoraOperacao() {
		return dataHoraOperacao;
	}

	private void setDataHoraOperacao(LocalDateTime dataHoraOperacao) {
		this.dataHoraOperacao = dataHoraOperacao;
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        sb.append("Transação:\n");
        sb.append("Entidade Crédito: ").append(entidadeCredito.getNome())
          .append(" (ID: ").append(entidadeCredito.getIdentificador()).append(")\n");
        sb.append("Entidade Débito: ").append(entidadeDebito.getNome())
          .append(" (ID: ").append(entidadeDebito.getIdentificador()).append(")\n");
        
        if (acao != null) {
            sb.append("Ação: ").append(acao.getNome())
              .append(" (ID: ").append(acao.getIdentificador()).append(")\n");
            sb.append("Data de Validade da Ação: ").append(acao.getDataDeValidade()).append("\n");
            sb.append("Valor Unitário da Ação: ").append(acao.getValorUnitario()).append("\n");
        } else {
            sb.append("Ação: null\n");
        }
        
        if (tituloDivida != null) {
            sb.append("Título de Dívida: ").append(tituloDivida.getNome())
              .append(" (ID: ").append(tituloDivida.getIdentificador()).append(")\n");
            sb.append("Data de Validade do Título: ").append(tituloDivida.getDataDeValidade()).append("\n");
            sb.append("Taxa de Juros: ").append(tituloDivida.getTaxaJuros()).append("\n");
        } else {
            sb.append("Título de Dívida: null\n");
        }
        
        sb.append("Valor da Operação: ").append(valorOperacao).append("\n");
        sb.append("Data e Hora da Operação: ").append(dataHoraOperacao.format(formatter)).append("\n");

        return sb.toString();
    }
}
