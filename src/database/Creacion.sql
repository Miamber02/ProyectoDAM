DROP TABLE IF EXISTS estudiante_materia;
DROP TABLE IF EXISTS Materia;
DROP TABLE IF EXISTS Libro;
DROP TABLE IF EXISTS Estudiante;
DROP TABLE IF EXISTS Profesor;


CREATE TABLE Estudiante (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            dni VARCHAR(255) NOT NULL UNIQUE,
                            nombre VARCHAR(255) NOT NULL,
                            apellidos VARCHAR(255) NOT NULL,
                            email VARCHAR(255) NOT NULL,
                            password VARCHAR(255) NOT NULL
);

CREATE TABLE Profesor (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          dni VARCHAR(255) NOT NULL UNIQUE,
                          nombre VARCHAR(255) NOT NULL,
                          apellidos VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL
);

CREATE TABLE Libro (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       titulo VARCHAR(255) NOT NULL,
                       id_estudiante INT,
                       id_profesor INT,
                       archivo_texto TEXT,
                       FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id),
                       FOREIGN KEY (id_profesor) REFERENCES Profesor(id)
);

CREATE TABLE Materia (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         nombre VARCHAR(255) NOT NULL,
                         horario VARCHAR(255) NOT NULL,
                         id_profesor INT,
                         FOREIGN KEY (id_profesor) REFERENCES Profesor(id)
);

CREATE TABLE Estudiante_Materia (
                                    id_estudiante INT,
                                    id_materia INT,
                                    PRIMARY KEY (id_estudiante, id_materia),
                                    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id),
                                    FOREIGN KEY (id_materia) REFERENCES Materia(id)
);