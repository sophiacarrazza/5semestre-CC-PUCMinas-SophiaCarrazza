import java.net.*;

class Servidor {
   private static int portaServidor = 9871;
   private static byte[] receiveData = new byte[1024];
   private static byte[] sendData = new byte[1024];

   public static void main(String args[]) throws Exception {
      try (DatagramSocket serverSocket = new DatagramSocket(portaServidor)) {

         while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); // pacote de recebimento

            System.out.println("Aguardando datagrama do cliente....");
            serverSocket.receive(receivePacket); // recebe o datagrama pelo socket (socket=portaServidor)

            System.out.println("RECEIVED: " + new String(receivePacket.getData()));
            InetAddress ipCliente = receivePacket.getAddress(); // ip do cliente
            int portaCliente = receivePacket.getPort(); // porta do cliente
            sendData = (new String(receivePacket.getData())).toUpperCase().getBytes(); // transforma em maiusculo

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipCliente, portaCliente); // envia
                                                                                                                // o
                                                                                                                // pacote
                                                                                                                // usando
                                                                                                                // o ip
                                                                                                                // e
                                                                                                                // porta
                                                                                                                // do
                                                                                                                // cliente
            serverSocket.send(sendPacket);
            System.out.println("Enviado...");
         }
      }
   }
}
