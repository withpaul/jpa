package jpa.basic;

import org.hibernate.type.LocalDateType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // persistence.xml에 있는 name='hello'를 넣어줌
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // db당 1나임
        EntityManager em = emf.createEntityManager(); // 고객의 요청이 올때마다 생성되고 사라짐(스레드간 절대 공유하면안됨)

        // jpa의 모든 변경은 transaction 안에서 돌아가야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setName("zzz"); // 위에 jpa find로 갖고오면 jpa가 관리를 해주고, 변경 내역이  있으면 commit할때 없데이트함
            member.setRollType(RollType.ADMIN);
            member.setCreateDate(new Date());
            member.setLocalDate(LocalDate.now());
            member.setLocalDateTime(LocalDateTime.now());
            em.persist(member); // update시에는 persist 안해도됨

            // JPQL (단순한 조회는 위에 FIND쓰면 되는데 나이가 18살인 회원 조회등 조건이 복잡한 거 할때 사용하는게 JPQL이다)
            // JPQL은 엔티티 객체를 대상으로쿼리(방언지원), SQL은 테이블을 대상으로쿼리
            // 즉, JPQL은 객체지향 쿼리이다.

            List<Member> result = em.createQuery("select m from jong as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("cout:"+ result.isEmpty());
            for (Member membmer : result) {
                System.out.println("membmer.getName() = " + membmer.getName());
            }
//            em.remove(member);
            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            tx.rollback();
        } finally {
            em.close(); // 항상 닫아줘야한다
        }
        emf.close(); // 항상 닫아줘야한다.
    }
}