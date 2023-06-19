-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 13, 2023 at 08:36 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpus`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `idbuku` int(11) NOT NULL,
  `judul_buku` varchar(45) DEFAULT NULL,
  `tahun_terbit` date DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `penerbit` varchar(45) DEFAULT NULL,
  `jumlah_halaman` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `peminjam` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`idbuku`, `judul_buku`, `tahun_terbit`, `author`, `penerbit`, `jumlah_halaman`, `status`, `peminjam`) VALUES
(1, 'doraemon', '2023-05-30', 'masashi kisimoto', 'gramedia', 500, 1, 'budi'),
(2, 'naruto', '2011-01-25', 'masashi kisimoto', 'namco', 155, 1, 'budi'),
(3, 'harry Potter', '1999-05-25', 'jk rowling', 'gramed', 1000, 1, 'budi'),
(5, 'persona', '2025-05-15', 'atlus', 'airlangga', 500, 1, 'budi'),
(6, 'bleach', '2025-12-25', 'naruto', 'airlanngga', 750, 0, 'budi'),
(7, 'one piece', '2024-11-05', 'ikioto', 'gramed', 150, 0, NULL),
(14, 'nba', '2024-01-12', 'kobe', 'gramed', 150, 0, 'budi'),
(18, 'fifa', '2023-11-13', 'messi', 'ronaldo', 1, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nrp` int(11) DEFAULT NULL,
  `jurusan` varchar(45) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `nrp`, `jurusan`, `role`) VALUES
('budi', '123', 23, 'kimia', 0),
('julius', '123', 56445345, 'elektro', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`idbuku`),
  ADD KEY `fk_buku_user_idx` (`peminjam`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `idbuku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `buku`
--
ALTER TABLE `buku`
  ADD CONSTRAINT `fk_buku_user` FOREIGN KEY (`peminjam`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
