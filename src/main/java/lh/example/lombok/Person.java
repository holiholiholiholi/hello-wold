package lh.example.lombok;

/**
 * Created by lihong on 15.06.17.
 */


import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Data
@Slf4j
public class Person {

    public enum Gender {FEMALE, MALE}


    private final LocalDate birthday;

    @NonNull
    private String lastname, surname;

    @Setter(AccessLevel.NONE) //disable the setter
    private short age;

    private Gender gender;

    public void print(){
        log.info(toString());
    }

//    public static void main(String args[]){
//        Person p = new Person(LocalDate.of(2016,1,1),"White","Peter");
//        p.print();
//    }
}
