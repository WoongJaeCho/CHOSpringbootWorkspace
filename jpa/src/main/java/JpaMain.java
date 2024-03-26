import entity.Customer;
import entity.Locker;
import entity.Major;
import entity.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JpaMain {

    public static void init(EntityManager em){

        Student stu1 = new Student("김씨","1학년");
        Student stu2 = new Student("이씨","2학년");
        Student stu3 = new Student("박씨","3학년");

        Major m1 = new Major("컴싸","소프르엔지니어링");
        em.persist(m1); // @Id 값 => 1을 가져오기 위해 

//        stu1.setMajorID(m1.getMajorID());
//        stu2.setMajorID(m1.getMajorID());
//        stu3.setMajorID(m1.getMajorID());

        stu1.setMajor(m1);
        stu2.setMajor(m1);
        stu3.setMajor(m1);

        em.persist(stu1);
        em.persist(stu2);
        em.persist(stu3);
    }

//    public static List<Customer> initCustomer(){
//        List<Customer> list = new ArrayList<>();
//        list.add(new Customer("10100","test1"));
//        list.add(new Customer("10101","test2"));
//        list.add(new Customer("10102","test3"));
//        list.add(new Customer("10103","test4"));
//        list.add(new Customer("10104","test5"));
//        list.add(new Customer("10105","test6"));
//        return list;
//    }

    public static void main(String[] args) {
        // 객체 sessionFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
        EntityManager em = emf.createEntityManager(); // session 객체
        EntityTransaction tx = em.getTransaction();

        tx.begin(); // start Transction;
        try {
            //init(em);

            Student stu = em.find(Student.class, 2L);
            Locker locker = em.find(Locker.class, 3L);


            //Student stu = em.find(Student.class, 1L);
            //Locker locker = new Locker(100);
            //em.persist(locker); //@Id 값 받아오고
            stu.setLocker(locker); //locker_id


//            List<Student> studentList = em.find(Major.class, 1L).getStudents();
//            studentList.forEach(s -> System.out.println("s = " + s) );


//            Student findStudent = em.find(Student.class,1L);
//            System.out.println("findStudent = "+ findStudent);
//
//            Major major = findStudent.getMajor();
//            System.out.println("major.getCategory() = " + major.getCategory());
            
//            Major findMajor = em.find(Major.class, findStudent.getMajor());
//            System.out.println("findMajor = " + findMajor);
            
//            Customer c = new Customer();
//            c.setName("test");
//            em.persist(c);
//
//            tx.commit();

//            String query = "select c from Customer c where c,name = :name";
//            Customer findCustomer = em.createQuery(query, Customer.class)
//                    .setParameter("name", "test2").getSingleResult();
//            System.out.println("findCustomer = " + findCustomer);

//            List<Customer> list = initCustomer();
//            list.forEach(c -> em.persist(c));
//
//            System.out.println("========= start ===========");
//
//            // query 문 실행 전에 자동으로 em.flush
//            Query query = em.createQuery("select c from Customer c", Customer.class);
//            List<Customer> customers = query.getResultList();
//
//            System.out.println("========= end ===========");
//
//            customers.forEach(c -> System.out.println("c = "+ c));


//            System.out.println("============================");


//            Customer c = new Customer("ID001", "test");
//            em.persist(c);
//            System.out.println("====================");
//            em.detach(c);
//            em.flush();
//            System.out.println("====================");
//            tx.commit();

            // 영속성 컨테이너에 값을 저장하는 두가지 방법 em.persist() / em.find()


//            Customer findCustomer = em.find(Customer.class,"ID002"); // 비영속 상태
//            System.out.println("findCustomer = "+findCustomer);
//
//            Customer c = new Customer("ID010","test");
//            em.persist(c); // 영속 상태
//            em.detach(c); // 준영속 상태
//
//            em.flush(); // db와 영속성 컨테이너의 데이터를 동기화 해준다.
//                        //쓰기 지연 저장소에 있는 쿼리를 즉시 날린다.
//            em.clear(); // 영속성 컨테이너 초기화 -> 준영속 상태
//            System.out.println("=============================");
//
//            findCustomer.setName("홍길동1");    // 변경 감지 : 최초 영속성 컨테이너 저장되어 있는 스냅샷 객체랑 비교
//                                            // 쓰기 지연 저장소 update 저장
//            System.out.println("findCustomer = "+findCustomer);

//            Customer c1 = new Customer("ID005","LEE");
//            Customer c2 = new Customer("ID006","HONG");
//            em.persist(c1); //영속성 컨테스트에 객체를 저장
//            em.persist(c2); //영속성 컨테스트에 객체를 저장
//            Customer c1 = em.find(Customer.class,"ID001");
//            em.remove(c1); //sql 쓰기지연에 쿼리문 저장
//            System.out.println("c1 = "+ c1);
//
//            Customer findCustomer = em.find(Customer.class, "ID001");
//            System.out.println("findCustomer = " + findCustomer);
            tx.commit(); //commit; 쓰기 지연 저장소에 있는 sql 쿼리문(insert, update, delete) 한꺼번에 나간다.
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
