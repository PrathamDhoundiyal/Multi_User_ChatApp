package com.prathamProject.chatapp.network;
//Thread == Worker
// Worker need a job to Perform
//For job u give runnable
//Once job is created via Runnable we write the job logic inside a run function
//Assign he job to a Thread/Worker

// Understanding Thread Concepts

//public class ServerWorker implements Runnable{
// public class ServerWorker extends Thread{
//    public void run(){
//    // Job to Perform
//        // Logic
//        for (int i = 0; i <=5; i++) {
//            System.out.println("RUN i->"+i+" "+Thread.currentThread());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//      //  ServerWorker job =new ServerWorker(); // --> Job Created
//        ServerWorker worker = new ServerWorker();
//        worker.start();
//        // Assign the job to a thread/Worker
//       // Thread worker = new Thread(job,"worker1"); --->Runnable way
//       // worker.start(); // internally it calls run()
//        for (int j = 0; j <=5; j++) {
//            System.out.println("MAIN j->"+j+" "+Thread.currentThread());
//        }
//    }
//}

import java.io.*;
import java.net.Socket;

public class ServerWorker extends Thread{
    private Socket clientSocket;
    private InputStream in;
    private OutputStream out;
    private Server server;

    ServerWorker(Socket clientSocket,Server server) throws IOException {
        this.server=server;
        this.clientSocket = clientSocket;
        in = clientSocket.getInputStream(); //Client Data Read
        out = clientSocket.getOutputStream();// Client Data Write
        System.out.println("new CLient Comes");
    }

    public void run(){
        // thread Job
   // Read Data from the Client and BroadCast the data to all
        BufferedReader br= new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while (true) {
                line = br.readLine(); //  \n needed to stop
                System.out.println("Line Read....."+line); // not working
                if (line.equalsIgnoreCase("quit")) {
                    break;// Client Chat End
                }
                for(ServerWorker serverWorker:server.workers){
                    line=line+"\n";
                    serverWorker.out.write(line.getBytes());
                }
            }
        }
            catch (IOException e){
             e.printStackTrace();
            }
        finally {
          try {
              if (br != null)
                  br.close();
              if (in != null)
                  in.close();
              if (out != null)
                  out.close();
              if (clientSocket != null)
                  clientSocket.close();
          }
          catch (Exception ex){
              ex.printStackTrace();
          }
        }
               }
    }

