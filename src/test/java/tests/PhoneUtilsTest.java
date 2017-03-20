package tests;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * {@link PhoneNumberUtil#parse(String, String)} test which verifies that the util parses typical valid phones
 * from exelare db and doesn't parse typical invalid phones
 */
public class PhoneUtilsTest {

    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    @Test
    public void testCMFormat1() throws NumberParseException {

        assertNumberParse("1-714-803-5949", 7148035949L);
        assertNumberParse("714-803-5949", 7148035949L);
        assertNumberParse("(714)803-5949", 7148035949L);
        assertNumberParse("714 803 5949", 7148035949L);
        assertNumberParse("7148035949", 7148035949L);
        assertNumberParse("+1-714-803-5949", 7148035949L);
        assertNumberParse("17148035949", 7148035949L);


        assertNumberParse("240.888.4976", 2408884976L);
        assertNumberParse("978-252-9090", 9782529090L);
        assertNumberParse("1-714-803-5949", 7148035949L);

        assertNumberParse("+1-408-769-9157", 4087699157L);
        assertNumberParse("+1 (323) 842-4386", 3238424386L);
        assertNumberParse("(561) 634-6251", 5616346251L);
        assertNumberParse("(312)-834-6510", 3128346510L);
        assertNumberParse("661-8600844", 6618600844L);
        assertNumberParse("3108716050", 3108716050L);
        assertNumberParse("Ÿ 412.439.1033", 4124391033L);
        assertNumberParse("1-714-803-5949", 7148035949L);


//
//
//        08730-480
//        +55-11-98082-6444
//
//        6194907490
//
//        2-1-4-3-1-0-7-2-07






    }

    @Test
    public void checkBadNumbers() {
        assertPhoneNotParsed("551697694");
        assertPhoneNotParsed("714-681-8782-2764");
        assertPhoneNotParsed("-000-9725");
    }

    private void assertPhoneNotParsed(String phone) {
        try {
            Phonenumber.PhoneNumber number = phoneUtil.parse(phone, "US");
            assertThat(number, notNullValue());
            assertThat(phoneUtil.isValidNumber(number), is(false));
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
    }



    private void assertNumberParse(String phone, long expectedPhone) throws NumberParseException {
        Phonenumber.PhoneNumber number = phoneUtil.parse(phone, "US");

        assertThat(number, notNullValue());
        assertThat(phoneUtil.isValidNumber(number), is(true));
        assertThat(number.getNationalNumber(), is(expectedPhone));
    }
}