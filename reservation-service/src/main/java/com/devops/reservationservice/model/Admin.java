package com.devops.reservationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admins")
public class Admin {

    @Id
    private Long id;
    @Column(name = "type", length = 16, columnDefinition = "VARCHAR(16)")
    private String name;
}
