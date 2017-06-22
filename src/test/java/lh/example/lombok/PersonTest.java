package lh.example.lombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

/**
 * Created by lihong on 15.06.17.
 */
public class PersonTest {

    @Test
    public  void test(){
        Person p = new Person(LocalDate.of(2000,1,1), "White","Peter");
        p.setGender(Person.Gender.MALE);
        assertEquals("White", p.getLastname());
        assertEquals("Peter",p.getSurname());
        assertEquals(2000, p.getBirthday().getYear());
        assertEquals(Month.JANUARY,p.getBirthday().getMonth());
        assertEquals(Person.Gender.MALE, p.getGender());
        p.print();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test2() {
        thrown.expect(IllegalArgumentException.class);
        Person p = new Person(LocalDate.of(2000,1,1), "White",null);
    }

    @Test
    public void jsonTest() throws IOException{
        Person p = new Person(LocalDate.of(2000,1,1), "White","Peter");
        p.getHobbies().add("A");
        p.getHobbies().add("B");
        p.getHobbies().add("C");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String json = mapper.writeValueAsString(p);
        System.out.println(json);

        mapper.readValue(json, Person.class).print();
    }

    @Test
    public void xmlTest() throws IOException {
        Person p = new Person(LocalDate.of(2000,1,1), "White","Peter");
        p.getHobbies().add("A J");
        p.getHobbies().add("B");
        p.getHobbies().add("C");

        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = mapper.writeValueAsString(p);
        System.out.println(xml);

        mapper.readValue(xml, Person.class).print();
    }
}