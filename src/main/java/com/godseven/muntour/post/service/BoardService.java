package com.godseven.muntour.post.service;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.dto.PostDto;
import com.godseven.muntour.post.entity.Board;
import com.godseven.muntour.post.entity.Tag;
import com.godseven.muntour.post.entity.TagMapping;
import com.godseven.muntour.post.repository.BoardRepository;
import com.godseven.muntour.post.repository.TagMappingRepository;
import com.godseven.muntour.post.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final TagRepository tagRepository;
    private final TagMappingRepository tagMappingRepository;

    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public List<PostDto> getBoards(){
        List<Board> boards = boardRepository.findAll();
        List<PostDto> boardDtos = new ArrayList<>();
        boards.forEach(s -> boardDtos.add(PostDto.toDto(s)));
        return boardDtos;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public PostDto getBoard(int id){
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Board Id를 찾을 수 없습니다.")
        );
        return PostDto.toDto(board);
    }

    @Transactional
    public PostDto write(PostDto postDto, Member member){
        Board board = new Board();
        board.setTitle(postDto.getTitle());
        board.setContent(postDto.getContent());
        board.setCategory(postDto.getCategory());
        board.setMember(member);
        boardRepository.save(board);

        addTagsToBoard(board, postDto.getHashtag());

        return PostDto.toDto(board);
    }

    @Transactional
    public PostDto update(int id, PostDto postDto){
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Board Id를 찾을 수 없습니다!")
        );
        board.setTitle(postDto.getTitle());
        board.setContent(postDto.getContent());
        board.setCategory(postDto.getCategory());

        // 기존 태그 삭제 후 새 태그 추가
        tagMappingRepository.deleteAll(board.getTagMappings());
        addTagsToBoard(board, postDto.getHashtag());

        return PostDto.toDto(board);
    }

    @Transactional
    public void delete(int id){
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Board Id를 찾을 수 없습니다!")
        );
        boardRepository.deleteById(id);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public List<PostDto> searchBoards(String keyword) {
        List<Board> boards = boardRepository.searchByTitleOrContent(keyword);
        List<PostDto> boardDtos = new ArrayList<>();
        boards.forEach(board -> boardDtos.add(PostDto.toDto(board)));
        return boardDtos;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public List<PostDto> getCategoryBoards(String category){
        List<Board> boards = boardRepository.findByCategory(category);
        List<PostDto> boardDtos = new ArrayList<>();
        boards.forEach(s -> boardDtos.add(PostDto.toDto(s)));
        return boardDtos;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public List<PostDto> getHashtagBoards(String hashtag){
        List<Board> boards = boardRepository.findByHashtag(hashtag);
        List<PostDto> boardDtos = new ArrayList<>();
        boards.forEach(s -> boardDtos.add(PostDto.toDto(s)));
        return boardDtos;
    }

    private void addTagsToBoard(Board board, String tags) {
        if (tags != null && !tags.isEmpty()) {
            for (String tagWord : tags.split(",")) {
                Tag tag = tagRepository.findByWord(tagWord.trim()).orElseGet(() -> {
                    Tag newTag = Tag.create(tagWord.trim());
                    return tagRepository.save(newTag);
                });
                TagMapping tagMapping = TagMapping.create(board, tag);
                tagMappingRepository.save(tagMapping);
            }
        }
    }
}
