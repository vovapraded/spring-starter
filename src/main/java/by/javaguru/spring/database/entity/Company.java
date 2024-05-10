package by.javaguru.spring.database.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@NamedQuery(
        name = "Company.findByName",
        query = "select  c from  Company  c where  lower(c.name) = lower(:name) "
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company implements BaseEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;
    @Builder.Default
    @ElementCollection
    @CollectionTable()
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String,String> locales = new HashMap<>();

    public Company(Integer id) {
        this.id = id;
    }
}

