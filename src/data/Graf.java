package data;

import exceptions.noCreat;
import exceptions.noAfegit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Graf<V extends Comparable<V>, E extends Comparable<E>> implements TAD<V, E> {



    /* --------------- < globals > --------------- */
    private HashMap<V, node<V, E>> graf;

    /* --------------- < iniciar > --------------- */

    public void CrearGraf() {
        graf = new HashMap<>();
    }
    /* --------------- < afegir aresta > --------------- */

    public void afegirAresta(V v1, V v2, E e) throws noAfegit {
        if(graf.get(v1) != null && graf.get(v2) != null) {
            Aresta<V, E> aux = graf.get(v1).newAresta(v2, e);
            graf.get(v2).addAresta(aux);
        }
        else {
            throw new noAfegit();
        }
    }
    /* --------------- < existeix aresta? > --------------- */

    public boolean existeixAresta(V v1, V v2) {
        return graf.get(v1).comprovarAresta(v2);
    }

    /* --------------- < valor aresta > --------------- */

    public E valorAresta(V v1, V v2) throws NoSuchElementException {
        E aux = graf.get(v1).valorArestaAmb(v2);
        if(aux == null) {
            aux = graf.get(v2).valorArestaAmb(v1);
            if(aux == null) {
                throw new NoSuchElementException();
            }
            else {
                return aux;
            }
        }
        else {
            return aux;
        }
    }

    /* --------------- < afegir node > --------------- */


    public void afegirNode(V v) {
        node<V, E> node = new node<>(v);
        graf.put(v, node);
    }

    /* --------------- < llista d'adjacents > --------------- */

    public ArrayList<V> adjacents(V v) throws noCreat {
        if(graf.containsKey(v)) {
            return graf.get(v).adjacents();
        }
        else {
            throw new noCreat();
        }
    }
        /* --------------- < <#nodes> > --------------- */

        public int numNodes() {
            return graf.size();
        }


    }

