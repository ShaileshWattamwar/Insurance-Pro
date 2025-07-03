package com.claim.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.notification.entity.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}
