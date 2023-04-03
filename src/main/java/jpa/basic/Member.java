package jpa.basic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //jpa가 처음로딩 될떄 아 jpa를 사용하는 얘니까 내가 관리해야겠다 이럼
public class Member {

    @Id
    private Long id;
    private String name;

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
}
