# CANSA-CHAT

CANSA-CHAT is a chat application designed to support work and study. It provides a convenient platform for users to communicate, collaborate, and share information in real-time. Whether you're working on a team project or studying with classmates, CANSA-CHAT helps streamline communication and enhances productivity.

## Requirements

- Docker (Laster Version)

## Installation
Install project with npm:

1. Step 1:
    ```bash
    git clone https://gitlab.com/app-chat-can-sa/cansa-chat-back-end.git
    ```
2. Step 2:
    ```bash
    npm install
    ```
3. Step 3:
    ```bash
    npm run pull
    ```
4. Step 4:
    ```bash
    npm run build
    ```
5. Step 5:
    ```bash
    npm start
    ```
Install project with yarn:
1. Step 1:
    ```bash
    git clone https://gitlab.com/app-chat-can-sa/cansa-chat-back-end.git
    ```
2. Step 2:
    ```bash
    yarn install
    ```
3. Step 3:
    ```bash
    yarn pull
    ```
4. Step 4:
    ```bash
    yarn build
    ```
5. Step 5:
    ```bash
    yarn start
    ```
## Usage
    After successfully building the Docker image for the first time, you don't need to repeat the process of pulling and building in subsequent runs. If you have checked and found that the container and image exist on Docker Desktop, please run the start command to launch the application.
The mapping address between Docker and the local machine is typically```127.0.0.1:8080``` or ```localhost:8080```. This means that when you run a service or application inside a Docker container and expose it on port 8080, you can access it on your local machine using either ```127.0.0.1:8080``` or ```localhost:8080``` in your web browser.
    


