# Messenger

This repository contains a simple messaging application implemented in Java, utilizing sockets for communication between a server and multiple clients. The server-side code is built around `ServerThread` and `MsgServer`, while the client-side code uses ClientSocket. The application also includes a database-like structure based on a LinkedList.

## Features
Server-side implementation using `MsgServer` and `ServerThread` for concurrent communication.
Client-side implementation using `MsgCleint` and `ClientThread` for connecting to the server.
Basic messaging functionality allowing clients to send and receive messages.
Database-like structure using a LinkedList for storing messages.
## Prerequisites
Java Development Kit (JDK) 8 or above
IDE (e.g., Eclipse, IntelliJ) or a text editor
Terminal or Command Prompt
## Getting Started
To get started with the Messenger application, follow these steps:

Clone this repository to your local machine using the following command:
Copy code
`git clone https://github.com/denomelchenko/Messenger.git`

Open the project in your preferred IDE or text editor.

Compile and run the server-side code by executing the `Server.java` file.

Compile and run the client-side code by executing the `Client.java` file on multiple instances to simulate multiple clients.

## Usage
Once the server and client applications are running, you can start sending messages between the clients. The following instructions outline the basic usage of the Messenger application:

Launch the client application(s) by running the Client.java file.

Upon launching the client, you will be prompted to enter a username.

Once the username is entered, you will be connected to the server.

To send a message, type the username message content (like: username::message) and press Enter.

To see all users, which connected to Server print message "users"

The message will be sent to the server and broadcasted to all connected clients.

All clients will receive the message and display it in their respective consoles.

You can continue sending messages until you decide to exit (by message "exit") the application.
