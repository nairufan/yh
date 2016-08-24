package com.jl.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by fannairu on 2016/7/10.
 */
@Component
public class CheckCodeGenerator {
    private char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private Random random = new Random();

    public String getCodes(int length) {
        StringBuilder codes = new StringBuilder();
        int sequenceLen = codeSequence.length;
        for (int i = 0; i < length; i++) {
            int rand = random.nextInt(sequenceLen);
            codes.append(codeSequence[rand]);
        }
        return codes.toString();
    }
}
