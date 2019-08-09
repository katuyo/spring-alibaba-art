package org.featx.katuyo.springali.trans;

import java.util.List;
import java.util.Set;

public interface RecommendCache {
    /**
     *  将一定时间内的贴子缓存
     * @param postIdSet 要缓存的贴子id的集合
     */
    void initPostIds(Set<String> postIdSet);

    /**
     * 重新记录某贴子的分数，若为0或时间超时 删除
     * @param postId 要记分的贴子 id
     * @param score 要给贴子记录的分值
     */
    void scorePost(String postId, Double score);

    /**
     * 记录某主体读过的贴子
     * @param postId 已经读过的贴子
     * @param subjectId 主体id, 可以是用户，设备，或其他
     */
    void readPostBySubject(String postId, String subjectId);

    /**
     * 清除某主体读过的贴子记录
     * @param subjectId 主体id, 可以是用户，设备，或其他
     */
    void clearSubjectAllRead(String subjectId);

    /**
     * 检查某主体是否读过某贴子
     * @param subjectId 主体id, 可以是用户，设备，或其他
     * @param postId 要检查的是否读过的贴子id
     */
    boolean hasSubjectReadPost(String subjectId, String postId);

    List<String> getPostIdsAsScoreDesc(int start, int size);

    Set<String> getPostIdsOfSubject(String subjectId);
}
