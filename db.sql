-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2020 at 02:04 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kerja_praktek_unla`
--

-- --------------------------------------------------------

--
-- Table structure for table `bagian`
--

CREATE TABLE `bagian` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `deskripsi` longtext DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `departement_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bagian`
--

INSERT INTO `bagian` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `deskripsi`, `nama`, `departement_id`) VALUES
(1, 'SYSTEM', '2019-10-17 20:08:07', b'0', 'SYSTEM', '2019-10-17 20:08:10', 'Project', 'Project', 1),
(2, 'SYSTEM', '2019-10-17 20:08:07', b'0', 'SYSTEM', '2019-10-17 20:08:10', 'Testing and Penetration', 'Testing and Penetration', 1),
(3, 'peripurnama', '2019-12-22 21:36:27', b'0', 'peripurnama', '2019-12-22 21:36:27', '', '', 1),
(4, 'peripurnama', '2019-12-22 21:36:33', b'1', 'peripurnama', '2019-12-22 21:38:28', '', 'Solihin sadsa', 1),
(5, 'peripurnama', '2019-12-22 21:38:44', b'0', 'peripurnama', '2019-12-22 21:38:44', '', 'ASDSADSA', 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `description` longtext DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cuti`
--

CREATE TABLE `cuti` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `jumlah_cuti_tahunan` int(11) DEFAULT NULL,
  `tahun` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuti`
--

INSERT INTO `cuti` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `jumlah_cuti_tahunan`, `tahun`, `type`) VALUES
(1, 'peripurnama', '2019-12-23 18:49:32', b'0', 'peripurnama', '2019-12-23 18:49:32', 9, 2019, 'Tetap'),
(2, 'peripurnama', '2020-01-08 17:52:18', b'0', 'peripurnama', '2020-01-08 17:52:18', 9, 2020, 'Kontrak'),
(3, 'peripurnama', '2020-01-08 17:54:14', b'0', 'peripurnama', '2020-01-08 17:54:14', 9, 2020, 'Tetap'),
(4, 'peripurnama', '2020-01-08 17:56:02', b'0', 'peripurnama', '2020-01-08 17:56:02', 9, 2021, 'Tetap');

-- --------------------------------------------------------

--
-- Table structure for table `departement`
--

CREATE TABLE `departement` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `deskripsi` longtext DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departement`
--

INSERT INTO `departement` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `deskripsi`, `nama`) VALUES
(1, 'SYSTEM', '2019-10-17 20:06:39', b'0', 'SYSTEM', '2019-10-17 20:06:44', 'Developer', 'Developer');

-- --------------------------------------------------------

--
-- Table structure for table `jabatan`
--

CREATE TABLE `jabatan` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `deskripsi` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `bagian_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jabatan`
--

