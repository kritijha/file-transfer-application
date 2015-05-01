import java.net.*;
import java.io.*;
import java.util.*;

class FClient
{
 	FClient() throws Exception
 	{
  		Socket s = new Socket("127.0.0.1", 8181);
  		DataInputStream din = new DataInputStream(s.getInputStream());
  		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
  		Scanner user = new Scanner( System.in ); 
    		System.out.print("Name of File to be transferred : ");
		String fileName = user.nextLine().trim();
    		dout.writeUTF(fileName);
  		int flag = din.readInt();
  		if(flag == 1)
  		{
   			System.out.println(fileName + " not found");
  		}
  		else if(flag == 2)
  		{
   			System.out.println("Downloading File ");
   			long len = din.readLong();
   			long tot ;
   			//file save
   			String baseDir = "/home/kj/Documents/SEM 6";
   			FileOutputStream fout = new FileOutputStream(baseDir + "/" + fileName );
   			int data;
   			for(tot = 0; tot < len; tot++)
   			{
    				data =din.read();
    				fout.write(data);
   			}   
   
   			fout.flush();
   			fout.close();
  		}
  		s.close();
  	}

 	public static void main(String args[])
 	{
  		try
  		{
   			new FClient();
  		}
  		catch(Exception ex)
  		{
   			System.out.println(ex);
  		}
 	}
}
