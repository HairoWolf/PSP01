package Actividades_11_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ej_02Ejemplo2URL {
    public static void main(String[] args) {
        URL url = null;
        try
        {
            url = new URL("https://www.patrimoniomalaga.es");
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        try {
            InputStream inputStream=url.openStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            while ((inputLine=in.readLine())!=null) System.out.println(inputLine);
            in.close();
        }catch (IOException e) {e.printStackTrace();}
    }//fin del main
}//FIN DEL PROGRAMA
