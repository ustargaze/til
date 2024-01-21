# 连接 github 出现 kex_exchange_identification: Connection closed by remote host

两种解决办法

1. 修改 /etc/hosts

   通过 ping 工具找一个 github.com 可访问的 ip 添加到 /etc/hosts 文件。
2. 使用 443 端口进行 ssh 连接

   参考：https://docs.github.com/en/authentication/troubleshooting-ssh/using-ssh-over-the-https-port