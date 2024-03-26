package kr.study.jpa1.repository;

import kr.study.jpa1.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository{
    public Member save(Member member);
    List<Member> findAll();
    Member findById(long id);
    Member findByLoginId(String loginId);
    void deleteById(Long id);
}
