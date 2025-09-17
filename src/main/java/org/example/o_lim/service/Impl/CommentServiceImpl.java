package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.comment.request.CommentRequestDto;
import org.example.o_lim.dto.comment.response.CommentResponseDto;
import org.example.o_lim.repository.CommentRepository;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.CommentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    @Override
    public ResponseDto<CommentResponseDto> createComment(UserPrincipal userPrincipal, CommentRequestDto req) {
        return null;
    }

    @Override
    public ResponseDto<CommentResponseDto> deleteComment(UserPrincipal userPrincipal, Long taskId, Long commentId) {
        return null;
    }

    @Override
    public ResponseDto<CommentResponseDto> getAllCommentByCreatedAtDesc(Long taskId) {
        return null;
    }
}
