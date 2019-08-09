package org.featx.katuyo.springali.trans;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

import static org.featx.katuyo.springali.util.Constants.CacheKey.RECOMMEND_POST_IDS;
import static org.featx.katuyo.springali.util.Constants.CacheKey.USER_READ_POST_IDS;

@Component
public class RecommendCacheImpl implements RecommendCache {

    private static final Logger log = LoggerFactory.getLogger(RecommendCacheImpl.class);

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Value("${recommend.score.default:1.0}")
    private Double defaultScore;

    @Override
    public void initPostIds(Set<String> postIdSet) {
        BoundZSetOperations<String, String> postIdsCache = redisTemplate.boundZSetOps(RECOMMEND_POST_IDS);
        postIdSet.forEach(postId -> postIdsCache.add(postId, defaultScore));
    }

    @Override
    public void scorePost(final String postId, final Double score) {
        Optional.ofNullable(score).ifPresent(s -> {
            BoundZSetOperations<String, String> postIdsCache = redisTemplate.boundZSetOps(RECOMMEND_POST_IDS);
            if (score.equals(0.0)) {
                postIdsCache.remove(postId);
            } else {
                postIdsCache.add(postId, score);
            }
        });
    }

    @Override
    public void readPostBySubject(String postId, String subjectId) {
        BoundSetOperations<String, String> subjectReadPostIds =
                redisTemplate.boundSetOps(String.format(USER_READ_POST_IDS, subjectId));
        subjectReadPostIds.add(postId);
    }

    @Override
    public void clearSubjectAllRead(String subjectId) {
        BoundSetOperations<String, String> subjectReadPostIds =
                redisTemplate.boundSetOps(String.format(USER_READ_POST_IDS, subjectId));
        subjectReadPostIds.expireAt(new Date());
    }

    @Override
    public boolean hasSubjectReadPost(String subjectId, String postId) {
        BoundSetOperations<String, String> subjectReadPostIds =
                redisTemplate.boundSetOps(String.format(USER_READ_POST_IDS, subjectId));
        return subjectReadPostIds.isMember(postId);
    }

    @Override
    public List<String> getPostIdsAsScoreDesc(int offset, int size) {
        BoundZSetOperations<String, String> postIdsCache = redisTemplate.boundZSetOps(RECOMMEND_POST_IDS);
        return Lists.newArrayList(Objects.requireNonNull(postIdsCache.reverseRange(offset, size)));
    }

    @Override
    public Set<String> getPostIdsOfSubject(String subjectId) {
        BoundSetOperations<String, String> subjectReadPostIds =
                redisTemplate.boundSetOps(String.format(USER_READ_POST_IDS, subjectId));
        return subjectReadPostIds.members();
    }

}
