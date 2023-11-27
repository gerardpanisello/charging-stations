package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import exceptions.noAfegit;
import exceptions.noCreat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import estacions.Endoll;
import estacions.Estacions;
import data.Graf;



public class Main {

    public static void main(String[] args) throws noAfegit, noCreat, FileNotFoundException {

        /* --------------- < globals > --------------- */

        ArrayList<Estacions> llista = carregaDades();
        Graf<Estacions, Double>  graf = new Graf<>();

        /* --------------- < crear graf > --------------- */

        graf.CrearGraf();

        for (int i = 0; i < llista.size(); i++) {
            Estacions zona = llista.get(i);
            graf.afegirNode(zona);
        }
        graf = unirNodes(llista, graf);

        System.out.println(llista);

        ArrayList<Estacions> adjacents;

        /* --------------- < cami optim > --------------- */

        System.out.println("\nCAMI OPTIM:");
        for (int i = 0; i < 10;i++) {

            System.out.println("\n\tOrigen: " + llista.get(i).getNom() + "\n\tDesti: " + llista.get(i+50).getNom());
            System.out.println(camiOptim(llista.get(i).getNom(), llista.get(i+50).getNom(), graf, llista));

        }

    }




    private static ArrayList<Integer> camiOptim(String origen, String desti, Graf<Estacions, Double> graf, ArrayList<Estacions> llista) throws noCreat{
        ArrayList<Integer> zones = new ArrayList<>();
        Queue<Estacions> cua = new LinkedList<>();
        HashMap<Estacions, Double> dist = new HashMap<>();
        ArrayList<Estacions> adjacents = new ArrayList<>();
        HashMap<Estacions, Estacions> prev = new HashMap<>();
        Estacions actual = null, objectiu = null, inici = null;

        int i = 0;
        boolean trobat = false;
        while(!trobat && i < llista.size()) {
            if(llista.get(i).getNom().equals(origen)) {
                actual = llista.get(i);
                inici = llista.get(i);
                trobat = true;
            }
            i++;
        }

        i = 0;
        trobat = false;
        while(!trobat && i < llista.size()) {
            if(llista.get(i).getNom().equals(desti)) {
                objectiu = llista.get(i);
                trobat = true;
            }
            i++;
        }

        for(Estacions v : llista) {
            dist.put(v, Double.MAX_VALUE);
            cua.add(v);
        }

        dist.put(actual, 0.0);
        double min, alt;
        trobat = false;

        while(!cua.isEmpty() && !trobat) {
            min = Double.MAX_VALUE;
            for(Estacions obj : cua) {
                if(dist.get(obj) < min || (dist.get(obj) == min && obj.getPotencia() > actual.getPotencia())) {
                    min = dist.get(obj);
                    actual = obj;
                }
            }
            if(!cua.remove(actual)) {
                throw new noCreat();
            }

            assert actual != null;
            assert objectiu != null;
            if(actual.compareTo(objectiu) != 0) {
                try {
                    adjacents = graf.adjacents(actual);
                }
                catch(noCreat e){
                    e.printStackTrace();
                }

                for(Estacions obj : adjacents) {
                    if(graf.valorAresta(actual, obj) <= (double) 40) {
                        alt = dist.get(actual) + graf.valorAresta(actual, obj);
                        if(alt < dist.get(obj)) {
                            dist.put(obj, alt);
                            prev.put(obj, actual);
                        }
                    }
                }
            }
            else {
                trobat = true;
            }
        }

        while(actual != inici) {
            zones.add(actual.getId_estacio());
            actual = prev.get(actual);
        }

        assert actual != null;
        zones.add(actual.getId_estacio());
        return zones;
    }

    private static Graf<Estacions, Double> unirNodes(ArrayList<Estacions> llista, Graf<Estacions, Double> graf) throws noAfegit {
        Estacions zona;
        boolean connexa;
        double distancia;
        double distanciaMin;
        int indexMin = 0;
        int nArestes = 0;

        for(int i = 0; i < llista.size(); i++) {
            zona = llista.get(i);
            connexa = false;
            distanciaMin = Double.MAX_VALUE;
            for(int j = i+1; j < llista.size(); j++) {
                distancia = zona.distLatLon(llista.get(j));
                if(distancia <= 40) {
                    graf.afegirAresta(zona, llista.get(j), distancia);
                    connexa = true;
                    nArestes++;
                }
                else if(distancia < distanciaMin){
                    distanciaMin = distancia;
                    indexMin = j;
                }
            }

            if(!connexa) {
                for(int j = 0; j < i; j++) {
                    distancia = zona.distLatLon(llista.get(j));
                    if(distancia < distanciaMin && !graf.existeixAresta(zona, llista.get(j))) {
                        distanciaMin = distancia;
                        indexMin = j;
                    }
                }
                graf.afegirAresta(zona, llista.get(indexMin), distanciaMin);
                nArestes++;
            }
        }
        System.out.println("Numero de arestes = " + nArestes);
        System.out.println("Numero de nodes = " + graf.numNodes());
        return graf;
    }


    private static ArrayList<Estacions> carregaDades() {
        ArrayList<Estacions> llista = new ArrayList<>();
        Estacions zona = null;
        Endoll endoll;
        JSONParser parser = new JSONParser();
        JSONArray array;
        try {
            array = (JSONArray) parser.parse(new FileReader("icaen.json"));
            int id_aux = -1;

            for (Object obj : array)
            {
                JSONObject estacio = (JSONObject) obj;
                int id = Integer.parseInt((String) estacio.get("id"));
                double consum = estacio.get("consum").equals("") ? 0 : Double.parseDouble((String) estacio.get("consum"));
                int temps = estacio.get("temps").equals("") ? 0 : Integer.parseInt((String) estacio.get("temps"));
                double potencia = estacio.get("potencia").equals("") ? 0 : Double.parseDouble((String) estacio.get("potencia"));

                endoll = new Endoll(id, consum, temps, potencia, (String) estacio.get("tipus"), (String) estacio.get("estat"));

                if(id_aux == Integer.parseInt((String) estacio.get("id_estacio"))){
                    assert zona != null;
                    zona.addEndoll(endoll);
                }
                else {
                    if(id_aux != -1) { llista.add(zona); }
                    double latitud = Double.parseDouble((String) estacio.get("latitud"));
                    double longitud = Double.parseDouble((String) estacio.get("longitud"));

                    id_aux = Integer.parseInt((String) estacio.get("id_estacio"));
                    zona = new Estacions(id_aux, (String) estacio.get("nom"), (String) estacio.get("data"), (String) estacio.get("carrer"),
                            (String) estacio.get("ciutat"), latitud, longitud);
                    zona.addEndoll(endoll);
                }
            }
        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return llista;
    }

}
