package com.example.user.loginsignup;

import android.content.Context;
import android.os.Environment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by user on 30-01-2018.
 */

public class UserCluster implements Serializable{
    HashMap<String, User> userMap;

    public UserCluster() {
        userMap = new HashMap<>();
    }

    public boolean addUser(User user) {
        if(exists(user.getUsername()))
            return false;

        user.setPassword(hashPassword(user.getPassword()));
        userMap.put(user.getUsername(), user);

        return true;

    }

    public boolean exists(String username) {
        if(userMap.containsKey(username))
            return true;
        else
            return false;
    }

    protected String hashPassword(String password) {
        String hash = null;
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            byte[] result = sha1.digest(password.getBytes());
            StringBuffer digest = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                digest.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = digest.toString();
        } catch (NoSuchAlgorithmException e) {

        }

        return hash;
    }

    public User authenticate(User user) {
        User u = userMap.get(user.getUsername());
        if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(hashPassword(user.getPassword()))) {
            return u;
        }
        return null;
    }

    public void save(FileOutputStream fos) {

        try {
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(this);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static UserCluster load(FileInputStream fis) {
        try {

            ObjectInputStream is = new ObjectInputStream(fis);
            UserCluster userCluster = (UserCluster) is.readObject();
            is.close();
            fis.close();
            return userCluster;
        } catch (IOException e) {
            return new UserCluster();
        } catch (ClassNotFoundException e) {
            return new UserCluster();
        }


    }
}


/**
 * Created by user on 30-01-2018.
 */

