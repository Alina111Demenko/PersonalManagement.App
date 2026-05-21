import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class JUnitTest {

    @Test
    void testAddAbteilung(){
        Abteilung ab=new Abteilung("Markt");
        Person p1=new Person("vortest","nachtest","05.08.2001","1234567890","1234567890@gmail.com");
        ab.personList.add(p1);
        int num=ab.personList.size();
        assertEquals(1,num);
    }

    @Test
    void testRemoveAbteilung(){
        Abteilung ab=new Abteilung("Markt");
        Person p1=new Person("vortest","nachtest","05.08.2001","1234567890","1234567890@gmail.com");
        ab.personList.add(p1);
        ab.personLoschen("1234567890@gmail.com");
        int num=ab.personList.size();
        assertEquals(0,num);
    }


}
