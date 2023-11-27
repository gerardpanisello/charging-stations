package data;

import java.util.ArrayList;

public class node<V extends Comparable<V>, E extends Comparable<E>> {

    private final V valor;
    private Aresta<V, E> aresta;

    /* --------------- < constructor > --------------- */

    public node(V valor) {
        this.valor = valor;
        aresta = null;
    }

    /* --------------- < aresta vertex amb km > --------------- */

    public Aresta<V, E> newAresta(V vertex, E pes) {
        if(this.aresta == null) {
            this.aresta = new Aresta<V, E>();
        }
        return this.aresta.newLink(valor, vertex, pes);
    }

    /* --------------- < aresta vertex amb km > --------------- */

    public void addAresta(Aresta<V, E> a) {
        Aresta<V, E> aux = aresta;
        if(aux == null) {
            aresta = a;
        }
        else {
            while(aux.getNextAresta(valor) != null) {
                aux = aux.getNextAresta(valor);
            }
            aux.setNextAresta(valor, a);
        }
    }

    /* --------------- < comprovacio aresta entre 2 nodes > --------------- */


    public boolean comprovarAresta(V vertex) {
        Aresta<V, E> aux = aresta;
        boolean existeix = false;
        while(aux!= null && !existeix) {
            if(aux.teVertex(vertex)) {
                existeix = true;
            }
            else {
                aux = aux.getNextAresta(valor);
            }
        }
        return existeix;
    }






    public E valorArestaAmb(V vertex) {
        if(aresta == null) {
            return null;
        }
        else {
            Aresta<V, E> aux = aresta;
            do {
                if(aux.teVertex(vertex)) {
                    return aux.getValor();
                }
                aux = aux.getNextAresta(valor);
            }while(aux != null);
        }
        return null;
    }


    public ArrayList<V> adjacents(){
        ArrayList<V> adjacents = new ArrayList<V>();
        Aresta<V, E> aux = aresta;
        while(aux != null) {
            adjacents.add(aux.getAdjacent(valor));
            aux = aux.getNextAresta(valor);
        }
        return adjacents;
    }
}