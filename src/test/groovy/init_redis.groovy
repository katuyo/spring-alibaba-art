import redis.clients.jedis.Jedis

def userId = "1fd30aeecdaf4fc9a6094bd5eaef7b75";
def jedis = new Jedis()
def randomScore = new Random()

def randomString(def size) {
    def pool = "zxcvbnmlkjhgfdsaqwertyuiop1234567890"
    def stringBuilder = new StringBuilder();
    def random = new Random();
    for (def i = 0; i <  size; i++) {
        stringBuilder.append(pool.charAt(random.nextInt(36)))
    }
    return stringBuilder.toString();
}

for (def i = 0; i < 100000; i++) {
    def randomPostId = randomString(32)
    jedis.zadd("post-ids#recommend", randomScore.nextDouble(), randomPostId)
    if (randomScore.nextBoolean()) {
        jedis.sadd(String.format("post-ids#user_1fd30aeecdaf4fc9a6094bd5eaef7b75_read", userId), randomPostId)
    }
}

