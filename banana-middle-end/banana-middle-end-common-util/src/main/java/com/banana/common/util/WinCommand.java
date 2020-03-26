package com.banana.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class WinCommand {

    public static void main(String[] args) {
        String mCurrentPath = System.getProperty("user.dir");
        String cmdCode = "cmd /c cd C:\\zhengji\\zj-container-central_m\\kitedge-middle-end\\config\\target" +
                " && " + "java -jar middle-end-config.jar --spring.profiles.active=local";
        System.out.println("打印Code: >>> "+cmdCode);
        exeCmd(cmdCode);
    }


    private static void exeCmd(String cmdCode) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(cmdCode);
            br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
                log.info(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}