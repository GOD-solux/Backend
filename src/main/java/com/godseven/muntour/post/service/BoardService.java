package com.godseven.muntour.post.service;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.dto.BoardDto;
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
    //태그 만들면서 추가된 것
    private final TagRepository tagRepository;
    private final TagMappingRepository tagMappingRepository;

    //전체 게시물 조회
    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public List<BoardDto> getBoards(){
        List<Board> boards= boardRepository.findAll();
        List<BoardDto> boardDtos=new ArrayList<>();
        boards.forEach(s-> boardDtos.add(BoardDto.toDto(s)));
        return boardDtos;
    }

    //개별 게시물 조회
    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public BoardDto getBoard(int id){
        Board board= boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다.");
        });
        BoardDto boardDto =BoardDto.toDto(board);
        return boardDto;
    }

    //게시물 작성
    @org.springframework.transaction.annotation.Transactional
    //member
    //태그 구현을 위해 List<String> tags 추가
    public BoardDto write(BoardDto boardDto, Member member, List<String> tags){
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setMember(member);
        boardRepository.save(board);

        //태그 추가
        addTagsToBoard(board,tags);

        return BoardDto.toDto(board);
    }

    //게시물 수정
    @Transactional
    //태그 구현을 위해 List<String> tags 추가
    public BoardDto update(int id, BoardDto boardDto, List<String> tags){
        Board board=boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다!");
        });
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        //태그 관련> 기존 태그 삭제 후 새 태그
        tagMappingRepository.deleteAll(board.getTagMappings());
        addTagsToBoard(board, tags);

        return BoardDto.toDto(board);
    }

    //게시글 삭제
    @Transactional
    public void delete(int id){
        Board board=boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다!");
        });
        //게시글이 있는 경우 삭제 처리
        boardRepository.deleteById(id);
    }

    // 게시글 검색
    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public List<BoardDto> searchBoards(String keyword) {
        List<Board> boards = boardRepository.searchByTitleOrContent(keyword);
        List<BoardDto> boardDtos = new ArrayList<>();
        boards.forEach(board -> boardDtos.add(BoardDto.toDto(board)));
        return boardDtos;
    }

    //태그 관련?
    private void addTagsToBoard(Board board, List<String> tags) {
        for (String tagWord : tags) {
            Tag tag = tagRepository.findByWord(tagWord).orElseGet(() -> {
                Tag newTag = Tag.create(tagWord); // 정적 팩토리 메서드 사용
                return tagRepository.save(newTag);
            });
            TagMapping tagMapping = TagMapping.create(board, tag); // 정적 팩토리 메서드 사용
            tagMappingRepository.save(tagMapping);
        }
    }
}
