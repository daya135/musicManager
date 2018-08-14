package org.jzz.musicManger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
    private static Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println("This is println message.");

        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }


}