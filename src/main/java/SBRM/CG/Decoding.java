package SBRM.CG;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Decoding {

	public Decoding() {
		// TODO Auto-generated constructor stub
	}

	
    public static void DecodeAllConfigurationsInFile(String sourcePath, String targetPath) throws IOException
    {
    	String decodedConfigurations="product1_DefaultCallProtocol;product1_ListenPort;product1_DefaultTransportProtocol;product1_Encryption;product1_SipZrtpAttribute;product1_AudioCodec;product1_VideoCodec;product1_Resolution;product1_SipMode;product1_H323Mode;product1_MTU;product1_DefaultCallRate;product1_MaxReceivedCallRate;product1_MaxTransmitedCallRate;product2_DefaultCallProtocol;product2_ListenPort;product2_DefaultTransportProtocol;product2_Encryption;product2_SipZrtpAttribute;product2_AudioCodec;product2_VideoCodec;product2_Resolution;product2_SipMode;product2_H323Mode;product2_MTU;product2_DefaultCallRate;product2_MaxReceivedCallRate;product2_MaxTransmitedCallRate\n";
		try {
			List<String> lines = Files.readAllLines(Paths.get(sourcePath), Charset.defaultCharset());
	    	for (String line : lines) {
//		    	System.out.println("line read: " + line);
		    	String[] encodedConfigurations = line.split(" ");
		    	
		    	if(encodedConfigurations[0].equals("0"))
		    		decodedConfigurations+= "sip;";
				if(encodedConfigurations[0].equals("1"))
					decodedConfigurations+= "aim;";
				if(encodedConfigurations[0].equals("2"))
					decodedConfigurations+= "googletalk;";
				if(encodedConfigurations[0].equals("3"))
					decodedConfigurations+= "icq;";
				if(encodedConfigurations[0].equals("4"))
					decodedConfigurations+= "ippi;";
				if(encodedConfigurations[0].equals("5"))
					decodedConfigurations+= "iptel.org;";
				if(encodedConfigurations[0].equals("6"))
					decodedConfigurations+= "irc;";
				if(encodedConfigurations[0].equals("7"))
					decodedConfigurations+= "h323;";
		
				//Product1_ListenPort
				if(encodedConfigurations[1].equals("0"))
					decodedConfigurations+= "off;";
				if(encodedConfigurations[1].equals("1"))
					decodedConfigurations+= "on;";
		
				//Product1_DefaultTransportProtocol
				if(encodedConfigurations[2].equals("0"))
					decodedConfigurations+= "auto;";
				if(encodedConfigurations[2].equals("1"))
					decodedConfigurations+= "udp;";
				if(encodedConfigurations[2].equals("2"))
					decodedConfigurations+= "tcp;";
				if(encodedConfigurations[2].equals("3"))
					decodedConfigurations+= "tls;";
			
				//Product1_Encryption
				if(encodedConfigurations[3].equals("0"))
					decodedConfigurations+= "off;";
				if(encodedConfigurations[3].equals("1"))
					decodedConfigurations+= "on;";
				if(encodedConfigurations[3].equals("2"))
					decodedConfigurations+= "besteffort;";
			
				//Product1_SipZrtpAttribute
				if(encodedConfigurations[4].equals("0"))
					decodedConfigurations+= "auto;";
				if(encodedConfigurations[4].equals("1"))
					decodedConfigurations+= "true;";
				if(encodedConfigurations[4].equals("2"))
					decodedConfigurations+= "false;";
				
				//Product1_AudioCodec
				if(encodedConfigurations[5].equals("10"))
					decodedConfigurations+= "gsm-8000;";
				if(encodedConfigurations[5].equals("11"))
					decodedConfigurations+= "speex-8000;";
				if(encodedConfigurations[5].equals("12"))
					decodedConfigurations+= "amr-wb-16000;";
				if(encodedConfigurations[5].equals("13"))
					decodedConfigurations+= "silk-12000;";
				if(encodedConfigurations[5].equals("14"))
					decodedConfigurations+= "silk-8000;";
				if(encodedConfigurations[5].equals("15"))
					decodedConfigurations+= "telephone-event-80000;";
				if(encodedConfigurations[5].equals("0"))
					decodedConfigurations+= "auto;";
				if(encodedConfigurations[5].equals("1"))
					decodedConfigurations+= "opus-48000;";
				if(encodedConfigurations[5].equals("2"))
					decodedConfigurations+= "silk-24000;";
				if(encodedConfigurations[5].equals("3"))
					decodedConfigurations+= "silk-16000;";
				if(encodedConfigurations[5].equals("4"))
					decodedConfigurations+= "g722-16000;";
				if(encodedConfigurations[5].equals("5"))
					decodedConfigurations+= "speex-32000;";
				if(encodedConfigurations[5].equals("6"))
					decodedConfigurations+= "speex-16000;";
				if(encodedConfigurations[5].equals("7"))
					decodedConfigurations+= "pcmu-8000;";
				if(encodedConfigurations[5].equals("8"))
					decodedConfigurations+= "pcma-8000;";
				if(encodedConfigurations[5].equals("9"))
					decodedConfigurations+= "ilbc-8000;";
	
				//Product1_VideoCodec
				if(encodedConfigurations[6].equals("0"))
					decodedConfigurations+= "auto;";
				if(encodedConfigurations[6].equals("1"))
					decodedConfigurations+= "h264;";
				if(encodedConfigurations[6].equals("2"))
					decodedConfigurations+= "red;";
				if(encodedConfigurations[6].equals("3"))
					decodedConfigurations+= "rtx;";
				if(encodedConfigurations[6].equals("4"))
					decodedConfigurations+= "ulpfec;";
				if(encodedConfigurations[6].equals("5"))
					decodedConfigurations+= "vp8;";
		
				//Product1_Resolution
				if(encodedConfigurations[7].equals("0"))
					decodedConfigurations+= "1080;";
				if(encodedConfigurations[7].equals("1"))
					decodedConfigurations+= "720;";
				if(encodedConfigurations[7].equals("2"))
					decodedConfigurations+= "480;";
				if(encodedConfigurations[7].equals("3"))
					decodedConfigurations+= "360;";
				if(encodedConfigurations[7].equals("4"))
					decodedConfigurations+= "240;";
				
				//Product1_SipMode
				if(encodedConfigurations[8].equals("0"))
					decodedConfigurations+= "off;";
				if(encodedConfigurations[8].equals("1"))
					decodedConfigurations+= "on;";
				
				//Product1_H323Mode
				if(encodedConfigurations[9].equals("0"))
					decodedConfigurations+= "off;";
				if(encodedConfigurations[9].equals("1"))
					decodedConfigurations+= "on;";
				
				decodedConfigurations += encodedConfigurations[10]+ ";";
				decodedConfigurations += encodedConfigurations[11]+ ";";
				decodedConfigurations += encodedConfigurations[12]+ ";";
				decodedConfigurations += encodedConfigurations[13]+ ";";
		
				//********Decoding for Product 2********
				
				//Product2_DefaultCallProtocol
				if(encodedConfigurations[14].equals("0"))
					decodedConfigurations+= "sip;";
				if(encodedConfigurations[14].equals("1"))
					decodedConfigurations+= "aim;";
				if(encodedConfigurations[14].equals("2"))
					decodedConfigurations+= "googletalk;";
				if(encodedConfigurations[14].equals("3"))
					decodedConfigurations+= "icq;";
				if(encodedConfigurations[14].equals("4"))
					decodedConfigurations+= "ippi;";
				if(encodedConfigurations[14].equals("5"))
					decodedConfigurations+= "iptel.org;";
				if(encodedConfigurations[14].equals("6"))
					decodedConfigurations+= "irc;";
				if(encodedConfigurations[14].equals("7"))
					decodedConfigurations+= "h323;";
		
				//Product2_ListenPort
				if(encodedConfigurations[15].equals("0"))
					decodedConfigurations+= "off;";
				if(encodedConfigurations[15].equals("1"))
					decodedConfigurations+= "on;";
		
				//Product2_DefaultTransportProtocol
				if(encodedConfigurations[16].equals("0"))
					decodedConfigurations+= "auto;";
				if(encodedConfigurations[16].equals("1"))
					decodedConfigurations+= "udp;";
				if(encodedConfigurations[16].equals("2"))
					decodedConfigurations+= "tcp;";
				if(encodedConfigurations[16].equals("3"))
					decodedConfigurations+= "tls;";
			
				//Product2_Encryption
				if(encodedConfigurations[17].equals("0"))
					decodedConfigurations+= "off;";
				if(encodedConfigurations[17].equals("1"))
					decodedConfigurations+= "on;";
				if(encodedConfigurations[17].equals("2"))
					decodedConfigurations+= "besteffort;";
			
				//Product2_SipZrtpAttribute
				if(encodedConfigurations[18].equals("0"))
					decodedConfigurations+= "auto;";
				if(encodedConfigurations[18].equals("1"))
					decodedConfigurations+= "true;";
				if(encodedConfigurations[18].equals("2"))
					decodedConfigurations+= "false;";
				
				//Product2_AudioCodec
				if(encodedConfigurations[19].equals("10"))
					decodedConfigurations+= "gsm-8000;";
				if(encodedConfigurations[19].equals("11"))
					decodedConfigurations+= "speex-8000;";
				if(encodedConfigurations[19].equals("12"))
					decodedConfigurations+= "amr-wb-16000;";
				if(encodedConfigurations[19].equals("13"))
					decodedConfigurations+= "silk-12000;";
				if(encodedConfigurations[19].equals("14"))
					decodedConfigurations+= "silk-8000;";
				if(encodedConfigurations[19].equals("15"))
					decodedConfigurations+= "telephone-event-80000;";
				if(encodedConfigurations[19].equals("0"))
					decodedConfigurations+= "auto;";
				if(encodedConfigurations[19].equals("1"))
					decodedConfigurations+= "opus-48000;";
				if(encodedConfigurations[19].equals("2"))
					decodedConfigurations+= "silk-24000;";
				if(encodedConfigurations[19].equals("3"))
					decodedConfigurations+= "silk-16000;";
				if(encodedConfigurations[19].equals("4"))
					decodedConfigurations+= "g722-16000;";
				if(encodedConfigurations[19].equals("5"))
					decodedConfigurations+= "speex-32000;";
				if(encodedConfigurations[19].equals("6"))
					decodedConfigurations+= "speex-16000;";
				if(encodedConfigurations[19].equals("7"))
					decodedConfigurations+= "pcmu-8000;";
				if(encodedConfigurations[19].equals("8"))
					decodedConfigurations+= "pcma-8000;";
				if(encodedConfigurations[19].equals("9"))
					decodedConfigurations+= "ilbc-8000;";
	
			
				//Product2_VideoCodec
				if(encodedConfigurations[20].equals("0"))
					decodedConfigurations+= "auto;";
				if(encodedConfigurations[20].equals("1"))
					decodedConfigurations+= "h264;";
				if(encodedConfigurations[20].equals("2"))
					decodedConfigurations+= "red;";
				if(encodedConfigurations[20].equals("3"))
					decodedConfigurations+= "rtx;";
				if(encodedConfigurations[20].equals("4"))
					decodedConfigurations+= "ulpfec;";
				if(encodedConfigurations[20].equals("5"))
					decodedConfigurations+= "vp8;";
		
				//Product2_Resolution
				if(encodedConfigurations[21].equals("0"))
					decodedConfigurations+= "1080;";
				if(encodedConfigurations[21].equals("1"))
					decodedConfigurations+= "720;";
				if(encodedConfigurations[21].equals("2"))
					decodedConfigurations+= "480;";
				if(encodedConfigurations[21].equals("3"))
					decodedConfigurations+= "360;";
				if(encodedConfigurations[21].equals("4"))
					decodedConfigurations+= "240;";
				
				//Product2_SipMode
				if(encodedConfigurations[22].equals("0"))
					decodedConfigurations+= "off;";
				if(encodedConfigurations[22].equals("1"))
					decodedConfigurations+= "on;";
				
				//Product2_H323Mode
				if(encodedConfigurations[23].equals("0"))
					decodedConfigurations+= "off;";
				if(encodedConfigurations[23].equals("1"))
					decodedConfigurations+= "on;";
				
				decodedConfigurations += encodedConfigurations[24]+ ";";
				decodedConfigurations += encodedConfigurations[25]+ ";";
				decodedConfigurations += encodedConfigurations[26]+ ";";
				decodedConfigurations += encodedConfigurations[27]+ "\n";
    	
			}	// end of for loop
	    	
	    	Files.write(Paths.get(targetPath), decodedConfigurations.getBytes());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    public static String DecodeOneConfiguration(EncodedConfigurableParameters encodedConfiguration){
		String configurationRow ="";
		//********Decoding for Product 1********
		//Product1_DefaultCallProtocol
		if(encodedConfiguration.getProduct1_DefaultCallProtocol()==0)
			configurationRow+= "sip;";
		if(encodedConfiguration.getProduct1_DefaultCallProtocol()==1)
			configurationRow+= "aim;";
		if(encodedConfiguration.getProduct1_DefaultCallProtocol()==2)
			configurationRow+= "googletalk;";
		if(encodedConfiguration.getProduct1_DefaultCallProtocol()==3)
			configurationRow+= "icq;";
		if(encodedConfiguration.getProduct1_DefaultCallProtocol()==4)
			configurationRow+= "ippi;";
		if(encodedConfiguration.getProduct1_DefaultCallProtocol()==5)
			configurationRow+= "iptel.org;";
		if(encodedConfiguration.getProduct1_DefaultCallProtocol()==6)
			configurationRow+= "irc;";
		if(encodedConfiguration.getProduct1_DefaultCallProtocol()==7)
			configurationRow+= "h323;";

		//Product1_ListenPort
		if(encodedConfiguration.getProduct1_ListenPort()==0)
			configurationRow+= "off;";
		if(encodedConfiguration.getProduct1_ListenPort()==1)
			configurationRow+= "on;";

		//Product1_DefaultTransportProtocol
		if(encodedConfiguration.getProduct1_DefaultTransportProtocol()==0)
			configurationRow+= "auto;";
		if(encodedConfiguration.getProduct1_DefaultTransportProtocol()==1)
			configurationRow+= "udp;";
		if(encodedConfiguration.getProduct1_DefaultTransportProtocol()==2)
			configurationRow+= "tcp;";
		if(encodedConfiguration.getProduct1_DefaultTransportProtocol()==3)
			configurationRow+= "tls;";
	
		//Product1_Encryption
		if(encodedConfiguration.getProduct1_Encryption()==0)
			configurationRow+= "off;";
		if(encodedConfiguration.getProduct1_Encryption()==1)
			configurationRow+= "on;";
		if(encodedConfiguration.getProduct1_Encryption()==2)
			configurationRow+= "besteffort;";
	
		//Product1_SipZrtpAttribute
		if(encodedConfiguration.getProduct1_SipZrtpAttribute()==0)
			configurationRow+= "auto;";
		if(encodedConfiguration.getProduct1_SipZrtpAttribute()==1)
			configurationRow+= "true;";
		if(encodedConfiguration.getProduct1_SipZrtpAttribute()==2)
			configurationRow+= "false;";
		
		//Product1_AudioCodec
		if(encodedConfiguration.getProduct1_AudioCodec()==0)
			configurationRow+= "auto;";
		if(encodedConfiguration.getProduct1_AudioCodec()==1)
			configurationRow+= "opus-48000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==2)
			configurationRow+= "silk-24000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==3)
			configurationRow+= "silk-16000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==4)
			configurationRow+= "g722-16000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==5)
			configurationRow+= "speex-32000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==6)
			configurationRow+= "speex-16000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==7)
			configurationRow+= "pcmu-8000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==8)
			configurationRow+= "pcma-8000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==9)
			configurationRow+= "ilbc-8000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==10)
			configurationRow+= "gsm-8000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==11)
			configurationRow+= "speex-8000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==12)
			configurationRow+= "amr-wb-16000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==13)
			configurationRow+= "silk-12000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==14)
			configurationRow+= "silk-8000;";
		if(encodedConfiguration.getProduct1_AudioCodec()==15)
			configurationRow+= "telephone-event-80000;";
	
		//Product1_VideoCodec
		if(encodedConfiguration.getProduct1_VideoCodec()==0)
			configurationRow+= "auto;";
		if(encodedConfiguration.getProduct1_VideoCodec()==1)
			configurationRow+= "h264;";
		if(encodedConfiguration.getProduct1_VideoCodec()==2)
			configurationRow+= "red;";
		if(encodedConfiguration.getProduct1_VideoCodec()==3)
			configurationRow+= "rtx;";
		if(encodedConfiguration.getProduct1_VideoCodec()==4)
			configurationRow+= "ulpfec;";
		if(encodedConfiguration.getProduct1_VideoCodec()==5)
			configurationRow+= "vp8;";

		//Product1_Resolution
		if(encodedConfiguration.getProduct1_Resolution()==0)
			configurationRow+= "1080;";
		if(encodedConfiguration.getProduct1_Resolution()==1)
			configurationRow+= "720;";
		if(encodedConfiguration.getProduct1_Resolution()==2)
			configurationRow+= "480;";
		if(encodedConfiguration.getProduct1_Resolution()==3)
			configurationRow+= "360;";
		if(encodedConfiguration.getProduct1_Resolution()==4)
			configurationRow+= "240;";
		
		//Product1_SipMode
		if(encodedConfiguration.getProduct1_SipMode()==0)
			configurationRow+= "off;";
		if(encodedConfiguration.getProduct1_SipMode()==1)
			configurationRow+= "on;";
		
		//Product1_H323Mode
		if(encodedConfiguration.getProduct1_H323Mode()==0)
			configurationRow+= "off;";
		if(encodedConfiguration.getProduct1_H323Mode()==1)
			configurationRow+= "on;";
		
		configurationRow += encodedConfiguration.getProduct1_MTU() + ";";
		configurationRow += encodedConfiguration.getProduct1_DefaultCallRate() + ";";
		configurationRow += encodedConfiguration.getProduct1_MaxReceivedCallRate() + ";";
		configurationRow += encodedConfiguration.getProduct1_MaxTransmitedCallRate() + ";";

		//********Decoding for Product 2********
		
		//Product2_DefaultCallProtocol
		if(encodedConfiguration.getProduct2_DefaultCallProtocol()==0)
			configurationRow+= "sip;";
		if(encodedConfiguration.getProduct2_DefaultCallProtocol()==1)
			configurationRow+= "aim;";
		if(encodedConfiguration.getProduct2_DefaultCallProtocol()==2)
			configurationRow+= "googletalk;";
		if(encodedConfiguration.getProduct2_DefaultCallProtocol()==3)
			configurationRow+= "icq;";
		if(encodedConfiguration.getProduct2_DefaultCallProtocol()==4)
			configurationRow+= "ippi;";
		if(encodedConfiguration.getProduct2_DefaultCallProtocol()==5)
			configurationRow+= "iptel.org;";
		if(encodedConfiguration.getProduct2_DefaultCallProtocol()==6)
			configurationRow+= "irc;";
		if(encodedConfiguration.getProduct2_DefaultCallProtocol()==7)
			configurationRow+= "h323;";
		
		//Product2_ListenPort
		if(encodedConfiguration.getProduct2_ListenPort()==0)
			configurationRow+= "off;";
		if(encodedConfiguration.getProduct2_ListenPort()==1)
			configurationRow+= "on;";

		//Product2_DefaultTransportProtocol
		if(encodedConfiguration.getProduct2_DefaultTransportProtocol()==0)
			configurationRow+= "auto;";
		if(encodedConfiguration.getProduct2_DefaultTransportProtocol()==1)
			configurationRow+= "udp;";
		if(encodedConfiguration.getProduct2_DefaultTransportProtocol()==2)
			configurationRow+= "tcp;";
		if(encodedConfiguration.getProduct2_DefaultTransportProtocol()==3)
			configurationRow+= "tls;";
	
		//Product2_Encryption
		if(encodedConfiguration.getProduct2_Encryption()==0)
			configurationRow+= "off;";
		if(encodedConfiguration.getProduct2_Encryption()==1)
			configurationRow+= "on;";
		if(encodedConfiguration.getProduct2_Encryption()==2)
			configurationRow+= "besteffort;";
	
		//Product2_SipZrtpAttribute
		if(encodedConfiguration.getProduct2_SipZrtpAttribute()==0)
			configurationRow+= "auto;";
		if(encodedConfiguration.getProduct2_SipZrtpAttribute()==1)
			configurationRow+= "true;";
		if(encodedConfiguration.getProduct2_SipZrtpAttribute()==2)
			configurationRow+= "false;";
		
		//Product2_AudioCodec
		if(encodedConfiguration.getProduct2_AudioCodec()==0)
			configurationRow+= "auto;";
		if(encodedConfiguration.getProduct2_AudioCodec()==1)
			configurationRow+= "opus-48000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==2)
			configurationRow+= "silk-24000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==3)
			configurationRow+= "silk-16000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==4)
			configurationRow+= "g722-16000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==5)
			configurationRow+= "speex-32000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==6)
			configurationRow+= "speex-16000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==7)
			configurationRow+= "pcmu-8000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==8)
			configurationRow+= "pcma-8000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==9)
			configurationRow+= "ilbc-8000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==10)
			configurationRow+= "gsm-8000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==11)
			configurationRow+= "speex-8000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==12)
			configurationRow+= "amr-wb-16000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==13)
			configurationRow+= "silk-12000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==14)
			configurationRow+= "silk-8000;";
		if(encodedConfiguration.getProduct2_AudioCodec()==15)
			configurationRow+= "telephone-event-80000;";
	
		//Product2_VideoCodec
		if(encodedConfiguration.getProduct2_VideoCodec()==0)
			configurationRow+= "auto;";
		if(encodedConfiguration.getProduct2_VideoCodec()==1)
			configurationRow+= "h264;";
		if(encodedConfiguration.getProduct2_VideoCodec()==2)
			configurationRow+= "red;";
		if(encodedConfiguration.getProduct2_VideoCodec()==3)
			configurationRow+= "rtx;";
		if(encodedConfiguration.getProduct2_VideoCodec()==4)
			configurationRow+= "ulpfec;";
		if(encodedConfiguration.getProduct2_VideoCodec()==5)
			configurationRow+= "vp8;";

		//Product2_Resolution
		if(encodedConfiguration.getProduct2_Resolution()==0)
			configurationRow+= "1080;";
		if(encodedConfiguration.getProduct2_Resolution()==1)
			configurationRow+= "720;";
		if(encodedConfiguration.getProduct2_Resolution()==2)
			configurationRow+= "480;";
		if(encodedConfiguration.getProduct2_Resolution()==3)
			configurationRow+= "360;";
		if(encodedConfiguration.getProduct2_Resolution()==4)
			configurationRow+= "240;";
		
		//Product2_SipMode
		if(encodedConfiguration.getProduct2_SipMode()==0)
			configurationRow+= "off;";
		if(encodedConfiguration.getProduct2_SipMode()==1)
			configurationRow+= "on;";
		
		//Product2_H323Mode
		if(encodedConfiguration.getProduct2_H323Mode()==0)
			configurationRow+= "off;";
		if(encodedConfiguration.getProduct2_H323Mode()==1)
			configurationRow+= "on;";
		
		configurationRow += encodedConfiguration.getProduct2_MTU() + ";";
		configurationRow += encodedConfiguration.getProduct2_DefaultCallRate() + ";";
		configurationRow += encodedConfiguration.getProduct2_MaxReceivedCallRate() + ";";
		configurationRow += encodedConfiguration.getProduct2_MaxTransmitedCallRate() + "\n";
			
	return configurationRow;
    }
	
  
	public static void main(String[] args) throws IOException {
		DecodeAllConfigurationsInFile("IRC-Enc.txt","IRC-Enc-Dec.csv") ;	
	}

}
