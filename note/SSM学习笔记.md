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

### 高级特性：组件作用域和周期的配置

一个组件的周期通常包括`init`(创建),服务，`destroy`(销毁)；我们可以在Bean中配置相应的`init`和`destroy`回调函数

以下是`JavaBean`类的`init`和`destroy`方法

```java
public class JavaBean {
    // 必须是void和public以及无参数
    public void init() {
        System.out.println("JavaBean init");
    }

    // 销毁方法
    public void destroy() {
        System.out.println("JavaBean destroy");
    }
}
```

在XML文件中使用`init-method`和`destroy-method`去配置，组件将会自动在相应生命周期调用相应的回调函数

```xml
 <!--    通过init-method声明初始化变量名 通过destroy-method声明销毁方法变量名-->
    <bean id="JavaBean" class="com.itguigu.ioc_04.JavaBean" init-method="init" destroy-method="destroy"></bean>
```

### **FactoryBean**的特性和使用

在一些复杂的项目中，类的创建和配置是很复杂的，我们不想将类的创建和配置与逻辑代码混在一起，这不方便后期的维护和管理，因此我们会将类的创建和配置统一封装并交给`FactoryBean`，让程序员不必关注创建的复杂过程，可以直接获取对象并调用它的方法

以下是`JavaBean`类

```java
public class JavaBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

我们将`JavaBean`的创建交给`JavaBeanFactoryBean`，并且需要重写`getObject`和`getObjectType`函数

```java
public class JavaBeanFactoryBean implements FactoryBean<JavaBean> {
    @Override
    public JavaBean getObject() throws Exception {
        // 使用自己的方式实例化对象
        JavaBean javaBean = new JavaBean();
        javaBean.setName(value);
        return javaBean;
    }

    @Override
    public Class<?> getObjectType() {
        return JavaBean.class;
    }
}
```

xml文件的配置

```xml
 <bean id="javaBean" class="com.itguigu.ioc_05.JavaBeanFactoryBean"></bean>
```

进行代码的测试

```java
 @Test
    public void test_05(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-05.xml");

       // 读取组件
        JavaBean javaBean = applicationContext.getBean("javaBean", JavaBean.class);
        System.out.println(javaBean);
    }
```

但是`JavaBean`类存在私有成员变量`name`我们该如何传参呢？

你可能会写出以下代码

```xml
    <bean id="javaBean" class="com.itguigu.ioc_05.JavaBeanFactoryBean">
        <!--        此位置的属性是给JavaBean工厂配置的，而不是getObject-->
        <property name="value" value="Lucas"></property>
    </bean>
```

显然我们配置的是`JavaBeanFactoryBean`类，你这样写参数只会给`JavaBeanFactoryBean`，而不是`JavaBean`，因此我们可以对`JavaBeanFactoryBean`稍做配置

给那个类自定义私有成员变量`value`，并且接收外部传参，也就是我们配置的`property`

在`getObject`函数中` javaBean.setName(value);`从而实现`javaBean`类参数的注入

```java
public class JavaBeanFactoryBean implements FactoryBean<JavaBean> {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public JavaBean getObject() throws Exception {
        // 使用自己的方式实例化对象
        JavaBean javaBean = new JavaBean();
        javaBean.setName(value);
        return javaBean;
    }

