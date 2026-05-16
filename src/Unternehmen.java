import java.util.ArrayList;
import java.util.List;

public class Unternehmen{
    String name;
    List<Abteilung> abteilungs = new ArrayList<>();
    public  Unternehmen(String name){
        this.name=name;
    }

    public  void AbteilungEinfugen(Abteilung abteilung){

        abteilungs.add(abteilung);
    }
    public void  AbteilungLochen(String inputAbteilungname){
        abteilungs.removeIf(a->a.name.equals(inputAbteilungname));
    }

    public Abteilung getAbteilung(String name){
        for(Abteilung a : abteilungs){
            if(a.name.equals(name)) return a;
        }
        return null;
    }
}