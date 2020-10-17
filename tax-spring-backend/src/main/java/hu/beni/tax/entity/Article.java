package hu.beni.tax.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

	@Id
	@GeneratedValue
	private Long id;

	private String searchKey;

	private String title;

	@Lob
	private String content;

	private LocalDate date;

	@PrePersist
	public void generateSearchKey() {
		searchKey = title.toLowerCase().replace(" ", "-").replace('.', '-').replace("á", "a")
				.replace("é", "e").replace("ó", "o").replace("--", "-");
	}

}
