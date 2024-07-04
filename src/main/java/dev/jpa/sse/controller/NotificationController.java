package dev.jpa.sse.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import dev.jpa.sse.entity.NotificationVO;
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
    
//    @GetMapping("/api/notification/connect/{id}")
//    public String connect(@PathVariable("id") String id) {
//    	JSONObject json = new JSONObject();
//    	SseEmitter sseEmitter = notificationService.subscribe(id);
//    	
//    	json.put("emitter", sseEmitter);
//    	return json.toString();
//    }
    
    @GetMapping("/api/notification/comment/{scmtno}")
    public ResponseEntity<Void>comment(@PathVariable("scmtno") int scmono) {
    	System.out.println("9093에서 호출");
    	
    	int scon_no=replyService.findByScmtno(scmono).get().getSconno();
    	int acc_no=sconService.findbySconno(scon_no).get().getAccno();
    	String acc_id = accountService.findByAccno(acc_no).get().getAccId();

    	notificationService.notifyComment(scmono);
    	return ResponseEntity.ok().build(); // 응답으로 빈 200 OK를 반환
    	
    }
    @GetMapping("/api/notification/read/{acc_no}") //알림 게시판에 들어옴
    public String read(@PathVariable("acc_no") int acc_no) {
    	JSONObject json = new JSONObject();
    	JSONArray jsonArray = new JSONArray();
    	
    	//System.out.println("들어옴");
    	List<Object[]> list = notificationService.findNotificationDetailsByAccNo(acc_no);
    	
    	for (int i=0;i<list.size();i++) {
    		JSONObject jsr = new JSONObject();
    		  Object[] rowData = (Object[]) list.get(i); // 가정: list.get(i)가 객체 배열을 반환하는 경우
    		   String contents = (String) rowData[0]; // 첫 번째 컬럼(nt.contents)
    		    String sender = (String) rowData[1];   // 두 번째 컬럼(nt.sender)
    		    String createdAt = (String) rowData[2]; // 세 번째 컬럼(nt.created_at)
    		    int sconNo = Integer.parseInt(rowData[3].toString()); // 네 번째 컬럼(nt.scon_no)를 문자열로 가져와서 정수로 변환
    		    System.out.println(rowData[0]);
    		    jsr.put("contents", contents);
    		    jsr.put("sender", sender);
    		    jsr.put("create_At", createdAt);
    		    jsr.put("scon_no", sconNo);
    	    //System.out.println(row[0]);
    	    // 이후 처리 로직 작성
    	    System.out.println("Contents: " + contents);
    	    System.out.println("Sender: " + sender);
    	    System.out.println("CreatedAt: " + createdAt);
    	    System.out.println("Scon No: " + sconNo);
    	    jsonArray.put(jsr);
    	}
    	//System.out.println(jsonArray.get(0).toString());
       //System.out.println(jsonArray.get(1).toString());
    	json.put("res", jsonArray);
        return json.toString();
    }
    

//    
//    @GetMapping("/api/notification/read/{acc_no}")
//    public String read(@PathVariable("acc_no") String acc_no) {
//    	LIst
//    	JSONObject json = new JSONObject();
//    	return "";
//    	
//    }
    
}