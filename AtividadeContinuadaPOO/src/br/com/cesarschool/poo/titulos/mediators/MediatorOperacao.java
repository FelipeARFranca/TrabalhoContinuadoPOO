package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/*
 * Deve ser um singleton.
 * 
 * Deve ter os seguites atributos: 
 * 
 * mediatorAcao, do tipo MediatorAcao
 * mediatorTituloDivida, do tipo MediatorTituloDivida
 * mediatorEntidadeOperadora, do tipo MediatorEntidadeOperadora
 * repositorioTransacao, do tipo RepositorioTransacao
 * 
 * Estes atributos devem ser inicializados nas suas declarações.
 * 
 * Estes atributos serão usados exclusivamente pela classe, não tendo, portanto, métodos set e get.
 * 
 * Métodos: 
 * 
 * public String realizarOperacao(boolean ehAcao, int entidadeCredito, 
 * int idEntidadeDebito, int idAcaoOuTitulo, double valor): segue o 
 * passo a passo deste método.
 * 
 * 1- Validar o valor, que deve ser maior que zero. Se o valor for inválido,
 * retornar a mensagem "Valor inválido".
 * 
 * 2- Buscar a entidade de crédito no mediator de entidade operadora. 
 * Se não encontrar, retornar a mensagem "Entidade crédito inexistente".
 * 
 * 3- Buscar a entidade de débito no mediator de entidade operadora. 
 * Se não encontrar, retornar a mensagem "Entidade débito inexistente".
 * 
 * 4- Se ehAcao for true e a entidade de crédito não for autorizada para 
 * ação, retornar a mensagem "Entidade de crédito não autorizada para ação"
 *
 * 5- Se ehAcao for true e a entidade de débito não for autorizada para 
 * ação, retornar a mensagem "Entidade de débito não autorizada para ação"
 *
 * 6- Buscar a ação (se ehAcao for true) ou o título (se ehAcao for false)
 * no mediator de ação ou de título. 
 *
 * 7- A depender de ehAcao, validar o saldoAcao ou o saldoTituloDivida da 
 * entidade de débito. O saldo deve ser maior ou igual a valor. Se não for,
 * retornar a mensagem "Saldo da entidade débito insuficiente".
 * 
 * 8- Se ehAcao for true, verificar se valorUnitario da ação é maior do que
 * valor. Se for, retornar a mensagem 
 * "Valor da operação e menor do que o valor unitário da ação"
 * 
 * 9- Calcular o valor da operação. Se ehAcao for true, o valor da operação
 * é igual a valor. Se ehAcao for false, o valor da operação é igual ao 
 * retorno do método calcularPrecoTransacao, invocado a partir do título da 
 * dívida buscado. valor deve ser passado como parâmetro na invocação deste
 * método.
 * 
 *  10- Invocar o método creditarSaldoAcao ou creditarSaldoTituloDivida da 
 *  entidade de crédito, passando o valor da operação.
 *  
 *  11- Invocar o método debitarSaldoAcao ou debitarSaldoTituloDivida da 
 *  entidade de débito, passando o valor da operação.
 *  
 *  12- Alterar entidade de crédito no mediator de entidade operadora. Se
 *  o retorno do alterar for uma mensagem diferente de null, retornar a 
 *  mensagem. 
 *  
 *  13- Alterar entidade de débito no mediator de entidade operadora. Se
 *  o retorno do alterar for uma mensagem diferente de null, retornar a 
 *  mensagem. 
 *  
 *  14- Criar um objeto do tipo Transacao, com os seguintes valores de atributos:
 *  
 * entidadeCredito - a entidade de crédito buscada
 * entidadeDebito - a entidade de débito buscada
 * acao - se ehAcao for true, a ação buscada, caso contrário, null
 * tituloDivida - se ehAcao for false, o título buscado, caso contrário, null
 * valorOperacao - o valor da operação calculado no item 9
 * dataHoraOperacao - a data e a hora atuais
 * 
 * 15- Incluir a transação criada no repositório de transação.
 * 
 * public Transacao gerarExtrato(int entidade): 
 * 
 * 1- para este método funcionar, deve-se acrescentar no repositório de 
 * transação o método  
 * public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeDebito)
 * A busca deve retornar um array de transações cuja entidadeDebito tenha identificador igual ao
 * recebido como parâmetro. 
 *  
 * 2- Buscar as transações onde entidade é credora.
 * 
 * 3- Buscar as transações onde entidade é devedora.
 * 
 * 4- Colocar as transações buscadas nos itens 2 e 3 em um único novo array.
 * 
 * 5- Ordenar este novo array por dataHoraOperacao decrescente.
 * 
 * 6- Retornar o novo array.
 **/
