package com.rxliuli.example.websocket.web.spring.conf;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 用户 session 记录类
 *
 * @author rxliuli
 */
@Component
public class SocketSessionRegistry {
    /**
     * 未登录的用户默认存储的 user id
     */
    public static final String DIRECT_TOURIST = "DIRECT_TOURIST";
    /**
     * 这个集合存储 用户 id -> session 列表
     * 单个用户可能打开多个页面，就会出现多个 Socket 会话
     */
    private final ConcurrentMap<String, Set<String>> userSessionIds = new ConcurrentHashMap<>();
    private final Object lock = new Object();

    /**
     * 根据 user id 获取 sessionId
     *
     * @param user 用户 id
     * @return 用户关联的 sessionId
     */
    public Set<String> getSessionIds(String user) {
        Set<String> set = this.userSessionIds.get(user);
        return set != null ? set : Collections.emptySet();
    }

    /**
     * 获取所有 session
     *
     * @return 所有的 用户 id -> session 列表
     */
    public ConcurrentMap<String, Set<String>> getAllSessionIds() {
        return this.userSessionIds;
    }

    /**
     * 根据用户 id 注册一个 session
     *
     * @param user      用户 id
     * @param sessionId Socket 会话 id
     */
    public void registerSessionId(String user, String sessionId) {
        Assert.notNull(user, "User must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        synchronized (this.lock) {
            this.userSessionIds.putIfAbsent(user, new CopyOnWriteArraySet<>());
            Set<String> set = this.userSessionIds.get(user);
            set.add(sessionId);
        }
    }

    /**
     * 根据用户 id 删除一个 session
     *
     * @param user      用户 id
     * @param sessionId Socket 会话 id
     */
    public void unregisterSessionId(String user, String sessionId) {
        Assert.notNull(user, "User Name must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        synchronized (this.lock) {
            Set set = this.userSessionIds.get(user);
            if (set != null && set.remove(sessionId) && set.isEmpty()) {
                this.userSessionIds.remove(user);
            }
        }
    }
}