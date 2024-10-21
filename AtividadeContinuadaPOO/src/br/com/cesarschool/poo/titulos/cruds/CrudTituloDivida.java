package br.com.cesarschool.poo.titulos.cruds;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class CrudTituloDivida {

    public static void main(String[] args) {
        int identificador;
        String nome;
        LocalDate dataDeValidade;
        double taxaJuros;
        int escolha;
        Scanner scanner = new Scanner(System.in);
        MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.println("======================================");
            System.out.println("         MENU - TÍTULO DE DÍVIDA      ");
            System.out.println("======================================");
            System.out.println("0 - Sair");
            System.out.println("1 - Incluir Título de Dívida");
            System.out.println("2 - Alterar Título de Dívida");
            System.out.println("3 - Excluir Título de Dívida");
            System.out.println("4 - Buscar Título de Dívida");

            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 0:
                    System.out.println("Saindo do programa... Até mais!");
                    scanner.close();
                    return;
                case 1:
                    System.out.println("Você escolheu Incluir Título de Dívida.");

                    System.out.print("Digite o identificador (1-99999): ");
                    identificador = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite o nome do título: ");
                    nome = scanner.nextLine();

                    System.out.print("Digite a data de validade (dd/MM/yyyy): ");
                    dataDeValidade = LocalDate.parse(scanner.nextLine(), formatter);

                    System.out.print("Digite a taxa de juros: ");
                    taxaJuros = scanner.nextDouble();

                    TituloDivida tituloDivida = new TituloDivida(identificador, nome, dataDeValidade, taxaJuros);
                    String resultadoIncluir = mediatorTituloDivida.incluir(tituloDivida);
                    if (resultadoIncluir == null) {
                        System.out.println("Título de dívida incluído com sucesso.");
                    } else {
                        System.out.println("Erro ao incluir título de dívida: " + resultadoIncluir);
                    }
                    break;
                case 2:
                    System.out.println("Você escolheu Alterar Título de Dívida.");

                    System.out.print("Digite o identificador do título para alterar: ");
                    identificador = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Digite o novo nome do título: ");
                    nome = scanner.nextLine();

                    System.out.print("Digite a nova data de validade (dd/MM/yyyy): ");
                    dataDeValidade = LocalDate.parse(scanner.nextLine(), formatter);

                    System.out.print("Digite a nova taxa de juros: ");
                    taxaJuros = scanner.nextDouble();

                    TituloDivida tituloParaAlterar = new TituloDivida(identificador, nome, dataDeValidade, taxaJuros);
                    String resultadoAlterar = mediatorTituloDivida.alterar(tituloParaAlterar);
                    if (resultadoAlterar == null) {
                        System.out.println("Título de dívida alterado com sucesso.");
                    } else {
                        System.out.println("Erro ao alterar título de dívida: " + resultadoAlterar);
                    }
                    break;
                case 3:
                    System.out.println("Você escolheu Excluir Título de Dívida.");

                    System.out.print("Digite o identificador do título para excluir: ");
                    identificador = scanner.nextInt();

                    String resultadoExcluir = mediatorTituloDivida.excluir(identificador);
                    if (resultadoExcluir == null) {
                        System.out.println("Título de dívida excluído com sucesso.");
                    } else {
                        System.out.println("Erro ao excluir título de dívida: " + resultadoExcluir);
                    }
                    break;
                case 4:
                    System.out.println("Você escolheu Buscar Título de Dívida.");

                    System.out.print("Digite o identificador do título para buscar: ");
                    identificador = scanner.nextInt();

                    TituloDivida tituloBuscado = mediatorTituloDivida.buscar(identificador);
                    if (tituloBuscado == null) {
                        System.out.println("Título de dívida não encontrado.");
                    } else {
                        System.out.println("Título encontrado:");
                        System.out.println("Identificador: " + tituloBuscado.getIdentificador());
                        System.out.println("Nome: " + tituloBuscado.getNome());
                        System.out.println("Data de Validade: " + tituloBuscado.getDataDeValidade().format(formatter));
                        System.out.println("Taxa de Juros: " + tituloBuscado.getTaxaJuros());
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
