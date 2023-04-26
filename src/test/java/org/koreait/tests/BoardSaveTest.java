package org.koreait.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.controllers.BoardForm;
import org.koreait.models.board.BoardSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("게시글 저장 단위 및 통합 테스트")
@TestPropertySource(locations="classpath:application-test.properties")
public class BoardSaveTest {

    private BoardForm boardForm;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardSaveService saveService;

    @BeforeEach
    void init(){
        boardForm = new BoardForm();
        boardForm.setSubject("테스트 제목");
        boardForm.setContent("테스트 내용");
    }



    @Test
    @DisplayName("게시글이 정상적으로 등록, 수정되면 예외가 발생하지 않음")
    void saveSuccessTest() throws Exception {
        String jsonFormat = "{\"subject\":\"%s\", \"content\":\"%s\" }";
        String body = String.format(jsonFormat, boardForm.getSubject(), boardForm.getContent());

        mockMvc.perform(post("/api/board")
                        .content(body)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 삭제 성공?")
    void deleteTest() throws Exception{

        saveService.save(boardForm);

        mockMvc.perform(delete("/api/board/{id}", + boardForm.getId())
                        .contentType("application/json"))
                        .andExpect(status().isOk());
    }
}
