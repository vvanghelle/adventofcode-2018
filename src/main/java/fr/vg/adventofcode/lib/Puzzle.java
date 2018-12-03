package fr.vg.adventofcode.lib;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Puzzle {

    public Stream<String> readInput(String filename) throws IOException, URISyntaxException, NullPointerException {
        ClassLoader cl = getClass().getClassLoader();
        URL resource = cl.getResource(filename);
        URI uri = resource.toURI();
        Path path = Paths.get(uri);
        return Files.lines(path);
    }

}
