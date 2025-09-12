# mini-project-task-manager #

# 1. 스키마 생상 (이미 존재하면 삭제)
DROP DATABASE IF EXISTS `mini-project-task-manager-db`;


CREATE DATABASE IF NOT EXISTS `mini-project-task-manager-db`
	CHARACTER SET utf8mb4
	COLLATE utf8mb4_general_ci;
    
use `mini-project-task-manager-db`;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS task_assignees;
DROP TABLE IF EXISTS task_tag;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;

# user 테이블 생성
CREATE TABLE IF NOT EXISTS `users` (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login_id VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  nickname VARCHAR(50) NOT NULL,
  gender VARCHAR(10),
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT `uk_users_login_id` UNIQUE (login_id),
  CONSTRAINT `uk_users_email` UNIQUE (email),
  CONSTRAINT `uk_users_nickname` UNIQUE (nickname),
  CONSTRAINT `chk_users_gender` CHECK(gender IN ('MALE', 'FEMALE'))
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '사용자';
  
# roles 테이블
  CREATE TABLE IF NOT EXISTS `roles` (
  role_name VARCHAR(30) PRIMARY KEY
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '권한 코드(USER, MANAGER, ADMIN)';
  
# user_roles 테이블
CREATE TABLE IF NOT EXISTS `user_roles` (
  user_id BIGINT NOT NULL,
  role_name VARCHAR(30) NOT NULL,
  PRIMARY KEY(user_id, role_name),
  CONSTRAINT `fk_user_roles_user` FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT `fk_user_roles_role` FOREIGN KEY (role_name) REFERENCES roles(role_name)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '사용자 권한 매핑';

# 프로젝트 생성
CREATE TABLE IF NOT EXISTS `projects` (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  admin_id BIGINT NOT NULL,
  title VARCHAR(150) NOT NULL,
  description VARCHAR(255) NOT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT fk_project_admin FOREIGN KEY (admin_id) REFERENCES users(id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '프로젝트';

# tags 테이블
CREATE TABLE IF NOT EXISTS `tags` (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  project_id BIGINT NOT NULL,
  name       VARCHAR(50) NOT NULL,
  color      VARCHAR(20) NOT NULL,
  CONSTRAINT `uk_tags_project_id_name` UNIQUE (project_id, name),
  CONSTRAINT `uk_tags_project_id_color` UNIQUE (project_id, color),
  CONSTRAINT `fk_tag_project` FOREIGN KEY (project_id) REFERENCES projects(id),
  INDEX `idx_tag_project` (project_id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '태그';
  
# tasks 테이블
# - 생성자: created_user
# - 담당자: assigned_user / 담당자가 여러 명일 경우 테이블 분리 필요! (task_assigned_users)
CREATE TABLE IF NOT EXISTS `tasks` (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  project_id   BIGINT NOT NULL,
  created_user BIGINT NOT NULL,
  title        VARCHAR(200) NOT NULL,
  content      LONGTEXT NOT NULL,
  status       VARCHAR(20) NOT NULL DEFAULT 'TODO',
  priority     VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
  due_date     DATE NULL,
  created_at   DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at   DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
	CONSTRAINT `chk_task_status` CHECK (status IN ('TODO','IN_PROGRESS','DONE')),
	CONSTRAINT `chk_task_priority` CHECK (priority IN ('LOW','MEDIUM','HIGH')),
	CONSTRAINT `fk_task_project`  FOREIGN KEY (project_id) REFERENCES projects(id),
	CONSTRAINT `fk_task_created_user` FOREIGN KEY (created_user) REFERENCES users(id),
	INDEX `idx_task_project_status` (project_id, status)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '직무'; -- 규격 통일
  
# task_assignees 테이블
  CREATE TABLE IF NOT EXISTS `task_assignees` (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_id BIGINT NOT NULL,
	assignee_id BIGINT,
    CONSTRAINT `uk_task_assignees_task_id_assignee_id` UNIQUE (task_id, assignee_id),
	CONSTRAINT `fk_task_assignees_task` FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
	CONSTRAINT `fk_task_assignees_assignee`  FOREIGN KEY (assignee_id)  REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '직무 담당자 지정';
  
# task_tag 테이블
CREATE TABLE IF NOT EXISTS `task_tag` (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_id BIGINT NOT NULL,
    tag_id BIGINT,
    CONSTRAINT `uk_task_tag_task_id_tag_id` UNIQUE (task_id, tag_id),
    CONSTRAINT `fk_task_tag_task` FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    CONSTRAINT `fk_task_tag_tag` FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '직무 태그 지정';

# comments 테이블
CREATE TABLE IF NOT EXISTS `comments` (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  task_id    BIGINT NOT NULL,
  author_id  BIGINT NOT NULL,
  content    TEXT NOT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
	CONSTRAINT `fk_comment_task`   FOREIGN KEY (task_id)   REFERENCES tasks(id) ON DELETE CASCADE,
	CONSTRAINT `fk_comment_author` FOREIGN KEY (author_id) REFERENCES users(id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '직무 코멘트';

# notification 테이블
CREATE TABLE `notifications` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,  
    project_id BIGINT NOT NULL,
    title VARCHAR(50) NOT NULL,                         
    content LONGTEXT NOT NULL,                                
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),               
	CONSTRAINT `fk_notification_project_id` FOREIGN KEY (project_id) REFERENCES projects(id)  
  )ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '프로젝트 공지사항'; 

    
    
    
    
    