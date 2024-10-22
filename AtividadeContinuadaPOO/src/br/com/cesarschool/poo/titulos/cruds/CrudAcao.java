package br.com.cesarschool.poo.titulos.cruds;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

import java.time.LocalDate;
import java.util.Scanner;

public class CrudAcao {

	public static void main(String[] args) {
		Acao acao;
		int identificador;
		MediatorAcao mediatorAcao = MediatorAcao.getInstance();
		String nome;
		String dataStr;
		LocalDate dataDeValidade;
		double valorUnitario;
		
		Scanner scanner = new Scanner(System.in);
		
		int escolha = -1;
		
		while (true) {
            System.out.println("======================================");
            System.out.println("           MENU PRINCIPAL            ");
            System.out.println("======================================");
            System.out.println("0 - Sair");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar");
            System.out.print("Escolha uma opção: ");

            
            escolha = scanner.nextInt();

            switch (escolha) {
                case 0:
                    System.out.println("Saindo do programa... Até mais!");
                    scanner.close();
                    return; 
                case 1:
                	System.out.println("Você escolheu incluir.");
                	System.out.print("Digite o identificador: ");
    		        identificador = scanner.nextInt();
    		        scanner.nextLine(); 

    		        System.out.print("Digite o nome: ");
    		         nome = scanner.nextLine();

    		        System.out.print("Digite a data de validade (AAAA-MM-DD): ");
    		        dataStr = scanner.nextLine();
    		        dataDeValidade = LocalDate.parse(dataStr);

    		        System.out.print("Digite o valor unitário: ");
    		        valorUnitario = scanner.nextDouble();

    		        acao = new Acao(identificador, nome, dataDeValidade, valorUnitario);
                	
    		        if(mediatorAcao.incluir(acao) == null) {
    		        	System.out.println("Acao incluida com sucesso.");
    		        }else {
    		        	System.out.println(mediatorAcao.incluir(acao));
    		        }
    		        
                    break;
                case 2:
                	System.out.println("Você escolheu alterar.");
                	System.out.print("Digite o identificador: ");
    		        identificador = scanner.nextInt();
    		        scanner.nextLine(); 

    		        System.out.print("Digite o nome: ");
    		        nome = scanner.nextLine();

    		        System.out.print("Digite a data de validade (AAAA-MM-DD): ");
    		        dataStr = scanner.nextLine();
    		        dataDeValidade = LocalDate.parse(dataStr);

    		        System.out.print("Digite o valor unitário: ");
    		        valorUnitario = scanner.nextDouble();

    		        acao = new Acao(identificador, nome, dataDeValidade, valorUnitario);
    		        
    		        if(mediatorAcao.alterar(acao) == null) {
    		        	System.out.println("Acao alterada com sucesso.");
    		        }else {
    		        	System.out.println(mediatorAcao.alterar(acao));
    		        }
                    
                    break;
                case 3:
                	System.out.println("Você escolheu excluir.");
                	System.out.print("Digite o identificador: ");
    		        identificador = scanner.nextInt();
    		        
    		        if(mediatorAcao.excluir(identificador) == null) {
    		        	System.out.println("Acao excluida com sucesso.");
    		        }else {
    		        	System.out.println(mediatorAcao.excluir(identificador));
    		        }
                    
                    break;
                case 4:
                    System.out.println("Você escolheu buscar.");
                    System.out.print("Digite o identificador: ");
    		        identificador = scanner.nextInt();
    		        
    		        acao = mediatorAcao.buscar(identificador);
    		        
    		        if(acao != null) {
    		        	System.out.println("Identificador: "+ acao.getIdentificador());
    		        	System.out.println("Nome: "+ acao.getNome());
    		        	System.out.println("Data de Validade: "+ acao.getDataDeValidade());
    		        	System.out.println("Valor Unitario: "+ acao.getValorUnitario());
    		        }else {
    		        	System.out.println("Acao nao encontrada.");
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
