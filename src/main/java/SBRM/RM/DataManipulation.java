package SBRM.RM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataManipulation {

	public static void converCSV2ARFF(String sourceFilePath,String destinationFilePath){
		
		String header ="@relation Jitsi\n\n\n"+
					"@attribute product1_DefaultCallProtocol {sip,aim,googletalk,icq,ippi,iptel.org,irc,h323}\n"+
					"@attribute product1_ListenPort {on, off}\n"+
					"@attribute product1_DefaultTransportProtocol {auto, udp,tcp,tls}\n"+
					"@attribute product1_Encryption {off,on, besteffort}\n"+
					"@attribute product1_SipZrtpAttribute {auto, true, false}\n"+
					"@attribute product1_AudioCodec  {auto,opus-48000,silk-24000,silk-16000,g722-16000,speex-32000,speex-16000,pcmu-8000,pcma-8000,ilbc-8000,gsm-8000,speex-8000,amr-wb-16000,silk-12000,silk-8000,telephone-event-80000}\n"+
					"@attribute product1_VideoCodec {auto, h264,red,rtx,ulpfec,vp8}\n"+
					"@attribute product1_Resolution {1080,720,480,360,240}\n"+
					"@attribute product1_SipMode {off,on}\n"+
					"@attribute product1_H323Mode {off,on}\n"+
					"@attribute product1_MTU numeric\n"+
					"@attribute product1_DefaultCallRate numeric\n"+
					"@attribute product1_MaxReceivedCallRate numeric\n"+
					"@attribute product1_MaxTransmitedCallRate numeric\n"+
					"@attribute product2_DefaultCallProtocol {sip,aim,googletalk,icq,ippi,iptel.org,irc,h323}\n"+
					"@attribute product2_ListenPort {on, off}\n"+
					"@attribute product2_DefaultTransportProtocol {auto, udp,tcp,tls}\n"+
					"@attribute product2_Encryption {off,on, besteffort}\n"+
					"@attribute product2_SipZrtpAttribute {auto, true, false}\n"+
					"@attribute product2_AudioCodec  {auto,opus-48000,silk-24000,silk-16000,g722-16000,speex-32000,speex-16000,pcmu-8000,pcma-8000,ilbc-8000,gsm-8000,speex-8000,amr-wb-16000,silk-12000,silk-8000,telephone-event-80000}\n"+
					"@attribute product2_VideoCodec {auto, h264,red,rtx,ulpfec,vp8}\n"+
					"@attribute product2_Resolution {1080,720,480,360,240}\n"+
					"@attribute product2_SipMode {off,on}\n"+
					"@attribute product2_H323Mode {off,on}\n"+
					"@attribute product2_MTU numeric\n"+
					"@attribute product2_DefaultCallRate numeric\n"+
					"@attribute product2_MaxReceivedCallRate numeric\n"+
					"@attribute product2_MaxTransmitedCallRate numeric\n"+
					"@attribute State {Failed,Connected}\n"+
					"@data\n\n";
				
		String data="";

		
		File sourceFile = new File(sourceFilePath);
		File destinationFile = new File(destinationFilePath);
		try {
			Scanner scan = new Scanner(sourceFile);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (!line.toLowerCase().contains("protocol")){
				line=line.replaceAll(";", ",")+"\n";
				data+=line;
				}
			}
			
	    	// writing again
	    	FileWriter fw = new FileWriter(destinationFile,false);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	bw.write(header+data);
	    	bw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		converCSV2ARFF("AllData.csv","Data.arff");
		
		
	}
	
	
	
	

}
