package com.agr.workoutscheduledtelegrambot.db.entity;

import com.agr.workoutscheduledtelegrambot.db.meta.WSMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glassfish.grizzly.utils.ArraySet;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = WSMeta.user.name)
@Table(schema = WSMeta.schema, name = WSMeta.user.name)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = WSMeta.user.fld.id)
    private UUID id;

    @Column(name = WSMeta.user.fld.chat_id)
    private Long chatId;

    @Column(name = WSMeta.user.fld.name)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = WSMeta.user_training.name, schema = WSMeta.schema,
            joinColumns = {@JoinColumn(name = WSMeta.user_training.fld.user_id, referencedColumnName = WSMeta.user.fld.id)},
            inverseJoinColumns = {@JoinColumn(name = WSMeta.user_training.fld.training_id, referencedColumnName = WSMeta.training.fld.id)})
    private Set<Training> trainings;

    public void addTraining(Training training) {
        if (getTrainings() == null) {
            setTrainings(new HashSet<>());
        }
        getTrainings().add(training);
    }
}
