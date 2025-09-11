package org.example.o_lim.repository;

import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository <Notification, Long> {
}
