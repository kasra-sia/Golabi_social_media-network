package ir.sharif.AP.kasra_sia.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.AP.kasra_sia.model.*;
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

public class TweetDB implements DBSet<Tweet>, Commons {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Tweet get(int id) {
        File dir = new File(TWEETS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        if (TweetDirectoryListing != null) {
            try {
                return objectMapper.readValue(new FileReader(TWEETS_DIR_PATH + "/" + id + ".json"), new TypeReference<Tweet>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList all() {
        File dir = new File(TWEETS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        if (TweetDirectoryListing.length != 0) {
            LinkedList<Tweet> list = new LinkedList<>();
            for (File child : TweetDirectoryListing) {
                // Do something with child
                Tweet tweet = null;
                try {
                    tweet = objectMapper.readValue(new FileReader(child.getPath()), new TypeReference<Tweet>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(tweet);
            }
            Collections.sort(list, new Comparator<Tweet>() {
                @Override
                public int compare(Tweet o1, Tweet o2) {
                    return o2.getID() - o1.getID();
                }
            });
            return list;
        }
        return null;
    }

    @Override
    public void add(Tweet tweet) {
        File dir = new File(TWEETS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        assert TweetDirectoryListing != null;
        if (TweetDirectoryListing.length != 0) {
            tweet.setID(TweetDirectoryListing.length);
        } else {
            tweet.setID(0);
        }
        File file = new File(TWEETS_DIR_PATH + "/" + tweet.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), tweet);
            objectMapper.configure(FLUSH_AFTER_WRITE_VALUE, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Tweet tweet) {
        File dir = new File(TWEETS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        File temp = null;
        assert TweetDirectoryListing != null;
        if (TweetDirectoryListing.length != 0) {
            for (File child : TweetDirectoryListing) {
                if (child.getName().equals(String.valueOf(tweet.getID() + ".json")))
                    temp = child;
            }
            assert temp != null;
            removePhoto(tweet);
            temp.delete();
        }
    }

    @Override
    public void update(Tweet tweet) {

        File file = new File(TWEETS_DIR_PATH + "/" + tweet.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), tweet);
            objectMapper.configure(FLUSH_AFTER_WRITE_VALUE, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePhoto(Tweet tweet, String encodedImage) {
        BufferedImage image = null;
        try {
            image = ImageUtil.convert(encodedImage);
            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        try {
            File output_file = new File("src/main/resources/db/tweetsImage/" + tweet.getID() + ".png");
            ImageIO.write(image, "png", output_file);
            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public File getPhoto(int id) {
        File file = new File("src/main/resources/ir.sharif.AP.kasra_sia.db/tweetsImage/" + id + ".png");

        if (file.exists())
            return file;
        return null;
    }

    @Override
    public void removePhoto(Tweet tweet) {

        File dir = new File(TWEETS_IMG_DIR_PATH);
        File[] TweetIMageDirectoryListing = dir.listFiles();
        File temp = null;
        assert TweetIMageDirectoryListing != null;
        if (TweetIMageDirectoryListing.length != 0) {
            for (File child : TweetIMageDirectoryListing) {
                if (child.getName().equals(String.valueOf(tweet.getID() + ".png")))
                    temp = child;
            }
            assert temp != null;
            if (temp != null)
                temp.delete();

        }
    }


    public void report(Tweet tweet, User user) {
        if (!tweet.getReportCounter().contains(user.getID())) {
            FileWriter fileWriter = null;
            tweet.getReportCounter().add(user.getID());
            update(tweet);
            try {
                File file = new File("src/main/resources/ir.sharif.AP.kasra_sia.db/reportedTweets/" + tweet.getID() + ".txt");
                file.createNewFile();
                fileWriter = new FileWriter("src/main/resources/ir.sharif.AP.kasra_sia.db/reportedTweets/" + tweet.getID() + ".txt", true);
                fileWriter.write(user.getUsername() + " has reported this tweet");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (tweet.getReportCounter().size() == 3)
                remove(tweet);
        }
    }
}

