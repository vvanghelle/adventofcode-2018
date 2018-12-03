package fr.vg.adventofcode.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {

    public boolean hasDouble(String str) {
        return hasCharOccurence(str, 2);
    }

    public boolean hasTriple(String str) {
        return hasCharOccurence(str, 3);
    }

    public boolean hasCharOccurence(String str, final long charOccurence) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final int index = i;
            if (str.chars().filter(ch -> ch == chars[index]).count() == charOccurence) {
                return true;
            }
        }
        return false;
    }

    public long checkSum(Stream<String> inputs) {
        List<String> listinput = inputs.collect(Collectors.toList());
        long nbDouble = listinput.stream().filter(this::hasDouble).count();
        long nbTriple = listinput.stream().filter(this::hasTriple).count();
        return nbDouble * nbTriple;
    }

    public long nbCharDiffering(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length())
            throw new IllegalArgumentException("inputs nulls or not the same length");

        long differentChars = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i))
                differentChars += 1;
        }
        return differentChars;
    }


    public List<String> stringsWithOneCharacterDifferingFromString(String reference, List<String> strings) {
        return strings.stream()
                .filter(str -> nbCharDiffering(reference, str) == 1l)
                .collect(Collectors.toList());
    }

    public List<String> stringsWithOneCharacterDiffering(Stream<String> inputs) {
        List<String> inputList = inputs.collect(Collectors.toList());
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i< inputList.size(); i++){
            String reference = inputList.get(i);
            List<String> comparaison = new ArrayList<>(inputList);
            comparaison.remove(i);
            List<String> result = stringsWithOneCharacterDifferingFromString(reference, comparaison);
            if (result.size() > 0)
            {
                resultList.add(reference);
            }
        }
        return resultList;
    }

    public String charsInCommon(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length())
            throw new IllegalArgumentException("inputs nulls or not the same length");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i))
                sb.append(str1.charAt(i));
        }
        return sb.toString();
    }
}
