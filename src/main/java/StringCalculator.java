import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    private String separators = ",|\\n";

    public int add(String numbers) {
        int result = 0;
        if (numbers.isEmpty()) {
            return result;
        }

        List<Integer> negatives = new ArrayList<Integer>();
        numbers = calculateAndRemoveCustomSeparators(numbers);

        String[] arrayNumbers = numbers.split(separators);
        for (String number : arrayNumbers) {
            int numberInt = Integer.valueOf(number);
            if (numberInt < 0) {
                negatives.add(numberInt);
            }
            result += numberInt;
        }

        validateNegatives(negatives);

        return result;
    }

    private void validateNegatives(List<Integer> negatives) {
        if (!negatives.isEmpty()) {
            StringBuffer stringBuffer = new StringBuffer("Error! Negatives not allowed: ");
            for (Integer negative : negatives) {
                stringBuffer.append(negative);
                stringBuffer.append(" ");
            }

            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    private String calculateAndRemoveCustomSeparators(String numbers) {
        if (numbers.startsWith("//")) {
            String customSeparator = numbers.substring(2, 3);

            separators = separators.concat("|");
            separators = separators.concat(customSeparator);

            numbers = numbers.substring(4);
        }

        return numbers;
    }
}
