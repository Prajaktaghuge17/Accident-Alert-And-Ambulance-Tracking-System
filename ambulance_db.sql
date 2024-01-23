-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2022 at 07:11 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ambulance_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `accident_details`
--

CREATE TABLE `accident_details` (
  `id` int(11) NOT NULL,
  `car_id` varchar(45) NOT NULL,
  `latitude` varchar(45) NOT NULL,
  `longitude` varchar(45) NOT NULL,
  `ambulance_id` varchar(45) NOT NULL DEFAULT '',
  `added_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `request_accepted` varchar(45) NOT NULL DEFAULT '',
  `accident_status` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accident_details`
--

INSERT INTO `accident_details` (`id`, `car_id`, `latitude`, `longitude`, `ambulance_id`, `added_on`, `request_accepted`, `accident_status`) VALUES
(1, 'dinesh@gmail.com', '18.5604735', '73.9277687', 'lifeline@gmail.com', '2022-04-30 06:14:37', '30/04/2022 05:02:39', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `sr` int(10) UNSIGNED NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `utype` varchar(45) NOT NULL,
  `status_` varchar(45) NOT NULL,
  `registeron` varchar(45) NOT NULL,
  `latitude` varchar(45) DEFAULT NULL,
  `longitude` varchar(45) DEFAULT NULL,
  `logged_in` varchar(45) DEFAULT NULL,
  `register_token` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`sr`, `username`, `password`, `email`, `mobile`, `address`, `utype`, `status_`, `registeron`, `latitude`, `longitude`, `logged_in`, `register_token`) VALUES
(1, 'Dinesh', '12', 'dinesh@gmail.com', '7350456969', 'pune', 'user', 'active', '30/04/2022 03:56:08', '18.5604735', '73.9277687', 'offline', 'fwKof8OfTRw:APA91bFOH7Y6GY_8XHjaN68zxOXZbSS1fIJkZOQ_D24oQCetjsX3_BgOteBu9kIccPjuk9DPBhJQaJg-poTD6iD5YJ2BDanR_VsPBpyJWRW5Jeby4-1C-HqEQRvob_1xTAe9cK3KtALB'),
(2, 'Lifeline', '12345', 'lifeline@gmail.com', '9503986854', 'Pune', 'service', 'active', '30/04/2022 03:57:15', '18.5604733', '73.927768', 'offline', 'fwKof8OfTRw:APA91bFOH7Y6GY_8XHjaN68zxOXZbSS1fIJkZOQ_D24oQCetjsX3_BgOteBu9kIccPjuk9DPBhJQaJg-poTD6iD5YJ2BDanR_VsPBpyJWRW5Jeby4-1C-HqEQRvob_1xTAe9cK3KtALB');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accident_details`
--
ALTER TABLE `accident_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`sr`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accident_details`
--
ALTER TABLE `accident_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `sr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
