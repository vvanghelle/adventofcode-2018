package fr.vg.adventofcode.lib;

import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.vg.adventofcode.lib.day3.Claim.FABRIC_LENGTH;

public class Day3 {

    public List<String>[][] buildMap(Stream<String> inputs) {
        List<String>[][] values = new ArrayList[FABRIC_LENGTH][];
        for (int i = 0; i < FABRIC_LENGTH; i++) {
            values[i] = new ArrayList[FABRIC_LENGTH];
            for (int j = 0; j < FABRIC_LENGTH; j++) {
                values[i][j] = new ArrayList<>();
            }
        }

        inputs.forEach(claim ->{
            String[] claimsInfo = claim.split(" ");
            int offsetLeft = Integer.valueOf(claimsInfo[2].split(",")[0]);
            int offsetTop = Integer.valueOf(claimsInfo[2].split(",")[1].substring(0, claimsInfo[2].split(",")[1].length() - 1));
            int width = Integer.valueOf(claimsInfo[3].split("x")[0]);
            int height = Integer.valueOf(claimsInfo[3].split("x")[1]);
            for (int i = offsetLeft; i< offsetLeft + width; i++){
                for (int j = offsetTop; j< offsetTop + height; j++){
                    values[i][j].add(claimsInfo[0].substring(1));
                }
            }
        });

        return values;
    }

    public long nbInchSquareInConflict(Stream<String> inputs) {
        List<String>[][] values = buildMap(inputs);

        return Arrays.stream(values)
                .map(arr -> Arrays.stream(arr).filter(x -> x.size() > 1).count())
                .mapToLong(i -> i)
                .sum();
    }

    public String findClaimWithoutConflict(Stream<String> inputs) {
        List<String>[][] values = buildMap(inputs);
        Set<String> claimsInConflict = Arrays.stream(values)
                .map(arr -> Arrays.stream(arr)
                        .filter(x -> x.size() > 1)
                        .flatMap(List::stream)
                        .collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        Set<String> result = Arrays.stream(values)
                .map(arr -> Arrays.stream(arr)
                        .filter(x -> x.size() == 1 && !claimsInConflict.contains(x.get(0)))
                        .flatMap(List::stream)
                        .collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        return result.isEmpty() ? null : result.iterator().next();
    }

}
