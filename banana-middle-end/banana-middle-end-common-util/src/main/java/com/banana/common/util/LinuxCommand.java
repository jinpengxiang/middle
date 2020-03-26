package com.banana.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LinuxCommand {

    public static void main(String[] args) {
//        String cmd = "sort -t,  -k2n,2  -k1,1   aa";
//        LinuxCommand.exec(cmd);

        List<String> commands = new ArrayList<String>();
        commands.add("cd /home/whj/tmp/hello");
        commands.add("cd /home/whj/tmp/hello");
        LinuxCommand.execFlow(commands);
    }


    public static Object execFlow(List<String> commands) {
        try {
            String cmds = "";
            for (String cmd : commands) {
                cmds += cmd + ";";
            }
            String[] cmdA = {"/bin/sh", "-c", cmds};
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                log.info(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object exec(String cmd) {
        try {
            String[] cmdA = {"/bin/sh", "-c", cmd};
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                log.info(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}