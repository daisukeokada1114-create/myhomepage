# Java17環境
FROM eclipse-temurin:17-jdk

# 作業ディレクトリ
WORKDIR /app

# ファイルコピー
COPY . .

# Maven Wrapper権限付与
RUN chmod +x mvnw

# Springビルド
RUN ./mvnw clean package -DskipTests

# 起動
CMD java -jar target/*.jar