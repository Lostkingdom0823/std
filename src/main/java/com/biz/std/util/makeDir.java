package com.biz.std.util;

import java.io.File;

public class makeDir {
    private static String path = "D:\\JAVA\\std\\src\\main\\webapp\\images\\";

    public static void main(String[] args) {
        //循环生成文件夹
        for (int i = 1; i <101; i ++){
            String fileName =  String.valueOf(i) ;
            File file = new File(path + fileName);
            file.mkdir();
        }
        System.out.println("Ok, sucessful!");
    }
}
