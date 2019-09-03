package indi.tudan.uvnccall.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Id 工具类
 * JAVA 随机产生-100到Integer.MAX_VALUE之间的随机整数
 *
 * @author wangtan
 * @date 2019-09-03 15:28:11
 * @since 1.0
 */
public class IdUtils {

    private static final Set<String> STRING_SET = new HashSet<>();

    private IdUtils() {
    }

    /**
     * 求[min, max]区间之间的随机整数。
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
     * 生成 VNCSessionId
     *
     * @return String
     * @author wangtan
     * @date 2019-09-03 15:30:06
     * @since 1.0
     */
    public static String getVNCSessionId() {

        //
        String s = randomIntMinToMax(100, Integer.MAX_VALUE) + "";
        while (STRING_SET.contains(s)) {
            s = randomIntMinToMax(100, Integer.MAX_VALUE) + "";
        }
        STRING_SET.add(s);

        return s;
    }

    public static void main(String[] args) {
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(randomIntMinToMax(100, Integer.MAX_VALUE));

        Set<String> set = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            set.add(getVNCSessionId());
        }
        System.out.println(STRING_SET.size());
        System.out.println(set.size());
    }

}