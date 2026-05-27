FROM eclipse-temurin:17-jdk

# 作業フォルダ作成
WORKDIR /app

# プロジェクトをコンテナへコピー
COPY . .

# Maven実行権限付与
RUN chmod +x mvnw

# Spring Bootビルド
RUN ./mvnw clean package -DskipTests

# 作成されたjarを app.jar にコピー
RUN cp target/*.jar app.jar

# Renderが渡すPORTを使う
ENV PORT=8080

# アプリ起動
CMD java -Dserver.port=$PORT -jar app.jar
