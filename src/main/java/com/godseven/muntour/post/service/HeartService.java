package com.godseven.muntour.post.service;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.dto.HeartDto;
import com.godseven.muntour.post.entity.Heart;
import com.godseven.muntour.post.entity.Posts;
import com.godseven.muntour.post.repository.HeartRepository;
import com.godseven.muntour.post.repository.MemberRepository;
import com.godseven.muntour.post.repository.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addHeart(HeartDto heartDto) {
        Member member = memberRepository.findById(heartDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Posts posts = postsRepository.findById(heartDto.getPostsId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        Optional<Heart> existingHeart = heartRepository.findByMemberAndPosts(member, posts);
        if (existingHeart.isPresent()) {
            throw new IllegalStateException("Already liked this post");
        }

        Heart heart = new Heart(member, posts);
        heartRepository.save(heart);
    }

    @Transactional
    public void removeHeart(HeartDto heartDto) {
        Member member = memberRepository.findById(heartDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Posts posts = postsRepository.findById(heartDto.getPostsId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        Heart heart = heartRepository.findByMemberAndPosts(member, posts)
                .orElseThrow(() -> new IllegalArgumentException("No like found for this post"));

        heartRepository.delete(heart);
    }

    @Transactional
    public long countHearts(Long postsId) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        return heartRepository.countByPosts(posts);
    }
}