package fr.vg.adventofcode.lib;


import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {

    public Node buildGraph(String readInput) {
        StringTokenizer tk = new StringTokenizer(readInput);
        Integer nbChildren = Integer.valueOf(tk.nextToken());
        Integer metadata = Integer.valueOf(tk.nextToken());
        Node root = buildNode(null, nbChildren, metadata, tk);
        return root;
    }

    private Node buildNode(Node parentNode, Integer nbChildren, Integer metadata, StringTokenizer tk) {
        Node n = new Node(parentNode);
        for (int i = 0; i < nbChildren; i++) {
            n.children.add(buildNode(n, Integer.valueOf(tk.nextToken()), Integer.valueOf(tk.nextToken()), tk));
        }
        for (int i = 0; i < metadata; i++) {
            n.metadata.add(Integer.valueOf(tk.nextToken()));
        }
        return n;
    }

    public Integer buildGraphAndSumMetadata(String readInput) {
        Node root = buildGraph(readInput);
        return sumMetadata(root);
    }

    private Integer sumMetadata(Node node) {
        return node.metadataSum() + node.children.stream().mapToInt(n -> sumMetadata(n)).sum();
    }


    public Integer buildGraphAndCalculateRootNodeValue(String readInput) {
        Node root = buildGraph(readInput);
        return root.value();
    }


    public static class Node {
        public final Node parent;

        public final List<Node> children;

        public final List<Integer> metadata;

        public Node(Node parent) {
            this.parent = parent;
            this.children = new ArrayList<>();
            this.metadata = new ArrayList<>();
        }

        public Integer metadataSum() {
            return metadata.stream().mapToInt(value -> value).sum();
        }

        public Integer value() {
            if (children.isEmpty()) {
                return this.metadataSum();
            }
            return metadata.stream().mapToInt(m -> {
                try {
                    return this.children.get(m - 1).value();
                } catch (IndexOutOfBoundsException e) {
                    return 0;
                }
            }).sum();
        }
    }


}
