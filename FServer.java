import java.net.*;
import java.io.*;

class FServer
{
 	ServerSocket port;
 	FServer() throws Exception
 	{
 	 	port = new ServerSocket(8181);
  		System.out.println("Waiting for a client connection ");
  		Socket s = port.accept();
  		DataInputStream din = new DataInputStream(s.getInputStream());
  		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
  		String fileName = din.readUTF();  
  		String result = lookFor(fileName); 
  		if(result == null)
  		{
   			System.out.println("File Not Found");
   			dout.writeInt(1);//NOT FOUND
  		}
  		else
  		{
   			System.out.println("Transferring File ");
   			dout.writeInt(2);//FOUND
   			File f = new File(result);
   			dout.writeLong(f.length());
   			//file transfer
   			FileInputStream fin = new FileInputStream(f);
   			byte arr[] = new byte[1024];
   			int x;
   			while((x = fin.read(arr)) != -1)
   			{
     				dout.write(arr, 0, x);
   			}
   			dout.flush();
   			fin.close();
  		}
  		s.close();
  		port.close();
 	}	

 	String lookFor(String fileName)
 	{
  		String baseDir = "/home/kj/Documents/SEM 6/Untitled Folder";
  		File f = new File(baseDir);
  		String names[] = f.list();
  		int i;
  		for(i =0 ; i< names.length; i++)
  		{
  	 		if(names[i].equalsIgnoreCase(fileName))
     			return baseDir + "/" + fileName;
  		}
  		return null;
 	}

 	public static void main(String args[])
 	{
  		try
  		{
   			new FServer();
  		}
  		catch(Exception ex)
  		{
   			System.out.println(ex);
  		}
 	}
}
