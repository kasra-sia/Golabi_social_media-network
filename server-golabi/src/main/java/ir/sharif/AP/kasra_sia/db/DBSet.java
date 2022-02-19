package ir.sharif.AP.kasra_sia.db;


import java.io.File;
import java.util.LinkedList;

public interface DBSet<T> {
    T get(int id);
    LinkedList<T> all();
    void add(T t);
    void remove(T t);
    void update(T t);
    File getPhoto(int id);
    void updatePhoto(T t,String encodedImage);
    void removePhoto(T t);

}