package com.cyta.util.test.utilities;

//import org.junit.Assert;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.Base64;
import org.testng.Assert;

public class Utilities {
    private Utilities(){

    }

    /**
     * Method to compare two strings .
     *
     * @param string  1,2
     * @param compare strings with third string , can be set to "" if not exist
     * @return flag with true or false
     */

    public static boolean compareStrings(String s1, String s2, String validatedItem) {
        boolean flag = false;
        s1 = s1.trim();
        s2 = s2.trim();
        flag = s1.equalsIgnoreCase(s2);

        if (!validatedItem.equalsIgnoreCase("")) {
            if (flag)
                System.out.println(validatedItem + " : is validated successfully ");
            else
                System.out.println(validatedItem + " : is failed !! where the screen value: [" + s1
                        + "] and the expected value: [" + s2 + "]");
        }

        return flag;
    }

    /**
     * Method to get Todays date
     *
     * @return
     */
    public static String getSystemDate(String format, String timeZone) {

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        TimeZone obj = TimeZone.getTimeZone(timeZone);
        dateFormat.setTimeZone(obj);

        System.out.println("Local:: " + currentDate.getTime());
        System.out.println("Time Zone:: " + dateFormat.format(currentDate.getTime()));
        return dateFormat.format(currentDate.getTime());
    }

    /**
     * Method to add days,months or years to current system date.
     *
     * @param format       format of the needed date
     * @param calendarType the type of calendar
     * @param calendarNo   the no of needed date to be added to current date
     * @return String
     */
    public static String addDayMonthYearToCurrentDate(String format, String calendarType, int calendarNo) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        if (calendarType.equalsIgnoreCase("dd")) {
            c.add(Calendar.DAY_OF_YEAR, calendarNo);
        } else if (calendarType.equalsIgnoreCase("ww")) {
            c.add(Calendar.WEEK_OF_YEAR, calendarNo);
        } else if (calendarType.equalsIgnoreCase("mm")) {
            c.add(Calendar.MONTH, calendarNo);
        } else if (calendarType.equalsIgnoreCase("yy")) {
            c.add(Calendar.YEAR, calendarNo);
        }

        String myDate = sdf.format(c.getTime());
        System.out.println("selected  day is " + myDate);

