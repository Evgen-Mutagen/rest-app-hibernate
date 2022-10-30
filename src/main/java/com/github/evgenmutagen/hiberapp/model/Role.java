package com.github.evgenmutagen.hiberapp.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_roles")
public class Role extends AbstractBaseEntity {
    @Column(name = "role")
    private String roleName;
}