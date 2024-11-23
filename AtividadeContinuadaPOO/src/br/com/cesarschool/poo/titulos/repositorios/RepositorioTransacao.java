package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.util.ArrayList;
import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.gov.cesarschool.poo.daogenerico.Entidade;

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

public class RepositorioTransacao extends RepositorioGeral {


    public RepositorioTransacao() {
		super();
	}

    public boolean incluir(Transacao transacao) {
    	DAOSerializadorObjetos dao = getDao();
    	
        return dao.incluir(transacao);
    }


    public Transacao[] buscarPorEntidadeCredora(long identificadorEntidadeCredito) {
        DAOSerializadorObjetos dao = getDao();
        Entidade[] todasEntidades = dao.buscarTodos(); 
        ArrayList<Transacao> resultado = new ArrayList<>(); 

        for (Entidade entidade : todasEntidades) {
            Transacao transacao = (Transacao) entidade; 
            if (transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredito) {
                resultado.add(transacao); 
            }
        }

        return resultado.toArray(new Transacao[0]);
    }

    public Transacao[] buscarPorEntidadeDebito(long identificadorEntidadeDebito) {
        DAOSerializadorObjetos dao = getDao();
        Entidade[] todasEntidades = dao.buscarTodos(); 
        ArrayList<Transacao> resultado = new ArrayList<>(); 

        for (Entidade entidade : todasEntidades) {
            Transacao transacao = (Transacao) entidade; 
            if (transacao.getEntidadeDebito().getIdentificador() == identificadorEntidadeDebito) {
                resultado.add(transacao); 
            }
        }

        return resultado.toArray(new Transacao[0]); 
    }

    @Override
    public Class<?> getClasseEntidade() {
        return Transacao.class;
    }
}
