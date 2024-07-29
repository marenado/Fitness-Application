-- Inserting data into membership
INSERT INTO membership (start_date, duration, monthly_fee, membership_type, discount_rate, access_to_premium_content)
VALUES ('2023-01-01', 12, 100.0, 'Basic', 0.1, NULL), ('2023-01-01', 12, 200.0, 'Premium', NULL, true);

-- Inserting data into app_user
INSERT INTO app_user (name, email, password, weight, height, gender, date_of_birth, role, membership_id, nutrition_plan_id)
VALUES
    ('Alex Chaly', 'trainer1@example.com', '$2a$10$D4G5f18oW9hs1dGS4JHTT.flltJf8lTb.Nl5JvHsV7ZbIf4m5x3G6', 70.0, 175, 'Male', '1990-01-01', 'TRAINER', 1, NULL),
    ('Jane Birkin', 'client1@example.com', '$2a$10$wH5d19oQ7hR2s5FG4JJJfQ.fUIcJ8UTd.Nl2JrHsR7Yklg5r5x3G2', 60.0, 165, 'Female', '1992-02-02', 'CLIENT', 2, NULL),
    ('Ali Something', 'client2@example.com', '$2a$10$dG5f1L9oW2hQ9dGS2JHTT.gUIcJ8MTd.Nl5JrHcV7Zklg4m5x3G7', 65.0, 170, 'Female', '1994-03-03', 'CLIENT', 2, NULL);

-- Inserting data into nutrition_plan
INSERT INTO nutrition_plan (title, description, calories_per_day, meal_frequency, type, created_by)
VALUES ('Vegan Plan', 'A vegan meal plan', 2000.0, 3, 'vegan', 1), ('Low Carb Plan', 'A low carb meal plan', 1800.0, 4, 'low-carb', 1);

-- Updating app_user to reference nutrition_plan after inserting nutrition_plan
UPDATE app_user SET nutrition_plan_id = 1 WHERE email = 'client1@example.com';
UPDATE app_user SET nutrition_plan_id = 2 WHERE email = 'client2@example.com';

-- Inserting data into workout_plan
INSERT INTO workout_plan (title, description, difficulty_level, created_by, requirements, duration, max_capacity, trainer_id)
VALUES ('Plan A', 'Description A', 3, 1, 'Requirements A', 30, 10, 1), ('Plan B', 'Description B', 2, 1, 'Requirements B', 45, 15, 1);

-- Inserting data into enrollment
INSERT INTO enrollment (client_id, plan_id, start_date)
VALUES (2, 1, '2023-06-01'), (3, 2, '2023-07-01');

-- Inserting data into progress
INSERT INTO progress (date, completed_workouts, details, user_id, enrollment_id)
VALUES ('2023-06-15', 'Workout A, Workout B', 'Feeling great', 2, 1), ('2023-06-20', 'Workout C', 'Tired but accomplished', 2, 1), ('2023-07-05', 'Workout D', 'Struggled with form', 3, 2);

-- Inserting data into feedback
INSERT INTO feedback (comment, rating, date, trainer_id, progress_id)
VALUES ('Great progress!', 5, '2023-06-16', 1, 1) ;

-- Inserting data into goal
INSERT INTO goal (title, description, user_id)
VALUES ('Lose Weight', 'Lose 10 pounds in 3 months', 2), ('Build Muscle', 'Gain 5 pounds of muscle in 6 months', 3);

-- Inserting data into achievement
INSERT INTO achievement (title, description, date)
VALUES ('First 5K Run', 'Completed a 5K run', '2023-05-01');

-- Inserting data into client_achievement
INSERT INTO client_achievement (client_id, achievement_id, date)
VALUES (2, 1, '2023-05-01');

-- Inserting data into exercise
-- INSERT INTO exercise (name, description, duration, workout_plan_id)
-- VALUES ('Push-ups', 'Push-ups to build upper body strength', 10, 1), ('Squats', 'Squats to build lower body strength', 15, 1), ('Plank', 'Plank exercise to strengthen core muscles', 5, 2);
INSERT INTO exercise (name, description, created_by, exercise_type, distance, workout_plan_id)
VALUES ('Running', 'Running to improve cardiovascular endurance', 1, 'CARDIO', 5.0, 1),
       ('Bench Press', 'Bench press to build chest muscles', 1, 'STRENGTH', NULL, 1);

