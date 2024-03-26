package kr.study.jpa1.service;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecode;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.repository.MemberRepository;
import kr.study.jpa1.repository.StudyRecodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyRecodeService {

    private final MemberRepository memberRepository;
    private final StudyRecodeRepository recodeRepository;
    @Transactional
    public void saveRecord(StudyForm form, Member member){
        StudyRecode recode = StudyRecode.createRecord(form,member);
        recodeRepository.save(recode);
    }
    public List<StudyRecode> getAllRecodes(){
        return recodeRepository.selectAll();
    }

    public StudyRecode getOneRecode(Long id){
        Optional<StudyRecode> recode = recodeRepository.findById(id);
        return recode.isPresent()? recode.get() : null;
    }
    @Transactional
    public void deleteRecode(Long id){
        recodeRepository.deleteById(id);
    }
    @Transactional
    public void updateRecord(StudyForm form, StudyRecode recode){
        StudyRecode updateRecode = StudyRecode.modyfyRecord(form,recode);
        recodeRepository.save(updateRecode);
    }
    @Transactional
    public void deleteAllRecordByMember(Member member){
        List<StudyRecode> list = recodeRepository.searchStudyRecodesBymemberId(member.getId());
        if(list != null){
            list.forEach(recode->{
                log.trace("delete recode = {}",recode);
                recodeRepository.deletebyMember(recode.getMember());
            });
        }
    }
}
