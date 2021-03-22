package hu.beni.tax.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trafic {

	@Id
	@GeneratedValue
	private Long id;

	@CreationTimestamp
	private LocalDateTime timestamp;

	private String requestId;

	private String ip;

	private String url;

	private String methodType;

	private String headers;

	private String body;

	private Boolean request;

	private Integer responseStatus;

}
