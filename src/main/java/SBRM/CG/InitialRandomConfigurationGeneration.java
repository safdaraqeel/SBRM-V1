package SBRM.CG;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;


public class InitialRandomConfigurationGeneration {

public static int randInt(int min, int max) {
	Random rand = new Random(System.nanoTime()*System.currentTimeMillis());
    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;
return randomNum;   	    
}
	
public static EncodedConfigurableParameters generateOneEncodedRandomConfiguration(){
	EncodedConfigurableParameters configuration = new EncodedConfigurableParameters();
	
	configuration.setProduct1_DefaultCallProtocol(randInt(0, 7));
	configuration.setProduct1_ListenPort(randInt(0, 1));
	configuration.setProduct1_DefaultTransportProtocol(randInt(0, 3));
	configuration.setProduct1_Encryption(randInt(0, 2));
	configuration.setProduct1_SipZrtpAttribute(randInt(0, 2));
	configuration.setProduct1_AudioCodec(randInt(0, 15));
	configuration.setProduct1_VideoCodec(randInt(0, 5));
	configuration.setProduct1_Resolution(randInt(0, 4));
	configuration.setProduct1_SipMode(randInt(0, 1));
	configuration.setProduct1_H323Mode(randInt(0, 1));
	configuration.setProduct1_MTU(randInt(500, 1500));
	configuration.setProduct1_DefaultCallRate(randInt(50, 5000));
	configuration.setProduct1_MaxReceivedCallRate(randInt(50, 5000));
	configuration.setProduct1_MaxTransmitedCallRate(randInt(50, 5000));

	configuration.setProduct2_DefaultCallProtocol(randInt(0, 7));
	configuration.setProduct2_ListenPort(randInt(0, 1));
	configuration.setProduct2_DefaultTransportProtocol(randInt(0, 3));
	configuration.setProduct2_Encryption(randInt(0, 2));
	configuration.setProduct2_SipZrtpAttribute(randInt(0, 2));
	configuration.setProduct2_AudioCodec(randInt(0, 15));
	configuration.setProduct2_VideoCodec(randInt(0, 5));
	configuration.setProduct2_Resolution(randInt(0, 4));
	configuration.setProduct2_SipMode(randInt(0, 1));
	configuration.setProduct2_H323Mode(randInt(0, 1));
	configuration.setProduct2_MTU(randInt(500, 1500));
	configuration.setProduct2_DefaultCallRate(randInt(50, 5000));
	configuration.setProduct2_MaxReceivedCallRate(randInt(50, 5000));
	configuration.setProduct2_MaxTransmitedCallRate(randInt(50, 5000));
	
	return configuration;
	}
	

	public static void getInitialDecodedRandomConfigurations(String path, int totalConfigurations) throws IOException {
		String header="product1_DefaultCallProtocol;product1_ListenPort;product1_DefaultTransportProtocol;product1_Encryption;product1_SipZrtpAttribute;product1_AudioCodec;product1_VideoCodec;product1_Resolution;product1_SipMode;product1_H323Mode;product1_MTU;product1_DefaultCallRate;product1_MaxReceivedCallRate;product1_MaxTransmitedCallRate;product2_DefaultCallProtocol;product2_ListenPort;product2_DefaultTransportProtocol;product2_Encryption;product2_SipZrtpAttribute;product2_AudioCodec;product2_VideoCodec;product2_Resolution;product2_SipMode;product2_H323Mode;product2_MTU;product2_DefaultCallRate;product2_MaxReceivedCallRate;product2_MaxTransmitedCallRate\n";
		String initialConfigurations = header;
		for (int i = 0; i < totalConfigurations; i++) {
			EncodedConfigurableParameters randomlyGeneratedConfiguration = generateOneEncodedRandomConfiguration();
			String configurationRow = Decoding.DecodeOneConfiguration(randomlyGeneratedConfiguration);
			initialConfigurations += configurationRow;
//			System.out.println(configurationRow);
		}
		
		File file = new File(path);
		if(!file.exists() || !file.isDirectory()){
			if(!file.getParentFile().exists() || !file.getParentFile().isDirectory()){
				file.getParentFile().mkdir();
				}
			file.createNewFile();
		}
		
		Files.write(Paths.get(path), initialConfigurations.getBytes());

	}

	
	
	public static void getEncodedRandomConfigurations(String path, int totalConfigurations) throws IOException {
		String initialConfigurations = "";
		for (int i = 0; i < totalConfigurations; i++) {
			EncodedConfigurableParameters randomlyGeneratedConfiguration = generateOneEncodedRandomConfiguration();

			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_DefaultCallProtocol()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_ListenPort()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_DefaultTransportProtocol()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_Encryption()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_SipZrtpAttribute()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_AudioCodec()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_VideoCodec()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_Resolution()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_SipMode()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_H323Mode()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_MTU()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_DefaultCallRate()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_MaxReceivedCallRate()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct1_MaxTransmitedCallRate()+" ";

			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_DefaultCallProtocol()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_ListenPort()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_DefaultTransportProtocol()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_Encryption()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_SipZrtpAttribute()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_AudioCodec()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_VideoCodec()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_Resolution()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_SipMode()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_H323Mode()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_MTU()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_DefaultCallRate()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_MaxReceivedCallRate()+" ";
			initialConfigurations+= randomlyGeneratedConfiguration.getProduct2_MaxTransmitedCallRate()+"\n";
		}
		Files.write(Paths.get(path), initialConfigurations.getBytes());

	}

	
	
	
	public static void main(String[] args) {
		 try {
			getInitialDecodedRandomConfigurations("InitialConfigurations.csv", 50);
//			getEncodedRandomConfigurations("IRC-Enc.txt", 50);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
