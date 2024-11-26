-- Insert ToDo data
INSERT INTO todos (id, title, description, finished, created_date, due_date, finished_date)
VALUES
    (1, 'Complete Assignment 1', 'Complete the assignment for the Java course.', false, '2024-11-01', '2024-11-05', '2024-11-05'),
    (2, 'CrossFit Training', 'Attend the CrossFit training session at the gym.', true, '2024-10-20', '2024-10-20', '2024-10-20'),
    (3, 'Buy Groceries', 'Buy milk, bread, and eggs from the supermarket.', false, '2024-11-02', '2024-11-02', '2024-11-02'),
    (4, 'Submit Report', 'Submit the quarterly report to the manager.', true, '2024-10-15', '2024-10-17', '2024-10-17'),
    (5, 'Plan Weekend Trip', 'Plan the itinerary for the weekend trip.', false, '2024-11-03', '2024-11-10', '2024-11-10'),
    (6, 'Prepare Presentation', 'Prepare the presentation for the client meeting.', false, '2024-11-04', '2024-11-07', null),
    (7, 'Review Code', 'Review the code submitted by team members for bugs and improvements.', true, '2024-10-30', '2024-11-01', '2024-11-01'),
    (8, 'Attend Webinar', 'Join the webinar on Java Spring Boot development.', true, '2024-11-02', '2024-11-02', '2024-11-02'),
    (9, 'Clean the House', 'Complete the cleaning tasks for the house.', false, '2024-11-05', '2024-11-06', null),
    (10, 'Doctor Appointment', 'Go to the doctor for the yearly checkup.', true, '2024-10-25', '2024-10-25', '2024-10-25');

-- Insert Assignee data
INSERT INTO assignees (id, prename, name, email, to_do_id)
VALUES
    (1, 'Ana Cristina', 'Franco da Silva', 'ana-cristina.franco-da-silva@uni-stuttgart.de', 1),
    (2, 'Maximilian', 'Müller', 'maximilian.mueller@uni-stuttgart.de', 2),
    (3, 'John', 'Doe', 'john.doe@uni-stuttgart.de', 3),
    (4, 'Sarah', 'Williams', 'sarah.williams@uni-stuttgart.de', 4),
    (5, 'David', 'Smith', 'david.smith@uni-stuttgart.de', 5),
    (6, 'Olivia', 'Martinez', 'olivia.martinez@uni-stuttgart.de', 6),
    (7, 'James', 'Johnson', 'james.johnson@uni-stuttgart.de', 7),
    (8, 'Sophia', 'Lee', 'sophia.lee@uni-stuttgart.de', 8),
    (9, 'Emily', 'Brown', 'emily.brown@uni-stuttgart.de', 9),
    (10, 'Michael', 'Davis', 'michael.davis@uni-stuttgart.de', 10);
