package pl.kurs.java;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Application {
    private String name;
    private String surname;
    private int financialExpectation;
    private String city;
    private String phoneNo;
    private boolean acceptRelocation;
    private String email;
    private int experience;
    private List<String> skillSet;
}
