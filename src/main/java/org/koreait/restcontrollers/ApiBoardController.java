package org.koreait.restcontrollers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.koreait.commons.rest.JSONResult;
import org.koreait.controllers.BoardForm;
import org.koreait.models.board.BoardSaveValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@RequestMapping("/api/board")
public class ApiBoardController {


    @PostMapping("/go")
    public ResponseEntity<Object> write() {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/error")
    public ResponseEntity<Object> error(@RequestBody BoardForm boardForm){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){

        JSONResult<Object> jsonResult = JSONResult.builder()
                .success(true)
                .errorMessage("삭제 성공!!!")
                .build();

        return ResponseEntity.ok(jsonResult);
    }

}
