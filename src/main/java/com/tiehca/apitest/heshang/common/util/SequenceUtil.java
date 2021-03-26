package com.tiehca.apitest.heshang.common.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 序列号生成工具类
 * 从0到9999 循环生成
 * @author chen
 */
public class SequenceUtil {

    private static final AtomicInteger sequence = new AtomicInteger(0);

    private static final int MAX_VALUE = 10000;

    public static int getSequence () {
        int andIncrement = sequence.getAndIncrement();

        if (andIncrement == MAX_VALUE) {
            sequence.set(0);
        }

        return andIncrement;
    }

    public static String getSequenceString() {

        return String.format("%04d",getSequence());
    }
}
