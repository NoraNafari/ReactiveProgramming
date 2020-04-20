package com.noran.demo.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RandomStringGenerator {

    public List<String> generate(){
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            stringList.add(RandomStringUtils.randomAlphabetic(10));
        }
        return stringList;
    }
}
