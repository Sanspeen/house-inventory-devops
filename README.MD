# 🚀 Get Started

## 📌 Prerequisites
Before starting, make sure you have:
- **Git** installed on your system.
- **Gradle** installed (or use the Gradle wrapper).
- **PostgreSQL** or a **Neon database** set up.
- **Java 17+** installed.

---

## 🛠 Step 1: Clone the Repository
Clone this repository using **Git** or download it as a ZIP file.

```sh
git clone https://github.com/your-username/your-repo.git
cd your-repo
```

## 🔌 Step 2: Check Port Availability  

Before running the project, make sure **port 8080** is available. If it's already in use, you need to either **free the port** or **change it** in the configuration file.  

### 🔍 **Check if Port 8080 is in Use**  
Run the following command based on your operating system:  

**💻 macOS/Linux:**  
```sh
lsof -i :8080
```

## 🛠 Step 3: Configure Database Connection

To set up your database connection, retrieve the necessary credentials from **Neon** or **PostgreSQL** and set them as environment variables.

### 🔹 **Required Environment Variables**
Set the following environment variables in your system:

```sh
export DB_PASS="your-database-password"
export DB_HOST="your-database-host"
export DB_USER="your-database-username"
export SPRING_PROFILES_ACTIVE="local"
```

## 🚀 Step 4: Run the Application  

Now that your database is configured and your port is set, you can run the application using **Gradle**.  

### 🔹 **Run with Gradle**  
Execute the following command to start the application:  

```sh
./gradlew bootRun
```

## 🎯 Next Steps

After successfully running the application, follow these steps:

1. Open the application in your browser:
   ```sh
   http://localhost:8080
   ```

## Create Docker container.

1. First you need to run the following command:
   ```shell
      docker build -t inventory-app .
   ```
2. Then run the docker image filling the empty env variables in the
   empty spaces.
   ```shell
    docker run -p 8080:8080 \
    -e SPRING_PROFILES_ACTIVE=${ENVIRONMENT} \
    -e DB_HOST=${DB_HOST} \
    -e DB_USER=${DB_USER} \
    -e DB_PASS=${DB_PASS} \
    inventory-ms
   ```
   
   
