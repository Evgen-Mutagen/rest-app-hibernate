package com.github.evgenmutagen.hiberapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "calories_per_day")
    private Integer caloriesPerDay;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<Meal> meals;
}

