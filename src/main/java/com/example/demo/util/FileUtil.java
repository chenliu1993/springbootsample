package com.example.demo.util;

import lombok.NoArgsConstructor;
import java.io.*;
import java.util.UUID;

// import com.example.demo.domain.Post;

import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@Slf4j
public final class FileUtil {

    private static FileUtil fileUtil = null;
    static {
        log.info("Initialize the FileUtil instance");
        if(fileUtil == null) fileUtil = new FileUtil();
    }

    public final String postDir = "/tmp";

    public void WriteToPost(String postFileName, String content) {
        FileOutputStream post = null;
        try {
            post = new FileOutputStream(postFileName);
            post.write(content.getBytes());
            post.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("write to file finished");
    }

    public String ReadFromPost(String postFileName) {
        FileInputStream post = null;
        String result = "";
        try {
            post = new FileInputStream(postFileName);
            byte[] input = new byte[1024];
          
            int length = post.read(input);
            result = new String(input, 0, length);
            post.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String generateFileName(String userID) {
        UUID uuid = UUID.randomUUID();
        return postDir + File.separator + userID+uuid.timestamp()+".txt";
    }
}
