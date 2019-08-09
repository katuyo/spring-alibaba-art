package org.featx.katuyo.springali.controller;

import org.featx.katuyo.springali.model.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/index")
    public Mono<BaseResponse<Boolean>> index() {
        return Mono.just(BaseResponse.success(true));
    }


}
