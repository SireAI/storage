package com.sire.storage.ModuleEnvironment.Cache;

import java.util.Set;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/8/31
 * Author:Sire
 * Description:
 * ==================================================
 */
public interface CacheDelegate {
    int FOREVER = -1;

    void cacheObject(String key, Object object, int expire);

    void cacheObjectInSet(String key, Object object, double sorted);

    void cacheObjectInHash(String key, String field, Object value, int expire);

    /**
     * 更新数据，顺延已有的过期时间
     *
     * @param key
     * @param field
     * @param value
     */
    void updateObjectInHash(String key, String field, Object value);

    void cacheStringInSet(String key, String value, double sorted);

    void deleteObjectInSetByScore(String key, double sorted);

    void deleteStringInSetByScore(String key, double sorted);

    <T> T fetchObject(String key, Class<T> clazz);

    <T> T fetchObjectInHash(String key, String field, Class<T> clazz);

    void cacheKeyValue(String key, String value, int expire);


    String fetchValueByKey(String key);

    /**
     * redis获取 sorted set元素，顺序和逆序去元素
     *
     * @param key
     * @param minScore
     * @param maxScore
     * @param count
     * @param reverse
     * @return
     */
    Set<String> fetchSetBykey(String key, double minScore, double maxScore, int count, boolean reverse);

    void deleteKeyValue(String token);

    void deleteObjectInHash(String key, String feedId);

    void deleteStringValueInSet(String key, String feedId);
}
