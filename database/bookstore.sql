-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2022 at 07:22 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `EmployeeID` int(11) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Roll` int(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`EmployeeID`, `UserName`, `Password`, `Roll`, `Status`) VALUES
(1, 'ngoccanh1', '5b8c287fb65f96cf9395a11c5b04df2b786bcf87', 0, 1),
(2, 'vietdung2', '430d8611aea4a4d5e6619e5c48d8f44277551697', 1, 1),
(3, 'lamtuan3', '99d16e88e62df038555277704b0e8d37ba2aa9de', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `AuthorID` int(11) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`AuthorID`, `LastName`, `FirstName`) VALUES
(1, 'Nguyễn Thành', 'Nhân'),
(2, 'Phan Tấn', 'Quốc'),
(3, 'Nguyễn Nhựt', 'Đông'),
(4, 'Nguyễn Nhật', 'Ánh'),
(5, 'Văn', 'Hữu'),
(6, 'Dale', 'Carnegie'),
(7, 'Nguyễn Xuân', 'Nam'),
(8, 'Tố', 'Hữu'),
(9, 'Miêu Công', 'Tử'),
(10, 'Nguyễn Lê', 'Nam'),
(11, 'Nhiều tác ', 'giả');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `BillID` int(11) NOT NULL,
  `Total` float NOT NULL,
  `Date` date NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`BillID`, `Total`, `Date`, `EmployeeID`, `CustomerID`) VALUES
(1, 465000, '2022-05-11', 1, 1),
(2, 264000, '2022-05-11', 1, 5),
(3, 668700, '2022-09-01', 2, 5),
(4, 455600, '2022-06-01', 1, 4),
(21, 351200, '2022-11-18', 1, 7),
(22, 124200, '2022-11-18', 1, 9),
(23, 186300, '2022-11-19', 2, 7);

-- --------------------------------------------------------

--
-- Table structure for table `billdetail`
--

CREATE TABLE `billdetail` (
  `BillID` int(11) NOT NULL,
  `BookID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `TotalPrice` float NOT NULL,
  `DiscountID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `billdetail`
--

INSERT INTO `billdetail` (`BillID`, `BookID`, `Quantity`, `Price`, `TotalPrice`, `DiscountID`) VALUES
(1, 1, 2, 75000, 150000, 0),
(1, 2, 2, 65000, 130000, 0),
(1, 5, 2, 60000, 120000, 0),
(2, 2, 3, 65000, 195000, 0),
(2, 3, 2, 12000, 24000, 0),
(2, 4, 2, 65000, 130000, 5),
(2, 8, 1, 45000, 45000, 0),
(3, 8, 2, 45000, 76500, 1),
(3, 9, 4, 36000, 144000, 0),
(4, 1, 1, 75000, 75000, 2),
(4, 3, 1, 12000, 12000, 3),
(4, 4, 1, 65000, 65000, 5),
(4, 5, 1, 60000, 60000, 7),
(4, 6, 4, 65000, 300000, 0),
(4, 7, 2, 56000, 89600, 2),
(4, 8, 1, 45000, 45000, 1),
(4, 10, 1, 18000, 18000, 1),
(4, 11, 1, 66000, 66000, 3),
(21, 2, 2, 69000, 124200, 11),
(21, 7, 2, 56000, 112000, 2),
(21, 8, 3, 45000, 135000, 0),
(22, 2, 2, 69000, 124200, 11),
(23, 2, 3, 69000, 186300, 11);

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `BookID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Price` float NOT NULL,
  `Quantity` int(11) NOT NULL,
  `BooktypeID` int(11) NOT NULL,
  `AuthorID` int(11) NOT NULL,
  `PublisherID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`BookID`, `Name`, `Price`, `Quantity`, `BooktypeID`, `AuthorID`, `PublisherID`) VALUES
