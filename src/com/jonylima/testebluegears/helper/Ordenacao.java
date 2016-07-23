package com.jonylima.testebluegears.helper;

import java.util.List;
import com.jonylima.testebluegears.model.vo.Item;

public class Ordenacao {
	private List<Item> lista;
	private int ultimaPosicao;

	public Ordenacao(List<Item> lista) {
		this.lista = lista;
		ultimaPosicao = lista.size() - 1;
	}

	public void heapSort() {

		for (int i = (ultimaPosicao) / 2; i >= 0; i--) {
			criaHeap(i, ultimaPosicao);
		}

		for (int i = ultimaPosicao; i >= 1; i--) {
			troca(0, i);
			criaHeap(0, i - 1);
		}

	}

	private void troca(int i, int j) {
		Item aux = lista.get(i);
		lista.set(i, lista.get(j));
		lista.set(j, aux);
	}

	private void criaHeap(int posicaoPai, int posicaoFinal) {
		Item aux = lista.get(posicaoPai);
		int posicaoFilho = posicaoPai * 2 + 1;

		while (posicaoFilho <= posicaoFinal) {

			// verifica se possui dois filhos
			if (posicaoFilho < posicaoFinal) {

				// verifica quem é o maior filho
				if (menorQue(lista.get(posicaoFilho), lista.get(posicaoFilho + 1))) {
					posicaoFilho++;
				}
			}

			if (menorQue(aux, lista.get(posicaoFilho))) {
				lista.set(posicaoPai, lista.get(posicaoFilho));
				posicaoPai = posicaoFilho;
				posicaoFilho = 2 * posicaoPai + 1;
			} else {// quando o pai é maior que os filhos encerra o loop
				posicaoFilho = posicaoFinal + 1;
			}
		}

		lista.set(posicaoPai, aux);

	}

	private boolean menorQue(Item a, Item b) {
		return a.compareTo(b) < 0;
	}
}
