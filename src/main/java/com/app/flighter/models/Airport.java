package com.app.flighter.models;

import com.app.flighter.enums.AirportNameEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "airports")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private AirportNameEnum airportName;

    @NotBlank
    private String city;

    @OneToMany(mappedBy = "sourceAirport")
    private List<Flight> departingFlights = new ArrayList<>();

    @OneToMany(mappedBy = "destinationAirport")
    private List<Flight> arrivingFlights = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}