package br.com.cesarschool.poo.titulos.relatorios;

import java.util.ArrayList;
import java.util.List;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.utils.ComparadorTransacaoPorNomeCredora;
import br.com.cesarschool.poo.titulos.utils.Ordenador;
import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.gov.cesarschool.poo.daogenerico.Entidade;

public class RelatorioTransacaoBroker {
	public Transacao [] relatorioTransacaoPorNomeEntidadeCredora() {
		DAOSerializadorObjetos dao = new DAOSerializadorObjetos(Transacao.class);
		Entidade [] todasEntidades = dao.buscarTodos();
		
		List<Transacao> todasTransacoes = new ArrayList();
		
		
		for(Entidade entidade : todasEntidades) {
			todasTransacoes.add((Transacao) entidade);
		}
		
		Transacao [] todasTransacoesOrdenada = todasTransacoes.toArray(new Transacao [0]);
		
		ComparadorTransacaoPorNomeCredora comp = new ComparadorTransacaoPorNomeCredora();
		
		Ordenador.ordenar(todasTransacoesOrdenada, comp);
		
		return todasTransacoesOrdenada;
	}
	
	public Transacao [] relatorioTransacaoPorDataHora() {
		DAOSerializadorObjetos dao = new DAOSerializadorObjetos(Transacao.class);
		Entidade [] todasEntidades = dao.buscarTodos();
		
		List<Transacao> todasTransacoes = new ArrayList();
		
		
		for(Entidade entidade : todasEntidades) {
			todasTransacoes.add((Transacao) entidade);
		}
		
		Transacao [] todasTransacoesOrdenada = todasTransacoes.toArray(new Transacao [0]);
		
		Ordenador.ordenar(todasTransacoesOrdenada);
		
		return todasTransacoesOrdenada;
	}
}

