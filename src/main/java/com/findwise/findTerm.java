package com.findwise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class findTerm {
    final static String PATH = "src/main/resources/documentExamples";

    public static void addDocumentToIndex(SearchEngine se, Path filePath) {
        String id = filePath.toFile().getName();
        try {
            String content = Files.readString(filePath);
            se.indexDocument(id, content);
            System.out.println(id + ": document added to search engine");
        } catch (IOException e) {
            System.out.println(id + ": couldn't read file");
        }

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        SingleTermSearchEngine searchEngine = new SingleTermSearchEngine();
        String path;
        String ans;


        while (true) {
            System.out.println("Do you want to use your documents? Y/N: ");
            ans = scn.next();
            if (ans.equals("Y")) {
                System.out.println("Please give absolute path to your documents folder: ");
                path = scn.next();
                break;
            } else if (ans.equals("N")) {
                System.out.println("The documents from resources will be used");
                path = new File(PATH).getAbsolutePath();
                break;
            }

        }

        System.out.println("Reading documents from: " + path);
        try (Stream<Path> walk = Files.walk((new File(path).toPath())).sorted()) {
            List<Path> result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
            for (Path file : result) {
                addDocumentToIndex(searchEngine, file);
            }

        } catch (IOException e) {
            System.out.println("Path is invalid");
            scn.close();
            return;
        }

        System.out.println("\nWhat term do you want to search for: ");
        String term = scn.next();
        scn.close();

        List<IndexEntry> result = searchEngine.search(term);
        if (result.size() == 0)
            System.out.println("\nAll documenst have TF-IDF score = 0 ");
        else {
            System.out.println("\nSearch result: ");

            for (IndexEntry entry : result) {
                System.out.println(entry.getId() + " " + entry.getScore());
            }
        }
    }


}
