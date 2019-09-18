import java.util.ArrayList;
import java.util.List;

public class NumberService {

    public static List<Integer> getNumberList(String numbers, String separators) {
        List<Integer> numberList = new ArrayList<>();

        for (String numberString : numbers.split(separators)) {
            int number = Integer.valueOf(numberString);

            if (number < 0) {
                throw new IllegalArgumentException(numberString + " can't be negative");
            }

            if (number <= 1000) {
                numberList.add(number);
            }
        }

        return numberList;
    }
}
