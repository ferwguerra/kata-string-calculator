import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeparatorService {

    private static final String OPENING_CHARACTER_ARBITRARY_LENGTH_SEPARATOR = "[";
    private static final String CLOSING_CHARACTER_ARBITRARY_LENGTH_SEPARATOR = "]";
    private static final String INDICATOR_CUSTOM_SEPARATOR = "//";

    private final String customSeparatorString;
    private final String CUSTOM_SEPARATORS = ",|\\\n";
    private final Map<Character, String> separatorsEscaped;

    public SeparatorService(String numbers) {
        this.customSeparatorString = getSubstringOfCustomSeparators(numbers);

        this.separatorsEscaped = new HashMap<>();
        this.separatorsEscaped.put('*', "\\*");
    }

    private String getSubstringOfCustomSeparators(String numbers) {
        String result = "";
        if (numbers.startsWith(INDICATOR_CUSTOM_SEPARATOR)) {
            result = numbers.substring(0, numbers.indexOf('\n') + 1);
        }
        return result;
    }

    public String getSeparators() {
        return CUSTOM_SEPARATORS + getCustomSeparator();
    }

    private String getCustomSeparator() {
        String separator = "";

        if (hasCustomSeparator()) {
            if (hasArbitrarySeparator()) {
                List<String> arbitrarySeparators = getArbitrarySeparators();
                separator = escapeArbitrarySeparators(arbitrarySeparators);
            } else {
                separator = "|" + escape(customSeparatorString.charAt(2));
            }

        }

        return separator;
    }

    private String escapeArbitrarySeparators(List<String> arbitrarySeparators) {
        String separator = "";

        for (String arbitrarySeparator : arbitrarySeparators) {
            separator = separator.concat("|" + escape(arbitrarySeparator));
        }
        return separator;
    }

    private List<String> getArbitrarySeparators() {
        List<String> separators = new ArrayList<>();
        String separatorsWithSquareBrackets = customSeparatorString.substring(2, customSeparatorString.indexOf('\n'));

        int i = 0;
        while (i < separatorsWithSquareBrackets.length()) {
            int from = separatorsWithSquareBrackets.indexOf(OPENING_CHARACTER_ARBITRARY_LENGTH_SEPARATOR, i);
            int to = separatorsWithSquareBrackets.indexOf(CLOSING_CHARACTER_ARBITRARY_LENGTH_SEPARATOR, i);

            separators.add(separatorsWithSquareBrackets.substring(from + 1, to));
            i = to + 1;
        }

        return separators;
    }

    private boolean isCharacterInNeedOfEscape(Character character) {
        return separatorsEscaped.containsKey(character);
    }

    private String escape(String string) {
        String escaped = "";
        for (Character character : string.toCharArray()) {
            escaped = escaped.concat(escape(character));
        }
        return escaped;
    }

    private String escape(Character character) {
        String characterEscaped;
        if (isCharacterInNeedOfEscape(character)) {
            characterEscaped = separatorsEscaped.get(character);
        } else {
            characterEscaped = String.valueOf(character);
        }
        return characterEscaped;
    }

    private boolean hasArbitrarySeparator() {
        return customSeparatorString.contains(OPENING_CHARACTER_ARBITRARY_LENGTH_SEPARATOR);
    }

    private boolean hasCustomSeparator() {
        return !customSeparatorString.isEmpty();
    }

    public String getNumbersWithoutCustomSeparators(String numbers) {
        return numbers.replace(customSeparatorString, "");
    }
}
