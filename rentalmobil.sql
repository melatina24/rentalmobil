-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 24, 2019 at 01:24 PM
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
  `nama_penyewa` varchar(50) NOT NULL,
  `petugas_kd` varchar(50) NOT NULL,
  `mobil_kd` varchar(50) NOT NULL,
  `sopir_kd` varchar(50) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `lama_sewa` varchar(50) NOT NULL,
  `total_bayar` int(11) NOT NULL,
  PRIMARY KEY (`kd_inventaris`),
  KEY `petugas_kd` (`petugas_kd`),
  KEY `mobil_kd` (`mobil_kd`),
  KEY `sopir_kd` (`sopir_kd`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventaris`
--

INSERT INTO `inventaris` (`kd_inventaris`, `nama_penyewa`, `petugas_kd`, `mobil_kd`, `sopir_kd`, `tgl_pinjam`, `tgl_kembali`, `lama_sewa`, `total_bayar`) VALUES
(15, 'gbjjjjj', '0', '0', '0', '2019-11-04', '2019-11-06', '4', 3000),
(9, 'enung', '4', '2', '2', '2019-11-04', '2019-11-07', '3 hari', 3000),
(16, 'ggg', '2', '28', '12', '2019-11-02', '2019-11-04', '2 hr', 2000),
(19, 'dddd', '4', '28', '2', '2019-01-02', '2019-01-04', '3', 3000);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
CREATE TABLE IF NOT EXISTS `kategori` (
  `kd_kategori` int(11) NOT NULL AUTO_INCREMENT,
  `jenis_mobil` varchar(50) NOT NULL,
  PRIMARY KEY (`kd_kategori`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`kd_mobil`, `no_polisi`, `merk`, `harga`, `kategori_kd`) VALUES
(2, 'dd 4343 ff', 'Ayla', 250000000, '2'),
(31, 'dd 4444 ff', 'Xenia', 200000, '1'),
(28, 'hj98', 'bjk', 40000, '0');

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

DROP TABLE IF EXISTS `petugas`;
CREATE TABLE IF NOT EXISTS `petugas` (
  `kd_petugas` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(30) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `hak_akses` varchar(50) NOT NULL,
  PRIMARY KEY (`kd_petugas`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`kd_petugas`, `nama`, `jenis_kelamin`, `alamat`, `username`, `password`, `hak_akses`) VALUES
(1, 'Latifa', 'Perempuan', 'Sengon', 'admin', 'admin', 'Admin'),
(2, 'Mela', 'Perempuan', 'Tulis', 'mela', '123', 'Pegawai'),
(3, 'saya', 'Laki-laki', 'dimana-mana', 'saya', 'saya', 'Pegawai'),
(4, 'admin', 'Laki-laki', 'cobalagi', 'coba', 'coba', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `sopir`
--

DROP TABLE IF EXISTS `sopir`;
CREATE TABLE IF NOT EXISTS `sopir` (
  `kd_sopir` int(11) NOT NULL AUTO_INCREMENT,
  `nama_sopir` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `biaya_sopir` int(11) NOT NULL,
  PRIMARY KEY (`kd_sopir`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sopir`
--

INSERT INTO `sopir` (`kd_sopir`, `nama_sopir`, `jenis_kelamin`, `alamat`, `biaya_sopir`) VALUES
(1, 'Hagam', 'Laki-laki', 'Jakarta', 100000),
(2, 'Aska', 'Laki-laki', 'Jakarta Utara', 150000),
(12, 'ew1', 'Laki-laki', 'werew', 11);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
