package Actividades_11_03;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EncontrarMbappe {
    public static void main(String[] args) {
        URL url = null;
        URLConnection urlCon = null;
        int contadorMbappe = 0;
        String ficheroEspejo = "C:\\Users\\jtienda\\Documents\\Borrado\\ficheroEspejo.txt";
        try {
            url = new URL("https://www.as.com");
            urlCon = url.openConnection();
            InputStream inputStream = urlCon.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            FileWriter fileWriter = new FileWriter(ficheroEspejo);
            String inputLine;
            while((inputLine=in.readLine())!=null) {
                if(inputLine.contains("Mbappé") || inputLine.contains("mbappé")){
                    contadorMbappe++;
                    inputLine = inputLine.replaceAll("Mbappé", "McGuire");
                    inputLine = inputLine.replaceAll("mbappé", "mcGuire");
                    fileWriter.write(inputLine);
                }
                else {
                    fileWriter.write(inputLine);
                }
            }
            fileWriter.close();
            in.close();
            System.out.println("Aparece la palabra Mbappé " + contadorMbappe + " veces");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
