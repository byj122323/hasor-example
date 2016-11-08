启动注册中心步骤：

    1. 使用 CenterServerAlone 类启动注册中心，或者下载 RsfCenter 程序包。下载地址：
            http://project.hasor.net/hasor/packages/hasor-rsf/rsf-center/1.0.1/rsf-center-v1.0.1.zip
            http://project.hasor.net/hasor/packages/hasor-rsf/rsf-center/1.0.1/rsf-center-v1.0.1.tar.gz
    2. 将 rsf-config.xml 替换程序包中的配置。两个配置文件不同的地方是：修改了 RSF_SERVICE_IP 服务IP 为本机 127.0.0.1。
    3. 运行 center bin 目录下的。 startup.sh 或 startup.bat