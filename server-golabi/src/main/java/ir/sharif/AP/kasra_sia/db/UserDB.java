package ir.sharif.AP.kasra_sia.db;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.AP.kasra_sia.model.account.User;

import ir.sharif.AP.kasra_sia.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import static com.fasterxml.jackson.databind.SerializationFeature.FLUSH_AFTER_WRITE_VALUE;

public class UserDB implements DBSet<User>, Commons {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public User get(int id) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing != null) {
            try {
                return objectMapper.readValue(new FileReader(USERS_DIR_PATH + "/" + id + ".json"), new TypeReference<User>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<User> all() {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        assert UserDirectoryListing != null;
        if (UserDirectoryListing.length != 0) {
            LinkedList<User> list = new LinkedList<>();
            for (File child : UserDirectoryListing) {
                // Do something with child
                User user = null;
                try {
                    user = objectMapper.readValue(new FileReader(child.getPath()), new TypeReference<User>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(user);
            }
            Collections.sort(list, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getID()- o2.getID();
                }
            });
            return list;
        }
        return null;
    }

    @Override
    public void add(User user) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        assert UserDirectoryListing != null;
        if (UserDirectoryListing.length != 0) {
            user.setID(UserDirectoryListing.length);
        } else {
            user.setID(0);
        }
        File file = new File(USERS_DIR_PATH + "/" + user.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), user);
            objectMapper.configure(FLUSH_AFTER_WRITE_VALUE, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        File temp = null;

        assert UserDirectoryListing != null;
        if (UserDirectoryListing.length != 0) {
            for (File child : UserDirectoryListing) {
                if (child.getName().equals(String.valueOf(user.getID() + ".json")))
                    temp = child;
            }
            temp.delete();
            removePhoto(user);
        }
    }

    @Override
    public void update(User user) {

        File file = new File(USERS_DIR_PATH + "/" + user.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, user);
            objectMapper.configure(FLUSH_AFTER_WRITE_VALUE, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public File getPhoto(int id) {
        File file = new File("src/main/resources/db/usersPhoto/" + id + ".png");
        if (file.exists())
            return file;
        return new File(Commons.GALLERY_IMG.getDescription());
    }

    @Override
    public void updatePhoto(User user,String encodedImage) {
        int width = Commons.PROFILE_BIG_IMG_WIDTH;
        int height = Commons.PROFILE_BIG_IMG_HEIGHT;
        BufferedImage image = null;
        try {
            image = ImageUtil.convert(encodedImage);

            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        try {
            File output_file = new File("src/main/resources/db/usersPhoto/" + user.getID() + ".png");
            ImageIO.write(image, "png", output_file);

            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void removePhoto(User user) {
        File dir = new File(USERS_IMG_DIR_PATH);
        File[] UserPhotoDirectoryListing = dir.listFiles();
        File temp = null;
        assert UserPhotoDirectoryListing != null;
        if (UserPhotoDirectoryListing.length != 0) {
            for (File child : UserPhotoDirectoryListing) {
                if (child.getName().equals(String.valueOf(user.getID() + ".png")))
                    temp = child;
            }
            if (temp != null)
                temp.delete();

        }
    }

}

