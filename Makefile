PROJECT_NAME = sodau-backend-sign

build-image:
	./gradlew bootBuildImage

build-jar:
	./gradlew clean bootJar

run-native:
	java -Dspring.aot.enabled=true -agentlib:native-image-agent=config-merge-dir=src/main/resources/META-INF/native-image,config-write-period-secs=300,config-write-initial-delay-secs=5 -jar build/libs/eds-api-0.0.1-SNAPSHOT.jar

build-images:
	- set -e
	- docker compose -p sodau-backend-sign -f docker-compose.yml build

deploy:
	- set -e
	- docker compose -p sodau-backend-sign -f docker-compose.yml build
	- docker compose -p sodau-backend-sign -f docker-compose.yml up -d --force-recreate

start:
	docker compose -p sodau-backend-sign -f docker-compose.yml up -d --force-recreate

stop:
	docker compose -p sodau-backend-sign -f docker-compose.yml down --remove-orphans

build-native:
	- set -e
	- docker buildx build -f Dockerfile.native.base -t eds-api-base . --platform=linux/amd64
	- docker compose -f docker-compose.native.yml up --force-recreate --build
