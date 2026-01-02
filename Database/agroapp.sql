-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2024 at 11:21 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agroapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `mobileno` varchar(100) NOT NULL,
  `payment` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `name`, `email`, `location`, `mobileno`, `payment`) VALUES
(1, 'Jayvardhan Gaikwad ', 'jay23@gmail.com', 'Kadlas ,Sangola', '9386579293', 'Cash on Delivery'),
(2, 'Suhani Pawar', 'suhanipawar2503@gmail.com', 'Solapur ', '9875375867', 'Online Payment'),
(3, 'Janhvi Ghodake ', 'janhavi06@gmail.com', 'Sangali', '9578976756', 'Cash on Delivery'),
(4, 'Suyash Pawar', 'suyash12@gmail.com', 'Sangola', '8976364748', 'Online Payment');

-- --------------------------------------------------------

--
-- Table structure for table `farmmachines`
--

CREATE TABLE `farmmachines` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `mrp` double NOT NULL,
  `pic` varchar(100) NOT NULL,
  `decription` varchar(1000) NOT NULL,
  `stock` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `fertilizers`
--

CREATE TABLE `fertilizers` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `mrp` double NOT NULL,
  `pic` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `stock` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fertilizers`
--

INSERT INTO `fertilizers` (`id`, `name`, `price`, `mrp`, `pic`, `description`, `stock`) VALUES
(1, 'Dap Fertilizer ', 378, 450, '66b46af173aaf.jpg', 'Dap Fertilizer ', 'In_Stock'),
(2, 'Capcicum Fertilizer ', 560, 700, '66b46c2a622c8.jpg', 'Fertilizer ', 'In_Stock');

-- --------------------------------------------------------

--
-- Table structure for table `fungicides`
--

CREATE TABLE `fungicides` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `mrp` double NOT NULL,
  `pic` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `stock` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fungicides`
--

INSERT INTO `fungicides` (`id`, `name`, `price`, `mrp`, `pic`, `description`, `stock`) VALUES
(1, 'Score Fungicide ', 550, 609, '66b46b1216f9d.jpg', 'Fungicide ', 'In_Stock');

-- --------------------------------------------------------

--
-- Table structure for table `herbicides`
--

CREATE TABLE `herbicides` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `mrp` double NOT NULL,
  `pic` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `stock` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `herbicides`
--

INSERT INTO `herbicides` (`id`, `name`, `price`, `mrp`, `pic`, `description`, `stock`) VALUES
(0, 'Krup Herbicide ', 345, 500, '66b46b320de3a.jpg', 'Herbicide  ', 'In_Stock');

-- --------------------------------------------------------

--
-- Table structure for table `pesticides`
--

CREATE TABLE `pesticides` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `mrp` double NOT NULL,
  `pic` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `stock` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pesticides`
--

INSERT INTO `pesticides` (`id`, `name`, `price`, `mrp`, `pic`, `description`, `stock`) VALUES
(2, 'Alloy', 250, 300, '66b46a64eb8a3.jpg', 'Alloy pesticide', 'In_Stock'),
(3, 'Benevia ', 670, 879, '66b46c55c762d.jpg', 'pesticide ', 'In_Stock');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `mrp` double NOT NULL,
  `pic` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `stock` varchar(100) NOT NULL DEFAULT 'In_Stock'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seeds`
--

CREATE TABLE `seeds` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `mrp` double NOT NULL,
  `pic` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `stock` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seeds`
--

INSERT INTO `seeds` (`id`, `name`, `price`, `mrp`, `pic`, `description`, `stock`) VALUES
(1, 'Syngenta Maka (4kg) seeds', 2265, 2500, '66922bb56a4f7.jpg', 'High yield potential, stability.', 'In_Stock'),
(2, 'Millet(Bajara) seeds', 480, 580, '66922c4b6a3eb.jpg', 'Hybrid Bajara Seeds', 'In_Stock'),
(3, 'White Jowar Seeds ', 299, 359, '66922d4de64f5.jpg', 'White Jowar Seeds ', 'In_Stock'),
(4, 'Combo Raw Flax Seeds and Pumpkin seeds', 249, 399, '66922dbc5a229.jpg', 'combo pack', 'In_Stock'),
(5, 'Seven Seeds', 80, 130, '66922e54ce78a.jpg', 'Seven Seeds ', 'In_Stock');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `mobileno` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `city`, `mobileno`, `password`) VALUES
(1, 'Suhani Pawar', 'suhanipawar2503@gmail.com', 'Sangola ', '9518932662', '2503'),
(2, 'Janhavi Ghodake', 'janhavi06@gmail.com', 'Solapur', '9847859621', '2504');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `farmmachines`
--
ALTER TABLE `farmmachines`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fertilizers`
--
ALTER TABLE `fertilizers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fungicides`
--
ALTER TABLE `fungicides`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pesticides`
--
ALTER TABLE `pesticides`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seeds`
--
ALTER TABLE `seeds`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `farmmachines`
--
ALTER TABLE `farmmachines`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `fertilizers`
--
ALTER TABLE `fertilizers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `fungicides`
--
ALTER TABLE `fungicides`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pesticides`
--
ALTER TABLE `pesticides`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `seeds`
--
ALTER TABLE `seeds`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
