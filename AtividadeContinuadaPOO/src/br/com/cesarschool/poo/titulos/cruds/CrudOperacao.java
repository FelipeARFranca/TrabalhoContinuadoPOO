package br.com.cesarschool.poo.titulos.cruds;

import java.util.Scanner;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

public class CrudOperacao {

	public static void main(String[] args) {
		boolean ehAcao;
		int entidadeCredito;
		int idEntidadeDebito;
		int idAcaoOuTitulo;
		double valor;
		int entidade;
		int escolha;
		String ehAcaoStr;
		Transacao[] todasTransacoes;
		Scanner scanner = new Scanner(System.in);
		MediatorOperacao mediatorOperacao = MediatorOperacao.getInstance();
		
		while (true) {
            System.out.println("======================================");
            System.out.println("           MENU PRINCIPAL            ");
            System.out.println("======================================");
            System.out.println("0 - Sair");
            System.out.println("1 - Realizar Operacao");
            System.out.println("2 - Gerar Extrato");

            System.out.print("Escolha uma opção: ");

            
            escolha = scanner.nextInt();

            switch (escolha) {
                case 0:
                    System.out.println("Saindo do programa... Até mais!");
                    scanner.close();
                    return; 
                case 1:
                	System.out.println("Você escolheu Realizar Operacao.");
                	
                	System.out.print("E uma acao?(true or false): ");
                	ehAcaoStr = scanner.nextLine();
                	
                	ehAcao = Boolean.parseBoolean(ehAcaoStr);
                	
                	System.out.print("Digite o identificador da entidade de credito: ");
                	entidadeCredito = scanner.nextInt();
                	
                	System.out.print("Digite o identificador da entidade de debito: ");
                	idEntidadeDebito = scanner.nextInt();
                	
                	System.out.print("Digite o identificador da acao ou titulo: ");
                	idAcaoOuTitulo = scanner.nextInt();
                	
                	System.out.print("Digite o valor: ");
                	valor = scanner.nextDouble();
                	
                	System.out.println(mediatorOperacao.realizarOperacao(ehAcao, entidadeCredito, idEntidadeDebito, idAcaoOuTitulo, valor));
    		        
                    break;
                case 2:
                	System.out.println("Voce escolheu gerar o extrato.");
                	
                	System.out.print("Digite o identificador da entidade: ");
                	entidade = scanner.nextInt();
                	
                	todasTransacoes = mediatorOperacao.gerarExtrato(entidade);
                	
                	if (todasTransacoes.length == 0) {
                        System.out.println("Nenhuma transação encontrada para essa entidade.");
                    } else {
                        System.out.println("Extrato de transações:");
                        for (Transacao transacao : todasTransacoes) {
                            System.out.println("--------------------------------------------------");
                            System.out.println("Entidade de Crédito: " + transacao.getEntidadeCredito().getNome());
                            System.out.println("Entidade de Débito: " + transacao.getEntidadeDebito().getNome());
                            if (transacao.getAcao() != null) {
                                System.out.println("Ação: " + transacao.getAcao().getNome());
                            } else if (transacao.getTituloDivida() != null) {
                                System.out.println("Título de Dívida: " + transacao.getTituloDivida().getNome());
                            }
                            System.out.println("Valor da Operação: " + transacao.getValorOperacao());
                            System.out.println("Data e Hora da Operação: " + transacao.getDataHoraOperacao());
                        }
                        System.out.println("--------------------------------------------------");
                    }
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                    break;
            }

            System.out.println(); 
        }

	}

}
