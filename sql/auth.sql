-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- 생성 시간: 20-12-22 09:56
-- 서버 버전: 10.4.14-MariaDB
-- PHP 버전: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `auth`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `role`
--

INSERT INTO `role` (`id`, `name`, `description`) VALUES
(1, 'ADMIN', 'Admin role'),
(2, 'USER', 'User role');

-- --------------------------------------------------------

--
-- 테이블 구조 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `user`
--

INSERT INTO `user` (`id`, `email`, `password`) VALUES
(5, 'alstmdsha@nate.com', '$2a$10$4SrvLVUK1DJp7dglkjfeQey9i0bcN9Fl4C9u2kuHsQ3gKczlSxAXm'),
(8, 'alstmdsha@smilegate.com', '$2a$10$1.CQaJUTKOCthDdE7reGQeQULdvh3BRMIbT/ViQtrh4gVAKDlRPJO'),
(9, 'alstmdsha@ee', '$2a$10$xN/C07oFgAeJLWMb1cDUwO0MRJgDtJCV/CgO5NMQ78Xki3yNJhVNK'),
(10, 'alstmdsha@gmaile.com', '$2a$10$kMHdHHJpp7VSEoGXZVn8B.by9txhGeoqeUL/O/JqtgmrlgB3ex4Cm'),
(11, 'alstmdsha@gmail.com', '$2a$10$h4mIE.wO/N.onmIY95H9V.d2TAL2YvQ2fijjrZ9B4T9VLKT3FJija'),
(12, 'alstmdsha@ggggg', '$2a$10$/YQU9bVcfuzUEEvLjo8vg.db6GkjLpRmiIH/3FMf.EzLHB4P4GxRm'),
(13, 'alstmdsha@gmail.com', '$2a$10$c131mqjXBuzLiDBXzA06XOaQBb8vujwfVPzCWsPk65Yz/qyrsJ/S2'),
(14, 'alstmdsha@abcd', '$2a$10$dgf2MXW4Vb64k18bhsy4cu7UfmhB3/6qLzpVC9AZKIXoCP/o/yggq'),
(15, 'alstmdsha@naver.com', '$2a$10$TpDlRo6nZFfYrZ0JevQ1SeMB2yBSL1InOHr7rdaK/d7ccDJUVx4uS');

-- --------------------------------------------------------

--
-- 테이블 구조 `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(11, 1),
(15, 1);

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- 테이블의 인덱스 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 테이블의 인덱스 `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `user` (`user_id`),
  ADD KEY `role` (`role_id`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 테이블의 AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- 덤프된 테이블의 제약사항
--

--
-- 테이블의 제약사항 `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
