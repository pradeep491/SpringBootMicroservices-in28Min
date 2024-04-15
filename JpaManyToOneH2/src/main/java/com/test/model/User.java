package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "name should have at least 2 characters...!")
    @JsonProperty("user_name")
    private String name;
    @Past(message = "Birth Date should be in the past...!")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
    //private Date birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
}
