package estacions;


public class Endoll {

    /* --------------- < global > --------------- */

    private final int temps;
    private final int id;
    private final String tipus;
    private final String estat;
    private final double potencia;
    private final double consum;


    /* --------------- < constructor > --------------- */

    public Endoll(int id, double consum, int temps, double potencia, String tipus, String estat) {
        this.tipus = tipus;
        this.estat = estat;
        this.potencia = potencia;
        this.consum = consum;
        this.temps = temps;
        this.id = id;
    }

    /* --------------- < getters > --------------- */
    public double getPotencia() { return potencia; }

    /* --------------- < toString > --------------- */

    public String toString() {

        String aux;
        aux = "\n ID [" + id + "]:\n" +
                tipus + " (" +
                estat + ")\n\t" +
                "Potencia:" + potencia + "\n";

        if(consum == 0) {
            aux += "Consum: 0 \n";
        }
        else {
            aux += "Consum: " + consum + "\n";
        }

        if(estat.equals("ocupat")) { aux += "Temps: " + temps + "\n"; }

        aux = aux + "\n ----------------------------------------";
        return aux;
    }
}