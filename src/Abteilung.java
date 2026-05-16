import java.util.ArrayList;
import java.util.List;

public class Abteilung {
    String name;
    List<Person>personList=new ArrayList<>();

    public Abteilung(String name){
        this.name=name;
    }

    public void Personeinfugen(Person person) {
        //prüfen ob man schon in list ist(mit email).
        boolean exists = personList.stream().anyMatch(p -> p.email.equals(person.email));
        if(!exists){
            personList.add(person);
        }
    }
    //Löschen Person, wenn Vorname gleich wie Valiable
    public void PersonLochen(String email){
        personList.removeIf(p -> p.email.equals((email)));
    }
}

