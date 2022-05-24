package ru.doczilla;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    Path root = Path.of(args[0]);

    boolean executable = Files.isExecutable(root);
    if (!executable) {
      System.out.println("Directory is not executable!");
    }
    else {
      List<Path> paths = LetterGluer.getSortedListOfPaths(root);
      LetterGluer.writeToOneFile(paths, root);
    }
  }
}
