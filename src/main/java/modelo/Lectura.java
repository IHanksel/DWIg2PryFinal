package modelo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class Lectura {

    public List<Universidad> listarUniversidad(String nombre) throws IOException {
        List<Universidad> lista = new ArrayList<>();
        try {
            URL url = new URL("http://universities.hipolabs.com/search?country=Peru");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int code = con.getResponseCode();
            if (code != 200) {
                throw new RuntimeException("Error: " + code);
            } else {
                try ( Scanner sc = new Scanner(url.openStream())) {
                    StringBuilder sg = new StringBuilder();
                    while (sc.hasNext()) {
                        sg.append(sc.nextLine());
                    }
                    JSONArray jsa = new JSONArray(sg.toString());
                    for (int f = 0; f < jsa.length(); f++) {
                        JSONObject obj = jsa.getJSONObject(f);
                        String nombreUniversidad = obj.getString("name");

                        if (nombreUniversidad.toLowerCase().contains(nombre.toLowerCase())) {
                            Universidad universidad = new Universidad();
                            universidad.setNombre(nombreUniversidad);
                            universidad.setUrl(obj.getJSONArray("web_pages").getString(0));
                            lista.add(universidad);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            throw ex;
        }

        return lista;
    }
}
