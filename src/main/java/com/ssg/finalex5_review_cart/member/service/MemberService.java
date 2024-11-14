package com.ssg.finalex5_review_cart.member.service;

import com.ssg.finalex5_review_cart.member.dto.MemberDTO;
import com.ssg.finalex5_review_cart.member.entity.MemberEntity;
import com.ssg.finalex5_review_cart.member.exception.MemberExceptions;
import com.ssg.finalex5_review_cart.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

         @Service
 @RequiredArgsConstructor
 @Log4j2
 @Transactional
 public class MemberService {

          private final MemberRepository memberRepository;

           private final PasswordEncoder passwordEncoder;

         public MemberDTO read(String mid, String mpw) {

            Optional<MemberEntity> result = memberRepository.findById(mid);

            MemberEntity memberEntity = result.orElseThrow(MemberExceptions.BAD_CREDENTIALS::get);

           if (!passwordEncoder.matches(mpw, memberEntity.getMpw())) {
                  throw MemberExceptions.BAD_CREDENTIALS.get();
              }

             MemberDTO memberDTO = new MemberDTO(memberEntity);
             return memberDTO;

           }

          public MemberDTO getByMid(String mid) {

          Optional<MemberEntity> result = memberRepository.findById(mid);

        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);

           return new MemberDTO(memberEntity);

           }


         }