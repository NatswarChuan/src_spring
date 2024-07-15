-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th7 15, 2024 lúc 01:44 AM
-- Phiên bản máy phục vụ: 8.0.31
-- Phiên bản PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `relationship`
--
CREATE DATABASE IF NOT EXISTS `relationship` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `relationship`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rls_classes`
--

DROP TABLE IF EXISTS `rls_classes`;
CREATE TABLE IF NOT EXISTS `rls_classes` (
  `class_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT (uuid()),
  `class_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `teacher_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Cắt ngắn bảng trước khi chèn `rls_classes`
--

TRUNCATE TABLE `rls_classes`;
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rls_logs`
--

DROP TABLE IF EXISTS `rls_logs`;
CREATE TABLE IF NOT EXISTS `rls_logs` (
  `log_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT (uuid()),
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `api` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `teacher_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Cắt ngắn bảng trước khi chèn `rls_logs`
--

TRUNCATE TABLE `rls_logs`;
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rls_students`
--

DROP TABLE IF EXISTS `rls_students`;
CREATE TABLE IF NOT EXISTS `rls_students` (
  `student_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT (uuid()),
  `student_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Cắt ngắn bảng trước khi chèn `rls_students`
--

TRUNCATE TABLE `rls_students`;
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rls_students_classes`
--

DROP TABLE IF EXISTS `rls_students_classes`;
CREATE TABLE IF NOT EXISTS `rls_students_classes` (
  `student_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `class_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`student_id`,`class_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Cắt ngắn bảng trước khi chèn `rls_students_classes`
--

TRUNCATE TABLE `rls_students_classes`;
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rls_teachers`
--

DROP TABLE IF EXISTS `rls_teachers`;
CREATE TABLE IF NOT EXISTS `rls_teachers` (
  `teacher_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT (uuid()),
  `teacher_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`teacher_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Cắt ngắn bảng trước khi chèn `rls_teachers`
--

TRUNCATE TABLE `rls_teachers`;
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rls_users`
--

DROP TABLE IF EXISTS `rls_users`;
CREATE TABLE IF NOT EXISTS `rls_users` (
  `user_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT (uuid()),
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `refresh_token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT (uuid()),
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Cắt ngắn bảng trước khi chèn `rls_users`
--

TRUNCATE TABLE `rls_users`;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
