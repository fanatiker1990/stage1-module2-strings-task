package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        Iterator<String> stringIterator = delimiters.iterator();
        List<String> resultFinal = new ArrayList<>();
        String line = source;
        while (stringIterator.hasNext()) {
            StringBuilder stringInn = new StringBuilder();
            StringTokenizer stringTokenizer = new StringTokenizer(line, stringIterator.next());
            while (stringTokenizer.hasMoreTokens()) {
                stringInn.append(stringTokenizer.nextToken()).append(" ");
            }
            line = stringInn.toString();
        }
        line=line.trim();
        StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
        while (stringTokenizer.hasMoreTokens()) {
            resultFinal.add( stringTokenizer.nextToken());
        }
        return resultFinal;
    }
}
