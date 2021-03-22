package hu.beni.tax.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

	@Id
	@GeneratedValue
	private Long id;

	@CreationTimestamp
	private LocalDateTime timestamp;

	private String ip;

	private String url;

	private String methodType;

	@Type(type = "org.hibernate.type.TextType")
	private String headers;

	@Type(type = "org.hibernate.type.TextType")
	private String body;

}
