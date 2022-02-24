package com.zhaipz.study.datastructure.service.IO;

import common.utils.BusiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @author zhaipz
 * @ClassName: FileUtils
 * @Description: 常用I/O流
 * @date 2022/2/23 10:57
 */
@Component
@Slf4j
public class FileUtils {


    public static String getFileName(String f) {
        String fname = "";
        int i = f.lastIndexOf('.');

        if (i > 0 && i < f.length() - 1) {
            fname = f.substring(0, i);
        }
        return fname;
    }

    public void doUncompressFile(String inFileName) {

        try {
            log.info("Opening the compressed file.");
            GZIPInputStream in;
            try {
                in = new GZIPInputStream(new FileInputStream(inFileName));
            } catch (FileNotFoundException e) {
                throw new BusiException("common","0001",e.toString());
            }

            log.info("Open the output file.");
            String outFileName = getFileName(inFileName);
            FileOutputStream out;
            try {
                out = new FileOutputStream(outFileName);
            } catch (FileNotFoundException e) {
                throw new BusiException("common","0002",e.toString());
            }

            log.info("Transfering bytes from compressed file to the output file.");
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            log.info("Closing the file and stream");
            in.close();
            out.close();

        } catch (IOException e) {
            throw new BusiException("common","0003",e.toString());
        }

    }
}
