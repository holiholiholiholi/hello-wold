package lh.example.lombok;

/**
 * Created by lihong on 15.06.17.
 */


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lh.example.json.LocalDateDeserializer;
import lh.example.json.LocalDateSerializer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(exclude={"age","hobbies"})
@Slf4j
@JacksonXmlRootElement(localName="person")
public class Person {

    public enum Gender {FEMALE, MALE}

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private final LocalDate birthday;

    @NonNull
    @JacksonXmlProperty(isAttribute=true)
    private String lastname, surname;

    @Setter(AccessLevel.NONE) //disable the setter
    @JsonIgnore
    private short age;

    private Gender gender;

    @Setter(AccessLevel.NONE)
    @JacksonXmlElementWrapper(localName = "hobbies",useWrapping=true)
    @JacksonXmlProperty(localName = "hobby")
    private final ArrayList<String> hobbies = new ArrayList();

    public Person(@JsonProperty("birthday") LocalDate birthday, @JsonProperty("lastname") @NonNull String lastname,
                  @JsonProperty("surname") @NonNull String surname) {
        this.birthday = birthday;
        this.lastname = lastname;
        this.surname = surname;
    }

    public void print() {
        log.info(toString());
    }

//    public static void main(String args[]){
//        Person p = new Person(LocalDate.of(2016,1,1),"White","Peter");
//        p.print();
//    }
}
