package kr.study.jpa1.service;


import kr.study.jpa1.domain.Member;
import kr.study.jpa1.repository.MemberJpaRepositry;
import kr.study.jpa1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용 트렌젝셔널 -> sql 저장소 빠진거
public class MemberService {

    private final MemberJpaRepositry memberRepository;
    @Transactional // 디폴트 => 읽기, 쓰기(삭제, 수정)
    public Long join(Member member) throws IllegalStateException {

            validDuplicateMember(member);
            Member m = memberRepository.save(member);
            log.trace( "savedmember = {} ",m );
            return m.getId();
    }

    public Member findByLoginId(String loginId){
        log.trace("LoginId = {}",loginId);
        Member member = memberRepository.findByLoginId(loginId);
        log.trace("member = {}",member);
        return member;
    }

    private void validDuplicateMember(Member member) throws IllegalStateException{
        if(memberRepository.findByLoginId(member.getLoginId()) != null){

            throw new IllegalStateException("이미 존재하는 아이디가 있습니다.");
        }
    }

    public List<Member> getList(){
        return memberRepository.findAll();
    }

    @Transactional
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
