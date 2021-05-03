package com.agr.workoutscheduledtelegrambot.db.entity;

import com.agr.workoutscheduledtelegrambot.db.meta.WSMeta;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = WSMeta.performing_exercise.name)
@Table(schema = WSMeta.schema, name = WSMeta.performing_exercise.name)
public class PerformingExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = WSMeta.performing_exercise.fld.id)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = WSMeta.performing_exercise.fld.user_id, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = WSMeta.performing_exercise.fld.exercise_id, nullable = false)
    private Exercise exercise;

    @Column(name = WSMeta.performing_exercise.fld.quantity)
    private Integer quantity;

    @Column(name = WSMeta.performing_exercise.fld.weight)
    private Double weight;
}
