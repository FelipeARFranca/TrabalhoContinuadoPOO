package br.com.cesarschool.poo.titulos.cruds;

import java.util.Scanner;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class CrudEntidadeOperadora {

	public static void main(String[] args) {
		EntidadeOperadora entidadeOperadora;
		long identificador;
		MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstance();
		String nome;
		boolean autorizadoAcao;
		double saldoAcao;
		double saldoTituloDivida;
		String autorizadoAcaoStr;
		Scanner scanner = new Scanner(System.in);
		
		int escolha = -1;
		
		while(escolha != 0) {
			
		}
		
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
    		        identificador = scanner.nextLong();
    		        scanner.nextLine(); 

    		        System.out.print("Digite o nome: ");
    		         nome = scanner.nextLine();


    		        System.out.print("Digite o status de autorizacao (true or false): ");
    		        autorizadoAcaoStr = scanner.nextLine();
    		        
    		        autorizadoAcao = Boolean.parseBoolean(autorizadoAcaoStr);
    		        
    		        entidadeOperadora = new EntidadeOperadora(identificador,nome,autorizadoAcao);
                	
    		        System.out.print("Digite o saldo da acao: ");
    		        saldoAcao = scanner.nextDouble();
    		        
    		        System.out.print("Digite o saldo titulo divida: ");
    		        saldoTituloDivida = scanner.nextDouble();
    		        
    		        entidadeOperadora.creditarSaldoAcao(saldoAcao);
    		        entidadeOperadora.creditarSaldoTituloDivida(saldoTituloDivida);
    		       
    		        
    		        if(mediatorEntidadeOperadora.incluir(entidadeOperadora) == null) {
    		        	System.out.println("Entidade Operadora incluida com sucesso.");
    		        }else {
    		        	System.out.println(mediatorEntidadeOperadora.incluir(entidadeOperadora));
    		        }
    		        
                    break;
                case 2:
                	System.out.println("Você escolheu alterar.");
                	System.out.print("Digite o identificador: ");
    		        identificador = scanner.nextLong();
    		        scanner.nextLine(); 

    		        System.out.print("Digite o nome: ");
    		         nome = scanner.nextLine();


    		        System.out.print("Digite o status de autorizacao (true or false): ");
    		        autorizadoAcaoStr = scanner.nextLine();
    		        
    		        autorizadoAcao = Boolean.parseBoolean(autorizadoAcaoStr);
    		        
    		        entidadeOperadora = new EntidadeOperadora(identificador,nome,autorizadoAcao);
                	
    		        System.out.print("Digite o saldo da acao: ");
    		        saldoAcao = scanner.nextDouble();
    		        
    		        System.out.print("Digite o saldo titulo divida: ");
    		        saldoTituloDivida = scanner.nextDouble();
    		        
    		        entidadeOperadora.creditarSaldoAcao(saldoAcao);
    		        entidadeOperadora.creditarSaldoTituloDivida(saldoTituloDivida);
    		       
    		        
    		        if(mediatorEntidadeOperadora.alterar(entidadeOperadora) == null) {
    		        	System.out.println("Entidade Operadora alterada com sucesso.");
    		        }else {
    		        	System.out.println(mediatorEntidadeOperadora.alterar(entidadeOperadora));
    		        }
                    
                    break;
                case 3:
                	System.out.println("Você escolheu excluir.");
                	System.out.print("Digite o identificador: ");
    		        identificador = scanner.nextLong();
    		        
    		        if(mediatorEntidadeOperadora.excluir((int)identificador) == null) {
    		        	System.out.println("Entidade Operadora excluida com sucesso.");
    		        }else {
    		        	System.out.println(mediatorEntidadeOperadora.excluir((int)identificador));
    		        }
                    
                    break;
                case 4:
                    System.out.println("Você escolheu buscar.");
                    System.out.print("Digite o identificador: ");
    		        identificador = scanner.nextLong();
    		        
    		        entidadeOperadora = mediatorEntidadeOperadora.buscar((int)identificador);
    		        
    		        if(entidadeOperadora != null) {
    		        	System.out.println("Identificador: "+ entidadeOperadora.getIdentificador());
    		        	System.out.println("Nome: "+ entidadeOperadora.getNome());
    		        	System.out.println("Acao autorizada: "+ entidadeOperadora.getAutorizadoAcao());
    		        	System.out.println("Saldo Acao: "+ entidadeOperadora.getSaldoAcao());
    		        	System.out.println("Saldo Titulo Divida: "+ entidadeOperadora.getSaldoTituloDivida());
    		        }else {
    		        	System.out.println("Entidade Operadora nao encontrada.");
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
