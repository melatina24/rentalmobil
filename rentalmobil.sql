-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 17, 2019 at 07:19 AM
-- Server version: 5.7.26
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentalmobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventaris`
--

DROP TABLE IF EXISTS `inventaris`;
CREATE TABLE IF NOT EXISTS `inventaris` (
  `kd_inventaris` int(11) NOT NULL AUTO_INCREMENT,
  `no_inventaris` varchar(50) NOT NULL,
  `petugas_kd` varchar(50) NOT NULL,
  `mobil_kd` varchar(50) NOT NULL,
  `sopir_kd` varchar(50) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `lama_sewa` varchar(50) NOT NULL,
  `total_bayar` int(11) NOT NULL,
  `uang_bayar` int(11) NOT NULL,
  `uang_kembali` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  PRIMARY KEY (`kd_inventaris`),
  KEY `petugas_kd` (`petugas_kd`),
  KEY `mobil_kd` (`mobil_kd`),
  KEY `sopir_kd` (`sopir_kd`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
CREATE TABLE IF NOT EXISTS `kategori` (
  `kd_kategori` int(11) NOT NULL AUTO_INCREMENT,
  `jenis_mobil` varchar(50) NOT NULL,
  PRIMARY KEY (`kd_kategori`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`kd_kategori`, `jenis_mobil`) VALUES
(1, 'Sedan'),
(2, 'MPV'),
(3, 'SUV'),
(4, 'Convertible');

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

DROP TABLE IF EXISTS `mobil`;
CREATE TABLE IF NOT EXISTS `mobil` (
  `kd_mobil` int(11) NOT NULL AUTO_INCREMENT,
  `no_polisi` varchar(50) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `harga` int(11) NOT NULL,
  `kategori_kd` varchar(50) NOT NULL,
  PRIMARY KEY (`kd_mobil`),
  KEY `kategori_kd` (`kategori_kd`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`kd_mobil`, `no_polisi`, `merk`, `harga`, `kategori_kd`) VALUES
(1, 'ff 5454 dd', 'Xenia', 200000000, '2'),
(2, 'dd 4343 ff', 'Ayla', 250000000, '2');

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

DROP TABLE IF EXISTS `petugas`;
CREATE TABLE IF NOT EXISTS `petugas` (
  `kd_petugas` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `hak_akses` varchar(50) NOT NULL,
  `foto` varchar(50) NOT NULL,
  PRIMARY KEY (`kd_petugas`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`kd_petugas`, `nama`, `jenis_kelamin`, `alamat`, `username`, `password`, `hak_akses`, `foto`) VALUES
(1, 'Latifa', 'P', 'Sengon', 'admin', 'admin', 'admin', 'foto.jpg'),
(2, 'Mela', 'P', 'Tulis', 'mela', '123', 'umum', 'foto1.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `sopir`
--

DROP TABLE IF EXISTS `sopir`;
CREATE TABLE IF NOT EXISTS `sopir` (
  `kd_sopir` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `foto` varchar(50) NOT NULL,
  `biaya_sopir` int(11) NOT NULL,
  PRIMARY KEY (`kd_sopir`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sopir`
--

INSERT INTO `sopir` (`kd_sopir`, `nama`, `jenis_kelamin`, `alamat`, `foto`, `biaya_sopir`) VALUES
(1, 'Hagam', 'L', 'Jakarta', 'foto.jpg', 100000),
(2, 'Aska', 'L', 'Jakarta Utara', 'foto1.jpg', 150000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
