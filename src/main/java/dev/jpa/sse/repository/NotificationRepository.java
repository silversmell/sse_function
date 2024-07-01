package dev.jpa.sse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.jpa.sse.entity.NotificationVO;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationVO, Long> {
    Optional<NotificationVO> findById(Long id);
}