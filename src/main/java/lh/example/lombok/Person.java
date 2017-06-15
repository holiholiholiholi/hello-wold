package lh.example.lombok;

/**
 * Created by lihong on 15.06.17.
 */


import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.io.InputStream;
import java.time.LocalDate;

@Data
public class Person {

    public enum Gender {FEMALE, MALE}


    private final LocalDate birthday;

    @NonNull
    private String lastname, surname;

    @Setter(AccessLevel.NONE) //disable the setter
    private short age;

    private Gender gender;

}
