package com.agr.workoutscheduledtelegrambot.db.entity;

import com.agr.workoutscheduledtelegrambot.db.meta.WSMeta;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = WSMeta.type_training.name)
@Table(schema = WSMeta.schema, name = WSMeta.type_training.name)
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = WSMeta.type_training.fld.id)
    private UUID id;

    @Column(name = WSMeta.type_training.fld.name)
    private String name;
}
