-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-12-2023 a las 01:10:47
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

CREATE DATABASE bd_universidad;
use bd_universidad;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_universidad`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_asignatura` (IN `codigoAsignatura` VARCHAR(50))   BEGIN
    -- Paso 1: Eliminar las filas de la tabla matricula que hacen referencia a la asignatura
    DELETE FROM matricula
    WHERE Nombre_asignatura = codigoAsignatura;

    -- Paso 2: Eliminar la fila de la tabla asignatura
    DELETE FROM asignatura
    WHERE Codigo = codigoAsignatura;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_matricula` (IN `p_id` INT)   BEGIN
    DELETE FROM matricula WHERE id = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_asignatura` (`in_codigo` VARCHAR(50), `in_carrera` VARCHAR(50), `in_nombre` VARCHAR(255), `in_creditos` INT)   BEGIN
    DECLARE carrera_existente INT;

    -- Verificar si la carrera existe
    SELECT COUNT(*) INTO carrera_existente FROM carrera_profesional WHERE Codigo = in_carrera;

    IF carrera_existente = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La carrera no existe en la tabla carrera_profesional.';
    END IF;

    -- Insertar en la tabla asignatura
    INSERT INTO asignatura (Codigo, Nombre_carrera_profesional, Nombre_asignatura, Creditos)
    VALUES (in_codigo, in_carrera, in_nombre, in_creditos);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_matricula` (`in_codigo_alumno` VARCHAR(50), `in_semestre` INT, `in_nombre_asignatura` VARCHAR(50), `in_turno` VARCHAR(50), `in_fecha_matricula` DATE)   BEGIN
    DECLARE alumno_existente INT;
    DECLARE asignatura_existente INT;

    -- Verificar si el alumno existe
    SELECT COUNT(*) INTO alumno_existente FROM alumno WHERE Codigo = in_codigo_alumno;

    IF alumno_existente = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El alumno no existe en la tabla alumno.';
    END IF;

    -- Verificar si la asignatura existe
    SELECT COUNT(*) INTO asignatura_existente FROM asignatura WHERE Codigo = in_nombre_asignatura;

    IF asignatura_existente = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La asignatura no existe en la tabla asignatura.';
    END IF;

    -- Insertar en la tabla matricula
    INSERT INTO matricula (Codigo_alumno, Semestre, Nombre_asignatura, Turno, Fecha_de_matricula)
    VALUES (in_codigo_alumno, in_semestre, in_nombre_asignatura, in_turno, in_fecha_matricula);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_asignatura` (IN `p_codigo` VARCHAR(50), IN `p_nombre_carrera_profesional` VARCHAR(50), IN `p_nombre_asignatura` VARCHAR(255), IN `p_creditos` INT)   BEGIN
    UPDATE asignatura
    SET
        Nombre_carrera_profesional = p_nombre_carrera_profesional,
        Nombre_asignatura = p_nombre_asignatura,
        Creditos = p_creditos
    WHERE Codigo = p_codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_matricula` (IN `p_id` INT, IN `p_codigo_alumno` VARCHAR(255), IN `p_semestre` INT, IN `p_nombre_asignatura` VARCHAR(255), IN `p_turno` VARCHAR(255), IN `p_fecha_de_matricula` DATE)   BEGIN
    UPDATE matricula
    SET
        Codigo_alumno = p_codigo_alumno,
        Semestre = p_semestre,
        Nombre_asignatura = p_nombre_asignatura,
        Turno = p_turno,
        Fecha_de_matricula = p_fecha_de_matricula
    WHERE id = p_id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `Codigo` varchar(50) NOT NULL,
  `Nombres` varchar(255) DEFAULT NULL,
  `Apellidos` varchar(255) DEFAULT NULL,
  `DNI` varchar(20) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Nombre_carrera_profesional` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`Codigo`, `Nombres`, `Apellidos`, `DNI`, `Direccion`, `Telefono`, `Nombre_carrera_profesional`) VALUES
