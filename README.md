A. Specification of Basic and Extra Functionalities

1. General Description The application is a Web-based software solution developed in Java, utilizing the Spring Boot framework, designed to digitize processes within an educational institution. The main objective is the efficient management of fundamental university entities: students, professors, courses, sessions (didactic activities), enrollments, and attendance.

2. Basic Functionalities (CRUD) The application allows complete Create, Read, Update, and Delete operations for all entities in the system:

    Student Management: Adding students with data validation (names must contain only letters, groups only digits), editing personal data, and deleting them from the system.

    Professor Management: Administration of academic staff and their departments.

    Course Management: Creating courses, assigning a titular professor, and defining the semester.

    Session Management: Planning activities (Lecture/Laboratory/Seminar) and linking them to existing subjects.

    Enrollment and Attendance Management: Associating students with courses and monitoring attendance at activities.

3. Extra Functionalities (For Maximum Grade) According to the requirements for maximum scoring, the project includes advanced data processing functionalities:

    Complex Queries and Reports: A dedicated "Statistics" section that aggregates data from multiple tables using JOINs and groupings (GROUP BY).

    Variable Parameter: The ability to filter students based on a minimum number of attendances input by the user.

    Advanced Validation: Implementation of a dual validation system (Front-end HTML and Back-end Java) using Regular Expressions (Regex) to ensure data integrity.

    Dynamic Logic: Automatic calculation of academic years based on the current system date, without hardcoding.

B. Description of Classes, Methods, and Attributes

The application architecture follows the Model-View-Controller (MVC) design pattern, ensuring a clear separation between business logic and the user interface.

1. model Package (JPA Entities) These classes represent the database tables. Relationships are managed through Hibernate annotations.

    Student.java: Contains attributes nume (name), prenume (surname), email, grupa (group). Has @OneToMany relationships with Enrollments and Attendance (with CascadeType.ALL for cascading deletion).

    Profesor.java: Contains professor data. Has a @OneToMany relationship with Courses.

    Cursuri.java: The central entity linking professors to students. Includes @Pattern validation for the semester (single digit).

    Inscrieri.java: Link table (Many-to-Many resolved) between Student and Course, including the anUniversitar (academic year) attribute.

    Prezente.java: Records a student's participation in a specific session.

2. repository Package (Data Access) Interfaces extending JpaRepository, offering standard methods and custom JPQL queries (@Query).

    PrezenteRepository: Contains the complex query findStudentiCuPrezenteMinime which uses HAVING and a variable parameter.

    CursuriRepository: Contains the findCursuriPopulare method which counts enrolled students per course.

    ProfesorRepository: Calculates professor workload (number of courses taught).

    SedinteRepository: Offers statistics on activity types (Lecture vs Laboratory) and resolves Lazy Loading issues using JOIN FETCH.

3. controller Package (Application Logic) Manages HTTP requests and communicates with the View.

    StudentController / ProfesorController / CursuriController: Manage routes for listing, add/edit forms, and save actions. Implement validation logic (BindingResult) to return errors to the user.

    StatisticiController: A specialized controller that aggregates data from all repositories and sends it to the reports page.

    InscrieriController: Includes a smart private method that generates the list of academic years (current year ± 1 year) using java.time.LocalDate.

C. Description of Functional Elements (Graphical Interface)

The interface is built using Thymeleaf and styled with Bootstrap 5, featuring a modern, responsive design and a distinct color palette for each module.

1. Dashboard (Main Page) Provides quick access to all application modules via interactive cards. Includes a fixed navigation menu. [INSERT SCREENSHOT OF MAIN PAGE / DASHBOARD HERE]

2. Add/Edit Forms The forms are intuitive and include protection mechanisms.

    Mandatory fields are marked.

    If the user enters letters instead of digits in the "Group" field, the field turns red, an error message appears, and the data is not saved to the database.

    Selection of academic years or professors is done via dropdown menus populated dynamically from the database. [INSERT SCREENSHOT OF A VALIDATED STUDENT FORM HERE]

3. Statistics Page This page demonstrates the complexity of the project. It includes:

    A filtering form where the user inputs a number (e.g., 3), and the table displays only students with at least 3 attendances.

    Summary tables for the most popular courses and professor activity. [INSERT SCREENSHOT OF STATISTICS PAGE HERE]

D. Aspects Regarding Application Testing

Testing was performed manually, following normal usage scenarios and error scenarios (edge cases).

1. Data Validation Testing

    Scenario: Attempting to add a student with digits in the name (e.g., "Smith12").

    Result: The application rejected the save, the page reloaded, and displayed the message "Name can only contain letters!".

2. Referential Integrity Testing (Cascading Delete)

    Scenario: Deleting a Course that already had enrolled students and scheduled sessions.

    Result: Due to the CascadeType.ALL and orphanRemoval=true configuration, the deletion was successful, automatically removing associated enrollments or sessions without generating "Foreign Key Constraint" errors (Status 500).

3. Complex Query Testing

    Scenario: Verifying the attendance filter.

    Result: When entering the value "0", all students were displayed. When entering a higher value, the list was filtered correctly, demonstrating the functionality of the SQL HAVING clause.

E. 5 Ideas for Application Improvement

For further development of the project, I propose the following directions:

    Authentication and Authorization System: Implementing Spring Security to create distinct roles (Admin, Professor, Student). Professors should only be able to modify their own courses, and students should only view their grades.

    Report Export: Adding functionality to export academic records in PDF or Excel format using libraries like Apache POI or JasperReports.

    REST API for Mobile App: Exposing data via JSON endpoints (@RestController) to allow connection with an Android/iOS mobile application for students.

    Email Notifications: Integrating an email service (JavaMailSender) to automatically notify students when a grade or absence is recorded.

    Visual Schedule Interface: Implementing a graphic calendar (e.g., FullCalendar.js) where sessions are displayed visually by day and hour, rather than just as a list.
