package io.github.coderodde.intfilesorter;

import io.github.coderodde.util.ExternalIntMergeSort;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IntFileSorter {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            return;
        }
        
        Path source = Path.of(args[0]);
        Path target = Path.of(args[1]);
        
        if (!Files.exists(source)) {
            System.out.println("Source file does not exist.");
            return;
        }
        
        if (Files.exists(target)) {
            try {
                if (Files.isSameFile(source, target)) {
                    System.err.println("Source and target refer to the same file.");
                    System.exit(2);
                }
            } catch (IOException e) {
                System.err.println("Failed to compare source/target: " + e.getMessage());
                System.exit(2);
            }
        }
        
        long ta = System.currentTimeMillis();
        ExternalIntMergeSort.sort(source, target);
        long tb = System.currentTimeMillis();
        
        System.out.println("File sorted in " + (tb - ta) + " ms.");
    }
}
