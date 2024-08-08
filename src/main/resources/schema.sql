-- Create courses table
CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL
);

-- Create students table
CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(255) NOT NULL,
    student_class VARCHAR(255) NOT NULL,
    student_roll VARCHAR(255) NOT NULL,
    course_id BIGINT,
    subject_id BIGINT,
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

-- Create subjects table
CREATE TABLE subjects (
    subject_id SERIAL PRIMARY KEY,
    subject_name VARCHAR(255) NOT NULL
);

-- Create a join table for many-to-many relationship between courses and subjects
CREATE TABLE course_subjects (
    course_id BIGINT,
    subject_id BIGINT,
    PRIMARY KEY (course_id, subject_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

