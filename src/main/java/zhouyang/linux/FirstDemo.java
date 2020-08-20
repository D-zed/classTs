package zhouyang.linux;

public class FirstDemo {
    /**
     * 起手式 查看整机的性能               先总后分
         * top 命令
         *    1.1 看cpu mem的占用的百分比
         *    1.2 按1看核心数 看 id值（idle） cpu空闲率 越高越好
         *    1.3 command列 看对应的应用的进程 pid 是其进程id
         *    1.4 load average 系统负载率 其中显示的是 1分钟 5分钟 15分钟的系统负载量
         *        三者相加除以三*100%高于  60%  说明系统负载重
         *        按q退出 不要 ctrl + c
         * 第二招
         * uptime 青春版 top
     * 查看内存
     *
     *      vmstat -n 2 3     主要看 us和sy 代表user 和sys 二者超过 80%则服务器负载大   id还是代表空闲
     *      查询两秒钟内 刷新三条数据
     *      r b 代表运行和等待cpu时间片的 进程的总数 平均如果大于我们核心总数的两倍 了那就代表过于繁忙了
     *      查看磁盘io 如果磁盘io高可能是数据库读取问题
     *      iostat -xdk 2 3  r w util 长期持续过高都是问题
     *
     *      查看每个cpu核心的数值，比较详细
     *      mpstat
     *
     * 详细查看某一个进程 的 user system 各自占用 百分比
     *      pidstat  -u 1 -p 5835
     *      每两秒刷新一次
     *      pidstat -p 5835 -r 2
     *
     *         free 查看系统内存 -g 以g为单位  -m以m为单位
     *      * 查看磁盘空间
     *      *      df （disk free） -h（人类） 以人类的方式查看磁盘
     *      * 查看cpu 并不仅限与cpu
     */
}
