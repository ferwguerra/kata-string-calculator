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
        separators = separators + calculateCustomSeparators(numbers);
        numbers = cleanSeparatorsFromNumbers(numbers);

        String[] arrayNumbers = numbers.split(separators);
        for (String number : arrayNumbers) {
            int numberInt = Integer.valueOf(number);

            if (numberInt < 0) {
                negatives.add(numberInt);
            }

            if (numberInt <= 1000) {
                result += numberInt;
            }
        }

        validateNegatives(negatives);

        return result;
    }

    private String cleanSeparatorsFromNumbers(String numbers) {
        if(numbers.startsWith("//")) {
            return numbers.substring(numbers.indexOf("\n") + 1);
        }
        return numbers;

    }

    private String calculateCustomSeparators(String numbers) {
        if (numbers.startsWith("//")) {
            return "|" + numbers.substring(2, 3);
        }
        return "";
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

}