    @Override
    public Class<?> getObjectType() {
        return JavaBean.class;
    }
}
```

运行结果

`JavaBean{name='Lucas'}`

### 基于XMl方式整合三层架构

#### 需求分析

搭建一个三层架构案例，模拟查询全部学生（学生表）信息，持久层使用JdbcTemplate和Druid技术，使用XML方式进行组件管理！

#### 结构图

![XML实现三层架构图](./assets/XML实现三层架构图.png)

在一个后端中我们通常将业务分成三层架构，分别是**控制层**，**业务层**，**DAO层**

##### DAO层

DAO的全称是**DataBase Access Object** 数据库访问对象，主要负责连接数据库，完成数据库的增删改查业务

##### 业务层（Service）

业务层负责处理DAO层传递过来的原始数据，对数据进行深加工

##### 控制层（Controller）

控制层负责通过网络与前端进行交流，传递业务层加工过的数据

---

下面我将举一个例子方便理解这三个业务层的关系

假设数据库里面存储着一个人的工资表，其中包括基础工资、绩效、补贴等信息，DAO层负责从数据库里面原封不动的取出这些数据，并把他们丢给业务层，业务层负责处理这些未加工的数据，比如计算工资总额，进行扣税、缴纳社保等操作，并将结果交给控制层，控制层负责与前端沟通，如果前端需要工资总额的数据，就将业务层处理过的数据交给前端展示

理清楚这些关系后我们回到业务上面，假设我们的目的是获取全部学生数据，分别进行三层架构的编写

#### DAO层代码

1.编写`StudentDao`接口，对外暴露获取DAO层数据的方法

```java
public interface StudentDao {
    List<Student> queryAll();
}
```

2.编写`StudentDao`的具体实现方法,类命名为`StudentDaoImpl`，继承自`StudentDao`

由于我们DAO层是通过`jdbcTemplate`的方法进行注入，需要构造他的`Setter`方法

并且我们需要重写`queryAll`方法

特别说明这两行代码

```java
String sql = "select id, name, age, class as classes from students";
List<Student> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
```

由于class是java 的关键字，因此数据库的字段名取别名为classes

通过BeanPropertyRowMapper帮助我们自动映射列和属性值，我们不需要根据字段名取出对应数据

```java
public class StudentDaoImpl implements StudentDao {

    // 注入jdbcTemplate对象
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> queryAll() {
        String sql = "select id, name, age, class as classes from students";
        List<Student> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        System.out.println(students);
        return students;
    }
}
```

#### Service层代码

1.编写`StudentService`接口，负责将获取业务层数据的方法对外暴露出去

```java
public interface StudentService {
    List<Student> findAll();
}
```

2.实现接口的具体方法类

我们也需要提供`StudentDao`的`Setter`方法，并且重写`findAll`函数，调用DAO层的`queryAll`方法

```java
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = studentDao.queryAll();
        System.out.println("studentService:" + students);
        return students;
    }
}
```

#### Controller层代码

1.由于控制层只负责网络数据传输，因此他不需要接口

也需要提供`StudentService`的`Setter`方法

```java
public class StudentController {
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    private StudentService studentService;

    public void findAll(){
        List<Student> all = studentService.findAll();
        System.out.println("最终学员数据为：" + all);
    }
}
```

#### XML编写

我们通常会将数据库的用户名、密码、URL、驱动信息单独写成`jdbc.properties`配置文件

```
itguigu.url=jdbc:mysql://localhost:3306/studb
itguigu.driver=com.mysql.cj.jdbc.Driver
itguigu.username=root
itguigu.password=123456
```

使用`<context:property-placeholder location="classpath:jdbc.properties" />`引入配置文件

```xml
<context:property-placeholder location="classpath:jdbc.properties" />
<!--    配置druid-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="url" value="${itguigu.url}"></property>
    <property name="username" value="${itguigu.username}"></property>
    <property name="password" value="${itguigu.password}"></property>
    <property name="driverClassName" value="${itguigu.driver}"></property>
</bean>

<!--    jdbcTemplate-->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="dataSource"></property>
</bean>
<!--    dao配置 di jdbcTemplate-->
<bean id="studentDao" class="com.itguigu.dao.impl.StudentDaoImpl">
    <property name="jdbcTemplate" ref="jdbcTemplate"></property>
</bean>
<!--    service配置di dao-->
<bean id="studentService" class="com.itguigu.service.impl.StudentServiceImpl">
    <property name="studentDao" ref="studentDao"></property>
