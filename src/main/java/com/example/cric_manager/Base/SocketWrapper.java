package com.example.cric_manager.Base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketWrapper {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SocketWrapper(String s, int port) throws IOException { // used by the client
        System.out.println("Eh re");
        this.socket = new Socket(s, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

    }

    public SocketWrapper(Socket s) throws IOException { // used by the server
        this.socket = s;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public Object read() throws IOException, ClassNotFoundException {
        synchronized (ois) {
            return ois.readObject();
        }

    }

    public void write(Object o) throws IOException {
        synchronized (oos) {
            oos.writeObject(o);
            oos.flush();
        }

    }

    public void closeConnection() throws IOException {
        ois.close();
        oos.close();
    }
}
