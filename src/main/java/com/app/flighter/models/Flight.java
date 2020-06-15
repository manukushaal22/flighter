package com.app.flighter.models;

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
@Table(name = "flights")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "fk_flight_to_source_airport")
    private Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "fk_flight_to_destination_airport")
    private Airport destinationAirport;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date departingTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivingTime;

    @OneToMany(mappedBy = "traveller")
    private List<TravellerFlight> travellerFlights = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}