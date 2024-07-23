package com.godseven.muntour.post.repository;

import com.godseven.muntour.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
