-- Insert ToDo data
INSERT INTO todos (id, title, description, finished, created_date, due_date, finished_date)
VALUES
    (1, 'Complete Assignment 1', 'Complete the assignment for the Java course.', false, '2024-11-01', '2024-11-05', '2024-11-05'),
    (2, 'CrossFit Training', 'Attend the CrossFit training session at the gym.', true, '2024-10-20', '2024-10-20', '2024-10-20'),
    (3, 'Buy Groceries', 'Buy milk, bread, and eggs from the supermarket.', false, '2024-11-02', '2024-11-02', '2024-11-02'),
    (4, 'Submit Report', 'Submit the quarterly report to the manager.', true, '2024-10-15', '2024-10-17', '2024-10-17'),
    (5, 'Plan Weekend Trip', 'Plan the itinerary for the weekend trip.', false, '2024-11-03', '2024-11-10', '2024-11-10');

-- Insert Assignee data
INSERT INTO assignees (id, prename, name, email, to_do_id)
VALUES
    (1, 'Ana Cristina', 'Franco da Silva', 'ana-cristina.franco-da-silva@uni-stuttgart.de', 1),
    (2, 'Maximilian', 'Müller', 'maximilian.mueller@uni-stuttgart.de', 2),
    (3, 'John', 'Doe', 'john.doe@uni-stuttgart.de', 3);
