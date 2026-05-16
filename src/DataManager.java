//Klasse zum Speichern und Laden
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class DataManager {
    private static final String JSON_FILE = "data.json";
    private static final String CSV_FILE  = "data.csv";
    //object Gson
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void save(Unternehmen unternehmen){
       try(Writer writer = new FileWriter(JSON_FILE)){
           gson.toJson(unternehmen, writer);
           System.out.println("JSON gespeichert"); //save
       }
       catch(IOException e){
           System.out.println("Fehler beim Speichern" + e.getMessage());
       }
       exportCsv(unternehmen); // CSV automatic beim Speichern
    }

    //erstellen csv beim klicken "Speichern"
    private static void exportCsv(Unternehmen unternehmen){
        try(Writer writer = new FileWriter(CSV_FILE)){
            writer.write("Vorname; Nachname; Geburtstag; Abteilung; Telefonnummer; E-Mail\n");
            for(Abteilung a : unternehmen.abteilungs){
                for(Person p : a.personList){
                  writer.write(p.vorname + ";" + p.nachname + ";" + p.geburtstag + ";"
                          + a.name + ";" + p.telefon + ";" + p.email + "\n");
                }
            }
            System.out.println("CSV exportiert");
        }
        catch (IOException e) {
            System.out.println("Fehler beim CSV-Export: " + e.getMessage());
        }

    }
    public static Unternehmen load(){
        File file = new File(JSON_FILE);

        if(!file.exists())
        {
            System.out.println("Keine Daten gefunden");
            return new Unternehmen("Meine Firma");
        }
        try(Reader reader = new FileReader(JSON_FILE)){
            return gson.fromJson(reader, Unternehmen.class);
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim Laden: " + e.getMessage());
            return new Unternehmen("Meine Firma");
        }
    }
}
