package Actividades_11_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Ej_03Ejemplo1URLCon {
    public static void main(String[] args)
    {
        URL url = null;
        URLConnection urlCon = null;
        try
        {
            url = new URL("https://www.fbi.gov");
            urlCon = url.openConnection();
            InputStream inputStream = urlCon.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            while((inputLine=in.readLine())!=null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {e.printStackTrace();
        } catch (IOException e) {e.printStackTrace();}

    }

}
