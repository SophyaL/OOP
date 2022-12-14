package ru.nsu.fit.lylova;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Class that contains function main.
 */
public class Main {
    /**
     * Implementation of example in task.
     *
     * @param args command line
     */
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>();
        tree.add("A");
        var nodeB = tree.addVertexByValue("B");
        tree.addVertexByParentAndValue(nodeB, "AB");
        tree.addVertexByParentAndValue(nodeB, "BB");
        Iterator<Node<String>> iterator;
        iterator = tree.new TreeDfsIterator();
        var streamNodes = Stream.generate(() -> null)
                .takeWhile(x -> iterator.hasNext())
                .map(n -> iterator.next());
        var resArray = streamNodes
                .filter(node -> node.getValue().contains("B") && node.childrenCount() == 0)
                .toArray();
        System.out.println("Array of strings that are lists in tree: "
                + Arrays.toString(resArray));
    }
}