CREATE DATABASE  IF NOT EXISTS `greatreads`;
GRANT ALL PRIVILEGES ON greatreads.* TO 'springstudent'@'%' IDENTIFIED BY 'springstudent';
USE `greatreads`;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;

CREATE TABLE `types` (
                            `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `name` varchar(45) DEFAULT NULL,
                            INDEX(name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `shelves`
--

DROP TABLE IF EXISTS `shelves`;

CREATE TABLE `shelves` (
                         `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `status` varchar(45) DEFAULT NULL,
                         INDEX(status)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
                           `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `title` varchar(45) DEFAULT NULL,
                           `author` varchar(45) DEFAULT NULL,
                           `rating` int(2) DEFAULT NULL,
                           `date_added` DATE DEFAULT NULL,
                           `date_finished` DATE DEFAULT NULL,
                           `shelves_id` int(4)  UNSIGNED NOT NULL,
                           `types_id` int(4)  UNSIGNED NOT NULL,
                           INDEX(title),
                           FOREIGN KEY (shelves_id) REFERENCES shelves(id),
                           FOREIGN KEY (types_id) REFERENCES types(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



