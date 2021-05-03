package com.agr.workoutscheduledtelegrambot.db.entity;

import com.agr.workoutscheduledtelegrambot.db.meta.WSMeta;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = WSMeta.exercise.name)
@Table(schema = WSMeta.schema, name = WSMeta.exercise.name)
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = WSMeta.exercise.fld.id)
    private UUID id;

    @Column(name = WSMeta.exercise.fld.name)
    private String name;

    @ManyToOne
    @JoinColumn(name = WSMeta.exercise.fld.training_id, nullable = false)
    private Training training;
}
