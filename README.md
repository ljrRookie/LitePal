# LitePal
LitePal实现简单操作数据库
##准备工作：
*   1.添加依赖 compile 'org.litepal.android:core:1.4.1'。
*   2.在main目录下新建一个assets文件夹，在assets目录下新建litepal.xml文件。
*   litepal.xml
*<?xml version="1.0" encoding="utf-8"?>
*<litepal>
*    <dbname value="BookStore"></dbname>
*    <version value="2"></version>
*   <list>
*        <mapping class="com.example.litepal.javabean"></mapping>
*        <mapping class="com.example.litepal.javabean"></mapping>
*    </list>
*</litepal>
*   Red3.配置LitePalApplication，修改AndroidManifest.xml中的代码。
*<application
*        android:name="org.litepal.LitePalApplication"
*        ....>
