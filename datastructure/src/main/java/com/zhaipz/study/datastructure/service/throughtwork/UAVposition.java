package com.zhaipz.study.datastructure.service.throughtwork;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UAVposition {
    public static void main(String[] args) throws IOException {
        String uavFilePath = "datastructure/src/main/resources/test.txt";
        List<String> res = getUAVRecords(uavFilePath);

        System.out.println(res.toString());
    }

    public static List<String> getUAVRecords(String fileName) throws IOException {
        List<String> res = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        Stream<String> stringStream = Files.lines(Paths.get(fileName));
        stringStream.parallel().forEachOrdered(res::add);

        return res;
    }
}
