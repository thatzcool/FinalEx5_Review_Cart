package com.ssg.finalex5_review_cart.member.repository;

import com.ssg.finalex5_review_cart.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}