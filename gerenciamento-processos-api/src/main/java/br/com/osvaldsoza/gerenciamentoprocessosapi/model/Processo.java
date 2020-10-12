package br.com.osvaldsoza.gerenciamentoprocessosapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "processo")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(example = "1", required = true)
	private Long id;

	@Column(nullable = false)
	@ApiModelProperty(example = "0014039584RGT", required = true)
	private String numero;

	@CreationTimestamp
	@Column(name = "data_entrada", nullable = false, columnDefinition = "datetime")
	@ApiModelProperty(example = "2020-10-10 13:37:25", required = true)
	private LocalDateTime dataEntrada;

	@Column(name = "data_baixa", columnDefinition = "datetime")
	@ApiModelProperty(example = "2020-10-15 13:37:25")
	private LocalDateTime dataBaixa;

	@Column(nullable = false)
	@ApiModelProperty(example = "Em andamento", required = true)
	private String parecer;

	@ManyToOne
	@JoinColumn(nullable = false, name = "id_usuario")
	private Usuario usuario;
}
