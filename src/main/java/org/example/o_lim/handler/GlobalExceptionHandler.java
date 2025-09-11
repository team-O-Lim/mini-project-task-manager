//package org.example.o_lim.handler;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.example.o_lim.common.enums.ErrorCode;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authorization.AuthorizationDeniedException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.naming.AuthenticationException;
//import java.nio.file.AccessDeniedException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@RestControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler {
//
//    private ResponseEntity<ResponseDto<Object>> fail(
//            ErrorCode code, String reason, List<FieldError> errors
//
//    ) {
//
//        String finalReason = (reason != null && !reason.isBlank()) ? reason : code.defaultMessage;
//
//        ErrorResponse body = ErrorResponse.of(code.code, finalReason, errors);
//        return ResponseEntity.status(code.status)
//                .body(ResponseDto.setFailed(finalReason, body));
//
//   }
//   private List<FieldError> toFieldErrors(MethodArgumentNotValidException e) {
//    List<FieldError> list = new ArrayList<>();
//
//    e.getBindingResult().getFieldErrors().forEach(err -> {
//        list.add(new FieldError(
//                err.getField(),
//                Objects.toString(err.getRejectedValue(), "null"),
//                err.getDefaultMessage()
//        ));
//    });
//
//    e.getBindingResult().getGlobalErrors().forEach(err -> {
//        list.add(new FieldError(err.getObjectName(), "", err.getDefaultMessage()));
//    });
//    return list;
//    }
//
//    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
//     public ResponseEntity<ResponseDto<Object>> handleBadRequest(Exception e) {
//    log.warn("Bad Request: {}", e.getMessage());
//    return fail(ErrorCode.BAD_REQUEST, null, null);
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseDto<Object>> handleValidation(MethodArgumentNotValidException e) {
//      log.warn("Validation failed: {}", e.getMessage());
//      return fail(ErrorCode.VALIDATION_ERROR, null, toFieldErrors(e));
//    }
//    @ExceptionHandler(AuthenticationException .class)
//    public ResponseEntity<ResponseDto<Object>> handleAuth(AuthenticationException e) {
//        log.warn("UnAuthorized: {}", e.getMessage());
//        return fail(ErrorCode.UNAUTHORIZED, null, null);
//    }
//
//    // === 403 Forbidden: 접근 거부 === //
//    @ExceptionHandler({ AccessDeniedException.class, AuthorizationDeniedException.class })
//    public ResponseEntity<ResponseDto<Object>> handleAccessDenied(AccessDeniedException e) {
//        log.warn("AccessDenied: {}", e.getMessage());
//        return fail(ErrorCode.FORBIDDEN, null, null);
//    }
//
//    // === 404 Not Found: 엔티티 조회 실패 === //
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ResponseDto<Object>> handleNotFound(EntityNotFoundException e) {
//        log.warn("Not Found: {}", e.getMessage());
//        return fail(ErrorCode.NOT_FOUND, null, null);
//    }
//
//    // === 409 Conflict: 무결성 위반(중복/제약조건) === //
//    @ExceptionHandler(DataIntegrityViolationException.class) // Unique 키 충돌, FK 위반 등
//    public ResponseEntity<ResponseDto<Object>> handleConflict(DataIntegrityViolationException e) {
//        log.warn("Conflict: {}", e.getMessage());
//        return fail(ErrorCode.CONFLICT, null, null);
//    }
//
//    // === 500 Internal Server Error: 그 밖의 모든 예외에 대한 최종 안정망 === //
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseDto<Object>> handleException(Exception e) {
//        log.error("Internal error", e);
//        return fail(ErrorCode.INTERNAL_ERROR, null, null);
//    }
//}

