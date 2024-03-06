package org.uispec4j.utils;

import org.uispec4j.assertion.testlibrairies.AssertAdapter;

import java.util.*;

public class ArrayUtils {

  public static String toString(Object[] objects) {
    StringBuffer buffer = new StringBuffer();
    appendLine(buffer, objects, ",");
    return buffer.toString();
  }

  public static String toVerticalString(Object[] array) {
    StringBuilder buffer = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      buffer.append(array[i]);
      if (i < array.length - 1) {
        buffer.append(Utils.LINE_SEPARATOR);
      }
    }
    return buffer.toString();
  }

  private static Integer[] toArray(int[] actual) {
    Integer[] result = new Integer[actual.length];
    for (int i = 0; i < actual.length; i++) {
      result[i] = actual[i];
    }
    return result;
  }

  public static String toString(Object[][] objects) {
    StringBuffer buffer = new StringBuffer();
    buffer.append('[');
    for (int i = 0; i < objects.length; i++) {
      if (i > 0) {
        buffer.append('\n');
        buffer.append(' ');
      }
      appendLine(buffer, objects[i], ",\t");
    }
    buffer.append(']');
    return buffer.toString();
  }

  private static void appendLine(StringBuffer buffer, Object[] objects, String separator) {
    buffer.append('[');
    for (int i = 0; i < objects.length; i++) {
      if (objects[i] == null) {
        buffer.append("null");
      }
      else if (objects[i].getClass().isArray()) {
        buffer.append(toString((Object[])objects[i]));
      }
      else {
        buffer.append(objects[i]);
      }
      if (i < (objects.length - 1)) {
        buffer.append(separator);
      }
    }
    buffer.append(']');
  }

  public static String toString(int[] ints) {
    StringBuilder buffer = new StringBuilder();
    buffer.append('[');
    for (int i = 0; i < ints.length; i++) {
      buffer.append(ints[i]);
      if (i < (ints.length - 1)) {
        buffer.append(',');
      }
    }
    buffer.append(']');
    return buffer.toString();
  }

  public static String toString(List list) {
    StringBuilder buffer = new StringBuilder();
    buffer.append('[');
    for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
      buffer.append(iterator.next());
      if (iterator.hasNext()) {
        buffer.append(',');
      }
    }
    buffer.append(']');
    return buffer.toString();
  }

  public static Boolean[][] toBooleanObjects(boolean[][] source) {
    Boolean[][] result = new Boolean[source.length][];
    for (int i = 0; i < result.length; i++) {
      result[i] = new Boolean[source[i].length];
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = source[i][j];
      }
    }
    return result;
  }

  public static void assertEquals(String message, Object[] expected, Object[] actual) {
    if (!Arrays.equals(expected, actual)) {
      fail(message, expected, actual);
    }
  }

  private static void fail(String message, Object[] expected, Object[] actual) {
    boolean verticalDisplay = expected.length > 5 || actual.length > 5;
    if (verticalDisplay) {
      AssertAdapter.assertEquals(message, toVerticalString(expected), toVerticalString(actual));
    }
    else {
      String prefix = message != null ? message + "\n" : "";
      AssertAdapter.fail(prefix +
                         "Expected: " + toString(expected) +
                         "\nActual:   " + toString(actual));
    }
  }

  public static void assertEquals(Object[] expected, Object[] actual) {
    if (actual == null) {
      AssertAdapter.assertNull("Actual array is not null", expected);
    }
    if (!Arrays.equals(expected, actual)) {
      fail(null, expected, actual);
    }
  }

  public static void assertEquals(Object[][] expected, Object[][] actual) {
    AssertAdapter.assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      assertEquals("Error at row " + i + ":", expected[i], actual[i]);
    }
  }

  public static void assertEquals(int[] expected, int[] actual) {
    if (!Arrays.equals(expected, actual)) {
      fail(null, toArray(expected), toArray(actual));
    }
  }

  public static void assertEquals(Object[] expectedArray, List list) {
    assertEquals(expectedArray, list.iterator());
  }

  public static void assertEquals(Object[] expectedArray, Iterator actualIterator) {
    int index = 0;
    List actualList = new ArrayList();
    while (actualIterator.hasNext()) {
      if (index >= expectedArray.length) {
        for (Iterator iterator = actualIterator; iterator.hasNext(); ) {
          actualList.add(iterator.next());
        }
        AssertAdapter.fail("The iterator contains too many elements: expected: " +
                           toString(expectedArray) + " but was: " + actualList);
      }
      Object obj = actualIterator.next();
      actualList.add(obj);
      if (!obj.equals(expectedArray[index])) {
        AssertAdapter.fail("Mismatch at index " + index + ". expected: " + expectedArray[index] +
                           " but was: " + obj);
      }
      index++;
    }
    if (index < expectedArray.length) {
      fail("Several elements are missing from the iterator", expectedArray, actualList.toArray());
    }
  }

  public static void orderedCompare(Object[][] expectedData, Object[][] actualData) {
    compareCollection(actualData, expectedData);
  }

  private static void compareCollection(Object[][] actualData, Object[][] expectedData) {
    Collection<String> actual = new ArrayList<>();
    Collection<String> expected = new ArrayList<>();
    for (Object[] anActualData : actualData) {
      actual.add(toString(anActualData));
    }
    for (Object[] anExpectedData : expectedData) {
      expected.add(toString(anExpectedData));
    }
    AssertAdapter.assertEquals(expected, actual);
  }

  public static void assertEmpty(Object[] array) {
    if ((array != null) && (array.length > 0)) {
      AssertAdapter.fail("Array should be empty but is " + toString(array));
    }
  }

  public static void assertEmpty(List list) {
    if ((list != null) && (!list.isEmpty())) {
      AssertAdapter.fail("List should be empty but is " + toString(list));
    }
  }
}

