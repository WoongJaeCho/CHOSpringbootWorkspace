package service;

import kr.boot.basic.domain.Member;
import kr.boot.basic.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {


    private final MemberRepository memberRepository;
    public MemberService(MemberRepository repository){
        this.memberRepository = repository;
    }

    //회원가입
    public void join(Member member){
        if(validatsDupilicateMember(member)) {
            memberRepository.save(member);
        } else {
            System.out.println("이미 존재하는 회원입니다.");
        }
    }

    //아이디 중복검사

    private boolean validatsDupilicateMember(Member member){
//        if(memberRepository.findById(Member.getid()) != null){
//            throw new IllegalArgumentException()
//        }
        //memberRepository.findById((member.getId())).ifPresent(m -> throw new IllegalArgumentException("이미 존재하는 회원 입니다." ));
        return memberRepository.findByName(member.getName()).isPresent();

    }
    // 전체회원
    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    //회원 한명 조회
   public Optional<Member> findOneMember(Long id){
        return memberRepository.findById(id);
   }
}
