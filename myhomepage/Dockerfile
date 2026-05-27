FROM eclipse-temurin:17-jdk

# 作業ディレクトリ
WORKDIR /app

# プロジェクト全体コピー
COPY . .

# mvnwに実行権限付与
RUN chmod +x mvnw

# Spring Bootビルド
RUN ./mvnw clean package -DskipTests

# jarを固定名へコピー
RUN cp target/*.jar app.jar

# Renderが渡すポートで起動
ENTRYPOINT ["sh","-c","java -Dserver.port=$PORT -jar app.jar"]