INSERT INTO `jabatan` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `deskripsi`, `nama`, `bagian_id`) VALUES
(1, 'SYSTEM', '2019-10-17 20:08:55', b'0', 'SYSTEM', '2019-10-17 20:08:56', 'Junior Develover', 'Junior Develover', 1),
(2, 'SYSTEM', '2019-10-17 20:09:05', b'0', 'SYSTEM', '2019-10-17 20:09:07', 'Senior Developer', 'Senior Developer', 1),
(3, 'SYSTEM', '2019-10-17 20:09:05', b'0', 'SYSTEM', '2019-10-17 20:09:07', 'Web Developer', 'Web Developer', 1),
(4, 'SYSTEM', '2019-10-17 20:09:05', b'0', 'SYSTEM', '2019-10-17 20:09:07', 'Full Stack Web Developer', 'Full Stack Web Developer', 1),
(5, 'SYSTEM', '2019-10-17 20:09:05', b'0', 'SYSTEM', '2019-10-17 20:09:07', 'Quality Asurance', 'Quality Asurance', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `agama` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `kewarganegaraan` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `nik` varchar(16) NOT NULL,
  `npwp` varchar(255) DEFAULT NULL,
  `pendidikan_terakhir` varchar(255) DEFAULT NULL,
  `status_pekerjaan` varchar(255) DEFAULT NULL,
  `status_perkawinan` varchar(255) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `tanggal_masuk` date DEFAULT NULL,
  `telepon` varchar(255) DEFAULT NULL,
  `jabatan_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `agama`, `alamat`, `kewarganegaraan`, `nama`, `nik`, `npwp`, `pendidikan_terakhir`, `status_pekerjaan`, `status_perkawinan`, `tanggal_lahir`, `tanggal_masuk`, `telepon`, `jabatan_id`, `user_id`, `person_id`) VALUES
(1, 'peripurnama', '2019-12-23 18:47:54', b'0', 'peripurnama', '2019-12-23 18:48:40', 'Islam', 'Bandung', 'WNI', 'Putra', '4141241221', '34234234323342', 'SMP', 'Tetap', 'Belum menikah', '2019-12-14', '2019-12-28', '0897432432432', 4, 2, NULL),
(2, 'peripurnama', '2019-12-23 19:02:22', b'0', 'peripurnama', '2019-12-23 19:02:58', 'Islam', 'Bandung', 'WNI', 'Putri', '34324323233233', '89432432423432', 'SMP', 'Tetap', 'Belum menikah', '2019-12-28', '2019-12-14', '087423432432', 4, 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai_cuti`
--

CREATE TABLE `pegawai_cuti` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cuti_id` bigint(20) NOT NULL,
  `pegawai_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai_cuti`
--

INSERT INTO `pegawai_cuti` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `cuti_id`, `pegawai_id`) VALUES
(1, 'peripurnama', '2019-12-23 18:50:31', b'0', 'peripurnama', '2019-12-23 18:50:31', 1, 1),
(2, 'peripurnama', '2019-12-23 19:07:47', b'0', 'peripurnama', '2019-12-23 19:07:47', 1, 2),
(3, 'putra', '2020-01-08 18:17:19', b'0', 'putra', '2020-01-08 18:17:19', 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai_cuti_detail`
--

CREATE TABLE `pegawai_cuti_detail` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `approved` bit(1) DEFAULT NULL,
  `discarded` bit(1) DEFAULT NULL,
  `discarded_by` varchar(255) DEFAULT NULL,
  `sisa_cuti_tahunan` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tahun` int(11) DEFAULT NULL,
  `total_cuti` int(11) DEFAULT NULL,
  `pegawai_cuti_id` bigint(20) NOT NULL,
  `pengajuan_cuti_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai_cuti_detail`
--

INSERT INTO `pegawai_cuti_detail` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `approved`, `discarded`, `discarded_by`, `sisa_cuti_tahunan`, `status`, `tahun`, `total_cuti`, `pegawai_cuti_id`, `pengajuan_cuti_id`) VALUES
(1, 'putra', '2019-12-23 18:59:04', b'0', 'putra', '2019-12-23 19:10:03', b'1', b'0', NULL, 7, 'Disetujui', 2019, 2, 1, 1),
(2, 'putri', '2019-12-23 19:08:20', b'0', 'putri', '2019-12-23 19:08:20', b'0', b'0', NULL, 9, 'Menunggu Persetujuan', 2019, 2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `pengajuan_cuti`
--

CREATE TABLE `pengajuan_cuti` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `body` longtext DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `file_name` longtext DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total_cuti` int(11) DEFAULT NULL,
  `pegawai_id` bigint(20) NOT NULL,
  `approved` bit(1) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengajuan_cuti`
--

INSERT INTO `pengajuan_cuti` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `body`, `end_date`, `file_name`, `start_date`, `title`, `total_cuti`, `pegawai_id`, `approved`, `status`) VALUES
(1, 'putra', '2019-12-23 18:59:04', b'0', 'putra', '2019-12-23 18:59:04', 'Cuti nikah', '2019-12-26', 'http://res.cloudinary.com/iteacode/image/upload/v1577102342/djepati/23-12-2019-18-59-00-download.jpg', '2019-12-25', 'Cuti nikah', 2, 1, NULL, NULL),
(2, 'putri', '2019-12-23 19:08:20', b'0', 'putri', '2019-12-23 19:08:20', 'Cuti Test', '2019-12-26', 'http://res.cloudinary.com/iteacode/image/upload/v1577102899/djepati/23-12-2019-19-08-17-download.jpg', '2019-12-25', 'Cuti Test', 2, 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `agama` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `kewarganegaraan` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `nik` varchar(16) NOT NULL,
  `status_perkawinan` varchar(255) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `telepon` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `code` varchar(8) DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `deskripsi` longtext DEFAULT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `deskripsi`, `name`) VALUES
(1, 'SYSTEM', '2019-10-17 20:33:45', b'0', 'SYSTEM', '2019-10-17 20:33:47', 'ROLE_PIMPINAN', 'ROLE_PIMPINAN'),
(2, 'SYSTEM', '2019-10-17 20:33:45', b'0', 'SYSTEM', '2019-10-17 20:33:47', 'ROLE_HR', 'ROLE_HR'),
(3, 'SYSTEM', '2019-10-17 20:33:45', b'0', 'SYSTEM', '2019-10-17 20:33:47', 'ROLE_KARYAWAN', 'ROLE_KARYAWAN');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(200) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_by`, `created_date`, `deleted`, `last_modified_by`, `last_modified_date`, `email`, `password`, `username`, `person_id`) VALUES
(2, 'peripurnama', '2019-12-23 18:48:40', b'0', 'peripurnama', '2019-12-23 18:48:40', 'putra@gmail.com', '$2a$10$YEzygVXV0ZoBwGE1L6SrQuTipahECaEnrCUlwq3LPfRd9JiPORqeK', 'putra', NULL),
(3, 'peripurnama', '2019-12-23 19:02:58', b'0', 'putra', '2020-01-08 20:03:36', 'putri@mailnesia.com', '$2a$10$Cgr1fRoaoyvgf0maTJTNreo/LPnJD8dYO.6VXQ8sy9g7iDJoMlV5i', 'putri', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(2, 2),
(3, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bagian`
--
ALTER TABLE `bagian`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7msh05m3vyv4eb2j2mwijp1p2` (`departement_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cuti`
--
ALTER TABLE `cuti`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `departement`
--
ALTER TABLE `departement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfh7qpef50udegloqww4o5cbi5` (`bagian_id`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_te6sdll4v2m4pdr34lvj4s02g` (`nik`),
  ADD KEY `FKfqhtko3uv12dsru8c7aopln1v` (`jabatan_id`),
  ADD KEY `FKhuytts7lo6td9fsmclfhymwnq` (`user_id`),
  ADD KEY `FKj5odwd77oeebr6stg7b7x99u5` (`person_id`);

--
-- Indexes for table `pegawai_cuti`
--
ALTER TABLE `pegawai_cuti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn4eq9hi3t72hsj6fvc6vwca9r` (`cuti_id`),
  ADD KEY `FKdm0ajir4s5rtab8mo0nw3a5f5` (`pegawai_id`);

--
-- Indexes for table `pegawai_cuti_detail`
--
ALTER TABLE `pegawai_cuti_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3rdftpte1f2tmsj352qms31b8` (`pegawai_cuti_id`),
  ADD KEY `FKkria6lon8d2rx6ehdwlibfpwp` (`pengajuan_cuti_id`);

--
-- Indexes for table `pengajuan_cuti`
--
ALTER TABLE `pengajuan_cuti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq0a4cno58di8y3x5dqgy4941y` (`pegawai_id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_p67sbyfb5xbyuftmaovjyjt7w` (`nik`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_h3w5r1mx6d0e5c6um32dgyjej` (`code`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  ADD KEY `FKd21kkcigxa21xuby5i3va9ncs` (`person_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bagian`
--
ALTER TABLE `bagian`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cuti`
--
ALTER TABLE `cuti`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `departement`
--
ALTER TABLE `departement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `jabatan`
--
ALTER TABLE `jabatan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pegawai_cuti`
--
ALTER TABLE `pegawai_cuti`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pegawai_cuti_detail`
--
ALTER TABLE `pegawai_cuti_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pengajuan_cuti`
--
ALTER TABLE `pengajuan_cuti`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bagian`
--
ALTER TABLE `bagian`
  ADD CONSTRAINT `FK7msh05m3vyv4eb2j2mwijp1p2` FOREIGN KEY (`departement_id`) REFERENCES `departement` (`id`);

--
-- Constraints for table `jabatan`
--
ALTER TABLE `jabatan`
  ADD CONSTRAINT `FKfh7qpef50udegloqww4o5cbi5` FOREIGN KEY (`bagian_id`) REFERENCES `bagian` (`id`);

--
-- Constraints for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD CONSTRAINT `FKfqhtko3uv12dsru8c7aopln1v` FOREIGN KEY (`jabatan_id`) REFERENCES `jabatan` (`id`),
  ADD CONSTRAINT `FKhuytts7lo6td9fsmclfhymwnq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKj5odwd77oeebr6stg7b7x99u5` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`);

--
-- Constraints for table `pegawai_cuti`
--
ALTER TABLE `pegawai_cuti`
  ADD CONSTRAINT `FKdm0ajir4s5rtab8mo0nw3a5f5` FOREIGN KEY (`pegawai_id`) REFERENCES `pegawai` (`id`),
  ADD CONSTRAINT `FKn4eq9hi3t72hsj6fvc6vwca9r` FOREIGN KEY (`cuti_id`) REFERENCES `cuti` (`id`);

--
-- Constraints for table `pegawai_cuti_detail`
--
ALTER TABLE `pegawai_cuti_detail`
  ADD CONSTRAINT `FK3rdftpte1f2tmsj352qms31b8` FOREIGN KEY (`pegawai_cuti_id`) REFERENCES `pegawai_cuti` (`id`),
  ADD CONSTRAINT `FKkria6lon8d2rx6ehdwlibfpwp` FOREIGN KEY (`pengajuan_cuti_id`) REFERENCES `pengajuan_cuti` (`id`);

--
-- Constraints for table `pengajuan_cuti`
--
ALTER TABLE `pengajuan_cuti`
  ADD CONSTRAINT `FKq0a4cno58di8y3x5dqgy4941y` FOREIGN KEY (`pegawai_id`) REFERENCES `pegawai` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKd21kkcigxa21xuby5i3va9ncs` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
