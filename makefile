api/tests:
	./mvnw clean
	./mvnw test

test/server:
	./mvnw allure:serve

test/performance:
	./mvnw gatling:test -Pperformance-test

