package com.zhaipz.study.datastructure;

import com.zhaipz.study.datastructure.service.IO.FileUtils;
import com.zhaipz.study.datastructure.service.throughtwork.UAVposition;
import common.utils.BusiException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class DatastructureApplicationTests {

    @Autowired
    FileUtils fileUtils;

    @Autowired
    UAVposition uaVposition;

    @BeforeEach
    public void init() {
        log.info("开始测试-----------------");
    }

    @AfterEach
    public void after() {
        log.info("测试结束-----------------");
    }

    @Test
    @DisplayName("测试文件")
    void contextLoads() {
        fileUtils.doUncompressFile("zhaipzTest.gz");
    }

    @ParameterizedTest
//    @ValueSource(strings = {"src/main/resources/plane1.txt", "src/main/resources/plane2.txt"})
    @ValueSource(strings = {"src/main/resources/plane1.txt"})
    @DisplayName("HomeWork异常测试")
    void getUAVRecordsTest(String string) throws BusiException {

        String res = uaVposition.getUAVRecord(string,1);
        Assertions.assertEquals("plane1 1 1 2 3",res);
        log.info(res);

        res = uaVposition.getUAVRecord(string,2);
        Assertions.assertEquals("plane1 2 2 3 4",res);
        log.info(res);

        res = uaVposition.getUAVRecord(string,3);
        Assertions.assertEquals("plane1 3 3 4 5",res);
        log.info(res);

        res = uaVposition.getUAVRecord(string,4);
        Assertions.assertEquals("Error : 4",res);
        log.info(res);

        res = uaVposition.getUAVRecord(string,5);
        Assertions.assertEquals("Error : 5",res);
        log.info(res);

        res = uaVposition.getUAVRecord(string,100);
        Assertions.assertEquals("Can Not Find : 100",res);
        log.info(res);
    }



}
