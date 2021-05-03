CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA main;

CREATE TABLE main.training
(
    id uuid NOT NULL DEFAULT main.uuid_generate_v4(),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_training_id PRIMARY KEY (id)
);

COMMENT ON TABLE main.training
    IS 'Тренировка';

COMMENT ON COLUMN main.training.id
    IS 'Идентификатор';

COMMENT ON COLUMN main.training.name
    IS 'Наименование тренировки';

CREATE TABLE main.exercise
(
    id uuid NOT NULL DEFAULT main.uuid_generate_v4(),
    name text COLLATE pg_catalog."default" NOT NULL,
    training_id uuid NOT NULL,
    CONSTRAINT pk_exercise_id PRIMARY KEY (id),
    CONSTRAINT fk_training_exercise FOREIGN KEY (training_id)
        REFERENCES main.training (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE CASCADE
);

COMMENT ON TABLE main.exercise
    IS 'Упражнение';

COMMENT ON COLUMN main.exercise.id
    IS 'Идентификатор';

COMMENT ON COLUMN main.exercise.name
    IS 'Наименование упражнения';

COMMENT ON COLUMN main.exercise.training_id
    IS 'Ссылка на тренировку';

CREATE TABLE main."user"
(
    id uuid NOT NULL DEFAULT main.uuid_generate_v4(),
    chat_id bigint NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

COMMENT ON TABLE main."user"
    IS 'Пользователь';

COMMENT ON COLUMN main."user".id
    IS 'Идентификатор';

COMMENT ON COLUMN main."user".chat_id
    IS 'Идентификатор чата';

COMMENT ON COLUMN main."user".name
    IS 'Имя пользователя';

CREATE TABLE main.type_training
(
    id uuid NOT NULL DEFAULT main.uuid_generate_v4(),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_type_training_id PRIMARY KEY (id)
);

COMMENT ON TABLE main.type_training
    IS 'Тип тренировки';

COMMENT ON COLUMN main.type_training.id
    IS 'Идентификатор';

COMMENT ON COLUMN main.type_training.name
    IS 'Наименование типа тренировки';

CREATE TABLE main.training_type_training
(
    training_id uuid NOT NULL,
    type_training_id uuid NOT NULL,
    CONSTRAINT unique_training_type_training UNIQUE (training_id, type_training_id),
    CONSTRAINT fk_training FOREIGN KEY (training_id)
        REFERENCES main.training (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE CASCADE,
    CONSTRAINT fk_type_training FOREIGN KEY (type_training_id)
        REFERENCES main.type_training (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE CASCADE
);

COMMENT ON TABLE main.training_type_training
    IS 'Связь тренировок с типами тренировок';

COMMENT ON COLUMN main.training_type_training.training_id
    IS 'Ссылка на тренировку';

COMMENT ON COLUMN main.training_type_training.type_training_id
    IS 'Ссылка на тип тренировки';

CREATE TABLE main.user_training
(
    user_id uuid NOT NULL,
    training_id uuid NOT NULL,
    CONSTRAINT unique_training_user UNIQUE (user_id, training_id),
    CONSTRAINT fk_training FOREIGN KEY (training_id)
        REFERENCES main.training (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES main."user" (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE CASCADE
);

COMMENT ON TABLE main.user_training
    IS 'Тренировки пользователя';

COMMENT ON COLUMN main.user_training.user_id
    IS 'Ссылка на пользователя';

COMMENT ON COLUMN main.user_training.training_id
    IS 'Ссылка на тренировку';

CREATE TABLE main.performing_exercise
(
    id uuid NOT NULL DEFAULT main.uuid_generate_v4(),
    exercise_id uuid NOT NULL,
    quantity bigint,
    weight double precision,
    user_id uuid,
    CONSTRAINT pk_permorming_exercise PRIMARY KEY (id),
    CONSTRAINT fk_exercise FOREIGN KEY (exercise_id)
        REFERENCES main.exercise (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES main."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON TABLE main.performing_exercise
    IS 'Выполнение упражнения';

COMMENT ON COLUMN main.performing_exercise.id
    IS 'Идентификатор';

COMMENT ON COLUMN main.performing_exercise.exercise_id
    IS 'Ссылка на упражнение';

COMMENT ON COLUMN main.performing_exercise.quantity
    IS 'Количество повторение';

COMMENT ON COLUMN main.performing_exercise.weight
    IS 'Вес';

COMMENT ON COLUMN main.performing_exercise.user_id
    IS 'Ссылка на пользователя';