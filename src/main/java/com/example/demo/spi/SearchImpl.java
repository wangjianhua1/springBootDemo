package com.example.demo.spi;

public class SearchImpl implements Search {
    @Override
    public String search(String keyword) {
        return "查找成功";
    }
}
