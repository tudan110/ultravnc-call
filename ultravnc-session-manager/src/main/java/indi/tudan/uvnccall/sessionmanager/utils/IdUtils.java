package indi.tudan.uvnccall.sessionmanager.utils;

import indi.tudan.uvnccall.sessionmanager.common.ConfigConstants;
import indi.tudan.uvnccall.sessionmanager.model.Session;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Id 工具类
 * JAVA 随机产生 100 到 Integer.MAX_VALUE 之间的随机整数
 *
 * @author wangtan
 * @date 2019-09-03 15:28:11
 * @see #getVNCSessionId() 随机生成 VNCSessionId，大量数据不能保证唯一
 * @see #getVNCSessionId(boolean) 通过编号池来控制，保证唯一，注意：使用后应立即销毁编号（从编号池中移出，以免占用不必要的内存空间）
 * @since 1.0
 */
public class IdUtils {

    /**
     * 存储正在使用的编号，避免编号重复
     */
    //public static final Set<String> ID_STRING_SET = new HashSet<>();
    public static final Map<String, Session> SESSION_MAP = new LinkedHashMap<>();
    /**
     * 默认最小值
     */
    private static final int DEFAULT_MIN_VALUE = 100;
    /**
     * 默认最大值
     */
    private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE;

    /**
     * Don't let anyone else instantiate this class
     */
    private IdUtils() {
    }

    /**
     * 随机生成 VNCSessionId，生成 100 ~ 0x7fffffff 的随机整数
     * 大量数据不能保证唯一
     *
     * @return String
     * @author wangtan
     * @date 2019-09-04 09:03:10
     * @since 1.0
     */
    public static String getVNCSessionId() {
        return getVNCSessionId(false);
    }

    /**
     * 生成 VNCSessionId，生成 100 ~ 0x7fffffff 的随机整数
     *
     * <pre>
     *     两种方式：
     *     <ul>
     *         <li>
     *             1. absolutely == true，通过编号池来控制，保证唯一，注意：使用后应立即销毁编号（从编号池中移出，以免占用不必要的内存空间）。
     *             效率：200 000 / s （一秒钟生成 2 百万个）
     *         </li>
     *         <li>
     *             2. absolutely == false，大量数据不能保证唯一
     *             效率：1 000 000 / s （一秒钟生成 1 千万个）
     *         </li>
     *     </ul>
     * </pre>
     *
     * @param absolutely 是否控制，必须唯一
     * @return String
     * @author wangtan
     * @date 2019-09-03 15:30:06
     * @since 1.0
     */
    public static String getVNCSessionId(boolean absolutely) {

        String sessionId = "";

        if (absolutely) {
            // 通过编号池来控制，保证唯一，注意：使用后应立即销毁编号（从编号池中移出，以免占用不必要的内存空间）

            sessionId = randomIntMinToMaxToString(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);

            // 判断编号是否正在被使用
            while (contains(sessionId)) {
                sessionId = randomIntMinToMaxToString(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
            }

            // 若当前编号没有被使用，则加入编号池
            add(sessionId, Session.builder()
                    .id(sessionId)
                    .description(ConfigConstants.DEFAULT_SESSION_DESCRIPTION)
                    .build());
        } else {
            sessionId = randomIntMinToMaxToString(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
        }

        return sessionId;
    }

    /**
     * 获取从 min 到 max 的编号
     *
     * @param min 最小值
     * @param max 最大值
     * @return String
     * @author wangtan
     * @date 2019-09-04 09:46:36
     * @since 1.0
     */
    public static String randomIntMinToMaxToString(int min, int max) {
        return randomIntMinToMax(min, max) + "";
    }

    /**
     * 求 [min, max] 区间之间的随机整数。
     *
     * @param min 最小值
     * @param max 最大值
     * @return 一个 min 和 max 之间的随机整数
     * @author wangtan
     * @date 2019-09-03 17:20:17
     * @since 1.0
     */
    public static int randomIntMinToMax(int min, int max) {

        //如果相等，直接返回，还生成个屁
        if (min == max) {
            return max;
        }

        //如果 min 比 max 大，交换两个的值，如果不交换下面 nextInt() 会出错
        if (min > max) {
            min ^= max;
            max ^= min;
            min ^= max;
        }

        // nextInt() 不是静态方法，不能直接用类名调用
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * 判断编号池是否包含此编号
     *
     * @param id 编号
     * @author wangtan
     * @date 2019-09-04 09:14:55
     * @since 1.0
     */
    private static boolean contains(String id) {
        return SESSION_MAP.containsKey(id);
    }

    /**
     * 加入编号池
     *
     * @param id 编号
     * @author wangtan
     * @date 2019-09-04 09:12:52
     * @since 1.0
     */
    private static void add(String id, Session session) {
        SESSION_MAP.put(id, session);
    }

    /**
     * 移出编号池
     *
     * @param id 编号
     * @author wangtan
     * @date 2019-09-04 09:13:15
     * @since 1.0
     */
    private static void remove(String id) {
        SESSION_MAP.remove(id);
    }

    /**
     * 移出编号池
     *
     * @param id 编号
     * @author wangtan
     * @date 2019-09-04 09:13:26
     * @since 1.0
     */
    public static void release(String id) {
        remove(id);
    }

}