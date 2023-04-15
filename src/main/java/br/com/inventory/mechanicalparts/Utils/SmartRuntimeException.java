//package br.com.inventory.mechanicalparts.Util;
//
//package com.smartbr.clinica.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.List;
//
//@ResponseStatus(HttpStatus.BAD_REQUEST)
//public class SmartRuntimeException extends RuntimeException {
//
//    private List<String> details;
//
//    public SmartRuntimeException() {
//    }
//
//    public SmartRuntimeException(String message) {
//        super(message);
//    }
//
//    @Deprecated
//    public SmartRuntimeException(String message, Object... args) {
//        this(MessageExceptionBundle.getMensagem(message, args));
//    }
//
//    public SmartRuntimeException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public SmartRuntimeException(ISmartException messageTemplate, Throwable cause) {
//        this(MessageExceptionBundle.getMensagem(messageTemplate), cause);
//    }
//
//    public SmartRuntimeException(ISmartException messageTemplate) {
//        this(MessageExceptionBundle.getMensagem(messageTemplate));
//    }
//
//    public SmartRuntimeException(ISmartException messageTemplate, List<String> details) {
//        this(MessageExceptionBundle.getMensagem(messageTemplate));
//        this.details = details;
//    }
//
//    public SmartRuntimeException(ISmartException messageTemplate, Object... args) {
//        this(MessageExceptionBundle.getMensagem(messageTemplate, args));
//    }
//
//    public SmartRuntimeException(Throwable cause) {
//        super(cause.getMessage(), cause);
//    }
//
//    public SmartRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }
//
//    public List<String> getDetails() {
//        return details;
//    }
//
//    @FunctionalInterface
//    public interface Check<T> {
//        T checked();
//    }
//
//    public static <T> T checked(Check<T> next) {
//        try {
//            return next.checked();
//        } catch (Exception e) {
//            throw new SmartRuntimeException(e);
//        }
//    }
//
//}