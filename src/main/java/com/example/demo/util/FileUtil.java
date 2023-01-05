package com.example.demo.util;

import lombok.NoArgsConstructor;
import java.io.*;
import java.util.UUID;


@NoArgsConstructor
public final class FileUtil {

    private static FileUtil fileUtil = null;
    static {
        if(fileUtil == null) fileUtil = new FileUtil();
    }

    public final String postDir = "/tmp";

    public void WriteToPost(String postFileName, String content) {
        try {
            File post = new File(postFileName);
            if(!post.exists()){
                post.createNewFile();
            }
            FileWriter writer = new FileWriter(post);
            
            writer.write(content);
            writer.flush();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("write to file finished");
    }

    public String ReadFromPost(String postFileName) {
        char[] input = new char[255];
        try {
            File post = new File(postFileName);
            if(!post.exists()){
                throw new FileNotFoundException("file doesn't exists");
            }
            FileReader reader = new FileReader(post);
          
            reader.read(input);
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return input.toString();
    }

    public String generateFileName(String userID) {
        UUID uuid = UUID.randomUUID();
        return postDir + File.separator + userID+uuid.timestamp()+".txt";
    }
}