public class MediatorOperacao {

    private static MediatorOperacao instancia;

    private MediatorAcao mediatorAcao = MediatorAcao.getInstance();
    private MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstance();
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstance();
    private RepositorioTransacao repositorioTransacao = new RepositorioTransacao();

    private MediatorOperacao() {}

    public static MediatorOperacao getInstance() {
        if (instancia == null) {
            instancia = new MediatorOperacao();
        }
        return instancia;
    }

    public String realizarOperacao(boolean ehAcao, int entidadeCredito, int idEntidadeDebito, int idAcaoOuTitulo, double valor) {
        if (valor <= 0) {
            return "Valor inválido";
        }

        EntidadeOperadora credito = mediatorEntidadeOperadora.buscar(entidadeCredito);
        if (credito == null) {
            return "Entidade crédito inexistente";
        }

        EntidadeOperadora debito = mediatorEntidadeOperadora.buscar(idEntidadeDebito);
        if (debito == null) {
            return "Entidade débito inexistente";
        }

        if (ehAcao && !credito.getAutorizadoAcao()) {
            return "Entidade de crédito não autorizada para ação";
        }

        if (ehAcao && !debito.getAutorizadoAcao()) {
            return "Entidade de débito não autorizada para ação";
        }
        
        Acao acao = null;
        TituloDivida tituloDivida = null;
        if (ehAcao) {
            acao = mediatorAcao.buscar(idAcaoOuTitulo);
        } else {
            tituloDivida = mediatorTituloDivida.buscar(idAcaoOuTitulo);
        }
        
        if (ehAcao) {
            if (debito.getSaldoAcao() < valor) {
                return "Saldo da entidade débito insuficiente";
            }
        } else {
            if (debito.getSaldoTituloDivida() < valor) {
                return "Saldo da entidade débito insuficiente";
            }
        }

        if (ehAcao && acao.getValorUnitario() > valor && acao != null) {
            return "Valor da operação é menor do que o valor unitário da ação";
        }

        double valorOperacao;
        if (ehAcao) {
            valorOperacao = valor;
        } else {
            valorOperacao = tituloDivida.calcularPrecoTransacao(valor);
        }

        if (ehAcao) {
            credito.creditarSaldoAcao(valorOperacao);
        } else {
            credito.creditarSaldoTituloDivida(valorOperacao);
        }

        if (ehAcao) {
            debito.debitarSaldoAcao(valorOperacao);
        } else {
            debito.debitarSaldoTituloDivida(valorOperacao);
        }

        String retornoCredito = mediatorEntidadeOperadora.alterar(credito);
        if (retornoCredito != null) {
            return retornoCredito;
        }

        String retornoDebito = mediatorEntidadeOperadora.alterar(debito);
        if (retornoDebito != null) {
            return retornoDebito;
        }

        Transacao transacao = new Transacao(
            credito, debito, ehAcao ? acao : null, !ehAcao ? tituloDivida : null, valorOperacao, LocalDateTime.now()
        );

        repositorioTransacao.incluir(transacao);
        
        return "Operação realizada com sucesso";
    }

    public Transacao[] gerarExtrato(int entidade) {
        Transacao[] transacoesCredora = repositorioTransacao.buscarPorEntidadeCredora(entidade);

        Transacao[] transacoesDevedora = repositorioTransacao.buscarPorEntidadeDebito(entidade);

        int tamanhoTotal = transacoesCredora.length + transacoesDevedora.length;
        Transacao[] todasTransacoes = new Transacao[tamanhoTotal];

        System.arraycopy(transacoesCredora, 0, todasTransacoes, 0, transacoesCredora.length);

        System.arraycopy(transacoesDevedora, 0, todasTransacoes, transacoesCredora.length, transacoesDevedora.length);

        Arrays.sort(todasTransacoes, Comparator.comparing(Transacao::getDataHoraOperacao).reversed());

        return todasTransacoes;
    }
}
