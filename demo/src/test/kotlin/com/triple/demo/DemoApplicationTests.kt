package com.triple.demo

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests(
    @Autowired private val mockMvc: MockMvc
) {
    private val defaultUserId: String = "3ede0ef2-92b7-4817-a5f3-0c575361f745"
    private val testData = "{\n" +
            "  \"type\": \"REVIEW\",\n" +
            "  \"action\": \"ADD\",\n" +
            "  \"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
            "  \"content\": \"좋아요!\",\n" +
            "  \"attachedPhotoIds\": [\n" +
            "    \"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\",\n" +
            "    \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"\n" +
            "  ],\n" +
            "  \"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
            "  \"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
            "}"
    private val testDataModText = "{\n" +
            "    \"type\": \"REVIEW\",\n" +
            "    \"action\": \"MOD\",\n" +
            "    \"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
            "    \"content\": \"\",\n" +
            "    \"attachedPhotoIds\": [\n" +
            "        \"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\",\n" +
            "        \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"\n" +
            "    ],\n" +
            "    \"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
            "    \"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
            "}"
    private val testDataModPhoto = "{\n" +
            "    \"type\": \"REVIEW\",\n" +
            "    \"action\": \"MOD\",\n" +
            "    \"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
            "    \"content\": \"좋아요!\",\n" +
            "    \"attachedPhotoIds\": [],\n" +
            "    \"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
            "    \"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
            "}"
    private val testDataMod = "{\n" +
            "  \"type\": \"REVIEW\",\n" +
            "  \"action\": \"MOD\",\n" +
            "  \"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
            "  \"content\": \"좋아요!\",\n" +
            "  \"attachedPhotoIds\": [\n" +
            "    \"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\",\n" +
            "    \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"\n" +
            "  ],\n" +
            "  \"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
            "  \"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
            "}"
    private val testDataDelete = "{\n" +
            "  \"type\": \"REVIEW\",\n" +
            "  \"action\": \"DELETE\",\n" +
            "  \"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
            "  \"content\": \"좋아요!\",\n" +
            "  \"attachedPhotoIds\": [\n" +
            "    \"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\",\n" +
            "    \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"\n" +
            "  ],\n" +
            "  \"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
            "  \"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
            "}"
    @Test
    @DisplayName(value = "리뷰 등록/수정/삭제 시 테스트")
    fun test() {
        /**
         * 리뷰 등록 전 사용자 포인트 조회
         * 결과 : 0
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.get("/events/$defaultUserId")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("0"))
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 등록 포인트 적립
         * 결과 : 성공
         * */
         mockMvc.perform(
             MockMvcRequestBuilders.post("/events")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(testData)
         ).andExpect(MockMvcResultMatchers.status().isOk)
             .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 등록 후 사용자 포인트 조회
         * 결과 : 3
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.get("/events/$defaultUserId")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("3"))
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 등록 후 같은 장소에 리뷰 등록
         * 결과 : 400 에러
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testData)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 수정(텍스트X, 사진O) 포인트 적립
         * 결과 : 성공
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testDataModText)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 수정(텍스트X, 사진O) 후  포인트 조회
         * 결과 : 2
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.get("/events/$defaultUserId")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("2"))
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 수정(텍스트O, 사진X) 포인트 적립
         * 결과 : 성공
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testDataModPhoto)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 수정(텍스트O, 사진X) 후 포인트 조회
         * 결과 : 2
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.get("/events/$defaultUserId")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("2"))
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 수정(글 추가, 사진 추가) 포인트 적립
         * 결과 : 성공
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testDataMod)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 수정(글 추가, 사진 추가) 후 포인트 조회
         * 결과 : 3
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.get("/events/$defaultUserId")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("3"))
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 삭제 포인트 적립
         * 결과 : 성공
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testDataDelete)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())

        /**
         * 리뷰 삭제 후 포인트 조회
         * 결과 : 0
         * */
        mockMvc.perform(
            MockMvcRequestBuilders.get("/events/$defaultUserId")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("0"))
            .andDo(MockMvcResultHandlers.print())
    }




}
