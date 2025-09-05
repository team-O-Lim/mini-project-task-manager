# mini-project-task-manager #

# 1. 스키마 생상 (이미 존재하면 삭제)
DROP DATABASE IF EXISTS `mini-project-task-maneger-db`;


CREATE DATABASE IF NOT EXISTS `mini-project-task-maneger-db`
	CHARACTER SET utf8mb4
	COLLATE utf8mb4_general_ci;
    
    
# 1. 프로젝트 생성
CREATE TABLE IF NOT EXISTS `projects`(


)ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '프로젝트';


CREATE TABLE IF NOT EXISTS `tasks`(


)ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '프로젝트 작업';
  
  CREATE TABLE IF NOT EXISTS `comments`(


)ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '작업 코멘트';