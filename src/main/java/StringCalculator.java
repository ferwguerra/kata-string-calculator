public class StringCalculator {

    private String separators = ",|\\n";

    public int add(String numbers) {
        int result = 0;
        if(!numbers.isEmpty()) {

            numbers = calculateSeparatorsCustom(numbers);

            String[] arrayNumbers = numbers.replaceAll("\n", ",").split(separators);
            for (String number : arrayNumbers) {
                result += Integer.valueOf(number);
            }



        }
        return result;
    }

    private String calculateSeparatorsCustom(String numbers) {
        if(numbers.startsWith("//")) {
            String customSeparator = numbers.substring(2, 3);

            separators = separators.concat("|");
            separators = separators.concat(customSeparator);

            numbers = numbers.substring(4, numbers.length());
        }

        return numbers;
    }
}
