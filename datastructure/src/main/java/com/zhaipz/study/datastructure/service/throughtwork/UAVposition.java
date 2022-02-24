package com.zhaipz.study.datastructure.service.throughtwork;

import common.utils.BusiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UAVposition {

    public String getUAVRecord(String file,int index) throws BusiException {
        List<String> res = getAllUAVRecords(file);
        if(index > res.size())return "Can Not Find : " + index;
        return res.get(index-1);
    }

    public static List<String> getAllUAVRecords(String fileName) throws BusiException {
        List<String> res = new ArrayList<>();
        //BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Stream<String> stringStream = null;

        try {
            stringStream = Files.lines(Paths.get(fileName));
        }catch (Exception e){
            throw new BusiException("service","0000","处理文件失败：" + e.toString());
        }

        List<String> records;
        records = stringStream.collect(Collectors.toList());

        UAV lasted = new UAV();
        int index;
        for(int i = 0;i < records.size();i++){
            String[] temp = records.get(i).split(" ");
            index = i+1;
            if(i == 0){
                lasted = new UAV(temp[1],temp[2],temp[3]);
                lasted.setName(temp[0]);
                lasted.setAlive(true);
                res.add(lasted.name + " " + index + " " + lasted.x + " " + lasted.y + " " + lasted.z);
                continue;
            }

            checkUAV(temp,lasted);

            if(!lasted.isAlive){
                res.add("Error : " + index);
                continue;
            }
            lasted.changeLocation(temp[4],temp[5],temp[6]);
            res.add(lasted.name + " " + index + " " + lasted.x + " " + lasted.y + " " + lasted.z);
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
