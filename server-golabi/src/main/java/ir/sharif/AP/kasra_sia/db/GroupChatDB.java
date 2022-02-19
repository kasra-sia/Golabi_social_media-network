package ir.sharif.AP.kasra_sia.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.AP.kasra_sia.model.*;
import ir.sharif.AP.kasra_sia.model.chat.GroupChat;
import ir.sharif.AP.kasra_sia.util.Commons;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class GroupChatDB implements DBSet<GroupChat>, Commons {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public GroupChat get(int id) {
        File dir = new File(GROUP_CHATS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        if (TweetDirectoryListing != null) {
            try {
                return objectMapper.readValue(new FileReader(GROUP_CHATS_DIR_PATH + "/" + id + ".json"), new TypeReference<GroupChat>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<GroupChat> all() {
        File dir = new File(GROUP_CHATS_DIR_PATH);
        File[] GroupChatsDirectoryListing = dir.listFiles();
        if (GroupChatsDirectoryListing.length != 0) {
            LinkedList<GroupChat> list = new LinkedList<>();
            for (File child : GroupChatsDirectoryListing) {
                // Do something with child
                GroupChat groupChat = null;
                try {
                    groupChat = objectMapper.readValue(new FileReader(child.getPath()), new TypeReference<GroupChat>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(groupChat);
            }
            Collections.sort(list, Comparator.comparingInt(Model::getID));
            return list;
        }
        return null;
    }

    @Override
    public void add(GroupChat groupChat) {
        File dir = new File(GROUP_CHATS_DIR_PATH);
        File[] GroupChatsDirectoryListing = dir.listFiles();
        assert GroupChatsDirectoryListing != null;
        if (GroupChatsDirectoryListing.length != 0) {
            groupChat.setID(-(GroupChatsDirectoryListing.length+1));
        } else {
            groupChat.setID(-1);
        }

        File file = new File(GROUP_CHATS_DIR_PATH + "/" + groupChat.getID() + ".json");

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), groupChat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(GroupChat groupChat) {
        File dir = new File(GROUP_CHATS_DIR_PATH);
        File[] GroupChatsDirectoryListing = dir.listFiles();
        File temp = null;
        assert GroupChatsDirectoryListing != null;
        if (GroupChatsDirectoryListing.length != 0) {
            for (File child : GroupChatsDirectoryListing) {
                if (child.getName().equals(String.valueOf(groupChat.getID() + ".json")))
                    temp = child;
            }
            assert temp != null;
            removePhoto(groupChat);
            temp.delete();
        }
    }

    @Override
    public void update(GroupChat groupChat) {

        File file = new File(GROUP_CHATS_DIR_PATH + "/" + groupChat.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), groupChat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getPhoto(int id) {
        return null;
    }

    @Override
    public void updatePhoto(GroupChat groupChat, String path) {

    }

    @Override
    public void removePhoto(GroupChat groupChat) {

    }

}