('A10', 'Camila', 'Hernández', '12345678', 'Av. Principal 345', '345678901', 'CP002'),
('A11', 'Alejandro', 'Gómez', '23456789', 'Calle Secundaria 567', '456789012', 'CP003'),
('A12', 'Elena', 'Jiménez', '34567890', 'Av. Principal 678', '567890123', 'CP001'),
('A13', 'Daniel', 'Pérez', '45678901', 'Calle 789', '678901234', 'CP002'),
('A14', 'Sara', 'López', '56789012', 'Av. Principal 901', '789012345', 'CP003'),
('A15', 'Javier', 'Martínez', '67890123', 'Calle Secundaria 012', '890123456', 'CP001'),
('A16', 'Marina', 'García', '78901234', 'Av. Principal 234', '901234567', 'CP002'),
('A17', 'Pablo', 'López', '89012345', 'Calle Secundaria 456', '123456789', 'CP003'),
('A2', 'María', 'Gómez', '87654321', 'Calle Secundaria 456', '987123456', 'CP2'),
('A3', 'Luis', 'Martínez', '34567890', 'Calle 123', '567890123', 'CP001'),
('A4', 'Sofía', 'García', '45678901', 'Av. Principal 456', '678901234', 'CP002'),
('A5', 'Miguel', 'López', '56789012', 'Calle Secundaria 789', '789012345', 'CP003'),
('A6', 'Lorena', 'Pérez', '67890123', 'Av. Principal 789', '890123456', 'CP001'),
('A7', 'David', 'Rodríguez', '78901234', 'Calle Secundaria 123', '901234567', 'CP002'),
('A8', 'Valeria', 'Sánchez', '89012345', 'Av. Principal 012', '123456789', 'CP003'),
('A9', 'Diego', 'Torres', '90123456', 'Calle 456', '234567890', 'CP001'),
('AL003', 'Carla', 'Lopez', '55555555', 'Calle C, Cusco', '22', 'CP003');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura`
--

CREATE TABLE `asignatura` (
  `Codigo` varchar(50) NOT NULL,
  `Nombre_carrera_profesional` varchar(50) DEFAULT NULL,
  `Nombre_asignatura` varchar(255) DEFAULT NULL,
  `Creditos` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignatura`
--

