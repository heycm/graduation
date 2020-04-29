package com.heycm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description Redis 工具类
 * @Author heycm@qq.com
 * @Date 2020-03-21 18:01
 */
@Component
public class RedisUtil {

    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // ================= Comm =================

    /**
     * Comm 设置过期时间
     *
     * @param key      键
     * @param time     存活时长，负值则永久存活
     * @param timeUnit 时间单位
     * @return 成功true 失败false
     */
    public Boolean expire(String key, long time, TimeUnit timeUnit) {
        if (time > 0) {
            return redisTemplate.expire(key, time, timeUnit);
        }
        return true;
    }

    /**
     * Comm 设置过期时间(分钟)
     *
     * @param key  键
     * @param time 存活时长，负值则永久存活
     * @return 成功true 失败false
     */
    public Boolean expire(String key, long time) {
        return expire(key, time, DEFAULT_TIME_UNIT);
    }

    /**
     * Comm 获取key的过期时间
     *
     * @param key      键
     * @param timeUnit 时间单位
     * @return 时间(单位 : timeUnit)，返回0代表永久有效
     */
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * Comm 获取key的过期时间(秒)
     *
     * @param key 键
     * @return 时间(秒)，返回0代表永久有效
     */
    public Long getExpire(String key) {
        return getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * Comm 判断key是否存在
     *
     * @param key 键
     * @return 存在 true 不存在 false
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * Comm 删除缓存
     *
     * @param key 可以传一个或多个键
     * @return 成功返回删除的数量，失败返回null
     */
    @SuppressWarnings("unchecked")
    public Long del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
                return 1L;
            } else {
                return redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
        return null;
    }

    /**
     * Comm 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return 成功true 失败false
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * Comm 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * Comm 普通缓存放入并设置时间
     *
     * @param key      键
     * @param value    值
     * @param time     时间 若time<0则无限期
     * @param timeUnit 时间单位
     * @return 成功 true 失败 false
     */
    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, timeUnit);
        } else {
            set(key, value);
        }
    }

    /**
     * Comm 普通缓存放入并设置时间(分钟)
     *
     * @param key   键
     * @param value 值
     * @param time  时间，若time<0则无限期
     * @return 成功 true 失败 false
     */
    public void set(String key, Object value, long time) {
        set(key, value, time, DEFAULT_TIME_UNIT);
    }

    /**
     * Comm 递增
     *
     * @param key   键
     * @param delta 递增因子，要增加几(大于0)
     * @return 自增后值
     */
    public Long increment(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * Comm 递减
     *
     * @param key   键
     * @param delta 递减因子，要减少几(大于0)
     * @return 自减后值
     */
    public Long decrement(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    // ================= Map =================

    /**
     * Hash get hash item
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * Hash get hash
     *
     * @param key 键
     * @return 对应的多个项值
     */
    public Map<Object, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Hash set hash
     *
     * @param key 键
     * @param map hash
     * @return true 成功 false 失败
     */
    public void hmSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }


    /**
     * Hash 向一张hash表中放入数据，如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功，false 失败
     */
    public void hSet(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * Hash 删除hash表中的项
     *
     * @param key  键 不能为null
     * @param item 项 可以传多个 不能为null
     * @return 删除数量 成功，null 失败
     */
    public Long hDel(String key, Object... item) {
        return redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * Hash 判断hash表中是否有该项
     *
     * @param key  键
     * @param item 值
     * @return true 存在 false不存在
     */
    public Boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * Hash hash递增 如果不存在，就会创建一个，并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   增量(大于0)
     * @return 自增后值
     */
    public Double hIncrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * Hash hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   减量(大于0)
     * @return 自减后值
     */
    public Double hDecrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ================= Set =================

    /**
     * Set 根据key获取Set所有值
     *
     * @param key 键
     * @return Set
     */
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * Set 在Set中查找value是否存在
     *
     * @param key   键
     * @param value 值
     * @return 存在 true 不存在 false
     */
    public Boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * Set 设置Set缓存
     *
     * @param key    键
     * @param values 值，可以多个
     * @return 成功 成功个数 失败 null
     */
    public Long sSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * Set 获得Set的长度
     *
     * @param key 键
     * @return 成功 长度 失败 null
     */
    public Long sGetSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * Set 移除Set中的value
     *
     * @param key    键
     * @param values 值，可以是多个
     * @return 成功 移除的数量 失败 null
     */
    public Long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    // ================= List =================

    /**
     * List 获取指定区间[start, end]的元素，[0, -1]获取所有
     *
     * @param key   键
     * @param start 起始索引
     * @param end   结束索引
     * @return 值
     */
    public List<Object> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * List 获取list所有值
     *
     * @param key 键
     * @return 值
     */
    public List<Object> lGetAll(String key) {
        return lGet(key, 0, -1);
    }

    /**
     * List 获取list长度
     *
     * @param key 键
     * @return 长度
     */
    public Long lGetListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * List 根据索引获取值
     *
     * @param key   键
     * @param index 索引
     * @return 值
     */
    public Object lGetIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * List 在尾部追加一个值
     *
     * @param key   键
     * @param value 值
     * @return 增加个数
     */
    public Long lRightPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * List 在尾部追加一个list
     *
     * @param key   键
     * @param value 值
     * @return 增加个数
     */
    public Long lRightPushAll(String key, List<Object> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * List 在尾部追加多个值
     *
     * @param key    键
     * @param values 值
     * @return 增加个数
     */
    public Long lRightPushAll(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * List 在头部插入一个value
     *
     * @param key   键
     * @param value 值
     * @return 增加个数
     */
    public Long lLeftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * List 在头部增加一个list
     *
     * @param key   键
     * @param value 值
     * @return 增加个数
     */
    public Long lLeftPushAll(String key, List<Object> value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * List 在头部插入多个值
     *
     * @param key    键
     * @param values 值
     * @return 增加个数
     */
    public Long lLeftPushAll(String key, Object... values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * List 根据索引修改值
     *
     * @param key   键
     * @param index 索引
     * @param value 新值
     * @return 成功 true 失败 false
     */
    public void lUpdateIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除最多count个值为value的项
     *
     * @param key   键
     * @param count 数量(count>0 从左往右;count<0 从右往左;count==0 移除所有)
     * @param value 值
     * @return 移除的个数
     */
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
}
