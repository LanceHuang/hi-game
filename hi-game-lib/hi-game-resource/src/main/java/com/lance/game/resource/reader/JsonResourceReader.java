package com.lance.game.resource.reader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * json资源读取
 *
 * @author Lance
 * @since 2020/12/3
 */
@Component
public class JsonResourceReader extends AbstractResourceReader {

    @Override
    public String getType() {
        return "json";
    }

    @Override
    public <T> Iterator<T> read(String path, Class<T> clazz) {
        List<T> result = new LinkedList<>();

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // todo
//                result.add()
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(bufferedReader);
            closeQuietly(fileReader);
        }

        return result.iterator();
    }

    public static void closeQuietly(Closeable obj) {
        if (obj != null) {
            try {
                obj.close();
            } catch (IOException e) {
            }
        }
    }
}
