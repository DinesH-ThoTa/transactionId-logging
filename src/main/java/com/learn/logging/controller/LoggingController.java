package com.learn.logging.controller;

import com.learn.logging.vo.OutputVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/log")
    public OutputVO logMessage(){
        logger.debug("LoggingController :: logMessage :: Exit");
        OutputVO outputVO = new OutputVO();
        outputVO.setOutput("Logged");
        logger.info("Logged transaction");
        logger.debug("LoggingController :: logMessage :: Exit");
        return outputVO;
    }
}
