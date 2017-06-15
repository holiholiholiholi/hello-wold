package lh.example.lombok;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test2() throws NullPointerException{
        thrown.expect(IllegalArgumentException.class);
        Person p = new Person(LocalDate.of(2000,1,1), "White",null);
    }
}