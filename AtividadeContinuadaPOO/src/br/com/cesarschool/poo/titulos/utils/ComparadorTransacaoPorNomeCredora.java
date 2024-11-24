package br.com.cesarschool.poo.titulos.utils;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

public class ComparadorTransacaoPorNomeCredora extends ComparadorPadrao implements Comparador{

	@Override
	public int comparar(Comparavel c1, Comparavel c2) {
		String nome1 = ((Transacao) c1).getEntidadeCredito().getNome();
		String nome2 = ((Transacao) c2).getEntidadeCredito().getNome();
		return nome1.compareTo(nome2);
	}

}