        return myDate;

    }

    /**
     * Method to generate random text.
     *
     * @param noOfChars length of the generated Text
     * @return random text
     */

    public static String getRandomString(int noOfChars) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int lengthOfAlphabet = alphabet.length();
        Random randomObj = new Random();
        char[] nameCharArr = new char[noOfChars];

        for (int i = 0; i < noOfChars; i++) {
            nameCharArr[i] = alphabet.charAt(randomObj.nextInt(lengthOfAlphabet));
        }

        String nameString = String.copyValueOf(nameCharArr);
        nameString = "AT" + nameString.toLowerCase();
        return nameString;
    }

    /**
     * Method to generate random Number.
     *
     * @param length length of the generated number
     * @return
     */
    public static String generateRandomNumber(int length) {
        char[] chars = "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();

    }


    /**
     * Takes two parameters, one is the array of special characters that are needed
     * to be replaced, and the text needed to be updated It converts text with
     * %40%23%24%25%26 ..etc (special characters) to return it with
     * %5C%5C%40%5C%5C%23%5C%5C%24%5C%5C%25%5C%5C%26
     *
     * @param specialCharactersArray an array of the special characters that will be
     *                               escaped
     * @param text                   the string that will have its special
     *                               characters escaped
     * @return updated texts with escaped special characters
     */
    public static String replaceRegex(String[] specialCharactersArray, String text) {
        // @#$%&
        // \\@\\#\\$\\%\\&

        String oldChar;
        for (int i = 0; i < (specialCharactersArray.length); i++) {
            oldChar = specialCharactersArray[i];
            specialCharactersArray[i] = ("\\" + specialCharactersArray[i]);
            text = text.replace(oldChar, specialCharactersArray[i]);
        }
        return text;
    }

    /**
     * Returns text after replaces its regular expressions which included in this
     * set []^$.|?*+(){}
     *
     * @param text the string that will have its special characters escaped
     * @return updated text after escaping its regular expressions
     */

    public static String replaceRegex(String text) {
        String[] specialCharactersArray = { "[", "]", "^", "$", ".", "|", "?", "*", "+", "(", ")", "{", "}" };
        return replaceRegex(specialCharactersArray, text);
    }

    public static String convertBase64(String text) {

        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    /**
     * Compares two objects (that can be cast to a string value) based on the
     * selected comparisonType and ValidationType, then returns the result in an
     * integer value
     *
     * @param expectedValue  the expected value (test data) of this assertion
     * @param actualValue    the actual value (calculated data) of this assertion
     * @param comparisonType 1 is literalComparison, 2 is regexComparison, 3 is
     *                       containsComparison, 4 is caseInsensitiveComparison
     * @param validationType either 'true' for a positive assertion that the objects
     *                       are equal, or 'false' for a negative assertion that the
     *                       objects are not equal
     * @return integer value; 1 in case of match, 0 in case of no match, -1 in case
     *         of invalid comparison operator, -2 in case of another unhandled
     *         exception
     */
    public static int compareTwoObjects(Object expectedValue, Object actualValue, int comparisonType,
                                        Boolean validationType) {

        if (Boolean.TRUE.equals(validationType)) {
            try {
                return compareTwoObjectsPositively(expectedValue, actualValue, comparisonType);
            } catch (AssertionError e) {
                return 0;
            } catch (Exception e) {
                System.out.println(e);
                return -2;
            }
        } else {
            try {
                return compareTwoObjectsNegatively(expectedValue, actualValue, comparisonType);
            } catch (AssertionError e) {
                return 0;
            } catch (Exception e) {
                System.out.println(e);
                return -2;
            }
        }

    }

    // Compare between two objects positively
    private static int compareTwoObjectsPositively(Object expectedValue, Object actualValue, int comparisonType) {
        switch (comparisonType) {
            case 1:
                // case sensitive literal equivalence
                if (expectedValue == null) {
                    Assert.assertNull(actualValue);
                } else if (actualValue != null) {
                    Assert.assertEquals(expectedValue, actualValue);
                } else {
                    Assert.assertNull(expectedValue);
                }
                break;
            case 2:
                // regex comparison
                Assert.assertTrue((String.valueOf(actualValue)).matches(String.valueOf(expectedValue)));
                break;
            case 3:
                // contains
                Assert.assertTrue((String.valueOf(actualValue)).contains(String.valueOf(expectedValue)));
                break;
            case 4:
                // case insensitive equivalence
                Assert.assertTrue((String.valueOf(actualValue)).equalsIgnoreCase(String.valueOf(expectedValue)));
                break;
            default:
                // unhandled case
                return -1;
        }
        return 1;
    }

    // Compare between two objects Negatively
    private static int compareTwoObjectsNegatively(Object expectedValue, Object actualValue, int comparisonType) {
        switch (comparisonType) {
            case 1:
                // case sensitive literal equivalence
                if (expectedValue == null) {
                    Assert.assertNotNull(actualValue);
                } else if (actualValue != null) {
                    Assert.assertNotEquals(expectedValue, actualValue);
                } else {
                    Assert.assertNotNull(expectedValue);
                }
                break;
            case 2:
                // regex comparison
                Assert.assertFalse((String.valueOf(actualValue)).matches(String.valueOf(expectedValue)));
                break;
            case 3:
                // contains
                Assert.assertFalse((String.valueOf(actualValue)).contains(String.valueOf(expectedValue)));
                break;
            case 4:
                // case insensitive equivalence
                Assert.assertFalse((String.valueOf(actualValue)).equalsIgnoreCase(String.valueOf(expectedValue)));
                break;
            default:
                // unhandled case
                return -1;
        }
        return 1;
    }

}
