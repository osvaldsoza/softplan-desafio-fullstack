package br.com.osvaldsoza.gerenciamentoprocessosapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name ="usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
	@NotBlank
	@Column( nullable = false)
	@ApiModelProperty(example = "Softplan01", required = true)
	private String login;
	
	@NotBlank
	@Column( nullable = false)
	@ApiModelProperty(example = "*********", required = true)
	private String senha;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(example = "Administrador", required = true)
	private ETipoUsuario tipo;
	
}
