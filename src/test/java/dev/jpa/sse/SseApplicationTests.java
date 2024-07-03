package dev.jpa.sse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.intThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import dev.jpa.sse.entity.AccountVO;
import dev.jpa.sse.entity.NotificationVO;
import dev.jpa.sse.entity.ReplyVO;
import dev.jpa.sse.entity.Share_contentsVO;
import dev.jpa.sse.repository.AccountRepository;
import dev.jpa.sse.repository.NotificationRepository;
import dev.jpa.sse.repository.ReplyRepository;
import dev.jpa.sse.service.AccountService;
import dev.jpa.sse.service.NotificationService;
import dev.jpa.sse.service.ReplyService;
import dev.jpa.sse.service.SconService;
import jakarta.transaction.Transactional;

//@SpringBootTest
@DataJpaTest

class SseApplicationTests {
	
    @Autowired
    private TestEntityManager entityManager;

	@Test
	void contextLoads() {
	}
	
  @Autowired
    private SconService sconService;
  
  @Autowired
  private NotificationRepository noticeService;
  
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
    public void  testSaveAndRetrieveShare() {
//    	Optional<NotificationVO> notice = noticeService.findByAccount_AccnoAndCreatedAt(1, "2024-07-02 15:52:52");
//    	assertThat(notice).isPresent(); // Optional이 존재하는지 확인
//    	System.out.println(notice.get().getId());
//    	System.out.println(notice.isPresent());
    	
    	noticeService.deleteById(73L);
    }
    
//    public void testSaveAndRetrieveNorice() {
//        // Given
//        int scon_no = 15;
//
//        // When
//        List<NotificationVO> notifications = notiService.findBySharecontents_Sconno(scon_no);
//        
//        for(int i = 0;i<notifications.size();i++) {
//        	System.out.println(notifications.get(i).getAccount().getAccId() + " " +notifications.get(i).getSender());
//        }
//
//        // Then
//        assertThat(notifications).isNotNull();
//        assertThat(notifications).isNotEmpty();
//        assertThat(notifications).allMatch(notification -> notification.getSharecontents().getSconno() == scon_no);
//    }

}

