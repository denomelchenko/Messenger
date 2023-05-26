# Messenger

This repository contains a simple messaging application implemented in Java, utilizing sockets for communication
between a server and multiple clients. The server-side code is built around `ServerThread` and `MessageServer`,
while the client-side code uses `MessageClient`. The application also includes a database-like structure based on a `HashMap`.

## Features
Server-side implementation using `MessageServer` and `ServerThread` for concurrent communication.
Client-side implementation using `MessageClient` and `ClientThread` for connecting to the server.
Basic messaging functionality allowing clients to send and receive messages.
Database-like structure using a LinkedList for storing messages.

## Prerequisites
1. Java Development Kit (JDK) 8 or above
2. IDE (e.g., Eclipse, IntelliJ) or a text editor
3. Terminal or Command Prompt

## Getting Started
To get started with the Messenger application, follow these steps:

1. Clone this repository to your local machine using the following command `git clone https://github.com/denomelchenko/Messenger.git`
2. Open the project in your preferred IDE or text editor.
3. Compile and run the server-side code by executing the `Server.java` file.
4. Compile and run the client-side code by executing the `Client.java` file on multiple instances to simulate multiple clients.

## Usage
1. Once the server and client applications are running, you can start sending messages between the clients.
   The following instructions outline the basic usage of the Messenger application:
2. Launch the client application(s) by running the `Client.java` file.
3. Upon launching the client, you will be prompted to enter a username.
4. Once the username is entered, you will be connected to the server.
5. To send a message, type the username message content (like: username::message) and press Enter.
6. To see all users, which connected to Server print message "users"
7. The message will be sent to the server and broadcasted to a specified client.
8. Specified client will receive the message and display it in their respective consoles.
9. You can continue sending messages until you decide to exit (by message "exit") the application.
