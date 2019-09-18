import java.util.List;

public class StringCalculator {

    private SeparatorService separatorService;

    public int add(String numbers) {
        int result = 0;
        if (!numbers.isEmpty()) {

            separatorService = new SeparatorService(numbers);
            String separators = separatorService.getSeparators();
            numbers = separatorService.getNumbersWithoutCustomSeparators(numbers);

            List<Integer> numberList = NumberService.getNumberList(numbers, separators);

            result = numberList.stream().mapToInt(number -> number).sum();
        }

        return result;
    }


}
