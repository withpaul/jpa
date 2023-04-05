package jpa.basic;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 */
@Entity(name="jong") //jpa가 관리하겠다는 어노테이션, name이 설정될 경우 createQuery(select m from 엔티티클래스명)으로 사용됨
@Table(name="Member") //테이블명을 실제 지정해줄 수 있음(@table 어노테이션이 없고 entity 어노테이션만 사용될 경우 관례상 클래스이름으로처리됨)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username", nullable = false, unique = true) // 유니크 네임이 쓰레기값으로 지정되어 예외터저도 파악하기어려움, 그래서 Table어노테이션 옵션에서 이름값주고 사용해라)
    private String name;

    @Enumerated(EnumType.STRING) // 주의 기본이 ordinal로 되어있는데 이걸로하면 순서꼬이는 문제발생하니 무조건 String쓸것
    private RollType rollType;

    // 구버전은 이런식으로 써야하고
    @Temporal(TemporalType.DATE)
    private Date createDate;

    // 신버전들은 대부분아래처럼 하면 어노테이션 없이 자동으로 타입보고 생성해줌
    private LocalDateTime localDateTime;
    private LocalDate localDate;

    @Lob
    private String description;

    @Transient // 이건 DB랑 연관없이 내가 사용하는 컬럼이라는 뜻
    private int sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RollType getRollType() {
        return rollType;
    }

    public void setRollType(RollType rollType) {
        this.rollType = rollType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
