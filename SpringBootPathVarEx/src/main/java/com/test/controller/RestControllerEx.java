package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.HelloWorld;

@RestController
public class RestControllerEx {

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
		return new HelloWorld(String.format("Hello World %s",name));
	}
}
