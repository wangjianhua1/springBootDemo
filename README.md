# springBootDemo


-Xms1024M -Xmx2048M -XX:PermSize=128M -XX:MaxPermSize=256M
在线上生产环境，JVM的Xms和Xmx设置一样大小的内存容量，避免在GC 后调整堆大小带来的压力
给JVM设置-XX:+HeapDumpOnOutOfMemoryError参数，让JVM碰到OOM场景时输出dump信息。