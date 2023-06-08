DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `actualizar_estudiante`(
    IN dni_param VARCHAR(255),
    IN nombre_param VARCHAR(255),
    IN apellidos_param VARCHAR(255),
    IN email_param VARCHAR(255),
    IN password_param VARCHAR(255)
)
BEGIN
    UPDATE Estudiante SET
                          nombre = nombre_param,
                          apellidos = apellidos_param,
                          email = email_param,
                          password = password_param
    WHERE dni = dni_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `actualizar_libro`(IN `id_libro_param` INT, IN `id_estudiante_param` INT)
BEGIN
    UPDATE Libro SET id_estudiante = id_estudiante_param WHERE id = id_libro_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `actualizar_materia`(
    IN id_materia_param INT,
    IN nombre_param VARCHAR(255),
    IN horario_param VARCHAR(255)
)
BEGIN
    UPDATE Materia SET nombre = nombre_param, horario = horario_param WHERE id_materia = id_materia_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `actualizar_profesor`(
    IN dni_param VARCHAR(255),
    IN nombre_param VARCHAR(255),
    IN apellidos_param VARCHAR(255),
    IN email_param VARCHAR(255),
    IN password_param VARCHAR(255)
)
BEGIN
    UPDATE Profesor SET
                        nombre = nombre_param,
                        apellidos = apellidos_param,
                        email = email_param,
                        password = password_param
    WHERE dni = dni_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `devolver_libro`(IN `id_libro_param` INT)
UPDATE Libro set id_estudiante = NULL where id = id_libro_param$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `eliminar_estudiante`(IN dni_param VARCHAR(255))
BEGIN
    DELETE FROM Estudiante WHERE dni = dni_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `eliminar_libro`(IN id_libro_param INT)
BEGIN
    DELETE FROM Libro WHERE id_libro = id_libro_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `eliminar_materia`(IN id_materia_param INT)
BEGIN
    DELETE FROM Materia WHERE id_materia = id_materia_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `eliminar_profesor`(IN dni_param VARCHAR(255))
BEGIN
    DELETE FROM Profesor WHERE dni = dni_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `insertar_estudiante`(
    IN dni_param VARCHAR(255),
    IN nombre_param VARCHAR(255),
    IN apellidos_param VARCHAR(255),
    IN email_param VARCHAR(255),
    IN password_param VARCHAR(255)
)
BEGIN
    INSERT INTO Estudiante (dni, nombre, apellidos, email, password)
    VALUES (dni_param, nombre_param, apellidos_param, email_param, password_param);
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `insertar_libro`(IN `title_param` VARCHAR(255), IN `fileInputStream_param` BLOB, IN `id_profesor_param` INT)
BEGIN
    INSERT INTO Libro (titulo, id_profesor, archivo_texto)
    VALUES (title_param, id_profesor_param, fileInputStream_param);
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `insertar_materia`(
    IN nombre_param VARCHAR(255),
    IN horario_param VARCHAR(255)
)
BEGIN
    INSERT INTO Materia (nombre, horario)
    VALUES (nombre_param, horario_param);
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `insertar_profesor`(
    IN dni_param VARCHAR(255),
    IN nombre_param VARCHAR(255),
    IN apellidos_param VARCHAR(255),
    IN email_param VARCHAR(255),
    IN password_param VARCHAR(255)
)
BEGIN
    INSERT INTO Profesor (dni, nombre, apellidos, email, password)
    VALUES (dni_param, nombre_param, apellidos_param, email_param, password_param);
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_dni_profesor_por_id_materia`(IN `id_materia_param` INT)
BEGIN
    SELECT id_profesor FROM Materia WHERE id = id_materia_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_estudiante_por_dni`(IN dni_param VARCHAR(255))
BEGIN
    SELECT * FROM Estudiante WHERE dni = dni_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_estudiantes`()
BEGIN
    SELECT dni AS dni_estudiante, nombre, apellidos, email FROM Estudiante;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_libro_por_id`(IN `id_libro_param` INT)
BEGIN
    SELECT * FROM Libro WHERE id = id_libro_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_libros`()
BEGIN
    SELECT l.id, l.titulo, CONCAT(e.nombre,' ',e.apellidos) AS nombre_estudiante
	FROM Libro AS l
	INNER JOIN Estudiante AS e ON e.id = l.id;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_libros_alquiler`()
BEGIN
    SELECT l.id, l.titulo, CONCAT(p.nombre,' ',p.apellidos) AS nombre_profesor
    FROM Libro AS l
    INNER JOIN Profesor AS p ON p.id = l.id
    WHERE l.id_estudiante IS NULL;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_materia_por_id`(IN id_materia_param INT)
BEGIN
    SELECT * FROM Materia WHERE id_materia = id_materia_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_mis_libros`(IN `id_estudiante_param` INT)
Select * from Libro where id_estudiante = id_estudiante_param$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_mis_materias`(IN `id_estudiante_param` INT)
SELECT m.id, m.nombre, m.horario
FROM materia AS m
         INNER JOIN estudiante_materia AS em ON m.id = em.id_materia
WHERE em.id_estudiante = id_estudiante_param$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_profesor_por_dni`(IN dni_param VARCHAR(255))
BEGIN
    SELECT * FROM Profesor WHERE dni = dni_param;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE DEFINER=`root`@`localhost` PROCEDURE `obtener_profesor_por_id`(IN `id_profesor_param` INT)
SELECT * FROM Profesor where id = id_profesor_param$$
DELIMITER ;

