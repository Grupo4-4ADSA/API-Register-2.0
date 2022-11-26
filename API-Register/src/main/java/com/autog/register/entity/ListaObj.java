package com.autog.register.entity;

public class ListaObj<T> {

    private T[] vetor;
    int nroElem;

    public ListaObj(int tam) {
        vetor = (T[]) new Object[tam];
        nroElem = 0;
    }


    public void adiciona(T valor) {
        if (nroElem < vetor.length) {
            vetor[nroElem] = valor;
            nroElem++;
        } else {
            System.out.println("Lista cheia");
        }
    }

    public void exibe() {
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
    }

    public int busca(T valor) {
        for (int i = 0; i < nroElem; i++) {
            if (valor.equals(vetor[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int valor) {
        if (valor >= 0 && valor <= (nroElem - 1)) {
            nroElem--;

            for (int i = valor; i < nroElem; i++) {
                vetor[i] = vetor[i + 1];
            }
            return true;
        }
        return false;
    }

    public boolean removeElemento(T valor) {
        for (int i = 0; i < nroElem; i++) {
            if (valor == vetor[i]) {
                nroElem--;

                for (int j = i; j < nroElem; j++) {
                    vetor[j] = vetor[j + 1];
                }
                return true;
            }
        }
        return false;
    }

    public int getTamanho(){
        return nroElem;
    }

    public T getElemento(int indice){
        if (indice >= 0 && indice <= (nroElem - 1)) {
            return vetor[indice];
        }
        return null;
    }

    public void limpa(){
        nroElem = 0;
    }

}
