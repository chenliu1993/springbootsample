

.PHONY: run
springrun: #### helper command for deploying the sample
	@mvn spring-boot:run

.PHONY: unittest
unittest: #### helper command for unite tests
	@mvn test