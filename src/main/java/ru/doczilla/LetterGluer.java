package ru.doczilla;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class LetterGluer {

  public static List<Path> getSortedListOfPaths(Path path) {

    List<Path> result;
    try (Stream<Path> walk = Files.walk(path)) {
      result = walk.filter(Files::isRegularFile)
          .sorted(Comparator.comparing(o -> o.getFileName().toString()))
          .collect(Collectors.toList());
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }

    return result;
  }

  public static void writeToOneFile(List<Path> pathsOfFiles, Path outPathOfFile) {

    Path out = Path.of(outPathOfFile.toString(), "file.txt");

    try (BufferedWriter writer = Files.newBufferedWriter(out)) {
      for (Path path : pathsOfFiles) {
        if (Files.isReadable(path)){
          List<String> list = Files.readAllLines(path);
          String s = String.join("\n", list);
          writer.write(s);
          writer.newLine();
          writer.newLine();
        }
      }
      System.out.println("Data saved to " + out);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
