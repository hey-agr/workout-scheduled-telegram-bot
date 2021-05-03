package com.agr.workoutscheduledtelegrambot.db.entity;

import com.agr.workoutscheduledtelegrambot.db.meta.WSMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = WSMeta.training.name)
@Table(schema = WSMeta.schema, name = WSMeta.training.name)
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = WSMeta.training.fld.id)
    private UUID id;

    @Column(name = WSMeta.training.fld.name)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = WSMeta.training_type_training.name, schema = WSMeta.schema,
            joinColumns = {@JoinColumn(name = WSMeta.training_type_training.fld.training_id, referencedColumnName = WSMeta.training.fld.id)},
            inverseJoinColumns = {@JoinColumn(name = WSMeta.training_type_training.fld.type_training_id, referencedColumnName = WSMeta.type_training.fld.id)})
    private Set<TrainingType> types;
}
