package com.example.checklist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {

    // implement Constant
    public static final String FILENAME = "checkListInfo.dat"; // create a file to store "to do" items

    public static ArrayList<String> readData(Context context) {
        ArrayList<String> itemsList = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME); // Open the checkListInfo file for reading from it.
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemsList = (ArrayList<String>) ois.readObject(); // read the contents of the checkListInfo file
        } catch (FileNotFoundException e) {
            itemsList = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemsList;
    }

    public static void writeData(ArrayList<String> items, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE); // Open the checkListInfo file for writing to it.
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items); // write contents to the file
            oos.close(); // close the file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
