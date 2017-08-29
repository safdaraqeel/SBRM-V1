package SBRM.PIS;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import SBRM.CG.DecodedConfigurableParameters;
import jmetal.util.JMException;

public class SimulatingInteraction {


	public static void captureSystemStates(String sourcePath, String targetPath) {

		try {
			String allConfigurations="product1_DefaultCallProtocol;product1_ListenPort;product1_DefaultTransportProtocol;product1_Encryption;product1_SipZrtpAttribute;product1_AudioCodec;product1_VideoCodec;product1_Resolution;product1_SipMode;product1_H323Mode;product1_MTU;product1_DefaultCallRate;product1_MaxReceivedCallRate;product1_MaxTransmitedCallRate;product2_DefaultCallProtocol;product2_ListenPort;product2_DefaultTransportProtocol;product2_Encryption;product2_SipZrtpAttribute;product2_AudioCodec;product2_VideoCodec;product2_Resolution;product2_SipMode;product2_H323Mode;product2_MTU;product2_DefaultCallRate;product2_MaxReceivedCallRate;product2_MaxTransmitedCallRate;State\n";
			List<String> lines = Files.readAllLines(Paths.get(sourcePath), Charset.defaultCharset());
			for (String line : lines) {
				if (!line.toLowerCase().contains("protocol")) {
//					System.out.println("line read: " + line);
					boolean status = RulesTogetSystemStateBasedOnConfigurations(line);
					if (status) {
						allConfigurations += line + ";Connected\n";
					} else {
						allConfigurations += line + ";Failed\n";
					}
				}
			}
			Files.write(Paths.get(targetPath), allConfigurations.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	    public static boolean RulesTogetSystemStateBasedOnConfigurations(String configuration) throws JMException{
	       
	    	DecodedConfigurableParameters decodedConfigurationInstance = DecodedConfigurableParameters.intializeConfigurationInstance(configuration);

	        //**************Rule for call*************
	          if (decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("sip") && decodedConfigurationInstance.getProduct2_ListenPort().toLowerCase().contains("off")){ // sip-listen port
	              return false;
//	          }if (decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("sip") && decodedConfigurationInstance.getProduct2_SipMode().toLowerCase().contains("off")){ // sip-mode and sip protocol
//	              return false;
//	          }if (decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("h323") && decodedConfigurationInstance.getProduct2_H323Mode().toLowerCase().contains("off")){ // h323-mode and h323 protocol
//	              return false;
	          }if (decodedConfigurationInstance.getProduct1_Encryption().toLowerCase().contains("on") && decodedConfigurationInstance.getProduct2_Encryption().toLowerCase().contains("off")){    // encryption on/off
	              return false;
	          }if (decodedConfigurationInstance.getProduct1_Encryption().toLowerCase().contains("off") && decodedConfigurationInstance.getProduct2_Encryption().toLowerCase().contains("on")){     // encryption off/on
	              return false;
	          }if (decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("sip") && ( (!decodedConfigurationInstance.getProduct1_DefaultTransportProtocol().toLowerCase().contains("auto")) || (!decodedConfigurationInstance.getProduct2_DefaultTransportProtocol().toLowerCase().contains("auto")) ||(! decodedConfigurationInstance.getProduct1_DefaultTransportProtocol().toLowerCase().equals(decodedConfigurationInstance.getProduct2_DefaultTransportProtocol().toLowerCase())))){ // transport protocol should be same
	              return false;
	          }if (Integer.parseInt(decodedConfigurationInstance.getProduct1_Resolution())>Integer.parseInt(decodedConfigurationInstance.getProduct2_Resolution())){ // MaxResolution of caller should be less than receiver
	              Random rand = new Random(System.currentTimeMillis()*System.nanoTime());
	              int c= rand.nextInt(4);
	              if (c==1){
	                  return false;
	              }
	          }if (decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("sip") && ((!decodedConfigurationInstance.getProduct2_SipZrtpAttribute().toLowerCase().equals(decodedConfigurationInstance.getProduct2_SipZrtpAttribute().toLowerCase()))||(!decodedConfigurationInstance.getProduct1_SipZrtpAttribute().toLowerCase().contains("auto"))||(!decodedConfigurationInstance.getProduct2_SipZrtpAttribute().toLowerCase().contains("auto")))){ // SipZrtpAttribute should be same for caller and receiver
	              return false;
	          }if ((decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("googletalk")||decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("ippi")||decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("iptel.org")||decodedConfigurationInstance.getProduct1_DefaultCallProtocol().toLowerCase().contains("sip"))  && ((decodedConfigurationInstance.getProduct2_VideoCodec().toLowerCase().contains("vp8"))||(decodedConfigurationInstance.getProduct2_VideoCodec().toLowerCase().contains("ulpfec")))){// GoogleTalk,iPPi, iptel.org, SIP support h264,red,rtx video codec  {,rtx,ulpfec,VP8}
	              return false;       
	          }if (decodedConfigurationInstance.getProduct1_VideoCodec().toLowerCase().contains("vp8")){// These video codecs are not supported at the moment with given prootcol
	              return false;          
	          }if (decodedConfigurationInstance.getProduct1_MTU() > decodedConfigurationInstance.getProduct2_MTU()){ // MTU of caller should not be greater than the the receiver
	              Random rand = new Random(System.currentTimeMillis()*System.nanoTime());
	              int c= rand.nextInt(4);
	              if (c==1){
	                  return false;
	              }
	          }if (decodedConfigurationInstance.getProduct1_DefaultCallRate()>decodedConfigurationInstance.getProduct1_MaxTransmitedCallRate()){ // callrate should be less than maxtransmit call rate
	              Random rand = new Random(System.currentTimeMillis()*System.nanoTime());
	              int c= rand.nextInt(4);
	              if (c==1){
	                  return false;
	              }
	          }if (decodedConfigurationInstance.getProduct1_DefaultCallRate()>decodedConfigurationInstance.getProduct2_MaxReceivedCallRate()){ // callrate should be less than maxreceived call rate of receiver
	              Random rand = new Random(System.currentTimeMillis()*System.nanoTime());
	              int c= rand.nextInt(4);
	              if (c==1){
	                  return false;
	              }
	          }
	        return true;
	    }

	    
	
	public static void main(String[] args) {
		captureSystemStates("IRC-Dec.csv","states.csv") ;

	}

}




