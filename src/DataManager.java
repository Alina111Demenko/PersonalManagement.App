//Klasse zum Speichern und Laden

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
public class DataManager {
    private static final String FILE_NAME = "data.json";
    //object Gson
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void save(Unternehmen unternehmen){
       try(Writer writer = new FileWriter(FILE_NAME)){
           gson.toJson(unternehmen, writer);
           System.out.println("Saved"); //save
       }
       catch(IOException e){
           System.out.println("Fehler beim Speichern" + e.getMessage());
       }

    }
    public static Unternehmen load(){
        File file = new File(FILE_NAME);
        if(!file.exists())
        {
            System.out.println("Keine Daten gefunden");
            return null;
        }
        try(Reader reader = new FileReader(FILE_NAME)){
            return gson.fromJson(reader, Unternehmen.class);
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim Laden: " + e.getMessage());
            return null;
        }

    }
}
