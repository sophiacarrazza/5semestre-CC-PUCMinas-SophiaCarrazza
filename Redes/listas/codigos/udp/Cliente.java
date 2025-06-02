import java.io.*;
import java.net.*;

class Cliente {
   private static int portaServidor = 9871;
   private static byte[] sendData = new byte[1024];
   private static byte[] receiveData = new byte[1024];

   public static byte[] lerString() throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // entrada de dados
      return in.readLine().getBytes();
   }

   public static void main(String args[]) throws Exception {
      DatagramSocket clientSocket = new DatagramSocket(); // cria o socket (porta aleatória)
      InetAddress ipServidor = InetAddress.getByName("colocar_seu_ip_aqui"); // ip do servidor
      sendData = lerString(); // lê a string do teclado

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServidor, portaServidor); // cria o
                                                                                                            // pacote
      clientSocket.send(sendPacket); // envia o pacote ao servidor

      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); // cria o pacote de
                                                                                          // recebimento
      clientSocket.receive(receivePacket); // recebe o pacote do servidor (o mesmo pacote que ele enviou mas em
                                           // maiusculo)
      clientSocket.close();

      System.out.println("FROM SERVER:" + new String(receivePacket.getData()));
   }
}
