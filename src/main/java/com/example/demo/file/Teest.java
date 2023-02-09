package com.example.demo.file;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Teest {

    public static void main(String[] args) {
        StringBuilder sql = new StringBuilder(
                "\nUPDATE t_user_account SET referrer_type = NULL ,referrer_account_id=NULL ,referrer_time=NULL WHERE account_id in (");
        try (CSVReader reader = new CSVReader(new FileReader(Paths.get("/Users/wanghan/Desktop/aaa.csv").toFile()))) {
            for (String[] next : reader) {
                if ("用户ID".equals(next[1])) {
                    continue;
                }
                sql.append(next[1]).append(",");
            }
            sql.replace(sql.length() - 1, sql.length(), "");
            sql.append(");\n");
            Path path = Paths.get("/Users/wanghan/Desktop/bbb.sql");
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            // 写文件末尾追加
            Files.write(path, sql.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
