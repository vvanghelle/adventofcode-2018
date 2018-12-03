package fr.vg.adventofcode.lib;

import fr.vg.adventofcode.lib.day3.Claim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

    public List<Claim> parseClaims(Stream<String> inputs) {
        return inputs.map(Claim::new).collect(Collectors.toList());
    }

    public Claim mergeClaims(Stream<String> inputs) {
        List<Claim> claims = inputs.map(Claim::new).collect(Collectors.toList());
        if (claims == null || claims.isEmpty())
                return null;
        Iterator<Claim> claimsIt = claims.iterator();
        Claim merged = claimsIt.next().clone();
        while (claimsIt.hasNext()){
            merged = merged.merge(claimsIt.next());
        }
        return merged;
    }
}
