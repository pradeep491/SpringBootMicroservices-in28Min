package com.test.controller;

import com.test.model.Name;
import com.test.model.PersonV1;
import com.test.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping(value = "/person/accept",produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionAcceptRequestHeader(){
        return new PersonV1("Kandyala Pradeep Kumar");
    }
    @GetMapping(value = "/person/accept",produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionAcceptRequestHeader(){
        return new PersonV2(new Name("KANDYALA","PRADEEP KUMAR"));
    }
}
