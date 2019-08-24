package repository;

import com.google.gson.Gson;
import com.sun.media.jfxmedia.logging.Logger;
import entity.*;
import entity.Error;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserFileRepository implements UserRepository {


    private final String defaultPath;
    private final IndexFile indexFile;

    public UserFileRepository(String defaultPath){
        this.defaultPath = defaultPath;
        indexFile = new IndexFile(this.defaultPath);
    }

    @Override
    public void save(User user) {
        try {
            createDefaultDirectoryIfNeeded();
            if(user.getPrimaryKey() != 0){
                Path path = Paths.get(defaultPath + File.separator + user.getPrimaryKey());
                Files.write(path, new Gson().toJson(user).getBytes());
            } else {
                int index = indexFile.getNextIndex();
                Path path = Paths.get(defaultPath + File.separator + index);
                user.setPrimaryKey(index);
                Files.write(path, new Gson().toJson(user).getBytes());
                indexFile.saveIndex(index);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public UserResult getUser(String fileName) {
        try {
            Path path = Paths.get(defaultPath + fileName);
            User user = new Gson().fromJson(new String(Files.readAllBytes(path)), User.class);
            UserResult userResult = new UserResult();
            userResult.setUser(user);
            user.setPrimaryKey(Integer.parseInt(fileName));
            return userResult;
        } catch (IOException e) {
            System.out.println(e);
            return new UserResult(null, Error.READ_ERROR);
        } catch (NumberFormatException e){
            Logger.logMsg(Logger.ERROR, "Parse primary key error");
            return new UserResult(null, Error.READ_ERROR);
        }
    }

    @Override
    public UsersResult getAllUsers() {
        try {
            Users users = new Users();
            Files.walk(Paths.get(defaultPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> !indexFile.getIndexFilePath().equals(path))
                    .forEach(path -> {
                        try {
                            users.add(new Gson().fromJson(new String(Files.readAllBytes(path)), User.class));
                        } catch (IOException e) {

                        }
                    });
            return new UsersResult(users, null);
        } catch (NullPointerException e) {
            return new UsersResult();
        } catch (IOException e) {
            Logger.logMsg(Logger.WARNING, "Read error");
            return new UsersResult(null, Error.getReadError());
        }
    }

    @Override
    public DeleteUserResult deleteUser(User user) {
        try {
            Path path = Paths.get(defaultPath + user.getPrimaryKey());
            if (Files.deleteIfExists(path)) {
                return new DeleteUserResult(DeleteUserResult.SUCCESS);
            } else return new DeleteUserResult(DeleteUserResult.ALREADY_DELETED);
        } catch (IOException e) {
            return new DeleteUserResult(DeleteUserResult.ERROR);
        }
    }

    private void createDefaultDirectoryIfNeeded() throws IOException {
        if (Files.notExists(Paths.get(defaultPath))) Files.createDirectories(Paths.get(defaultPath));
    }


}
