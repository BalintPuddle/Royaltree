package balintpuddle.io.file;

import balintpuddle.tree.card.TreeHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ProjectSerializer {
    private static final int version = 1;
    private static ObjectMapper mapper;
    private static final String PROJECT_JSON = "project.json";

    public static void init() {
        mapper = new ObjectMapper();
    }
    public static void save(Path file) throws IOException {
        Project project = new Project(version, TreeHandler.getCards());
        try (OutputStream outputStream = Files.newOutputStream(file)) {
            ZipOutputStream zip = new ZipOutputStream(outputStream);

            // Create project.json inside the ZIP
            ZipEntry projectEntry = new ZipEntry(PROJECT_JSON);
            zip.putNextEntry(projectEntry);

            // Write the project as JSON
            mapper.writeValue(zip, project);

            zip.closeEntry();
        }
    }

    public static Project load(Path file) throws IOException {
        try (InputStream inputStream = Files.newInputStream(file)) {
            ZipInputStream zip = new ZipInputStream(inputStream);

            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {

                if (entry.getName().equals(PROJECT_JSON)) {

                    return mapper.readValue(
                            zip,
                            Project.class
                    );
                }

                zip.closeEntry();
            }
        }

        throw new IOException("Failed to load: project.json not found");
    }

    public static int getFormatVersion() {
        return version;
    }
}
