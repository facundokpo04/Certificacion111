-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-12-2018 a las 00:23:04
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbcampos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campo`
--

CREATE TABLE `campo` (
  `id_campo` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `superficie` decimal(19,2) DEFAULT NULL,
  `id_estado_campo` int(11) DEFAULT NULL,
  `idGestor` varchar(255) DEFAULT NULL,
  `Idx` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadocampo`
--

CREATE TABLE `estadocampo` (
  `id_EstadoCampo` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `idGestor` varchar(255) DEFAULT NULL,
  `Idx` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gestorcampo`
--

CREATE TABLE `gestorcampo` (
  `cuit` varchar(255) NOT NULL,
  `razonSocial` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lote`
--

CREATE TABLE `lote` (
  `id_lote` int(11) NOT NULL,
  `numero_lote` int(11) DEFAULT NULL,
  `superficie` decimal(19,2) DEFAULT NULL,
  `id_tiposuelo` int(11) DEFAULT NULL,
  `idcampo` int(11) DEFAULT NULL,
  `Idx` int(11) DEFAULT NULL,
  `idGestor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposuelo`
--

CREATE TABLE `tiposuelo` (
  `id_tiposuelo` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `idGestor` varchar(255) DEFAULT NULL,
  `Idx` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `campo`
--
ALTER TABLE `campo`
  ADD PRIMARY KEY (`id_campo`),
  ADD KEY `FKtdpif9tpckqtmye6bk6vebrx5` (`id_estado_campo`),
  ADD KEY `FK5d65n7m75jy3xttm5gls57ks5` (`idGestor`);

--
-- Indices de la tabla `estadocampo`
--
ALTER TABLE `estadocampo`
  ADD PRIMARY KEY (`id_EstadoCampo`),
  ADD KEY `FKjse9f7bgh664xdy6nymin5q6w` (`idGestor`);

--
-- Indices de la tabla `gestorcampo`
--
ALTER TABLE `gestorcampo`
  ADD PRIMARY KEY (`cuit`);

--
-- Indices de la tabla `lote`
--
ALTER TABLE `lote`
  ADD PRIMARY KEY (`id_lote`),
  ADD KEY `FKh6fuwr0r9647wswukd06tr8vk` (`id_tiposuelo`),
  ADD KEY `FKb2ps5nlvqwjy0e6rqiwv48i0f` (`idcampo`),
  ADD KEY `FK7pr37ae36sapn85mtx4193yo2` (`idGestor`);

--
-- Indices de la tabla `tiposuelo`
--
ALTER TABLE `tiposuelo`
  ADD PRIMARY KEY (`id_tiposuelo`),
  ADD KEY `FKe290biwc0f3c9lre5mv6u2mnn` (`idGestor`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `campo`
--
ALTER TABLE `campo`
  ADD CONSTRAINT `FK5d65n7m75jy3xttm5gls57ks5` FOREIGN KEY (`idGestor`) REFERENCES `gestorcampo` (`cuit`),
  ADD CONSTRAINT `FKtdpif9tpckqtmye6bk6vebrx5` FOREIGN KEY (`id_estado_campo`) REFERENCES `estadocampo` (`id_EstadoCampo`);

--
-- Filtros para la tabla `estadocampo`
--
ALTER TABLE `estadocampo`
  ADD CONSTRAINT `FKjse9f7bgh664xdy6nymin5q6w` FOREIGN KEY (`idGestor`) REFERENCES `gestorcampo` (`cuit`);

--
-- Filtros para la tabla `lote`
--
ALTER TABLE `lote`
  ADD CONSTRAINT `FK7pr37ae36sapn85mtx4193yo2` FOREIGN KEY (`idGestor`) REFERENCES `gestorcampo` (`cuit`),
  ADD CONSTRAINT `FKb2ps5nlvqwjy0e6rqiwv48i0f` FOREIGN KEY (`idcampo`) REFERENCES `campo` (`id_campo`),
  ADD CONSTRAINT `FKh6fuwr0r9647wswukd06tr8vk` FOREIGN KEY (`id_tiposuelo`) REFERENCES `tiposuelo` (`id_tiposuelo`);

--
-- Filtros para la tabla `tiposuelo`
--
ALTER TABLE `tiposuelo`
  ADD CONSTRAINT `FKe290biwc0f3c9lre5mv6u2mnn` FOREIGN KEY (`idGestor`) REFERENCES `gestorcampo` (`cuit`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
