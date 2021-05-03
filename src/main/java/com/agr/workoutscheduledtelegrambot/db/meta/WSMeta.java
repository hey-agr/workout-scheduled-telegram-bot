package com.agr.workoutscheduledtelegrambot.db.meta;

public final class WSMeta {
    public static final String schema = "main";

    public static final class training {
        public static final String name = "training";

        public static final class fld {
            public static final String id = "id";
            public static final String name = "name";
        }
    }

    public static final class type_training {
        public static final String name = "type_training";

        public static final class fld {
            public static final String id = "id";
            public static final String name = "name";
        }
    }

    public static final class training_type_training {
        public static final String name = "training_type_training";

        public static final class fld {
            public static final String type_training_id = "type_training_id";
            public static final String training_id = "training_id";
        }
    }

    public static final class user {
        public static final String name = "user";

        public static final class fld {
            public static final String id = "id";
            public static final String chat_id = "chat_id";
            public static final String name = "name";
        }
    }

    public static final class user_training {
        public static final String name = "user_training";

        public static final class fld {
            public static final String user_id = "user_id";
            public static final String training_id = "training_id";
        }
    }

    public static final class exercise {
        public static final String name = "exercise";

        public static final class fld {
            public static final String id = "id";
            public static final String training_id = "training_id";
            public static final String name = "name";
        }
    }

    public static final class performing_exercise {
        public static final String name = "performing_exercise";

        public static final class fld {
            public static final String id = "id";
            public static final String exercise_id = "exercise_id";
            public static final String quantity = "quantity";
            public static final String weight = "weight";
            public static final String user_id = "user_id";
        }
    }
}
