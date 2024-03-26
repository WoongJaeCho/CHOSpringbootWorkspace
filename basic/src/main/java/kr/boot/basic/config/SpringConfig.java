package kr.boot.basic.config;

import jakarta.persistence.EntityManager;
import kr.boot.basic.repository.JpaMemberRepository;
import kr.boot.basic.repository.MemberRepository;
import kr.boot.basic.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

// 우리가 직접 @Repositroy , @Service -> 빈 생성
@RequiredArgsConstructor
//@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;
    @Bean // -> @Repository
    public MemberRepository memberRepository(){

       // return new MemoryMemberRepository();
       // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    @Bean
   public MemberService memberService(MemberRepository memberRepository){
        return null; //new MemberService(memberRepository);
    }

}
