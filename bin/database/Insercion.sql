INSERT INTO Estudiante (dni, nombre, apellidos, email, password) VALUES
                                                                     ('a', 'Juan', 'García', 'juan.garcia@example.com', 'a'),
                                                                     ('9876543210', 'María', 'López', 'maria.lopez@example.com', 'abcdef'),
                                                                     ('5432167890', 'Pedro', 'Rodríguez', 'pedro.rodriguez@example.com', 'qwerty'),
                                                                     ('9876541230', 'Ana', 'Martínez', 'ana.martinez@example.com', 'password1'),
                                                                     ('3216549870', 'Carlos', 'Hernández', 'carlos.hernandez@example.com', 'password2'),
                                                                     ('4567893210', 'Laura', 'Gómez', 'laura.gomez@example.com', 'password3'),
                                                                     ('7893216540', 'Luis', 'Torres', 'luis.torres@example.com', 'password4'),
                                                                     ('6541239870', 'Elena', 'Morales', 'elena.morales@example.com', 'password5'),
                                                                     ('9870123456', 'Javier', 'Sánchez', 'javier.sanchez@example.com', 'password6'),
                                                                     ('5432109876', 'Sara', 'Ortega', 'sara.ortega@example.com', 'password7');

INSERT INTO Profesor (dni, nombre, apellidos, email, password) VALUES
                                                                   ('b', 'José', 'Pérez', 'jose.perez@example.com', 'b'),
                                                                   ('987654321', 'Laura', 'Fernández', 'laura.fernandez@example.com', 'password9'),
                                                                   ('543216789', 'Manuel', 'González', 'manuel.gonzalez@example.com', 'password10'),
                                                                   ('987654123', 'Eva', 'Sánchez', 'eva.sanchez@example.com', 'password11'),
                                                                   ('321654987', 'Marta', 'Rojas', 'marta.rojas@example.com', 'password12'),
                                                                   ('456789321', 'David', 'Mendoza', 'david.mendoza@example.com', 'password13'),
                                                                   ('789321654', 'Carolina', 'Silva', 'carolina.silva@example.com', 'password14'),
                                                                   ('654123987', 'Francisco', 'Jiménez', 'francisco.jimenez@example.com', 'password15'),
                                                                   ('987012345', 'Paula', 'López', 'paula.lopez@example.com', 'password16'),
                                                                   ('543210987', 'Mario', 'Herrera', 'mario.herrera@example.com', 'password17');

INSERT INTO Libro (titulo, id_estudiante, archivo_texto) VALUES
                                                             ('Libro 1', 1, 'Texto del libro 1'),
                                                             ('Libro 2', 2, 'Texto del libro 2'),
                                                             ('Libro 3', 3, 'Texto del libro 3'),
                                                             ('Libro 4', 4, 'Texto del libro 4'),
                                                             ('Libro 5', 5, 'Texto del libro 5'),
                                                             ('Libro 6', 6, 'Texto del libro 6'),
                                                             ('Libro 7', 7, 'Texto del libro 7'),
                                                             ('Libro 8', 8, 'Texto del libro 8'),
                                                             ('Libro 9', 9, 'Texto del libro 9'),
                                                             ('Libro 10', 10, 'Texto del libro 10');

INSERT INTO Materia (nombre, horario, id_profesor) VALUES
                                                       ('Materia 1', 'Lunes 8:00-10:00', 1),
                                                       ('Materia 2', 'Martes 9:00-11:00', 2),
                                                       ('Materia 3', 'Miércoles 10:00-12:00', 3),
                                                       ('Materia 4', 'Jueves 11:00-13:00', 4),
                                                       ('Materia 5', 'Viernes 12:00-14:00', 5),
                                                       ('Materia 6', 'Lunes 14:00-16:00', 6),
                                                       ('Materia 7', 'Martes 15:00-17:00', 7),
                                                       ('Materia 8', 'Miércoles 16:00-18:00', 8),
                                                       ('Materia 9', 'Jueves 17:00-19:00', 9),
                                                       ('Materia 10', 'Viernes 18:00-20:00', 10);

INSERT INTO Estudiante_Materia (id_estudiante, id_materia) VALUES
                                                               (1, 1),
                                                               (1, 2),
                                                               (2, 3),
                                                               (2, 4),
                                                               (3, 5),
                                                               (3, 6),
                                                               (4, 7),
                                                               (4, 8),
                                                               (5, 9),
                                                               (5, 10);
