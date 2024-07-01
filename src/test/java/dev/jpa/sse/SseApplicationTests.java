package dev.jpa.sse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import dev.jpa.sse.entity.AccountVO;
import dev.jpa.sse.entity.NotificationVO;
import dev.jpa.sse.entity.ReplyVO;
import dev.jpa.sse.entity.Share_contentsVO;
import dev.jpa.sse.repository.AccountRepository;
import dev.jpa.sse.repository.ReplyRepository;
import dev.jpa.sse.service.AccountService;
import dev.jpa.sse.service.NotificationService;
import dev.jpa.sse.service.ReplyService;
import dev.jpa.sse.service.SconService;
import jakarta.transaction.Transactional;

@SpringBootTest
class SseApplicationTests {

	@Test
	void contextLoads() {
	}
	
  @Autowired
    private SconService sconService;
  
  @Autowired
  private NotificationService notiService;
  
  @Autowired
  private AccountService accountService;

    @Test
    @Transactional
    @Rollback(false)
//    public void testSaveAndRetrieveAccount() {
//    Optional<AccountVO> account = accountService.findByAccno(1);
//    assertThat(account).isPresent(); // Optional이 존재하는지 확인
//    //System.out.println(account.isPresent());
//    }

//    System.out.println(reply.isPresent());
//    
//    public void testSaveAndRetrieveReply() {
//    	System.out.println("test 들어옴");
//    List<ReplyVO> reply= replyService.getAll();
//    assertThat(reply).isNotEmpty();
//
//    }
//    public void  testSaveAndRetrieveShare() {
//    	Optional<Share_contentsVO> scon = sconService.findbySconno(15);
//    	assertThat(scon).isPresent(); // Optional이 존재하는지 확인
//    	//System.out.println(scon.isPresent());
//    }
    
    public void testSaveAndRetrieveNorice() {
    	String sender = "user1";
    	notiService.findBySenderDesc(sender);

    
    }

}

