-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 25, 2024 at 04:23 PM
-- Server version: 8.0.31
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university`
--

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(250) DEFAULT NULL,
  `full_name` varchar(250) DEFAULT NULL,
  `gender` varchar(250) DEFAULT NULL,
  `birthday_date` date DEFAULT NULL,
  `year` varchar(250) DEFAULT NULL,
  `course` varchar(250) DEFAULT NULL,
  `section` varchar(250) DEFAULT NULL,
  `payement` double DEFAULT NULL,
  `total_payement` double DEFAULT NULL,
  `status_payement` int DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date_insert` date DEFAULT NULL,
  `date_update` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `student_id`, `full_name`, `gender`, `birthday_date`, `year`, `course`, `section`, `payement`, `total_payement`, `status_payement`, `image`, `date_insert`, `date_update`, `date_delete`, `status`) VALUES
(1, '20241', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2024-07-13', NULL, NULL, 'Approval'),
(2, '20242', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2024-07-13', NULL, NULL, 'Approval');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` int DEFAULT NULL,
  `full_name` varchar(250) DEFAULT NULL,
  `gender` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `year_experience` varchar(250) DEFAULT NULL,
  `departement` varchar(250) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `salary_status` varchar(250) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date_insert` date DEFAULT NULL,
  `date_update` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `password`, `role`, `date`, `student_id`, `teacher_id`, `salt`) VALUES
(1, NULL, 'Admin', 'y/eHBzhMhWLqPaU/pqj05g==', 'Admin', '2024-07-13', NULL, NULL, '7yA6B32X9EydPHlkbWkORQ=='),
(2, 'qdsds', 'qdsqdsqd', 'S0PFWc22PMnCoDulexmXYQ==', 'Student', '2024-07-13', '20241', NULL, '6QwzAcPJY02JFqrLYsDA0A=='),
(3, 'zzzz', 'zzzzz', 'aQZRrTRwhIMHe9GMexRfAw==', 'Student', '2024-07-13', '20242', NULL, 'AUCjTSoDhUarTna+XgPfUw==');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
