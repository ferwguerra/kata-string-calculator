public class StringCalculator {

    public int add(String numbers) {
        int result = 0;
        if(!numbers.isEmpty()) {



            String[] arrayNumbers = numbers.replaceAll("\n", ",").split(",");
            for (String number : arrayNumbers) {
                result += Integer.valueOf(number);
            }



        }
        return result;
    }
}
