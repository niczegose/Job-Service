package client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Task {
    private final String name;
    private final BigInteger value;
    private final LocalDate deliveryDate;

}
