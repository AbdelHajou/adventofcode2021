package nl.abdel.aoc.helper;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class InputHelper {

    public static final String INPUTS_FOLDER = "/inputs/";

    public static int[] readLinesToIntegers(String fileName) throws IOException {
        List<String> lines = IOUtils.readLines(InputHelper.class.getResourceAsStream(INPUTS_FOLDER + fileName), Charset.defaultCharset());
        return lines.stream().mapToInt(Integer::parseInt).toArray();
    }

    public static List<String> readLines(String fileName) throws IOException {
        List<String> lines = IOUtils.readLines(InputHelper.class.getResourceAsStream(INPUTS_FOLDER + fileName), Charset.defaultCharset());
        return lines;
    }

    public static String readFileToString(String fileName) throws IOException {
        return new String(InputHelper.class.getResourceAsStream(INPUTS_FOLDER + fileName).readAllBytes(), Charset.defaultCharset());
    }
}
