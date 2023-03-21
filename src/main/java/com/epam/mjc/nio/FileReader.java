package com.epam.mjc.nio;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach((line) -> stringBuilder.append(line).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] everything = stringBuilder.toString().split("\n");
        Map<String, String> profileMap = new HashMap<String, String>();

        for (String line : everything) {
            String[] pair = line.split(": ");
            profileMap.put(pair[0], pair[1]);
        }

        return new Profile(
                profileMap.get("Name"),
                Integer.parseInt(profileMap.get("Age")),
                profileMap.get("Email"),
                Long.parseLong(profileMap.get("Phone"))
        );
    }
}
