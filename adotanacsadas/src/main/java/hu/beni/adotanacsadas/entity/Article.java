package hu.beni.adotanacsadas.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String searchKey;

    private String title;

    private String content;

    @CreationTimestamp
    private LocalDate date;

    private String articleSearch;

    @PreUpdate
    public void generateSearchKey() {
        log.info("krumpli");
    }

    @PrePersist
    public void generateSearchKey1() {
        log.info("krumpli");
        searchKey = title.toLowerCase().replace(" ", "-").replace('.', 'a');
    }

}
