package org.featx.katuyo.springali.util;

public class Constants {

    private Constants () {

    }

    public static final String FIRST_INDEX = "";

    public static class CacheKey {
        private CacheKey () {

        }

        public static final String RECOMMEND_POST_IDS = "post-ids#recommend";
        public static final String USER_READ_POST_IDS = "post-ids#user_%s_read";
        public static final String OFFSET_POST_IDS = "post-ids#user_%s_offset";
    }
}
