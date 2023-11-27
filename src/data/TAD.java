package data;

import exceptions.noAfegit;
import exceptions.noCreat;
import java.util.ArrayList;

public interface TAD<V,E> {


    /* ----------------------- < metode 1 > ----------------------- */

    void CrearGraf();

    //Metode que incialitza el graf

    /* ----------------------- < metode 2 > ----------------------- */

    void afegirAresta(V v1, V v2, E e) throws noAfegit;

    //Metode per afegir aresta entre dos nodes
    //params -> vertex

    /* ----------------------- < metode 3 > ----------------------- */

    boolean existeixAresta(V v1, V v2);

    //Metode que comprova l'existencia d'una aresta entre dos nodes

    /* ----------------------- < metode 4 > ----------------------- */

    E valorAresta(V v1, V v2);

    //Metode que retorna el valor d'una aresta

    /* ----------------------- < metode 5 > ----------------------- */

    ArrayList<V> adjacents(V v) throws noCreat;

    //Metode que retorna una llista dels nodes adjacents

    /* ------------------------------------------------------------ */
}