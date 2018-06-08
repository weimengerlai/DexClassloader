# DexClassloader
这个一个demo,用来实现加载class文件,如果在实际项目中可以实现,动态修改代码的业务逻辑

首先在安卓中如果我们想实现的动态加载,比如知道安卓的底层运行原理,

首先安卓底层下载的时候使用的是 Classloader,同时Classloader 分为三个在jvm中,但是在安卓中我们只能使用两个:

1: pathClassloader: Android 用来加载系统文件 和 应用的主文件
2: DexClassloader: Android 用来加载 jar/apk/dex 文件 
3: URLClassloader: 可以加载java的jar包,但是Dalvik 虚拟机不支持这种加载方式


