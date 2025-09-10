# mini-project-task-manager #

# 1. 스키마 생상 (이미 존재하면 삭제)
DROP DATABASE IF EXISTS `mini-project-task-manager-db`;


CREATE DATABASE IF NOT EXISTS `mini-project-task-manager-db`
	CHARACTER SET utf8mb4
	COLLATE utf8mb4_general_ci;
use `mini-project-task-manager-db`;

# 1. 프로젝트 생성
CREATE TABLE IF NOT EXISTS `projects` (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	owner_id BIGINT NOT NULL,
  title VARCHAR(150) NOT NULL,
  description VARCHAR(255) NOT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT fk_project_owner FOREIGN KEY (owner_id) REFERENCES users(id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '프로젝트';

# tasks: 실질적 할 일
# - 생성자: created_user
# - 담당자: assigned_user / 담당자가 여러 명일 경우 테이블 분리 필요! (task_assigned_users)
CREATE TABLE `tasks` (
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
	CONSTRAINT chk_task_status CHECK (status IN ('TODO','IN_PROGRESS','DONE')),
	CONSTRAINT chk_task_priority CHECK (priority IN ('LOW','MEDIUM','HIGH')),
	CONSTRAINT fk_task_project  FOREIGN KEY (project_id) REFERENCES projects(id),
	CONSTRAINT fk_task_created_user FOREIGN KEY (created_user) REFERENCES users(id),
	INDEX idx_task_project_status (project_id, status)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '직무'; -- 규격 통일
  
  CREATE TABLE `task_assignees` (
	task_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	PRIMARY KEY (task_id, user_id),
	CONSTRAINT fk_auth_assigned FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
	CONSTRAINT fk_auth_users  FOREIGN KEY (user_id)  REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '직무 담당자 지정';

CREATE TABLE `tags` (
	id         BIGINT PRIMARY KEY AUTO_INCREMENT,
	project_id BIGINT NOT NULL,
	name       VARCHAR(50) NOT NULL,
	color      VARCHAR(20) NULL,
	UNIQUE (project_id, name),
	CONSTRAINT fk_tag_projeck FOREIGN KEY (project_id) REFERENCES projects(id),
	INDEX idx_tag_projeck (project_id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '태그';

CREATE TABLE `comments` (
	id         BIGINT PRIMARY KEY AUTO_INCREMENT,
	task_id    BIGINT NOT NULL,
	author_id  BIGINT NOT NULL,
	content    TEXT NOT NULL,
	created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
	CONSTRAINT fk_comment_task   FOREIGN KEY (task_id)   REFERENCES tasks(id) ON DELETE CASCADE,
	CONSTRAINT fk_comment_author FOREIGN KEY (author_id) REFERENCES users(id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '직무 코멘트';

CREATE TABLE IF NOT EXISTS `users` (
	id BIGINT NOT NULL AUTO_INCREMENT,
  login_id VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  nickname VARCHAR(50) NOT NULL,
  gender VARCHAR(10),
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (id),
  CONSTRAINT `uk_users_login_id` UNIQUE (login_id),
  CONSTRAINT `uk_users_email` UNIQUE (email),
  CONSTRAINT `uk_users_nickname` UNIQUE (nickname),
  CONSTRAINT `chk_users_gender` CHECK(gender IN ('MALE', 'FEMALE'))
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '사용자';

CREATE TABLE IF NOT EXISTS `user_roles` (
	user_id BIGINT NOT NULL,
    role VARCHAR(30) NOT NULL,

    CONSTRAINT fk_user_roles_user
		FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
	CONSTRAINT uk_user_roles UNIQUE (user_id, role),
    
    CONSTRAINT chk_user_roles_role CHECK (role IN ('USER', 'ASSIGNEE', 'OWNER'))
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '사용자 권한';
  
  CREATE TABLE `notification` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,  
    project_id BIGINT NOT NULL,
    title VARCHAR(50) NOT NULL,                         
    content LONGTEXT NOT NULL,                                
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),               
	CONSTRAINT fk_notice_project FOREIGN KEY (project_id) REFERENCES projects(id)  
  )ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '프로젝트 공지사항'; 
  
    select * from projects;
    select * from tasks;
    select * from notification;
    select * from users;
    select * from user_roles;
    select * from tags;
    select * from comments;
    select * from task_assignees;
    
    
    
    
    