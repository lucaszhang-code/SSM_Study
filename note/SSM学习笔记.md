# SSM学习笔记

## Maven简单配置

Maven是专属于Java的包、依赖管理工具，通过xml进行配置，这里直接省略用法，后面Spring部分会多次用到

注意配置自己的Maven路径，虽然IDEA自带Maven包，但是我们可能会对Maven本地仓库和镜像等进行配置

![](./assets/Maven仓库选择.png)

## Bean编写

如下是一些类的配置方式

### 1.普通类

`<bean id="happyComponent" class="com.itguigu.ioc_01.HappyComponent"></bean>`配置的是`HappyComponent`这样一个无参类，`id`是他的唯一标识符，以后的引用或者调用都是它，`class`是组件的全限定符，是这个类的位置

### 2.静态工厂类

`<bean class="com.itguigu.ioc_01.ClientService" factory-method="createInstance" id="clientService"></bean>`是静态工厂类的配置方式，这里解释一下**工厂模式** 

- 工厂模式将对象的**具体创建过程**封装在工厂类中，而**用户只通过工厂接口来请求对象**，不需要了解如何创建这些对象

所以简单来说就是接口的封装，用户不能自己new出对应的类，只能通过预先设定好的接口函数去操作

`factory-method="createInstance"`限定这个类是静态工厂类

### 3.非静态工厂类

```xml
<!--    3.非静态工厂如何声明ioc配置-->
    <!--    3.1配置工厂类的组件-->
    <bean id="DefaultServiceLocator" class="com.itguigu.ioc_01.DefaultServiceLocator"></bean>
    <!--    3.2通过制定非静态工厂对象和方法名 来配置生成的ioc信息-->
    <bean id="clientService2" factory-bean="DefaultServiceLocator" factory-method="createClientServiceInstance"></bean>
```

### 4.有参构造类

已知`userDao`是自定义的数据类型，因此需要声明配置，而`userService`类在构造函数传入了自定义的`userDao`，因此，使用`constructor-arg`定义传参变量

```xml
    <bean id="userDao" class="com.itguigu.ioc_02.UserDao"></bean>

    <bean id="userService" class="com.itguigu.ioc_02.UserService">
        <!--        构造参数传值di的配置
        constructor-arg g构造参数传值的di配置
        value 直接属性值 String name = "你好"
        ref 引用其他的bean的id值-->
        <constructor-arg ref="userDao"></constructor-arg>
    </bean>
```

### 5.多个参数注入

已知`UserService`类拥有`userDao`、`age`、`name`三个变量，通过有参构造给变量赋值

```java
public class UserService {
    private UserDao userDao;
    private int age;
    private String name;

    public UserService(int age , String name ,UserDao userDao) {
        this.userDao = userDao;
        this.age = age;
        this.name = name;
    }
}
```

此时的Bean有三种写法

#### 方案一：构造参数的顺序填写

这种方法要求严格按照传参顺序编写，`value`是传入的默认值，如果传入的是对象类，则用`ref`写出对象的id

```xml
    <bean id="userServicel" class="com.itguigu.ioc_02.UserService">
        <!--       方案一：构造参数的顺序填写-->
        <constructor-arg value="18"></constructor-arg>
        <constructor-arg value="二狗子"></constructor-arg>
        <constructor-arg ref="userDao"></constructor-arg>
    </bean>
```

#### 方案二：构造参数的名字填写  推荐！

`name`就是传参的变量名，这样可以随意更换`constructor-arg`的顺序

```xml
    <bean id="userServicel" class="com.itguigu.ioc_02.UserService">
        <!--       方案二：构造参数的名字填写  推荐！-->
        <constructor-arg name="name" value="二狗子"></constructor-arg>
        <constructor-arg name="age" value="18"></constructor-arg>
        <constructor-arg ref="userDao"></constructor-arg>
    </bean>
```

#### 方案三：构造参数的下角标指定填写，不用考虑index

根据变量下标，index从0开始，不如第二种方法，因为你还得记住变量的顺序

```xml
    <bean id="userServicel" class="com.itguigu.ioc_02.UserService">
        <!--       方案三：构造参数的下角标指定填写，不用考虑index-->
        <constructor-arg index="1" value="二狗子"></constructor-arg>
        <constructor-arg index="0" value="18"></constructor-arg>
        <constructor-arg index="2" ref="userDao"></constructor-arg>
    </bean>
```

### 6.触发setter方法进行注入

单独声明`MovieFinder`类

```java
public class MovieFinder {
}
```

在`SimpleMovieLister`类中定义两个`setter`方法，专门用于更改数据

```java
public class SimpleMovieLister {
    private MovieFinder movieFinder;
    private String movieName;

    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    // business logic that actually uses the injected MovieFinder is omitted...
}
```

对于`setter`函数我们有专门的Bean配置方式

先声明`MovieFinder`类，然后使用`property`标签定义变量，`name`属性名就是setter方法的去掉set和首字母小写的名，比如你的函数名叫`setMovieFinder`，那么name就可以写成`movieFinder`

```xml
    <bean id="MovieFinder" class="com.itguigu.ioc_02.MovieFinder"></bean>

    <bean id="SimpleMovieLister" class="com.itguigu.ioc_02.SimpleMovieLister">
        <!--        name 属性名 setter方法的去掉set和首字母小写的名 setMovieFinder -> movieFinder
        value | ref 二选一 value="直接属性值“ ref="其他bean的id"-->
        <property name="movieName" value="消失的她"></property>
        <property name="movieFinder" ref="MovieFinder"></property>
    </bean>
```

## 获取ioc管理的类

如图创建`HappyComponent`类，里面有一个自定义函数`dowork`

```java
public class HappyComponent {

    //默认包含无参数构造函数

    public void doWork() {

        System.out.println("HappyComponent.doWork");
    }
}
```

简单配置Bean

```xml
<bean id="happyComponent" class="com.itguigu.ioc_03.HappyComponent"></bean>
```

### 创建ioc容器并且读取配置文件

#### 方式1：直接创建容器，并且指定配置文件

通过构造函数，完成ioc对象的声明，配置文件的指定到刷新

```java
 ApplicationContext context = new ClassPathXmlApplicationContext("spring-03.xml");
```

#### 方式2：先创建ioc对象，再指定配置文件，再刷新

顾名思义，`new`出`ClassPathXmlApplicationContext`对象，通过`setConfigLocation`指定配置文件，`refresh`手动刷新

```java
 ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
 applicationContext.setConfigLocation("spring-03.xml");
 applicationContext.refresh();
```

一般如果不会更换配置文件，通常使用第一种方法

### 获取组件

#### 方案一：直接根据bean id获取,返回值类型是Object,需要强制转化类型

默认返回的`happyComponent`变量是`Object`，需要强制转换类型

```java
HappyComponent happyComponent = (HappyComponent) applicationContext.getBean("happyComponent");
```

#### 方案二：跟据beanId，同时指出bean的类型class,推荐

`getBean`方法里面跟上组件的类型，无需强转类型

```java
HappyComponent happyComponent1 = applicationContext.getBean("happyComponent", HappyComponent.class);
```

#### 方案三：直接根据类型获取,同一个类型在ioc容器中只能有一个bean！！！不推荐

如果ioc容器存在多个bean会出现异常

```java
HappyComponent happyComponent2 = applicationContext.getBean(HappyComponent.class);
```



