package com.test.controller;

import com.test.model.Name;
import com.test.model.PersonV1;
import com.test.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping(value = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfRequestHeader(){
        return new PersonV1("Kandyala Pradeep Kumar");
    }
    @GetMapping(value = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfRequestHeader(){
        return new PersonV2(new Name("KANDYALA","PRADEEP KUMAR"));
    }
}
