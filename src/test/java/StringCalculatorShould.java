import org.junit.Assert;
import org.junit.Test;

public class StringCalculatorShould {
    StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void be_0_when_numbers_are_empties() {
        Assert.assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void be_4_when_send_just_one_number_and_it_is_4() {
        Assert.assertEquals(4, stringCalculator.add("4"));
    }

    @Test
    public void be_6_when_send_just_one_number_and_it_is_6() {
        Assert.assertEquals(6, stringCalculator.add("6"));
    }

    @Test
    public void be_7_when_send_4_and_3() {
        Assert.assertEquals(7, stringCalculator.add("4,3"));
    }

    @Test
    public void be_2_when_send_0_and_2() {
        Assert.assertEquals(2, stringCalculator.add("0,2"));
    }

    @Test
    public void be_6_when_send_1_and_2_and_3() {
        Assert.assertEquals(6, stringCalculator.add("1,2,3"));
    }

    @Test
    public void be_6_when_send_1_and_2_and_3_with_new_separator() {
        Assert.assertEquals(6, stringCalculator.add("1\n2,3"));
    }

    @Test
    public void be_28_when_send_1_to_7_with_new_separator() {
        Assert.assertEquals(28, stringCalculator.add("1\n2,3\n4,5,6\n7"));
    }

    @Test
    public void be_28_with_custom_separator() {
        Assert.assertEquals(28, stringCalculator.add("//;\n1;2;3;4;5;6;7"));
    }
}
