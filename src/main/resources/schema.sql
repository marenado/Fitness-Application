ALTER TABLE app_user DROP CONSTRAINT IF EXISTS fk_membership;
ALTER TABLE app_user DROP CONSTRAINT IF EXISTS fk_nutrition_plan;
ALTER TABLE workout_plan DROP CONSTRAINT IF EXISTS fk_trainer;
ALTER TABLE enrollment DROP CONSTRAINT IF EXISTS fk_client;
ALTER TABLE enrollment DROP CONSTRAINT IF EXISTS fk_plan;
ALTER TABLE nutrition_plan DROP CONSTRAINT IF EXISTS fk_created_by;
ALTER TABLE meal DROP CONSTRAINT IF EXISTS fk_nutrition_plan_meal;
ALTER TABLE goal DROP CONSTRAINT IF EXISTS fk_goal_user;
ALTER TABLE feedback DROP CONSTRAINT IF EXISTS fk_feedback_trainer;
ALTER TABLE feedback DROP CONSTRAINT IF EXISTS fk_feedback_progress;
ALTER TABLE progress DROP CONSTRAINT IF EXISTS fk_progress_user;
ALTER TABLE progress DROP CONSTRAINT IF EXISTS fk_progress_enrollment;
ALTER TABLE client_achievement DROP CONSTRAINT IF EXISTS fk_client_achievement_client;
ALTER TABLE client_achievement DROP CONSTRAINT IF EXISTS fk_client_achievement_achievement;
ALTER TABLE exercise DROP CONSTRAINT IF EXISTS fk_exercise_workout_plan;

DROP TABLE IF EXISTS exercise;
DROP TABLE IF EXISTS client_achievement;
DROP TABLE IF EXISTS achievement;
DROP TABLE IF EXISTS progress;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS goal;
DROP TABLE IF EXISTS meal;
DROP TABLE IF EXISTS nutrition_plan;
DROP TABLE IF EXISTS enrollment;
DROP TABLE IF EXISTS workout_plan;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS membership;




-- Create the membership table first
CREATE TABLE IF NOT EXISTS membership (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          start_date DATE,
                                          duration INT,
                                          monthly_fee DOUBLE,
                                          membership_type VARCHAR(50) NOT NULL,
    discount_rate DOUBLE,
    access_to_premium_content BOOLEAN
    );

-- Create the app_user table
CREATE TABLE IF NOT EXISTS app_user (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    weight DOUBLE NOT NULL,
    height INT NOT NULL,
    gender VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    role VARCHAR(50) NOT NULL,
    membership_id BIGINT,
    nutrition_plan_id BIGINT
    );

-- Create the workout_plan table
CREATE TABLE IF NOT EXISTS workout_plan (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            title VARCHAR(255) NOT NULL,
    description TEXT,
    difficulty_level INT CHECK (difficulty_level BETWEEN 1 AND 5),
    created_by BIGINT,
    requirements TEXT,
    duration INT,
    max_capacity INT,
    trainer_id BIGINT
    );

-- Create the enrollment table
CREATE TABLE IF NOT EXISTS enrollment (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          client_id BIGINT,
                                          plan_id BIGINT,
                                          start_date DATE
);

-- Create the nutrition_plan table
CREATE TABLE IF NOT EXISTS nutrition_plan (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    calories_per_day DOUBLE NOT NULL,
    meal_frequency INT NOT NULL,
    type VARCHAR(20) NOT NULL,
    created_by BIGINT NOT NULL
    );

-- Create the meal table
CREATE TABLE IF NOT EXISTS meal (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    calories DOUBLE NOT NULL,
    nutrition_plan_id BIGINT NOT NULL
    );

-- Create the goal table
CREATE TABLE IF NOT EXISTS goal (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    title VARCHAR(255) NOT NULL,
    description TEXT,
    user_id BIGINT
    );

-- Create the feedback table
CREATE TABLE IF NOT EXISTS feedback (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        comment TEXT,
                                        rating INT,
                                        date DATE,
                                        trainer_id BIGINT NOT NULL,
                                        progress_id BIGINT NOT NULL
);

-- Create the progress table
CREATE TABLE IF NOT EXISTS progress (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        date DATE,
                                        completed_workouts TEXT,
                                        details TEXT,
                                        user_id BIGINT NOT NULL,
                                        enrollment_id BIGINT NOT NULL
);

-- Create the achievement table
CREATE TABLE IF NOT EXISTS achievement (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           title VARCHAR(255) NOT NULL,
    description TEXT,
    date DATE
    );

-- Create the client_achievement table
CREATE TABLE IF NOT EXISTS client_achievement (
                                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                  client_id BIGINT,
                                                  achievement_id BIGINT,
                                                  date DATE
);

-- Create the exercise table
CREATE TABLE IF NOT EXISTS exercise (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    description TEXT,
    duration INT,
    workout_plan_id BIGINT,
    created_by INT,
    DISTANCE INT,
    DURATION_PER_SET INT,
    REPS INT,
    SETS INT,
    WEIGHT_LIFTED INT
    );

-- Adding constraints after table creation
ALTER TABLE app_user
    ADD CONSTRAINT fk_membership FOREIGN KEY (membership_id) REFERENCES membership(id);

ALTER TABLE app_user
    ADD CONSTRAINT fk_nutrition_plan FOREIGN KEY (nutrition_plan_id) REFERENCES nutrition_plan(id);

ALTER TABLE workout_plan
    ADD CONSTRAINT fk_trainer FOREIGN KEY (trainer_id) REFERENCES app_user(id);

ALTER TABLE enrollment
    ADD CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES app_user(id);

ALTER TABLE enrollment
    ADD CONSTRAINT fk_plan FOREIGN KEY (plan_id) REFERENCES workout_plan(id);

ALTER TABLE nutrition_plan
    ADD CONSTRAINT fk_created_by FOREIGN KEY (created_by) REFERENCES app_user(id);

ALTER TABLE meal
    ADD CONSTRAINT fk_nutrition_plan_meal FOREIGN KEY (nutrition_plan_id) REFERENCES nutrition_plan(id);

ALTER TABLE goal
    ADD CONSTRAINT fk_goal_user FOREIGN KEY (user_id) REFERENCES app_user(id);

ALTER TABLE feedback
    ADD CONSTRAINT fk_feedback_trainer FOREIGN KEY (trainer_id) REFERENCES app_user(id);

ALTER TABLE feedback
    ADD CONSTRAINT fk_feedback_progress FOREIGN KEY (progress_id) REFERENCES progress(id);

ALTER TABLE progress
    ADD CONSTRAINT fk_progress_user FOREIGN KEY (user_id) REFERENCES app_user(id);

ALTER TABLE progress
    ADD CONSTRAINT fk_progress_enrollment FOREIGN KEY (enrollment_id) REFERENCES enrollment(id);

ALTER TABLE client_achievement
    ADD CONSTRAINT fk_client_achievement_client FOREIGN KEY (client_id) REFERENCES app_user(id);

ALTER TABLE client_achievement
    ADD CONSTRAINT fk_client_achievement_achievement FOREIGN KEY (achievement_id) REFERENCES achievement(id);

ALTER TABLE exercise
    ADD CONSTRAINT fk_exercise_workout_plan FOREIGN KEY (workout_plan_id) REFERENCES workout_plan(id);

ALTER TABLE exercise
    ADD CONSTRAINT fk_created_by_user
        FOREIGN KEY (created_by)
            REFERENCES app_user(id);