</bean>
<!--    controller配置di service-->
<bean id="studentController" class="com.itguigu.contoller.StudentController">
    <property name="studentService" ref="studentService"></property>
</bean>
```

## 注解方式管理bean

在以前，我们都是通过配置xml文件的方式管理bean，这是很麻烦的，我们可以通过注解的方式进行bean配置

### 普通组件

普通组件采用`@Component`标识符，他的id是类名首写字母小写，class是类组件自动配置

如果要单独指定组件的id名，可以采用`@Component(value="你的名字")`或者`@Component("你的名字")`这种方式自定义

```java
@Component  //<bean id="commonComponent" class=类组件>

/**
 * 1.标记注解
 * 2.配置指定包
 */
public class CommonComponent {

}
```

### DAO组件

DAO组件使用`@Repository`标识符

```java
@Repository
public class XxxDao {
}
```

### Service组件

Service组件使用`@Service`标识符

```java
@Service
public class XxxService {
}
```

### Controller组件

Controller组件使用`@Controller`标识符

```java
@Controller
public class XxxController {
}
```

### XML配置

当然我们还是需要对xml文件进行简单的配置

base-package是进行bean管理的位置，如果是整个软件包，他会对里面所有做了注解的组件统一进行管理

```xml
   <!--    1.普通配置包扫描
            指定ioc容器去哪找注解类
    -->
    <context:component-scan base-package="com.itguigu.ioc_01"/>

    <!--    2.指定包但是排除注解-->
    <context:component-scan base-package="com.itguigu">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
    </context:component-scan>

    <!--    3.指定包含注解
    base-package指定包下所有的注解都生效！
    use-default-filters="false"指定包的所有注解都不生效-->
    <context:component-scan base-package="com.itguigu" use-default-filters="false">
        <!--        只扫描次包下的注解-->
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
    </context:component-scan>
```

### 组件作用域和周期

使用注解同样也可以配置周期对应的回调函数

#### 组件初始化回调函数

```java
@PostConstruct
public void init(){
    System.out.println("init");
}
```

#### 组件销毁回调函数

```java
@PreDestroy
public void destroy(){
    System.out.println("destroy");
}
```

#### 单例和多例模式切换

```java
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)		单例模式，默认
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)		多例模式
@Component
public void JavaBean{}
```

### 引用类型自动装配

我们知道在xml配置bean的时候，每个类需要对应的setter方法进行注入，例如

声明接口

```java
public interface UserService {
    public String show();
}
```

实现方法

```java
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String show() {
        return "UserServiceImpl show";
    }
}
```

Controller层

```java
@Controller
public class UserController {
    private UserService userService;
    
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public void show(){
        // 调用业务层的方法
        String show = userService.show();
        System.out.println(show);
    }
}
```

Test调用Controller

```java
    public void test03(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-03.xml");
        UserController userController = context.getBean(UserController.class);
        userController.show();
    }
```

但是通过注解的方式我们就不需要再编写setter方法

#### 使用`@Autowired`

```java
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public void show(){
        // 调用业务层的方法
        String show = userService.show();
        System.out.println(show);
    }
}
```

#### 使用`@Qualifier("")`自定义bean名称

虽然对于组件我们不用单独指定id，因为他的id默认是首字母的小写，但是一个接口可能会被多个类重写，如果我们不指定bean名称，DI就无法找到指定的组件

例如以下是一个接口

```java
public interface UserService {
    public String show();
}
```

下面两个类分别重写了show方法

```java
@Service
public class NewUserServiceImpl implements UserService {

    @Override
    public String show() {
        return "NewUserServiceImpl show";
    }
}
```

```java
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String show() {
        return "UserServiceImpl show";
    }
}

