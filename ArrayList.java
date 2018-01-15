import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ArrayList {

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    private static int length(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == (char) 0) {
                return i;
            }
        }

        return 0;
    }

    private static void push(char[][] array, char[] item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == (char) 0) {
                array[i] = item;
                return;
            }
        }
    }

    private static void delete(char[][] array, char[] item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                array[i] = new char[array[i].length];
                arrayRepair(array);
                return;
            }
        }

//        System.out.println("WARNING: nothing deleted");
    }

    private static void repair(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == (char) 0) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j][0] != (char) 0) {
                        array[i] = array[j];
                        array[j] = new char[array[i].length];
                        break;
                    }
                }
                if (array[i] == null) {
                    return;
                }
            }
        }
    }

    @Override
    private static String toString(char[][] array) {
        String s = new String();
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == (char) 0) {
                return s;
            }

            s += Arrays.toString(array[i]);
        }

        return s;
    }
}
