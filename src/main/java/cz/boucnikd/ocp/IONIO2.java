package cz.boucnikd.ocp;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ServiceLoader;
import java.util.stream.IntStream;

public class IONIO2 {
    public static void main(String[] args) throws Exception {

        var pf = Path.of("C:", "Program Files");
        var paths = Paths.get("C:", "Program Files");

        Files.walk(pf)
                .limit(100)
                .skip(99)
                .forEach(IONIO2::printFileInfo);

        try(var files = Files.list(paths)){
            printFileInfo(files.findFirst().get());
        }

        try(var numbers = IntStream.range(0,5)){
            numbers.forEach(System.out::println);
        }

        System.out.println("paths.getNameCount" + paths.getNameCount());
        System.out.println("paths.subpath(0,1).getFileName()" + paths.subpath(0,1).getFileName());
        System.out.println("paths.subpath(0,1).getFileName().getNameCount()" + paths.subpath(0,1).getNameCount());

        
    }

    private static void printFileInfo(Path path) {
        System.out.println(path.getFileName());
        System.out.println(path.toAbsolutePath());
        System.out.println("Directory" + path.toFile().isDirectory());
        System.out.println("Last modified" + path.toFile().lastModified());
        System.out.println("Size:" + path.toFile().length());
    }
}
