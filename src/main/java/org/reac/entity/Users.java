package org.reac.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@Table(name = "User")
public class Users {

    @Id
    private String id;
    private String name;
}
