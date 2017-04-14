package com.boz.common.utils;

public class ExcelException extends Exception {

    public ExcelException() {
        super();
    }

    // 这个方法接收的参数message一般为异常发生时的提示信息。
    public ExcelException(String message) {
        // 调用父类Throwable里面接收一个字符串参数的构造方法，它会把这个message的值赋给Throwable里面的detailMessage变量，detailMessage这个变量一般就是Throwable类在处理异常时(因为大多数异常最后都是抛给了它，由它来统一处理，当然，利用try...catch...捕获除外。要么最后抛出到Throwable处理，要么捕获处理)给出的提示信息。
        super(message);
    }
}
