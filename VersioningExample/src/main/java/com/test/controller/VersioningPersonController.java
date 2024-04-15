package com.test.controller;

import com.test.model.Name;
import com.test.model.PersonV1;
import com.test.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Kandyala Pradeep Kumar");
    }
    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){

        return new PersonV2(new Name("KANDYALA","PRADEEP KUMAR"));
    }
}
