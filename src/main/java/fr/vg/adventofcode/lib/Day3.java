package fr.vg.adventofcode.lib;

import java.util.Arrays;
import java.util.stream.Stream;

import static fr.vg.adventofcode.lib.day3.Claim.FABRIC_LENGTH;

public class Day3 {

    public long[][] buildMap(Stream<String> inputs) {
        long[][] values = new long[FABRIC_LENGTH][];
        for (int i = 0; i < FABRIC_LENGTH; i++) {
            values[i] = new long[FABRIC_LENGTH];
            for (int j = 0; j < FABRIC_LENGTH; j++) {
                values[i][j] = 0;
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
                    values[i][j] += 1;
                }
            }
        });

        return values;
    }

    public long nbInchSquareInConflict(Stream<String> inputs) {
        long[][] values = buildMap(inputs);

        return Arrays.stream(values)
                .map(arr -> Arrays.stream(arr).filter(x -> x > 1).count())
                .mapToLong(i -> i)
                .sum();
    }

}
