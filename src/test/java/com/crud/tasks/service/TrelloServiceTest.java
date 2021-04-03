package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrelloServiceTest {
    /*
    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        ofNullable(newCard).ifPresent(card->
        emailService.send(new Mail(
                "",
                SUBJECT,
                "New card: " + trelloCardDto.getName() + " has been created on your Trello account",
                null
        )));

        return newCard;
    }
     */
    @Autowired
    TrelloService trelloService;

    @MockBean
    TrelloClient trelloClient;

    @Test
    void fetchTrelloBoards() {
        //given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("name", "1", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //when
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();
        //then
        assertEquals(1, result.size());
    }

    @Test
    void createTrelloCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "desc1", "pos1", "list1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name1", "url1", null);
        when(trelloClient.createNewCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);
        //when
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);
        //then
        assertEquals(createdTrelloCardDto.getId(),result.getId());
        assertEquals(createdTrelloCardDto.getName(),result.getName());
        assertEquals(createdTrelloCardDto.getBadgesDto(),result.getBadgesDto());

    }
}