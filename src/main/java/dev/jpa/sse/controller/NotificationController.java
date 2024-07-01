package dev.jpa.sse.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import dev.jpa.sse.service.NotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationController {
	@Autowired
    private NotificationService notificationService;
	
    public static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();		// 1. 모든 Emitters를 저장하는 ConcurrentHashMap

    // 메시지 알림
    @GetMapping("/api/notification/subscribe/{id}") //이곳으로 연결됨
    public SseEmitter subscribe(@PathVariable("id") String id) {
        SseEmitter sseEmitter = notificationService.subscribe(id);

        return sseEmitter;
    }
    @GetMapping("/api/notification/comment/{scmtno}")
    public void comment(@PathVariable("scmtno") int scmono) {
    	notificationService.notifyComment(scmono);
    	
    }
}