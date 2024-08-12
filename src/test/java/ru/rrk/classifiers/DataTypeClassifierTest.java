package ru.rrk.classifiers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTypeClassifierTest {
    DataTypeClassifier classifier = new DataTypeClassifier("^-?\\d+$", "^[-+]?[0-9]*[.,][0-9]+(?:[eE][-+]?[0-9]+)?$");

    @Test
    public void testInteger_returnsInteger() {
        String input = "42";
        DataType expectedValue = DataType.INTEGER;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFloat_returnsFloat() {
        String input = "42.312";
        DataType expectedValue = DataType.FLOAT;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testString_returnsString() {
        String input = "Lorem ipsum";
        DataType expectedValue = DataType.STRING;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testNull_returnsVoid() {
        String input = null;
        DataType expectedValue = DataType.VOID;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEmptyString_returnsVoid() {
        String input = "";
        DataType expectedValue = DataType.VOID;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testBlankString_returnsVoid() {
        String input = "           ";
        DataType expectedValue = DataType.VOID;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testNegativeInteger_returnInteger() {
        String input = "-42123";
        DataType expectedValue = DataType.INTEGER;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testZeroWithMinus_returnInteger() {
        String input = "-0";
        DataType expectedValue = DataType.INTEGER;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFloatInExpFormatWithoutSign_returnFloat() {
        String input = "3.14e10";
        DataType expectedValue = DataType.FLOAT;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFloatInExpFormatWithPlus_returnFloat() {
        String input = "3.14e+10";
        DataType expectedValue = DataType.FLOAT;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFloatInExpFormatWithMinus_returnFloat() {
        String input = "3.14e-10";
        DataType expectedValue = DataType.FLOAT;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testNegativeFloat_returnFloat() {
        String input = "-0.00123";
        DataType expectedValue = DataType.FLOAT;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFloatWithZeroAfterDot_returnFloat() {
        String input = "42.0";
        DataType expectedValue = DataType.FLOAT;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFloatLessThanOne_returnFloat() {
        String input = "0.789";
        DataType expectedValue = DataType.FLOAT;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFloatWithTwoExpLetters_returnString() {
        String input = "3.12Ee-12";
        DataType expectedValue = DataType.STRING;

        DataType actualValue = classifier.classify(input);

        assertEquals(expectedValue, actualValue);
    }
}
