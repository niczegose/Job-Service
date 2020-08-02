package server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class SimpleQuestion implements Serializable {

    //private static final long serialVersionUID = 1L;

    private final String message;
    private final LocalDateTime time;

    @Override
    public String toString() {
        return "expression='" + message + '\'' +
                ", time=" + time;
    }
}