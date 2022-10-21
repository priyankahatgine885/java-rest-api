package com.cognologix.springboot.entities;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(unique = true, name = "name")
    private String name;

    @NotBlank
    @Column(name = "salary")
    private float salary;

}