(1, 'Điều kì diệu', 75000, 235, 2, 10, 6),
(2, 'Kỹ thuật lập trình', 69000, 73, 1, 3, 2),
(3, 'Ngữ văn 9', 12000, 11, 5, 3, 6),
(4, 'Tỷ phú bán giày', 65000, 45, 2, 6, 1),
(5, 'Miền đất hứa', 60000, 52, 2, 10, 8),
(6, 'Nghĩ giàu & Làm giàu', 75000, 25, 2, 5, 9),
(7, 'Khoa học máy tính', 56000, 67, 2, 7, 8),
(8, 'Cơ sở lập trình', 45000, 92, 9, 3, 3),
(9, 'Cô bé lọ lem', 36000, 50, 2, 5, 8),
(10, 'Nhà giả kim', 18000, 68, 6, 5, 2),
(11, 'Cuộc hành trình của Peter', 66000, 18, 6, 5, 2),
(12, 'Điều bình dị nhất', 50000, 48, 4, 5, 6),
(13, 'Tuổi trẻ đáng giá bao nhiêu', 65000, 20, 4, 7, 4),
(15, 'Lý thuyết ', 65000, 50, 4, 4, 7),
(19, 'Lý thuyết trò chơi', 64000, 5, 4, 7, 4),
(20, 'Cơ sở dữ liệu', 50000, 50, 1, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `booktype`
--

CREATE TABLE `booktype` (
  `BooktypeID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `booktype`
--

INSERT INTO `booktype` (`BooktypeID`, `Name`) VALUES
(1, 'Công nghệ thông tin'),
(2, 'Tiểu thuyết'),
(3, 'Chính trị'),
(4, 'Kỹ năng sống'),
(5, 'Sách giáo khoa'),
(6, 'Sách tham khảo'),
(7, 'Kinh tế'),
(8, 'Sách học ngoại ngữ'),
(9, 'Sách thiếu nhi'),
(10, 'Sách thanh thiếu niên');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CustomerID` int(11) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `Gender` enum('Nam','Nữ') NOT NULL,
  `Birthday` date NOT NULL,
  `PhoneNumber` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustomerID`, `LastName`, `FirstName`, `Gender`, `Birthday`, `PhoneNumber`) VALUES
(1, 'Nguyễn Thị ', 'Nữ', 'Nữ', '1981-05-05', '0356542589'),
(2, 'Lê Văn ', 'Khoa', 'Nam', '1990-05-10', '0891253698'),
(3, 'Lâm Trọng ', 'Nhân', 'Nữ', '2002-09-07', '0942194182'),
(4, 'Phạm Ngọc ', 'Thanh', 'Nam', '2000-10-17', '0357896235'),
(5, 'Hồ Thị ', 'Mai', 'Nữ', '2001-12-06', '0891562369'),
(6, 'Phạm Thị ', 'Như', 'Nữ', '2001-10-01', '0348961236'),
(7, 'Mai Anh ', 'Các', 'Nam', '2010-01-01', '0357892635'),
(8, 'Lê Thị ', 'Hoa', 'Nữ', '1970-10-05', '0341526985'),
(9, 'Trần Thị', 'Kiều', 'Nữ', '2002-05-19', '0351125896'),
(10, 'Lê Thị Hồng ', 'Thắm', 'Nữ', '2005-01-31', '0781258965'),
(11, 'Lê Hoàng', 'Phúc', 'Nam', '2001-10-20', '0891562369'),
(12, 'Đàm Mai', 'Trâm', 'Nữ', '2001-06-03', '0622952955'),
(13, 'Lê Huỳnh', 'Đức', 'Nam', '2000-01-30', '05616516'),
(14, 'Nguyễn Thị', 'Lan', 'Nam', '2001-02-09', '0956852146'),
(18, 'Nguyễn Thị', 'test', 'Nam', '2001-02-09', '0353315981'),
(19, 'Nguyễn', 'canhde', 'Nam', '2001-02-09', '0353315981'),
(20, 'Nguyễn', 'huy', 'Nam', '2001-02-09', '0353315981'),
(21, 'Nguyễn', 'cảnh', 'Nam', '2001-02-09', '0353315981'),
(22, 'Nguyễn', 'tèo', 'Nam', '2001-02-09', '0353315981'),
(23, 'Nguyễn Thị ', 'Ngân', 'Nam', '2001-03-13', '0353315981');

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `DiscountID` int(11) NOT NULL,
  `NameProgram` varchar(255) NOT NULL,
  `DateStart` date NOT NULL,
  `DateEnd` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`DiscountID`, `NameProgram`, `DateStart`, `DateEnd`) VALUES
(0, 'Không có', '2001-07-17', '2001-07-17'),
(1, 'Quốc khánh', '2022-09-01', '2022-09-02'),
(2, 'Quốc tế thiếu nhi', '2022-06-01', '2022-06-01'),
(3, 'Tết nguyên đán', '2022-02-13', '2022-04-23'),
(4, 'Giải phóng 30/4', '2022-04-29', '2021-04-30'),
(5, 'Quốc tế lao động', '2022-05-01', '2022-05-01'),
(7, 'Quốc tế lao động 2', '2022-05-01', '2022-05-01'),
(11, 'Nhà giáo việt nam', '2022-11-16', '2022-11-20'),
(16, 'Ngày hội sách việt nam', '2022-11-11', '2022-11-29');

-- --------------------------------------------------------

--
-- Table structure for table `discountdetail`
--

CREATE TABLE `discountdetail` (
  `DiscountID` int(11) NOT NULL,
  `BookID` int(11) NOT NULL,
  `Discount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `discountdetail`
--

INSERT INTO `discountdetail` (`DiscountID`, `BookID`, `Discount`) VALUES
(1, 2, 1),
(1, 8, 15),
(1, 10, 10),
(2, 1, 10),
(2, 2, 20),
(2, 7, 20),
(3, 3, 20),
(3, 9, 30),
(3, 11, 30),
(4, 8, 6),
(4, 10, 25),
(5, 4, 15),
(5, 12, 12),
(7, 2, 25),
(7, 5, 20),
(7, 10, 20),
(11, 2, 10),
(11, 3, 15),
(11, 4, 20),
(16, 2, 30);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EmployeeID` int(11) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `Gender` enum('Nam','Nữ') DEFAULT NULL,
  `Birthday` date NOT NULL,
  `PhoneNumber` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EmployeeID`, `LastName`, `FirstName`, `Gender`, `Birthday`, `PhoneNumber`) VALUES
(1, 'Nguyễn Ngọc', 'Cảnh', 'Nam', '1986-05-17', '0358890123'),
(2, 'Bùi Viết', 'Dũng', 'Nam', '1996-06-12', '0258695896'),
(3, 'Dịp Lâm', 'Tuấn', 'Nam', '1995-12-01', '0895680256'),
(4, 'Trần Kim', 'Phú', 'Nam', '1995-10-10', '0321250569'),
(5, 'Vương Đại', 'Nguyên', 'Nam', '1987-12-05', '0255625896'),
(6, 'Trần Thị', 'Lan', 'Nữ', '1999-12-06', '0356981269'),
(7, 'Trần Hạo', 'Nam', 'Nam', '1980-06-06', '0375691256'),
(8, 'Nguyễn Thị', 'Tâm', 'Nữ', '1990-12-01', '0384561263'),
(9, 'Trần Văn', 'Nhân', 'Nữ', '1985-05-12', '0791560263'),
(10, 'Nguyễn Thị', 'Thư', 'Nữ', '1996-10-06', '0347891562'),
(11, 'Nguyễn Thị', 'Huyền', 'Nữ', '1996-10-06', '0347891562'),
(13, 'Nguyễn Thị', 'Carnh', 'Nữ', '1996-10-06', '0347891562'),
(14, 'Nguyễn Thị', 'Canh', 'Nữ', '1996-10-06', '0347891562'),
(15, 'Nguyễn Thị', 'TèoEm', 'Nữ', '1996-10-06', '0347891562');

-- --------------------------------------------------------

--
-- Table structure for table `import`
--

CREATE TABLE `import` (
  `ImportID` int(11) NOT NULL,
  `Total` float NOT NULL,
  `Date` date NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `ProviderID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `import`
--

INSERT INTO `import` (`ImportID`, `Total`, `Date`, `EmployeeID`, `ProviderID`) VALUES
(1, 360000, '2021-04-13', 1, 3),
(2, 200000, '2021-05-05', 3, 4),
(3, 46000, '2021-02-15', 3, 9),
(7, 3572000, '2022-11-18', 1, 2),
(8, 600000, '2022-11-18', 1, 5),
(9, 250000, '2022-11-18', 3, 4),
(11, 2000000, '2022-11-19', 3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `importdetail`
--

CREATE TABLE `importdetail` (
  `ImportID` int(11) NOT NULL,
  `BookID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` float NOT NULL,
  `TotalPrice` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `importdetail`
--

INSERT INTO `importdetail` (`ImportID`, `BookID`, `Quantity`, `Price`, `TotalPrice`) VALUES
(1, 1, 2, 30000, 60000),
(1, 4, 10, 30000, 300000),
(2, 1, 1, 60000, 60000),
(2, 2, 2, 50000, 100000),
(2, 3, 1, 15000, 15000),
(2, 6, 5, 15000, 75000),
(3, 1, 2, 23000, 46000),
(7, 1, 20, 50000, 1000000),
(8, 2, 12, 50000, 600000),
(9, 19, 5, 50000, 250000),
(11, 20, 50, 40000, 2000000);

-- --------------------------------------------------------

--
-- Table structure for table `provider`
--

CREATE TABLE `provider` (
  `ProviderID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `PhoneNumber` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `provider`
--

INSERT INTO `provider` (`ProviderID`, `Name`, `Address`, `PhoneNumber`) VALUES
(1, 'AZ Việt Nam', '23A P. Yên Phụ, Q.Tây Hồ, Hà Nội', '0964484633'),
(2, 'Nhã Nam', '56B Trung Hòa, Cầu Giấy, Hà Nội', '0951256234'),
(3, 'Thái Nguyên Hà', '76D/K Phường 9, quận Phú Nhuận, HCM', '0282253264'),
(4, 'MCBooks', '3B12 Hiệp Bình Chánh, Thủ Đức, HCM', '0286660939'),
(5, 'NXB Giáo dục', '12B Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', '0243942201'),
(6, 'Đinh Tị', '14D P. Mai Động, Q. Hoàng Mai, Hà Nội', '0247309338'),
(7, 'Tân Việt', '7B5 Bạch Mai, Hai Bà Trưng, Hà Nội', '0243972810'),
(8, 'Minh Long', '12B/3 Minh Khai, Hai Bà Trưng, Hà Nội.', '0247300837'),
(9, 'NXB Kim Đồng', '16B/1 Nguyễn Du, Hai Bà Trưng, Hà Nội', '0293943449');

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

CREATE TABLE `publisher` (
  `PublisherID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `PhoneNumber` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `publisher`
--

INSERT INTO `publisher` (`PublisherID`, `Name`, `Address`, `PhoneNumber`) VALUES
(1, 'Nhà xuất bản Kim Đồng', 'Đống Đa, Hà Nội', '0283591263'),
(2, 'Đại Học Khoa học Tự Nhiên', 'Phường 4, Quận 5, TPHCM', '0283835202'),
(3, 'Đại Học Quốc Gia TPHCM', 'P. Linh Trung, Q. Thủ Đức, TP.HCM', '0372421815'),
(4, 'NXB Trẻ', 'Phường 7, Quận 3, TP. Hồ Chí Minh', '0283931628'),
(5, 'NXB Giáo Dục Việt Nam', 'Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', '0243942201'),
(6, 'NXB Lao động', '247 Cầu Giấy, Hà Nội', '0898986008'),
(7, 'NXB Văn học', 'Nguyễn Trường Tộ, Ba Đình, Hà Nội', '0283848348'),
(8, 'NXB Phụ Nữ', 'Hàng Chuối, Q. Hai Bà Trưng, Hà Nội', '0243971071'),
(9, 'NXB Việt', 'Lê Đại Hành, Hai Bà Trưng, Tp. Hà Nội', '0243726518');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`EmployeeID`);

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`AuthorID`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`BillID`),
  ADD KEY `HD_FK_MaKH` (`CustomerID`),
  ADD KEY `HD_FK_MaNV` (`EmployeeID`);

--
-- Indexes for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD PRIMARY KEY (`BillID`,`BookID`),
  ADD KEY `CTHD_FK_MaSach` (`BookID`),
  ADD KEY `CTHD_FK_MaKM` (`DiscountID`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`BookID`),
  ADD KEY `S_FK_MaTL` (`BooktypeID`),
  ADD KEY `S_FK_MaTG` (`AuthorID`),
  ADD KEY `S_FK_MaNXB` (`PublisherID`);

--
-- Indexes for table `booktype`
--
ALTER TABLE `booktype`
  ADD PRIMARY KEY (`BooktypeID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`DiscountID`);

--
-- Indexes for table `discountdetail`
--
ALTER TABLE `discountdetail`
  ADD PRIMARY KEY (`DiscountID`,`BookID`),
  ADD KEY `CTKM_FK_MaSach` (`BookID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EmployeeID`);

--
-- Indexes for table `import`
--
ALTER TABLE `import`
  ADD PRIMARY KEY (`ImportID`),
  ADD KEY `PN_FK_MaNV` (`EmployeeID`),
  ADD KEY `PN_FK_MaNCC` (`ProviderID`);

--
-- Indexes for table `importdetail`
--
ALTER TABLE `importdetail`
  ADD PRIMARY KEY (`ImportID`,`BookID`),
  ADD KEY `CTPN_FK_MaSach` (`BookID`);

--
-- Indexes for table `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`ProviderID`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`PublisherID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `AuthorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `BillID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `BookID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `booktype`
--
ALTER TABLE `booktype`
  MODIFY `BooktypeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `DiscountID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `discountdetail`
--
ALTER TABLE `discountdetail`
  MODIFY `DiscountID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `EmployeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `import`
--
ALTER TABLE `import`
  MODIFY `ImportID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `provider`
--
ALTER TABLE `provider`
  MODIFY `ProviderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `publisher`
--
ALTER TABLE `publisher`
  MODIFY `PublisherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `TK_FK_MaNV` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`);

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `HD_FK_MaKH` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  ADD CONSTRAINT `HD_FK_MaNV` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`);

--
-- Constraints for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD CONSTRAINT `CTHD_FK_MaHD` FOREIGN KEY (`BillID`) REFERENCES `bill` (`BillID`),
  ADD CONSTRAINT `CTHD_FK_MaKM` FOREIGN KEY (`DiscountID`) REFERENCES `discount` (`DiscountID`),
  ADD CONSTRAINT `CTHD_FK_MaSach` FOREIGN KEY (`BookID`) REFERENCES `book` (`BookID`);

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `S_FK_MaNXB` FOREIGN KEY (`PublisherID`) REFERENCES `publisher` (`PublisherID`),
  ADD CONSTRAINT `S_FK_MaTG` FOREIGN KEY (`AuthorID`) REFERENCES `author` (`AuthorID`),
  ADD CONSTRAINT `S_FK_MaTL` FOREIGN KEY (`BooktypeID`) REFERENCES `booktype` (`BooktypeID`);

--
-- Constraints for table `discountdetail`
--
ALTER TABLE `discountdetail`
  ADD CONSTRAINT `CTKM_FK_MaKM` FOREIGN KEY (`DiscountID`) REFERENCES `discount` (`DiscountID`),
  ADD CONSTRAINT `CTKM_FK_MaSach` FOREIGN KEY (`BookID`) REFERENCES `book` (`BookID`);

--
-- Constraints for table `import`
--
ALTER TABLE `import`
  ADD CONSTRAINT `PN_FK_MaNCC` FOREIGN KEY (`ProviderID`) REFERENCES `provider` (`ProviderID`),
  ADD CONSTRAINT `PN_FK_MaNV` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`);

--
-- Constraints for table `importdetail`
--
ALTER TABLE `importdetail`
  ADD CONSTRAINT `CTPN_FK_MaPN` FOREIGN KEY (`ImportID`) REFERENCES `import` (`ImportID`),
  ADD CONSTRAINT `CTPN_FK_MaSach` FOREIGN KEY (`BookID`) REFERENCES `book` (`BookID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
