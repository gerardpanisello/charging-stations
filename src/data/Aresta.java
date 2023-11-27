package data;


    public class Aresta <V extends Comparable<V>, E extends Comparable<E>>{

        /* --------------- < global > --------------- */

        private E distancia;
        private V vertexA;
        private V vertexB;
        private Aresta<V, E> nextA;
        private Aresta<V, E> nextB;

        /* --------------- < constructor sense params > --------------- */

        public Aresta() {
            this.vertexA = null;
            this.vertexB = null;
            this.distancia = null;
            nextA = null;
            nextB = null;
        }

        /* --------------- < constructor amb params> --------------- */

        public Aresta(V vertexA, V vertexB, E pes) {
            this.vertexA = vertexA;
            this.vertexB = vertexB;
            this.distancia = pes;
            nextA = null;
            nextB = null;
        }


        /* --------------- < aresta amb X node > --------------- */

        public boolean teVertex(V valor) {

            return (vertexA.compareTo(valor) == 0) || (vertexB.compareTo(valor) == 0);
        }

        /* --------------- < pes aresta > --------------- */

        public E getValor() {
            return distancia;
        }


        /* --------------- < get aresta > --------------- */


        public Aresta<V, E> getNextAresta(V valor) {
            return  (vertexA.compareTo(valor) == 0) ? nextA : nextB;
        }

        /* --------------- < get adjacent > --------------- */

        public V getAdjacent(V valor) {
            return (vertexA.compareTo(valor) == 0) ? vertexB : vertexA;
        }

        /* --------------- < setter > --------------- */

        public void setNextAresta(V valor, Aresta<V, E> a) {
            if(vertexA.compareTo(valor) == 0) {
                nextA = a;
            }
            else {
                nextB = a;
            }
        }

        /* --------------- < connectar nodes > --------------- */

        public Aresta<V, E> newLink(V valorA, V valorB, E pes) {
            Aresta<V, E> aux = null;
            if(this.distancia == null) {
                vertexA = valorA;
                vertexB = valorB;
                this.distancia = pes;
                return this;
            }
            else if(vertexA.compareTo(valorA) == 0) {
                if(nextA == null) {
                    nextA = new Aresta<>(valorA, valorB, pes);
                    aux = nextA;
                }
                else {
                    aux = nextA;
                    while(aux.getNextAresta(valorA) != null) {
                        aux = aux.getNextAresta(valorA);
                    }
                    aux.newLink(valorA, valorB, pes);
                    aux = aux.getNextAresta(valorA);
                }
            }
            else if(vertexB.compareTo(valorA) == 0) {
                if(nextB == null) {
                    nextB = new Aresta<>(valorA, valorB, pes);
                    aux = nextB;
                }
                else {
                    aux = nextB;
                    while(aux.getNextAresta(valorA) != null) {
                        aux = aux.getNextAresta(valorA);
                    }
                    aux.newLink(valorA, valorB, pes);
                    aux = aux.getNextAresta(valorA);
                }
            }
            return aux;
        }
    }

