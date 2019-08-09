package org.featx.katuyo.springali.service;

import java.util.List;

public interface RecommendService {

    List<String> recommendPostList(String userId, int page, int size);

    List<String> recommendOPostList(String userId, int page, int size);
}
