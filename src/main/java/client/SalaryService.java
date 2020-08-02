package client;

import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class SalaryService {
    /*
    public BigInteger calculateSalary(List<Task> tasks)
    Task: name, value, deliveryDate
    - jesli pensja jest obliczana w dni robocze to pensja = suma stawek taskow ALE jesli delivery date jest w weekend to suma wzrasta o 50%
    - esli pensja obliczna jest w weekend to wtedy suma stawek automatycznie mnozy sie * 150% a zadania wykonan w weekend maja + 100%
    + testy jedostkowe
     */
    private final DateProvider dateProvider;

    public BigInteger calculateSalary(List<Task> tasks) {
        int dayVal = dateProvider.now().getDayOfWeek().getValue();
        //System.out.println(dayVal);
        if (tasks==null){
            return BigInteger.ZERO;
        }
        try {
            return tasks.stream().filter(Objects::nonNull).map(task -> setSalary(task, dayVal)).reduce(BigInteger::add).orElse(BigInteger.ZERO);
        }catch (ClassCastException e){
            throw new ClassCastException("Wrong input data");
        }
    }

    private BigInteger setSalary(Task task, int dayVal) {
        if (dayVal == 7 || dayVal == 6) {
            return weekendSalary(task);
        } else {
            return workDaySalary(task);
        }
    }

    private BigInteger weekendSalary(Task task){
        if (isWeekend(task)) {
            return task.getValue().multiply(BigInteger.valueOf(2));
        } else {
            return BigInteger.valueOf((long) (task.getValue().doubleValue() * 1.5));
        }
    }

    private BigInteger workDaySalary(Task task){
        if (isWeekend(task)) {
            return BigInteger.valueOf((long) (task.getValue().doubleValue() * 1.5));
        } else {
            return task.getValue();
        }
    }

    private boolean isWeekend(Task task) {
        return task.getDeliveryDate().getDayOfWeek().getValue() == 6 || task.getDeliveryDate().getDayOfWeek().getValue() == 7;
    }
}
