package org.featx.katuyo.springali.controller;


import org.featx.katuyo.springali.model.BaseResponse;
import org.featx.katuyo.springali.service.RecommendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private String userId = "1fd30aeecdaf4fc9a6094bd5eaef7b75";

    @Resource
    private RecommendService recommendService;

    @GetMapping("/s1")
    public Mono<BaseResponse<List<String>>> testStrategy1() {
        List<String> result = recommendService.recommendPostList(userId, 1, 10);
        return Mono.just(BaseResponse.success(result));
    }

    @GetMapping("/s2")
    public Mono<BaseResponse<List<String>>> testStrategy2() {
        List<String> result = recommendService.recommendOPostList(userId, 1, 10);
        return Mono.just(BaseResponse.success(result));
    }
}
