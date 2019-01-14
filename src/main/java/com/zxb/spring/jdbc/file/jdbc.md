培训主题： Spring JDBC和Spring Data JPA详解

培训大纲：

数据库持久化框架简介：
    现在流行的持久层框架大多数使用Mybatis、Hibernate、Data JPA等等这些。
    持久化技术有很多种，而Hibernate、MyBatis和JPA只是其中的几种而已。今天我们要讲的是Spring JDBC和Spring Data JPA这两个持久层框架。


1、什么是JDBC?
  JDBC（Java Data Base Connectivity，Java数据库连接）是一种用于执行SQL语句的Java API，可以为多种关系数据库提供统一访问，它是一组用Java语言编写的类和接口组成。
  JDBC为开发人员提供了一个标准的API，据此可以构建更高级的工具和接口，使开发人员能够用纯Java API编写数据库应用程序，并且可跨平台运行，不受数据库供应商的限制。

JDBC连接数据库的流程及原理如下： 
（1）加载指定的数据库驱动程序。 
（2）在java程序中加载驱动程序。通过Class.forName(“指定数据库驱动程序”)的方式来加载，例如加载MySql的驱动：Class.forName(“com.mysql.jdbc.Driver”)。 
（3）创建数据库连接对象。通过DriverManager类来创建数据库连接对象Connection。DriverManager类作用于Java程序和  
    JDBC驱动程序直接，用于检测所加载的驱动程序是否可以建立连接，然后通过getConnection方法根据数据库的URL、用户名、密码创建一个JDBC Connection对象，
    如：Connection connection=DriverManager.getConnection(“数据库连接的URL”,”用户名”,”密码”)。 
（4）创建Statement对象。Statement类的主要作用是用于执行静态SQL语句并返回结果对象。通过Connection对象的createStatement()方法可以创建一个Statement对象。
    例如：Statement Statement =connection.createStatement()。 
（5）调用Statement对象的相关方法执行Sql语句。excuteUpdate方法对数据库进行更新，包括插入、删除操作。excuteQuery方法进行数据的查询操作，返回ResultSet对象，
    ResultSet对象表示执行查询数据库后返回的数据的集合，ResultSet对象具有指向当前数据行的指针。通过该对象的next()方法，使得指针指向下一行，然后将数据以列号或者字段名取出。 
（6）关闭数据库连接，使用connection.close()方法关闭。


2、如何在Spring中使用JDBC
    Spring中的JDBC连接与直接使用JDBC去连接还是有所差别，
    Spring对JDBC做了大量封装，消除了以上获取连接、关闭连接等冗余代码，使得开发量大大减小。 
    Spring的JDBC框架承担了资源管理和异常处理的工作，从而简化了JDBC代码，让我们只需编写从数据库读写数据的必需代码。
    Spring将数据访问的样板代码抽象到模板类之中。Spring为JDBC 提供了三个模板类供选择： 
    JdbcTemplate：最基本的Spring JDBC模板，这个模板支持简单的JDBC数据库访问功能以及基于索引参数的查询；     
    NamedParameterJdbcTemplate：使用该模板类执行查询时可以将值以命名参数的 形式绑定到SQL中，而不是使用简单的索引参数； 
    SimpleJdbcTemplate：该模板类利用Java 5的一些特性如自动装箱、泛型以及可变 参数列表来简化JDBC模板的使用。但是从Spring 3.1开始，SimpleJdbcTemplate已经被废弃了.
    并且只有在你需要使用命名参数的时候，才需要使用NamedParameterJdbcTemplate。这样的话，对于大多数的JDBC任务来说，JdbcTemplate就是最好的可选方案。



3、Spring JDBC的工作原理


4、总结
spring的JdbcTemplate用起来其实还是比较好用的，起码对原始的jdbc操作已经封装的很好了，尤其是设计里面代理模式的完美应用，各种callback。
虽说我们现在做项目都是使用mybatis或者hibernate这种ORM框架的居多，但是偶尔我们还是需要返璞归真一下，去探究底层的实现原理，加深自己的理解，提高自己的水平。


5、快速开发神器，Spring-Data-JPA初探


6、Spring Data JPA的工作原理


7、Spring Data JPA和Mybatis的对比


8、总结