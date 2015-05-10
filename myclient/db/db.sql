-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.42 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 test.tb_ad 结构
CREATE TABLE IF NOT EXISTS `tb_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_id` int(11) NOT NULL DEFAULT '0',
  `ad_name` varchar(200) DEFAULT NULL,
  `ad_desc` varchar(200) DEFAULT NULL,
  `req_num` bigint(20) DEFAULT NULL,
  `ad_content` varchar(500) DEFAULT NULL,
  `ad_res_num` bigint(20) DEFAULT NULL,
  `customer_name` varchar(200) DEFAULT NULL,
  `ad_addr_name` varchar(200) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 正在导出表  test.tb_ad 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_ad` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ad` ENABLE KEYS */;


-- 导出  表 test.tb_ad_accept 结构
CREATE TABLE IF NOT EXISTS `tb_ad_accept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_name` varchar(200) NOT NULL DEFAULT '0',
  `ad_addr` varchar(200) NOT NULL DEFAULT '0',
  `ad_url` varchar(200) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 正在导出表  test.tb_ad_accept 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_ad_accept` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ad_accept` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