```

如果我们只写一个`UserService userService`，就会报错

```java
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public void show(){
        // 调用业务层的方法
        String show = userService.show();
        System.out.println(show);
    }
}
```

修改办法，加上` @Qualifier("userServiceImpl")`这样就指定了组件

```java
@Controller
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    public void show(){
        // 调用业务层的方法
        String show = userService.show();
        System.out.println(show);
    }
}
```

#### 使用`@Resource(name = "")`简化写法

当然我们也有简化写法，比如`@Resource(name = "userServiceImpl")`，但是`@Resource`是一个包里面的内容

```java
@Controller
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    public void show(){
        // 调用业务层的方法
        String show = userService.show();
        System.out.println(show);
    }
}
```



```xml
<dependency>
    <groupId>jakarta.annotation</groupId>
    <artifactId>jakarta.annotation-api</artifactId>
    <version>3.0.0</version>
</dependency>
```

### bean属性赋值，基本属性赋值

#### 方案一：直接赋值

```java
 private String name = "Lucas";
```

#### 方案二：Value注解

```java
@Value("20")
private int age;
```

当然使用`@Value`注解一般是引入外部配置

例如`jdbc.properties`文件有以下信息

```properties
jdbc.password = 123456
```

Value注入可以写成

其中` @Value("${jdbc.username:admin}")`是指定默认值，为了防止配置文件里面没有值

```java
 @Value("${jdbc.username:admin}")
 private String userName;
 @Value("${jdbc.password:000000}")
 private String password;
```

### 使用注解的方式配置bean和DI，完成三层架构

由于在xml配置，完成三层架构的章节我们分析过这个案例，这里我就不再作说明，直接给出代码

![注解方式实现三层架构图](./assets/XML实现三层架构图.png)

#### Pojo层

```java
package com.itguigu.pojo;

public class Student {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String classes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", classes='" + classes + '\'' +
                '}';
    }
}
```

#### Dao层

##### 接口

```java
public interface StudentDao {
    List<Student> queryAll();
}
```

##### 实现类

```java
@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> queryAll() {
        String sql = "select * from students";
        List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        System.out.println("dao: " + studentList);
        return studentList;
    }
}
```

#### Service层

##### 接口

```java
public interface StudentService {
    List<Student> findAll();
}
```

##### 实现类

```java
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> findAll() {
        List<Student> studentList = studentDao.queryAll();
        System.out.println("service: " + studentList);
        return studentList;
    }
}
```

#### Controller层

```java
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    public void findAllStudents() {
        List<Student> studentList = studentService.findAll();
        System.out.println("controller: " + studentList);
    }
}
```

#### Test测试类

```java
public class SpringIocTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-01.xml");
        StudentController studentController = context.getBean(StudentController.class);
        studentController.findAllStudents();

    }
}
```

#### 配置xml

在这里`DruidDataSource`和`JdbcTemplate`都是New出来的，只能采取xml配置bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.itguigu"></context:component-scan>
    <context:property-placeholder location="jdbc.properties"></context:property-placeholder>

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${itguigu.driver}"></property>
        <property name="username" value="${itguigu.username}"></property>
        <property name="password" value="${itguigu.password}"></property>
        <property name="url" value="${itguigu.url}"></property>
    </bean>
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
</beans>
```

#### 配置文件

```properties
itguigu.url=jdbc:mysql://localhost:3306/studb
itguigu.driver=com.mysql.cj.jdbc.Driver
itguigu.username=root
itguigu.password=123456
```

## 基于配置类管理Bean

在之前的项目中，我们是采取注解+xml'的方式配置的管理DI，在未来的项目中，我们更多采用的方式是抛弃xml方式配置，转而采取配置类方式管理和配置Bean

### 配置类+注解

例如我们有一个配置类`JavaConfiguration`，专门用于配置类

| 配置类                                                 | XML                                                          |
| ------------------------------------------------------ | ------------------------------------------------------------ |
| `@ComponentScan(value = "com.itguigu.ioc_01")`         | `<context:component-scan base-package="com.itguigu.ioc_01"></context:component-scan>` |
| `@PropertySource(value = "classpath:jdbc.properties")` | `<context:property-placeholder location="jdbc.properties"></context:property-placeholder`> |
| `@Configuration`                                       | 无                                                           |

