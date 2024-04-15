package com.test.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.HelloWorld;

import java.util.Locale;

@RestController
public class RestControllerEx {

    private MessageSource messageSource;
    public RestControllerEx(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    @GetMapping(value = "/hello-world")
    public String hello() {
        return "Hello World...!";
    }

    @GetMapping(value = "/hello-world-bean")
    public HelloWorld helloWorldBean() {
        return new HelloWorld("Hello World Bean...!");
    }

    @GetMapping(value = "/hello-world-path/path-variable/{name}")
    public HelloWorld helloPath(@PathVariable String name) {
        return new HelloWorld(String.format("Hello World %s", name));
    }

    /*@GetMapping(value = "/hello-world-i18n")
    public String helloWorldI18N() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }*/
    @GetMapping(value = "/hello-world-i18n")
    public String helloWorldI18N() {
        Locale locale = LocaleContextHolder.getLocale();
        return  messageSource.getMessage("good.morning.message", null, locale);
    }
}