INSERT INTO `asignatura` (`Codigo`, `Nombre_carrera_profesional`, `Nombre_asignatura`, `Creditos`) VALUES
('AS001', 'CP001', 'Programación Avanzada', 4),
('AS002', 'CP002', 'Gestión Empresarial', 3),
('AS003', 'CP003', 'Psicología Clínica', 5),
('AS004', 'CP004', 'Nutrición y Dietética', 3),
('AS005', 'CP005', 'Psicología Infantil', 4),
('AS006', 'CP006', 'Diseño Estructural', 5),
('AS007', 'CP007', 'Diseño Gráfico Avanzado', 4),
('AS008', 'CP008', 'Anatomía Humana', 5),
('AS009', 'CP009', 'Diseño Arquitectónico', 4),
('AS010', 'CP010', 'Auditoría Contable', 3),
('AS011', 'CP011', 'Sistemas Eléctricos', 4),
('AS012', 'CP012', 'Política Internacional', 5),
('AS013', 'CP013', 'Historia del Renacimiento', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera_profesional`
--

CREATE TABLE `carrera_profesional` (
  `Codigo` varchar(50) NOT NULL,
  `Nombre_de_carrera` varchar(255) DEFAULT NULL,
  `Modalidad` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carrera_profesional`
--

INSERT INTO `carrera_profesional` (`Codigo`, `Nombre_de_carrera`, `Modalidad`) VALUES
('111111', 'aaaaaaa', 'Presencial'),
('CP001', 'Ingeniería Informática', 'Presencial'),
('CP002', 'Administración de Empresas', 'Virtual'),
('CP003', 'Leyes', 'hibrido'),
('CP004', 'Nutricion', 'Presencial'),
('CP005', 'Psicología', 'Virtual'),
('CP006', 'Ingeniería Civil', 'Presencial'),
('CP007', 'Diseño Gráfico', 'Virtual'),
('CP008', 'Medicina', 'Presencial'),
('CP009', 'Arquitectura', 'Híbrido'),
('CP010', 'Contabilidad', 'Presencial'),
('CP011', 'Ingeniería Eléctrica', 'Virtual'),
('CP012', 'Ciencias Políticas', 'Presencial'),
('CP013', 'Historia del Arte', 'Virtual'),
('CP014', 'Economía', 'Presencial'),
('CP015', 'Publicidad y Relaciones Públicas', 'Virtual'),
('CP1', 'Ingeniería Informática', 'Presencial'),
('CP2', 'Administración de Empresas', 'Virtual');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `convenio`
--

CREATE TABLE `convenio` (
  `id` int(11) NOT NULL,
  `nombre_universidad` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `duracion_mes` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `convenio`
--

INSERT INTO `convenio` (`id`, `nombre_universidad`, `pais`, `tipo`, `duracion_mes`) VALUES
(1, 'UBA', 'Argentina', 'Pregrado', 6),
(2, 'Harvard', 'Estados Unidos', 'Posgrado', 12),
(3, 'Oxford', 'Reino Unido', 'Intercambio', 4),
(4, 'UNAM', 'México', 'Investigación', 8),
(5, 'Sorbonne', 'Francia', 'Posgrado', 11),
(9, 'Universidad ABC', 'Estados Unidos', 'Intercambio', 6),
(10, 'Universidad XYZ', 'España', 'Colaboración', 12),
(11, 'Universidad ABC', 'Estados Unidos', 'Intercambio', 6),
(12, 'Universidad XYZ', 'España', 'Colaboración', 12),
(13, 'Universidad QWE', 'Canadá', 'Investigación', 8),
(14, 'Universidad ZXC', 'Australia', 'Posgrado', 10),
(15, 'Universidad RTY', 'Francia', 'Intercambio', 7),
(16, 'Universidad PLM', 'Italia', 'Investigación', 9),
(17, 'Universidad UIO', 'Alemania', 'Colaboración', 11),
(18, 'Universidad JKL', 'Reino Unido', 'Posgrado', 12),
(19, 'Universidad MNB', 'Japón', 'Investigación', 8),
(20, 'Universidad GHJ', 'Brasil', 'Intercambio', 6),
(21, 'Universidad VBN', 'China', 'Posgrado', 11),
(22, 'Universidad ASD', 'India', 'Colaboración', 9),
(23, 'Universidad FGH', 'Rusia', 'Intercambio', 7),
(24, 'Universidad YUI', 'Corea del Sur', 'Investigación', 10),
(25, 'Universidad XCV', 'México', 'Colaboración', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_extracurricular`
--

CREATE TABLE `curso_extracurricular` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `costo` double DEFAULT NULL,
  `certificado` varchar(255) DEFAULT NULL,
  `duracion_mes` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `curso_extracurricular`
--

INSERT INTO `curso_extracurricular` (`id`, `nombre`, `empresa`, `costo`, `certificado`, `duracion_mes`) VALUES
(1, 'Power BI', 'Microsoft', 300, 'Sí', 6),
(2, 'Inglés Avanzado', 'English Institute', 200, 'Sí', 4),
(3, 'Marketing Digital', 'Digital Academy', 150, 'Sí', 3),
(4, 'Diseño Gráfico', 'Creative Studio', 250, 'No', 5),
(5, 'Data Science', 'Code Academy', 180, 'Sí', 6),
(6, 'Curso de Inglés Avanzado', 'Language Institute', 100, 'Certificado', 4),
(7, 'Curso de Programación Web', 'Tech Academy', 120, 'Certificado', 3),
(8, 'a', 'e', 0, 's', 0),
(9, 'aa', 'ww', 2, 'a', 2),
(10, 'asf', 'asf', 34, 'ss', 34),
(11, 'Diseño de UI/UX', 'Creative Design Academy', 250, 'Sí', 4),
(12, 'Desarrollo de Aplicaciones Móviles', 'Mobile App Institute', 300, 'Sí', 5),
(13, 'Marketing Digital Avanzado', 'Digital Marketing Experts', 200, 'Sí', 3),
(14, 'Inglés para Negocios', 'Business English School', 150, 'Sí', 4),
(15, 'Fotografía Artística', 'Art Photography Studio', 180, 'Sí', 3),
(16, 'Diseño de Logotipos', 'Logo Design Studio', 120, 'Sí', 2),
(17, 'Programación en Python', 'Python Code Academy', 220, 'Sí', 6),
(18, 'Gestión de Proyectos', 'Project Management Institute', 280, 'Sí', 5),
(19, 'Diseño Gráfico Avanzado', 'Advanced Graphic Design School', 270, 'Sí', 4),
(20, 'Finanzas Personales', 'Personal Finance Institute', 150, 'Sí', 3),
(21, 'Redes Sociales para Empresas', 'Social Media Marketing Hub', 200, 'Sí', 4),
(22, 'Diseño de Videojuegos', 'Game Design Workshop', 320, 'Sí', 6),
(23, 'Negocios Internacionales', 'International Business School', 250, 'Sí', 5),
(24, 'Guitarra Eléctrica', 'Music Academy', 180, 'Sí', 4),
(25, 'Dibujo y Pintura', 'Artistic Creations Studio', 160, 'Sí', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docente`
--

CREATE TABLE `docente` (
  `Codigo` varchar(50) NOT NULL,
  `Nombres` varchar(255) DEFAULT NULL,
  `Apellidos` varchar(255) DEFAULT NULL,
  `DNI` varchar(20) DEFAULT NULL,
  `Especialidad` varchar(255) DEFAULT NULL,
  `Correo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `docente`
--

INSERT INTO `docente` (`Codigo`, `Nombres`, `Apellidos`, `DNI`, `Especialidad`, `Correo`) VALUES
('', '', '', '33333333', '', ''),
('D1', 'Carlos', 'Martínez', '56789012', 'Matemáticas', 'carlos@gmail.com'),
('D2', 'Ana', 'López', '67890123', 'Historia', 'ana@hotmail.com'),
('D3', 'Isabel', 'Ramírez', '45678901', 'Literatura', 'isabel@gmail.com'),
('D4', 'Martín', 'Gutiérrez', '56789012', 'Química', 'martin@hotmail.com'),
('D5', 'Luisa', 'Hernández', '67890123', 'Biología', 'luisa@example.com'),
('DOC001', 'Pedro', 'García', '22222222', 'Matemáticas', 'pedro@example.com'),
('DOC002', 'Laura', 'Vargas', '77777777', 'Historia', 'laura@example.com'),
('DOC003', 'Ana', 'Torres', '33333333', 'Física', 'ana@example.com'),
('DOC004', 'Mario', 'Sánchez', '44444444', 'Historia del Arte', 'mario@example.com'),
('DOC005', 'Mónica', 'López', '55555555', 'Geografía', 'monica@example.com'),
('DOC006', 'Eduardo', 'Mendoza', '66666666', 'Matemáticas', 'eduardo@example.com'),
('DOC007', 'Silvia', 'Vega', '77777777', 'Inglés', 'silvia@example.com'),
('DOC008', 'Juan', 'Herrera', '88888888', 'Educación Física', 'juan@example.com'),
('DOC009', 'Laura', 'García', '99999999', 'Arquitectura', 'laura@example.com'),
('DOC010', 'Pablo', 'Jiménez', '10101010', 'Economía', 'pablo@example.com'),
('DOC011', 'Carolina', 'Moreno', '12121212', 'Psicología', 'carolina@example.com'),
('DOC012', 'Francisco', 'Ortega', '13131313', 'Derecho', 'francisco@example.com'),
('DOC013', 'Rosa', 'Gómez', '14141414', 'Filosofía', 'rosa@example.com'),
('DOC014', 'Javier', 'Fernández', '15151515', 'Química', 'javier@example.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `id` int(11) NOT NULL,
  `Codigo_alumno` varchar(50) DEFAULT NULL,
  `Semestre` int(11) DEFAULT NULL,
  `Nombre_asignatura` varchar(50) DEFAULT NULL,
  `Turno` varchar(50) DEFAULT NULL,
  `Fecha_de_matricula` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`id`, `Codigo_alumno`, `Semestre`, `Nombre_asignatura`, `Turno`, `Fecha_de_matricula`) VALUES
(23, 'AL003', 4, 'AS003', 'Tarde', '2023-12-20'),
(33, 'AL003', 9, 'AS003', 'maÃ±ana', '2023-12-15'),
(34, 'A2', 2, 'AS003', 'Tarde', '2023-12-21'),
(42, 'AL003', 10, 'AS003', 'mañana', '2023-12-16'),
(44, 'AL003', 7, 'AS003', 'noche', '2023-12-21'),
(45, 'AL003', 8, 'AS003', 'tarde', '2023-12-01'),
(46, 'A2', 4, 'AS003', 'mañana', '2023-12-15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `taller`
--

CREATE TABLE `taller` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `duracion_mes` int(11) DEFAULT NULL,
  `horario` varchar(255) DEFAULT NULL,
  `costo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `taller`
--

INSERT INTO `taller` (`id`, `nombre`, `duracion_mes`, `horario`, `costo`) VALUES
(1, 'Taller de Guitarra', 3, '9am - 11am', 0),
(2, 'Taller de Pintura', 2, '2pm - 4pm', 50),
(3, 'Taller de Fotografía', 4, '10am - 12pm', 75),
(4, 'Taller de Escritura Creativa', 5, '6pm - 8pm', 30),
(5, 'Taller de Desarrollo móvil', 6, '3pm - 5pm', 100),
(6, 'Taller de Fotografía', 2, 'Tarde', 50),
(7, 'Taller de Marketing Digital', 3, 'Noche', 75),
(8, 'Piano', 3, '10 a 12', 2.4),
(13, 'Taller de Escultura', 3, '4pm - 6pm', 60),
(14, 'Taller de Fotografía Digital', 2, '6pm - 8pm', 50),
(15, 'Taller de Diseño Gráfico', 4, '2pm - 4pm', 80),
(16, 'Taller de Cocina Internacional', 5, '7pm - 9pm', 70),
(17, 'Taller de Marketing Digital Avanzado', 6, '10am - 12pm', 90),
(18, 'Taller de Yoga', 2, '8am - 10am', 30),
(19, 'Taller de Programación Python', 3, '5pm - 7pm', 75),
(20, 'Taller de Cerámica', 4, '3pm - 5pm', 55),
(21, 'Taller de Música', 5, '1pm - 3pm', 40),
(22, 'Taller de Idiomas', 6, '6pm - 8pm', 65),
(23, 'Taller de Escritura Creativa', 3, '7pm - 9pm', 45),
(24, 'Taller de Jardinería', 2, '10am - 12pm', 35),
(25, 'Taller de Ballet', 4, '4pm - 6pm', 50),
(26, 'Taller de Repostería', 5, '2pm - 4pm', 60),
(27, 'Taller de Canto', 6, '6pm - 8pm', 55);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`Codigo`),
  ADD KEY `Nombre_carrera_profesional` (`Nombre_carrera_profesional`);

--
-- Indices de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD PRIMARY KEY (`Codigo`),
  ADD KEY `Nombre_carrera_profesional` (`Nombre_carrera_profesional`);

--
-- Indices de la tabla `carrera_profesional`
--
ALTER TABLE `carrera_profesional`
  ADD PRIMARY KEY (`Codigo`);

--
-- Indices de la tabla `convenio`
--
ALTER TABLE `convenio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `curso_extracurricular`
--
ALTER TABLE `curso_extracurricular`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`Codigo`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Codigo_alumno` (`Codigo_alumno`),
  ADD KEY `Nombre_asignatura` (`Nombre_asignatura`);

--
-- Indices de la tabla `taller`
--
ALTER TABLE `taller`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `convenio`
--
ALTER TABLE `convenio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `curso_extracurricular`
--
ALTER TABLE `curso_extracurricular`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `matricula`
--
ALTER TABLE `matricula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de la tabla `taller`
--
ALTER TABLE `taller`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD CONSTRAINT `alumno_ibfk_1` FOREIGN KEY (`Nombre_carrera_profesional`) REFERENCES `carrera_profesional` (`Codigo`);

--
-- Filtros para la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD CONSTRAINT `asignatura_ibfk_1` FOREIGN KEY (`Nombre_carrera_profesional`) REFERENCES `carrera_profesional` (`Codigo`);

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `matricula_ibfk_1` FOREIGN KEY (`Codigo_alumno`) REFERENCES `alumno` (`Codigo`),
  ADD CONSTRAINT `matricula_ibfk_2` FOREIGN KEY (`Nombre_asignatura`) REFERENCES `asignatura` (`Codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
