package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest(){
        //given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.addAll(Arrays.asList(new TrelloBoardDto(
                "board1", "1", new ArrayList<>()
        ), new TrelloBoardDto("board2", "2", new ArrayList<>())
                ));
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.addAll(Arrays.asList(new TrelloBoard(
                        "board1", "1", new ArrayList<>()
                ), new TrelloBoard("board2", "2", new ArrayList<>())
        ));
        //when
        List<TrelloBoard> resultedTrelloBoardList = trelloMapper.mapToBoards(trelloBoardDto);
        //then
        assertEquals(trelloBoard.size(), resultedTrelloBoardList.size());
    }

    @Test
    public void mapToBoardsDtoTest(){
        //given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.addAll(Arrays.asList(new TrelloBoardDto(
                        "board1", "1", new ArrayList<>()
                ), new TrelloBoardDto("board2", "2", new ArrayList<>())
        ));
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.addAll(Arrays.asList(new TrelloBoard(
                        "board1", "1", new ArrayList<>()
                ), new TrelloBoard("board2", "2", new ArrayList<>())
        ));
        //when
        List<TrelloBoardDto> resultedTrelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoard);
        //then
        assertEquals(trelloBoard.size(), resultedTrelloBoardDtoList.size());
    }

    @Test
    public void mapToListTest(){
        //given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.addAll(Arrays.asList(new TrelloListDto(
                        "board1", "1", false
                ), new TrelloListDto("board2", "2", true)
        ));
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.addAll(Arrays.asList(new TrelloList(
                        "board1", "1", true
                ), new TrelloList("board2", "2", false)
        ));
        //when
        List<TrelloList> resultedTrelloList = trelloMapper.mapToList(trelloListDtos);
        //then
        assertEquals(trelloLists.size(), resultedTrelloList.size());
    }

    @Test
    public void mapToListDtoTest(){
        //given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.addAll(Arrays.asList(new TrelloListDto(
                        "board1", "1", false
                ), new TrelloListDto("board2", "2", true)
        ));
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.addAll(Arrays.asList(new TrelloList(
                        "board1", "1", true
                ), new TrelloList("board2", "2", false)
        ));
        //when
        List<TrelloListDto> resultedTrelloListDto = trelloMapper.mapToListDto(trelloLists);
        //then
        assertEquals(trelloLists.size(), resultedTrelloListDto.size());
    }

    @Test
    public void mapToCardDtoTest(){
        //given
        TrelloCard trelloCard = new TrelloCard("card1","desc1","1","1");
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1","desc1","1","1");
        //when
        TrelloCardDto resultedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //then
        assertAll(
                ()->assertEquals(resultedTrelloCardDto.getDescription(), trelloCardDto.getDescription()),
                ()->assertEquals(resultedTrelloCardDto.getListId(), trelloCardDto.getListId()),
                ()->assertEquals(resultedTrelloCardDto.getName(), trelloCardDto.getName()),
                ()->assertEquals(resultedTrelloCardDto.getPos(), trelloCardDto.getPos())
                        );
    }

    @Test
    public void mapToCardTest(){
        //given
        TrelloCard trelloCard = new TrelloCard("card1","desc1","1","1");
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1","desc1","1","1");
        //when
        TrelloCard resultedTrelloCard = trelloMapper.mapToCard(trelloCardDto);
        //then
        assertAll(
                ()->assertEquals(resultedTrelloCard.getDescription(), trelloCard.getDescription()),
                ()->assertEquals(resultedTrelloCard.getListId(), trelloCard.getListId()),
                ()->assertEquals(resultedTrelloCard.getName(), trelloCard.getName()),
                ()->assertEquals(resultedTrelloCard.getPos(), trelloCard.getPos())
        );
    }

}