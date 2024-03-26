package kr.study.jpa1.repository;

import jakarta.persistence.EntityManager;
import kr.study.jpa1.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository @Slf4j
@RequiredArgsConstructor
public class MemberJpaRepositry implements MemberRepository{

    private final EntityManager em;

//    @Autowired  --> @RequiredArgsConstructor
//    public MemberJpaRepositry(EntityManager em){
//        this.em = em;
//    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Member findById(long id) {
        return null;
    }

    @Override
    public Member findByLoginId(String loginId) {
        List<Member> result = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();

        return result.stream().findAny().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        int delCnt = em.createQuery("delete from Member m where m.id=:id")
                .setParameter("id",id).executeUpdate();
        if(delCnt == 0){
            log.error("msg={}","삭제실패");
        }
    }


}
