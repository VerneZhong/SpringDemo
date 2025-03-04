package com.learn.test;

import com.google.common.collect.Lists;
import com.learn.spring.data.jpa.entity.Student;
import com.learn.spring.data.jpa.repository.*;
import com.learn.spring.data.jpa.service.StudentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.zxb
 * @date 2019-01-20 14:58:35
 */
public class SpringDataJpaTest {

    private ApplicationContext context = null;

    private StudentRepository repository;

    private StudentService studentService;

    private StudentPagingAndSortingRepository sortingRepository;

    private StudentJpaRepository studentJpaRepository;

    private StudentJpaSpecificationExecutor studentJpaSpecificationExecutor;

    private UserJpaRepository userJpaRepository;

    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        repository = context.getBean(StudentRepository.class);
        studentService = context.getBean(StudentService.class);
        sortingRepository = context.getBean(StudentPagingAndSortingRepository.class);
        studentJpaRepository = context.getBean(StudentJpaRepository.class);
        studentJpaSpecificationExecutor = context.getBean(StudentJpaSpecificationExecutor.class);
        userJpaRepository = context.getBean(UserJpaRepository.class);
    }

    @After
    public void tearDown() {
        context = null;
        repository = null;
        studentService = null;
        sortingRepository = null;
        studentJpaRepository = null;
        studentJpaSpecificationExecutor = null;
        userJpaRepository = null;
    }

    @Test
    public void test() {
        System.out.println(repository);
        Student student = repository.findByName("张三");
        System.out.println(student);
    }

    @Test
    public void testQuery() throws ParseException {
        // 以什么开头
//        List<Student> students = repository.findByNameStartingWithAndAgeLessThan("zxb", 24);
        // 以什么结尾
//        List<Student> students = repository.findByNameEndingWithAndAgeLessThan("1", 24);
        // in or
//        List<Student> students = repository.findByNameInOrAgeLessThan(Lists.newArrayList("1", "2", "3"), 24);
        // in and
//        List<Student> students = repository.findByNameInAndAgeLessThan(Lists.newArrayList("zxb", "zxb2", "zxb3"), 24);

        // @Query 根据条件查询-索引参数
//        List<Student> students = repository.queryStudentByCondition("zxb5", 24);
        // @Query 根据条件查询-命名参数
//        List<Student> students = repository.queryStudentByCondition2("zxb5", 24);
        // @Query 根据条件查询-like模糊查询
//        List<Student> students = repository.queryStudentLike("zxb");
        // between ? and ?  2019-01-20 20:02:24
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Student> students = repository.findByCreateDateBetween(sdf.parse("2019-01-20 18:02:24"), sdf.parse("2019-01-20 20:02:24"));

        System.out.println(students);
    }

    @Test
    public void testQuery2() {
//        Student s = repository.getStudentById(1);
//        @Query 无条件查询
        Student s = repository.queryStudentMaxId();
        System.out.println(s);
    }

    @Test
    public void testCount() {
        long count = repository.getCount();
        System.out.println(count);
    }

    @Test
    public void testSaveOrUpdate() {

//        studentService.updateAgeById(1, 20);

//        studentService.deleteById(9);
//        Student s = repository.queryStudentMaxId();
//        System.out.println(s);

        Student s = new Student();
        s.setName("张三");
        s.setAge(28);
        s.setCreateDate(new Date());

        Student s2 = new Student();
        s2.setName("李四");
        s2.setAge(25);
        s2.setCreateDate(new Date());

        studentService.save(Lists.newArrayList(s, s2));
    }

    @Test
    public void testPage() {
        // page:index是从0开始
        Pageable pageable = PageRequest.of(1, 10);
        Page<Student> page = sortingRepository.findAll(pageable);

        print(page);
    }

    @Test
    public void testSort() {
        // 排序
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "createDate");
        Sort sort = Sort.by(order);
        // page:index是从0开始
        Pageable pageable = PageRequest.of(0, 5, sort);
        Page<Student> page = sortingRepository.findAll(pageable);

        print(page);
    }

    @Test
    public void testFind() {
//        Student student = studentJpaRepository.findOne(1);
//        System.out.println(student);

//        List<Student> students = studentJpaRepository.findAll();
//        System.out.println(students);

        Student student = new Student();
        student.setId(1);
        Example<Student> example = Example.of(student);
        boolean b1 = studentJpaRepository.exists(example);
        student.setId(100);
        boolean b2 = studentJpaRepository.exists(example);
        System.out.println(b1 + "--" + b2);
    }

    @Test
    public void testComplexQuery() {
        // 分页
        // 排序
        // 查询条件 age < 25
        // 排序
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "createDate");
        Sort sort = Sort.by(order);
        // page:index是从0开始
        Pageable pageable = PageRequest.of(0, 5, sort);

        /**
         * 查询条件
         * root:  就是我们要查询的类型（Student）
         * query: 添加查询条件
         * cb:    构建Predicate
         */
//        Specification<Student> specification = (root, query, cb) -> {
//            // root (student age)
//            Path path = root.get("age");
//            return cb.lt(path, 25);
//        };

        final List<Long> ids = Lists.newArrayList(1l, 2l, 3l);
        Specification<Student> specification = new Specification<Student>() {
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.like(root.<String>get("name"), "%钟%");
                criteriaQuery.where(predicate);
//                return criteriaBuilder.gt(root.<Number>get("age"), 25);
                return null;
            }
        };

        Page<Student> page = studentJpaSpecificationExecutor.findAll(specification, pageable);
        print(page);
    }

    private void print(Page page) {
        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询的当前第" + (page.getNumber() + 1) + "页");
        System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
        System.out.println("查询的数据：" + page.getContent());
    }
}
