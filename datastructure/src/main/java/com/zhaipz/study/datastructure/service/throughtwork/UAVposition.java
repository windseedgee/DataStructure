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
        List<String> res = getUAVRecords("D:\\IdeaProjects\\DataStructure\\datastructure\\src\\main\\resources\\test.txt");
        System.out.println(res.get(3));

    }

    public static List<String> getUAVRecords(String fileName) throws IOException {
        List<String> res = new ArrayList<>();
//        Stream<String> stringStream = Files.lines(Paths.get(fileName));
//        stringStream.parallel().forEachOrdered(res::add);
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String str;
        List<String> records = new ArrayList<>();
        while((str = reader.readLine()) != null){
            records.add(str);
        }
        UAV lasted = new UAV();
        for(int i = 0;i < records.size();i++){
            String[] temp = records.get(i).split(" ");
            if(i == 0){
                lasted = new UAV(temp[1],temp[2],temp[3]);
                lasted.setName(temp[0]);
                lasted.setAlive(true);
                res.add(lasted.name + " " + i + " " + lasted.x + " " + lasted.y + " " + lasted.z);
                continue;
            }

            checkUAV(temp,lasted);

            if(!lasted.isAlive){
                res.add("Error : " + i);
                continue;
            }
            lasted.changeLocation(temp[4],temp[5],temp[6]);
            res.add(lasted.name + " " + i + " " + lasted.x + " " + lasted.y + " " + lasted.z);
        }


        return res;
    }

    public static void checkUAV(String[] temp,UAV uav){
        if(!uav.isAlive)return;
        if(temp.length != 7){
            uav.setAlive(false);
            return;
        }
        if(Integer.parseInt(temp[1]) != uav.x || Integer.parseInt(temp[2]) != uav.y || Integer.parseInt(temp[3]) != uav.z)uav.setAlive(false);
    }
}
