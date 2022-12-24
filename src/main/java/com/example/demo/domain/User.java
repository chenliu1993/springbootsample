package com.example.demo.domain;

// import javax.validation.constraints.Max;
// import javax.validation.constraints.Min;
// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class User {

	// Users id
	private Long id;

	// User name
	// @NotBlank(message="Users Name")
	private String name;
}
