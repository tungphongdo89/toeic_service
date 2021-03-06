package com.migi.toeic.rest;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.migi.toeic.base.DataListDTO;
import com.migi.toeic.dto.TopicDTO;
import com.migi.toeic.service.impl.TopicServiceImpl;
import com.migi.toeic.utils.MessageUtils;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "API TOPIC MANAGEMENT")

@RequestMapping("/v1/topics")
@JsonRootName("snapshot")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TopicController {
    @Autowired
    TopicServiceImpl topicServiceImpl;

    @PostMapping(value = "/doSearch")
    public ResponseEntity<?> doSearch(@RequestBody TopicDTO obj) {
        DataListDTO data = topicServiceImpl.getListTopic(obj);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/getDetail")
    public ResponseEntity<?> getDetail(@RequestBody String code) {
        TopicDTO topic = topicServiceImpl.getDetail(code);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody TopicDTO obj, HttpServletRequest request) {
    	String message = topicServiceImpl.createTopic(obj, request);
    	return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody TopicDTO obj, HttpServletRequest request) {
    	String message = topicServiceImpl.updateTopic(obj, request);
		return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestBody TopicDTO obj, HttpServletRequest request){
    	String message = topicServiceImpl.deleteTopic(obj, request);
		return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // L???y danh s??ch lo???i b??i : nghe , ?????c , ?????ch
    @PostMapping(value = "/getListTypeExercise")
    public ResponseEntity<?> getListTypeExercise(){
        List<TopicDTO> listTypeExercise = topicServiceImpl.getListTypeExercise();
        return new ResponseEntity<>(listTypeExercise, HttpStatus.OK);
    }

    // L???y danh s??ch ph???n : Ph???n 1 , .....
    @PostMapping(value = "/getListPartExercise")
    public ResponseEntity<?> getListPartExercise(@RequestBody TopicDTO topicDTO){
        List<TopicDTO> listPartExercise = topicServiceImpl.getListPartExercise(topicDTO.getParamValue());
        return new ResponseEntity<>(listPartExercise, HttpStatus.OK);
    }

    // L???y danh s??ch ch??? ????? theo ph???n v?? level
    @PostMapping(value = "/getListTopicByPartAndLevel")
    public ResponseEntity<?> getListTopicByPartAndLevel(@RequestBody TopicDTO topicDTO){
        List<List<TopicDTO>> listTopicByPartAndLevel = topicServiceImpl.getListTopicByPartAndLevel(topicDTO.getParamCode()
                ,topicDTO.getParamValue());
        return new ResponseEntity<>(listTopicByPartAndLevel, HttpStatus.OK);
    }

    // L???y danh s??ch ch??? ????? theo ph???n
    @PostMapping(value = "/getListTopicByPart")
    public ResponseEntity<?> getListTopicByPart(@RequestBody String part){
        List<TopicDTO> listTopicByPart = topicServiceImpl.getListTopicByPart(part.substring(0 , part.length() - 1));
        return new ResponseEntity<>(listTopicByPart, HttpStatus.OK);
    }

    // L???y danh s??ch lo???i b??i v?? ph???n , danh s??ch ch??? ????? vs lo???i b??i d???ch
    @PostMapping(value = "/getListPractices")
    public ResponseEntity<?> getListPractices(){
        List<TopicDTO> listPractices = topicServiceImpl.getListPractices();
        return new ResponseEntity<>(listPractices, HttpStatus.OK);
    }
}