```java
@Configuration
@PropertySource(value = "classpath:jdbc.properties")
@ComponentScan(value = "com.itguigu.ioc_01")
public class JavaConfiguration {
    
}
```

例如，我们需要使用`Druid`连接池，就有以下写法

`@Value`注解在这里就可以引用外部配置文件

函数名默认就是Bean的id值，可以在函数里面实现具体的方法

凡是在配置类里面的方法都必须使用`@Bean`注解

`@Bean(value = "Lucas", initMethod = "", destroyMethod = "")`,其中`value`是Bean的别名，`initMethod`是初始化回调函数名，`destroyMethod`是销毁回调函数名

```java
@Configuration
@PropertySource(value = "classpath:jdbc.properties")
@ComponentScan(value = "com.itguigu.ioc_01")
public class JavaConfiguration {
    @Value("${itguigu.url}")
    private String url;
    @Value("${itguigu.username}")
    private String username;
    @Value("${itguigu.password}")
    private String password;
    @Value("${itguigu.driver}")
    private String driverClassName;
    
    @Bean(value = "Lucas", initMethod = "", destroyMethod = "")
    public DruidDataSource dataSource() {
        // 实现具体的实例化过程
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
```

如需在配置类里面引用其他类的方法，比如`JdbcTemplate`需要引用`DruidDataSource`类

只需要直接调用就可以了

```java
@Bean
public JdbcTemplate getJdbcTemplate() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    // 需要ioc里面的其他组件
    // 方案一：如果其他组件也是@Bean方法，可以直接调用 | 从ioc容器获取组件
    jdbcTemplate.setDataSource(dataSource1());
    return jdbcTemplate;
}
```

也有别的写法，直接将参数传进去

```java
@Bean
// Lucas是取的别名
public JdbcTemplate getJdbcTemplate1(DataSource Lucas) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    // 需要ioc里面的其他组件
    // 方案二：形参列表声明的想要的组件类型，可以是一个，也可以是多个！ioc容器也会注入
    // 形参变量注入，要求必须有对应的类型和组件，如果没有抛异常
    // 如果有多个，可以写形参名称等同于对应bean标识即可
    jdbcTemplate.setDataSource(Lucas);
    return jdbcTemplate;
}
```

### 创建ioc容器

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(配置类);
```

当然如果拆开写

```java
AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
annotationConfigApplicationContext.register(JavaConfiguration.class);
annotationConfigApplicationContext.refresh();
```

剩下的就一样了

### 使用配置类+注解的方式完成三层架构

由于大部分代码并没有区别，这里只展示配置类的代码

```java
@Configuration
@ComponentScan(basePackages = "com.itguigu")
@PropertySource(value = "classpath:jdbc.properties")
public class JavaConfig {
    @Value("${itguigu.driver}")
    private String driver;
    @Value("${itguigu.url}")
    private String url ;
    @Value("${itguigu.username}")
    private String username;
    @Value("${itguigu.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate JdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
```

### @Import扩展

`@Import` 注释允许从另一个配置类加载 `@Bean` 定义，如以下示例所示：

```java
@Configuration
public class ConfigA {

  @Bean
  public A a() {
    return new A();
  }
}

@Configuration
@Import(ConfigA.class)
public class ConfigB {

  @Bean
  public B b() {
    return new B();
  }
}
```

现在，在实例化上下文时不需要同时指定 `ConfigA.class` 和 `ConfigB.class` ，只需显式提供 `ConfigB` ，如以下示例所示：

```java
public static void main(String[] args) {
  ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);

  // now both beans A and B will be available...
  A a = ctx.getBean(A.class);
  B b = ctx.getBean(B.class);
}
```

