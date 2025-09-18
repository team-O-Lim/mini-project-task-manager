package org.example.o_lim.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.comment.request.CommentRequestDto;
import org.example.o_lim.dto.comment.response.CommentResponseDto;
import org.example.o_lim.entity.Comment;
import org.example.o_lim.repository.CommentRepository;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto<CommentResponseDto> createComment(UserPrincipal principal, CommentRequestDto request,Long taskId) {

        Comment comment = commentRepository.findByTaskId(taskId)
                .orElseThrow(() -> new EntityNotFoundException("해당 TaskId가 없습니다." + taskId));

        Comment saved = commentRepository.save(comment);

        CommentResponseDto data = null;

        data = CommentResponseDto.from(saved);

        return ResponseDto.setSuccess("댓글이 등록되었습니다.", data);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto<CommentResponseDto> deleteComment(UserPrincipal principal, Long taskId, Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 TaskId가 없습니다." + commentId));

        comment.delete(comment);

        return ResponseDto.setSuccess("댓글이 삭제되었습니다.", null);
    }
}
