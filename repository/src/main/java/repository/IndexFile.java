package repository;

import com.sun.media.jfxmedia.logging.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexFile {

    private static final String INDEX_FILE = "index";
    public static final int DEFAULT_PRIMARY_KEY = 1;

    private final String defaultPath;

    public IndexFile(String defaultPath){
        this.defaultPath = defaultPath;
    }

    private void createIndexFile() {
        try {
            Path path = Paths.get(defaultPath + INDEX_FILE);
            Files.createFile(path);
            saveIndex(DEFAULT_PRIMARY_KEY);
        } catch (IOException e) {
            Logger.logMsg(Logger.ERROR, "Creating index file error");
        }
    }

    public int getNextIndex() {
        try {
            Path path = Paths.get(defaultPath + INDEX_FILE);
            if (Files.notExists(path)) {
                createIndexFile();
                return DEFAULT_PRIMARY_KEY;
            } else {
                return ByteBuffer.wrap(Files.readAllBytes(path)).getInt() + 1;
            }
        } catch (IOException e) {
            Logger.logMsg(Logger.ERROR, "Read index file error");
            return DEFAULT_PRIMARY_KEY;
        }
    }

    public void saveIndex(int index) {
        try {
            Path path = Paths.get(defaultPath + INDEX_FILE);
            Files.write(path, ByteBuffer.allocate(4).putInt(index).array());
        } catch (IOException e) {
            Logger.logMsg(Logger.ERROR, "Write index to file error");
        }
    }

    public Path getIndexFilePath(){
        return Paths.get(defaultPath + INDEX_FILE);
    }
}
