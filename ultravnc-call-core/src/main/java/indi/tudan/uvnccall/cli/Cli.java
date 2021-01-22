package indi.tudan.uvnccall.cli;

import cn.hutool.log.StaticLog;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * VNCServer 启动命令行接口
 *
 * @author wangtan
 * @date 2021-01-18 14:51:25
 * @since 1.0
 */
public class Cli {

    public static boolean startServer(String... args) {

        // 创建一个解析器
        CommandLineParser parser = new DefaultParser();

        // 创建一个 Options，用来包装 option
        Options options = new Options();

        // help
        options.addOption("h", "help", false, "Print help");

        // 编号，唯一，必须是数字，且至少三位（即最小是 100，最大是 2147483647）
        options.addOption(Option.builder("id")
                .longOpt("id")
                .required()
                .hasArg()
                .desc("编号，唯一，必须是数字，且至少三位（即最小是 100，最大是 2147483647）")
                .build()
        );

        // vncviewer.exe 路径（可选参数）
        options.addOption(Option.builder("dir")
                .longOpt("directory")
                .hasArg()
                .desc("vncviewer.exe 路径（可选参数）")
                .build()
        );

        // 中继器服务器 IP（可选参数）
        options.addOption(Option.builder("ip")
                .longOpt("ip")
                .hasArg()
                .desc("中继器服务器 IP（可选参数）")
                .build()
        );

        // 中继器 UltraVNC Viewer 监听端口（可选参数）
        options.addOption(Option.builder("p")
                .longOpt("port")
                .hasArg()
                .desc("中继器 UltraVNC Viewer 监听端口（可选参数）")
                .build()
        );

        try {

            // 解析命令行参数
            CommandLine line = parser.parse(options, args);

            // 使用帮助
            HelpFormatter hf = new HelpFormatter();
            hf.setWidth(120);
            if (line.hasOption('h')) {
                // 打印使用帮助
                hf.printHelp("ultravnc-call", options, true);
            }

            // 编号，唯一，必须是数字，且至少三位（即最小是 100，最大是 2147483647）
            if (line.hasOption("id")) {
                System.out.println(line.getOptionValue("id"));
            }

            // vncviewer.exe 路径（可选参数）
            if (line.hasOption("dir")) {
                System.out.println(line.getOptionValue("dir"));
            }

            // 中继器服务器 IP（可选参数）
            if (line.hasOption("ip")) {
                System.out.println(line.getOptionValue("ip"));
            }

            // 中继器 UltraVNC Viewer 监听端口（可选参数）
            if (line.hasOption("p")) {
                System.out.println(line.getOptionValue("p"));
            }

            return true;

        } catch (ParseException e) {
            StaticLog.info("Unexpected exception:" + e.getMessage());
        }
        System.out.println(false);
        return false;

    }

}
