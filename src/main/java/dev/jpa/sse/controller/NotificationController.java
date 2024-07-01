package dev.jpa.sse.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import dev.jpa.sse.service.AccountService;
import dev.jpa.sse.service.NotificationService;
import dev.jpa.sse.service.ReplyService;
import dev.jpa.sse.service.SconService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9093") // 허용할 도메인을 명시
public class NotificationController {
	@Autowired
    private NotificationService notificationService;
	
	@Autowired
	private SconService sconService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private AccountService accountService;
	
    public static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();		// 1. 모든 Emitters를 저장하는 ConcurrentHashMap

    // 메시지 알림
    @GetMapping("/api/notification/subscribe/{id}") //이곳으로 연결됨
    public SseEmitter subscribe(@PathVariable("id") String id) {
    	System.out.println("들어옴");
        SseEmitter sseEmitter = notificationService.subscribe(id);

        return sseEmitter;
    }
    @GetMapping("/api/notification/comment/{scmtno}")
    public ResponseEntity<Void>comment(@PathVariable("scmtno") int scmono) {
    	System.out.println("9093에서 호출");
    	
    	int scon_no=replyService.findByScmtno(scmono).get().getSconno();
    	int acc_no=sconService.findbySconno(scon_no).get().getAccno();
    	String acc_id = accountService.findByAccno(acc_no).get().getAccId();

    	notificationService.notifyComment(scmono);
    	return ResponseEntity.ok().build(); // 응답으로 빈 200 OK를 반환
    	
    }
}