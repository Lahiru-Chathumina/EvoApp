package edu.icet.entity.supplier;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "outdoor_area")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutdoorAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "size",nullable = false)
    private double size;

    @Column(name = "seating_capacity",nullable = false)
    private Boolean seatingCapacity;

    @Column(name = "lighting",nullable = false)
    private Boolean lighting;

    @Column(name = "weather_protection",nullable = false)
    private Boolean weatherProtection;

}
