package com.org.config.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author :
 * @date : 2018/8/30
 * @description:
 */
public class FileUtil {

    /**
     * @author:
     * @date:2018/8/30
     * @description:从txt文件读取List<String>
     */
    public static Set<String> getFileContent() {

        //获取文件路径
        //获取项目根目录
        String path = null;
        try {
            File temPath = new File(ResourceUtils.getURL("").getPath());
            path = temPath.getAbsolutePath().replace("\\dept-8002", "")+"/SensitiveWord.txt";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Set<String> strSet = new HashSet<>();
        File file = new File(path);
        InputStreamReader read = null;
        BufferedReader reader = null;
        try {
            read = new InputStreamReader(new FileInputStream(file),"utf-8");
            reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                strSet.add(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return strSet;
    }

//    public static void main(String[] args) {
//        Set<String> fileContent =
//                FileUtil.getFileContent("F:\\综合实训\\mooc-sc\\SensitiveWord.txt");
//        for (String s : fileContent) {
//            System.out.println(s);
//        }
//    }

}