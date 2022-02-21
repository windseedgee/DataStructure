package com.zhaipz.study.datastructure.service.throughtwork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UAVposition {
    public static void main(String[] args) {

    }

    public static List<StringBuilder> getUAVRecords(String fileName) throws FileNotFoundException {
        List<StringBuilder> res = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        reader.lines();

        return res;
    }
}
