package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@JsonIgnoreProperties({"field1","field2"})
public class SomeBean {
    private String field1;
    @JsonIgnore
    private String field2;
    private String field3;
}
