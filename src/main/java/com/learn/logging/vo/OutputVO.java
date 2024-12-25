package com.learn.logging.vo;

import org.slf4j.MDC;

public class OutputVO {

    private String transactionId;

    private Object output;

    public OutputVO(){
        this.transactionId = MDC.get("transactionId");
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }
}
