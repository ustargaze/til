# SSH

## 端口转发

端口转发有三个参数 -D（动态端口转发）、-L（本地端口转发）、-R（远程端口转发）

### 动态端口转发

参数说明：

```
-D [bind_address:]port
```

port 为本地端口。指定端口之后，本地会分配一个socket监听此端口，并且和远程服务器建立一个加密通道。每当连接到该端口时，都会通过通道转发 SSH 服务器上，由 SSH 服务器根据应用协议决定访问哪里。应用协议的内容是由本地指定的，整个过程是动态的。

例如：

远程服务器（remote-host）执行命令

```sh
# 在 9000 端口启动一个 web 服务，但是端口没有对外暴露，外部无法访问
python -m http.server 9000
```

在本地执行

```sh
# 开启动态转发
ssh -D 8888 user@remote-host
# 设置全局代理参数
export ALL_PROXY=socks5://localhost:8888
# 会访问到远程服务器 9000 端口，如果不设置 ALL_PROXY 可以添加参数 -x socks5://localhost:8888 来指定 curl 的代理
curl http://localhost:9000
```

### 本地转发

参数说明：

```
-L [bind_address:]port:host:hostport
-L [bind_address:]port:remote_socket
-L local_socket:host:hostport
-L local_socket:remote_socket
```

可以将本地的端口或 socket 和目标主机的端口或 SSH 服务器的 socket 进行绑定，访问本地的端口或 socket 将会通过 SSH 服务器转发到目标主机的端口或 SSH 服务器的 socket 上。

例如：

**端口绑定**

远程服务器（remote-host）执行命令

```sh
python -m http.server 9000
```

在本地执行

```sh
# 开启本地转发，host 指定为 localhost，ssh 服务器和访问的远程服务器是同一个
ssh -L 8888:localhost:9000 user@remote-host
# 会访问到远程服务器的 9000 端口
curl http://localhost:8888
```

**端口和 socket 绑定**

例如 docker 的守护进程默认只监听 `/var/run/docker.sock`，通过这个可以访问 Docker Engine API。

```sh
# 在远程服务器（remote-host）上执行返回 docker 的版本信息
curl --unix-socket /var/run/docker.sock http://localhost/version
```

现在在本地执行如下代码

```sh
# 本地端口和远程 socket 绑定
ssh -L 8888:/var/run/docker.sock user@remote-host
# 同样返回 docker 版本信息
curl http://localhost:8888/version
```

> 可以利用这个简单快速的访问 docker api 进行测试，而不用开启 docker 的远程访问端口。
>
> docker 当然可以开启端口访问，但是不太推荐，因为如果不配置 TLS 的话没有任何的认证，任何人都可以访问。要配置 TLS 的话过程又有点繁琐，如果是简单测试的话，可以通过这种方式进行。

### 远程转发

参数说明：

```
-R [bind_address:]port:host:hostport
-R [bind_address:]port:local_socket
-R remote_socket:host:hostport
-R remote_socket:local_socket
-R [bind_address:]port
```

可以将 SSH 服务器的端口或 socket 和目标主机的端口或本地 socket 进行绑定，访问 SSH 服务器的端口或 socket 将会通过本机转发到目标主机的端口或本地 socket 上。如果指定了 SSH 服务器的端口或 socket 与 目标驻地的端口或本地 socket，则和本地转发正好相反，如果只指定端口则和动态转发相反相当于远程动态转发。

例如：

**远程转发**

本地执行命令

```sh
# 开启远程转发，将远程端口转发到本地
ssh -R 9000:localhost:8888 user@remote-host
# 本地运行 http 服务监听 8888 端口
python -m http.server 8888
```

远程服务器（remote-host）执行命令

```sh
# 在远程服务器上执行此命令，将访问到本地的 8888 端口
curl http://localhost:9000
```

> 其他的 socket 和 端口绑定等效果和本地转发差不多只不过是反的。

**远程动态转发**

本地执行命令

```sh
# 开启远程转发，将远程端口转发到本地
ssh -R 9000 user@remote-host
# 本地运行 http 服务监听 8888 端口
python -m http.server 8888
```

远程服务器（remote-host）执行命令

```sh
# 在远程服务器上执行此命令，将通过远程服务器的 9000 端口访问到本地的 8888 端口
curl -x socks5://localhost:9000 http://localhost:8888
```

### 小结

**-D**（动态转发） 和 **-L**（本地转发）相当于开启一个正向代理，-D 的访问目标是动态的，而 -L 的访问目标一开始就是固定的。**-R**（远程转发）相当于开启一个反向代理，相当于 -D + -L 反着来。
