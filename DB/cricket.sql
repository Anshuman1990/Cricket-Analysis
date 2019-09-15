/*
MySQL Data Transfer
Source Host: localhost
Source Database: cricket
Target Host: localhost
Target Database: cricket
Date: 16/05/2017 00:31:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `email` varchar(1000) DEFAULT NULL,
  `password` varchar(1000) DEFAULT NULL,
  `status` varchar(1000) DEFAULT NULL,
  `utype` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for match_analysis
-- ----------------------------
DROP TABLE IF EXISTS `match_analysis`;
CREATE TABLE `match_analysis` (
  `City` varchar(1000) DEFAULT NULL,
  `winner` varchar(1000) DEFAULT NULL,
  `POTM` varchar(1000) DEFAULT NULL,
  `venue` varchar(1000) DEFAULT NULL,
  `teams` varchar(1000) DEFAULT NULL,
  `team` varchar(1000) DEFAULT NULL,
  `over` varchar(1000) DEFAULT NULL,
  `ball` varchar(1000) DEFAULT NULL,
  `batsman` varchar(1000) DEFAULT NULL,
  `bowler` varchar(1000) DEFAULT NULL,
  `non_striker` varchar(1000) DEFAULT NULL,
  `runs_scored` varchar(1000) DEFAULT NULL,
  `player_out` varchar(1000) DEFAULT NULL,
  `kind` varchar(1000) DEFAULT NULL,
  `fielder` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register` (
  `fname` varchar(1000) DEFAULT NULL,
  `lname` varchar(1000) DEFAULT NULL,
  `email` varchar(1000) DEFAULT NULL,
  `phone` varchar(1000) DEFAULT NULL,
  `password` varchar(1000) DEFAULT NULL,
  `sta` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
