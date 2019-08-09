package org.featx.katuyo.springali.service;

import com.google.common.collect.Sets;
import org.featx.katuyo.springali.trans.RecommendCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private RecommendCache recommendCache;

    @Override
    public List<String> recommendPostList(String userId, int page, int size) {
        List<String> nextList = recommendCache.getPostIdsAsScoreDesc((page - 1) * size, size * 50);
        Set<String> userReadSet = recommendCache.getPostIdsOfSubject(userId);
        return Sets.difference(Sets.newTreeSet(nextList), userReadSet)
                .stream().limit(size).collect(Collectors.toList());
    }

    @Override
    public List<String> recommendOPostList(String userId, int page, int size) {
        List<String> nextList = recommendCache.getPostIdsAsScoreDesc((page - 1) * size, size * 50);
        return nextList.stream().filter(postId -> !recommendCache.hasSubjectReadPost(userId, postId))
                .limit(size).collect(Collectors.toList());
    }

}
