package estacions;
import java.util.ArrayList;

public class Estacions implements Comparable<Estacions>{

        /* --------------- < global > --------------- */

        private final ArrayList<Endoll> endolls;
        private final String ciutat;
        private final String carrer;
        private final String data;
        private final String nom;
        private final double latitud;
        private final double longitud;
        private final int id_estacio;

        /* --------------- < constructor > --------------- */

        public Estacions(int id, String nom, String data, String carrer, String ciutat, double latitud, double longitud) {
            this.ciutat = ciutat;
            this.carrer = carrer;
            this.data = data;
            this.nom = nom;
            this.latitud = latitud;
            this.longitud = longitud;
            this.id_estacio = id;
            endolls = new ArrayList<>();
        }

        public void addEndoll(Endoll endoll) {
            endolls.add(endoll);
        }

        public double distLatLon(Estacions altre) {
            double dist = Math.sqrt((latitud - altre.getLatitud())*(latitud - altre.getLatitud()) + (longitud - altre.getLongitud())*(longitud - altre.getLongitud()));
            dist = dist * 111.1;
            return dist;
        }

        /* --------------- < getters > --------------- */
        public String getNom() { return nom; }
        public double getLatitud() { return latitud; }
        public double getLongitud() { return longitud; }
        public int getId_estacio() { return id_estacio; }



        public double getPotencia() {
            double pot = Double.MIN_VALUE;

            for (int i = 0; i < endolls.size(); i++) {
                Endoll obj = endolls.get(i);
                if (obj.getPotencia() > pot) {
                    pot = obj.getPotencia();
                }
            }

            return pot;
        }



        public int compareTo(Estacions obj) {

            if(obj.getId_estacio() == id_estacio) {
                return 0;
            }
            else if(obj.getPotencia() < this.getPotencia()) {
                return 1;
            }
            else { return -1; }
        }

        /* --------------- < toString > --------------- */

        public String toString() {
            String aux;
            aux = "\nEstacio de carrega [" + id_estacio + "]: \n" +
                    nom + "\n" +
                    ciutat + "\nC/ " +
                    carrer + "\n" +
                    data + "\nLatitud:" +
                    latitud + "\nLongitud:" +
                    longitud + "\nEndolls:\n";

            for (int i = 0; i < endolls.size(); i++) {
                Endoll endoll = endolls.get(i);
                aux += endoll;
            }

            return aux;
        }

}
