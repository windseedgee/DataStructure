package com.zhaipz.study.datastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

//@SpringBootTest
@Slf4j
class DatastructureApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void getUserIds() throws IOException {

        File file = new File(String.valueOf(Path.of("src/test/java/com/zhaipz/study/datastructure/resource/userIds.txt")));
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
//        System.out.println("file content: "+content);
        List<String> userIds = new ArrayList<>();
        Pattern pattern = Pattern.compile("userId=(\\d+)");
        Matcher matcher = pattern.matcher(content.toString());
        while (matcher.find()) {
            userIds.add(matcher.group(1));
        }
        System.out.println("size: " + userIds.size());
        System.out.println(userIds);

        String param = "";

        for (int i = 0; i < userIds.size(); i++) {
            param = param + "&userIds=" + userIds.get(i);
        }

        System.out.println(param);

    }
}
