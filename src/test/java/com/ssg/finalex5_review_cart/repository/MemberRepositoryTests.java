package com.ssg.finalex5_review_cart.repository;

import com.ssg.finalex5_review_cart.member.entity.MemberEntity;
import com.ssg.finalex5_review_cart.member.exception.MemberExceptions;
import com.ssg.finalex5_review_cart.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest

public class MemberRepositoryTests {


    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void testInsert() {

        for (int i = 1; i <= 100; i++) {

            MemberEntity memberEntity = MemberEntity.builder()
                    .mid("user" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .mname("USER" + i)
                    .email("user" + i + "@ssg.com")
                    .role(i <= 80 ? "USER" : "ADMIN")
                    .build();


            memberRepository.save(memberEntity);

        }//end for

    }


    @Test
    public void testRead() {

        String mid = "user1";

        Optional<MemberEntity> result = memberRepository.findById(mid);

        MemberEntity memberEntity =
                result.orElseThrow(MemberExceptions.NOT_FOUND::get);

        System.out.println(memberEntity);
    }


    @Test
    @Transactional
    @Commit
    public void testUpdate() {
        String mid = "user1";

        Optional<MemberEntity> result = memberRepository.findById(mid);

        MemberEntity memberEntity =
                result.orElseThrow(MemberExceptions.NOT_FOUND::get);

        memberEntity.changePassword(passwordEncoder.encode("2222"));
        memberEntity.changeName("USER1-1");

    }

    @Test
    @Transactional
    @Commit


    public void testDelete() {

        String mid = "user1";

        Optional<MemberEntity> result = memberRepository.findById(mid);

        MemberEntity memberEntity =
                result.orElseThrow(() -> MemberExceptions.NOT_FOUND.get());

        memberRepository.delete(memberEntity);
    }


}