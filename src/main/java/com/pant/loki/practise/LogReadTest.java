package com.pant.loki.practise;

import com.alibaba.fastjson.JSON;
import com.pant.loki.entity.NginxInfo;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogReadTest {

    public static void main(String[] args) {
        File logFile = new File("C:\\Users\\44218\\Desktop\\LOGS\\info.log");
        List<NginxInfo> infoList = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(logFile));
            String lineStr;
            while ((lineStr = br.readLine()) != null) {
                if (StringUtils.isEmpty(lineStr)) {
                    continue;
                }
                NginxInfo info = JSON.parseObject(lineStr, NginxInfo.class);
                infoList.add(info);
            }
            System.out.println(JSON.toJSONString(infoList));
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
