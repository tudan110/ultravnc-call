import indi.tudan.uvnccall.cli.Cli;

/**
 * 命令行接口测试
 * 参考：https://www.cnblogs.com/onmyway20xx/p/7346709.html
 *
 * @author wangtan
 * @date 2021-01-18 16:04:45
 * @since 1.0
 */
public class CliTest {

    public static void main(String[] args) {

        args = new String[]{
                "-h",
                "-id", "90699",
                "-dir", "/dir/f",
                "-ip", "123123",
                "-port", "90612312399",
        };

        System.out.println(Cli.startServer(args));

    }

}
