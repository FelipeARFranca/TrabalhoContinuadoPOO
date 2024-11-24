package br.com.cesarschool.poo.titulos.utils;

public class Ordenador {
	public static void ordenar(Comparavel[] ents, Comparador comp) {
		int k = 1;
		boolean troca = false;
		
		for (int i = 0; i <= ents.length; i++) {
			troca = false;
			for (int j = 0; j < ents.length - k; j++) {
				if(comp.comparar(ents[j], ents[j + 1]) > 0) {
					troca = true;
					Comparavel aux = ents[j];
					ents[j] = ents[j + 1];
					ents[j + 1] = aux;
				}
			}
			
			k++;
			if (troca == false) {
				break;
			}
		}
	}
	
	public static void ordenar(Comparavel[] comps) {
		int k = 1;
		boolean troca = false;
		
		for (int i = 0; i <= comps.length; i++) {
			troca = false;
			for (int j = 0; j < comps.length - k; j++) {
				if(comps[j].comparar(comps[j + 1]) > 0) {
					troca = true;
					Comparavel aux = comps[j];
					comps[j] = comps[j + 1];
					comps[j + 1] = aux;
				}
			}
			
			k++;
			if (troca == false) {
				break;
			}
		}
	}
}
