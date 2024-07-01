package dev.jpa.sse.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.jpa.sse.entity.NotificationVO;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationVO, Long> {
    Optional<NotificationVO> findById(Long id);
    
    @Query(value = "SELECT contents, sender FROM (SELECT contents, sender, rownum as r FROM (SELECT contents, sender, id FROM notification WHERE sender = :sender ORDER BY created_at DESC, id DESC)) WHERE r <= 100", nativeQuery = true)
    List<NotificationVO> findBySenderDesc(@Param("sender") String sender);

}