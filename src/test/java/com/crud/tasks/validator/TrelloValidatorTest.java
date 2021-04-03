package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {
    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardsTest(){
        //given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.addAll(Arrays.asList(
                new TrelloBoard(
                       "1", "test", new ArrayList<>()
                ))
        );
        //when
        List<TrelloBoard> resultedList = trelloValidator.validateTrelloBoards(trelloBoards);
        //then
        assertEquals(0,resultedList.size());
    }

}