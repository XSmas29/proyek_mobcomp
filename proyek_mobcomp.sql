-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2021 at 01:32 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `proyek_mobcomp`
--
CREATE DATABASE IF NOT EXISTS `proyek_mobcomp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `proyek_mobcomp`;

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

DROP TABLE IF EXISTS `barang`;
CREATE TABLE `barang` (
  `id` int(11) NOT NULL,
  `fk_seller` varchar(50) NOT NULL,
  `fk_kategori` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `deskripsi` varchar(1000) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `gambar` varchar(50) NOT NULL DEFAULT 'default.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id`, `fk_seller`, `fk_kategori`, `nama`, `deskripsi`, `harga`, `stok`, `gambar`) VALUES
(1, 'asd', 1, 'asd', 'asd', 80000, 123, 'produk_1.jpg'),
(2, 'asd', 1, 'asd', 'asd', 80000, 123, 'produk_2.jpg'),
(3, 'asd', 1, 'qwe', 'qwe', 12000, 100, 'produk_3.jpg'),
(4, 'asd', 2, 'skin dota', 'skin dota 2', 124000, 200, 'produk_4.jpg'),
(5, 'asd', 1, 'Steam wallet 5$', 'steam wallet 5$', 95000, 123, 'produk_5.jpg'),
(6, 'asd', 4, 'ak 47 legion of anubis', 'skin csgo : ak 47 legion of anubis', 360000, 20, 'produk_6.png'),
(7, 'asd', 4, 'desert eagle code red', 'skin csgo : desert eagle code red', 450000, 28, 'produk_7.png'),
(8, 'asd', 1, 'asd', 'asd', 80000, 123, 'produk_8.jpg'),
(9, 'asd', 1, 'barang baru', 'barang baru', 10000, 123, 'produk_9.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `diskusi`
--

DROP TABLE IF EXISTS `diskusi`;
CREATE TABLE `diskusi` (
  `id` int(11) NOT NULL,
  `fk_barang` int(11) NOT NULL,
  `fk_user` varchar(50) NOT NULL,
  `konten` varchar(255) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dtrans`
--

DROP TABLE IF EXISTS `dtrans`;
CREATE TABLE `dtrans` (
  `id` int(11) NOT NULL,
  `fk_htrans` int(11) NOT NULL,
  `fk_barang` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL,
  `rating` int(11) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `htrans`
--

DROP TABLE IF EXISTS `htrans`;
CREATE TABLE `htrans` (
  `id` int(11) NOT NULL,
  `fk_customer` varchar(50) NOT NULL,
  `fk_seller` varchar(50) NOT NULL,
  `grandtotal` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  `bukti` varchar(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `htrans`
--

INSERT INTO `htrans` (`id`, `fk_customer`, `fk_seller`, `grandtotal`, `status`, `bukti`, `tanggal`) VALUES
(1, 'qwe', 'asd', 10000, 'rejected', 'test.jpg', '2021-11-01'),
(2, 'qwe', 'asdasd', 85000, 'rejected', 'test.jpg', '2021-11-09'),
(3, 'qweqwe', 'asd', 86000, 'rejected', 'test.jpg', '2021-11-19'),
(4, 'qweqwe', 'asd', 52000, 'completed', 'test.jpg', '2021-10-12'),
(5, 'qweqwe', 'asd', 41000, 'pending', 'test.jpg', '2021-09-29'),
(6, 'qweqwe', 'asd', 98000, 'pending', 'test.jpg', '2021-11-05'),
(7, 'qweqwe', 'asd', 122000, 'pending', 'test.jpg', '2021-10-21'),
(8, 'qweqwe', 'asd', 446000, 'processed', 'test.jpg', '2020-12-28'),
(9, 'qweqwe', 'asd', 555000, 'processed', 'test.jpg', '2021-10-05'),
(10, 'qweqwe', 'asd', 11000, 'pending', 'test.jpg', '2021-08-09'),
(11, 'qweqwe', 'asd', 250000, 'rejected', 'test.jpg', '2021-11-18'),
(12, 'qweqwe', 'asdasd', 127000, 'pending', 'test.jpg', '2021-10-11'),
(13, 'qweqwe', 'asd', 39000, 'pending', 'test.jpg', '2021-09-23'),
(14, 'qwe', 'asd', 92500, 'pending', 'test.jpg', '2021-11-02'),
(15, 'qwe', 'asd', 450000, 'pending', 'test.jpg', '2021-10-22'),
(16, 'qwe', 'asd', 426000, 'processed', 'test.jpg', '2020-12-27'),
(17, 'qwe', 'asd', 525000, 'processed', 'test.jpg', '2021-10-04'),
(18, 'qweqwe', 'asd', 21500, 'pending', 'test.jpg', '2021-08-02');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `tipe` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `nama`, `tipe`) VALUES
(1, 'Steam Wallet', 'voucher'),
(2, 'Dota 2', 'item'),
(3, 'Google Play Voucher', 'voucher'),
(4, 'Counter Strike', 'item');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

DROP TABLE IF EXISTS `komentar`;
CREATE TABLE `komentar` (
  `id` int(11) NOT NULL,
  `fk_diskusi` int(11) NOT NULL,
  `isi` varchar(255) NOT NULL,
  `tanggal` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `rekening` varchar(20) NOT NULL,
  `saldo` int(11) NOT NULL DEFAULT 0,
  `toko` varchar(50) DEFAULT '',
  `role` varchar(10) NOT NULL,
  `gambar` varchar(60) DEFAULT 'default.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `email`, `nama`, `rekening`, `saldo`, `toko`, `role`, `gambar`) VALUES
('asd', 'asdasdasd', 'asd@gmail.com', 'asd', 'asd', 0, 'TOKO 1', 'SELLER', 'default.jpg'),
('asdasd', 'asdasdasd', 'asdasd@gmail.com', 'asdasdasd', '123', 0, 'TOKO 2', 'SELLER', 'default.jpg'),
('qwe', '123123123', 'qwe@gmail.com', 'qwe', '123', 0, '', 'CUSTOMER', 'default.jpg'),
('qweqwe', 'qweqweqwe', 'qweqwe@gmail.com', 'qwe', '123', 0, '', 'CUSTOMER', 'default.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE `wishlist` (
  `id` int(11) NOT NULL,
  `fk_user` varchar(50) NOT NULL,
  `fk_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`id`, `fk_user`, `fk_barang`) VALUES
(25, 'qwe', 3),
(34, 'qwe', 6),
(35, 'qwe', 1),
(44, 'qwe', 7),
(56, 'qwe', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diskusi`
--
ALTER TABLE `diskusi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dtrans`
--
ALTER TABLE `dtrans`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `htrans`
--
ALTER TABLE `htrans`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `komentar`
--
ALTER TABLE `komentar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `diskusi`
--
ALTER TABLE `diskusi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dtrans`
--
ALTER TABLE `dtrans`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `htrans`
--
ALTER TABLE `htrans`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `komentar`
--
ALTER TABLE `komentar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
