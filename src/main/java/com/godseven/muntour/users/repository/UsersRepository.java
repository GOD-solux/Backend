package com.godseven.muntour.users.repository;

import com.godseven.muntour.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Member, Long> {

    boolean existsByMemberId(Long id);

    Optional<Member> findByMemberId(Long id);
}
