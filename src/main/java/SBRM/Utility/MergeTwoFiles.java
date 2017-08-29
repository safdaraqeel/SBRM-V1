package SBRM.Utility;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MergeTwoFiles {

	public static void merge(String sourceFilePath, String destinationFilePath) {

		try {
			String sourceData = new String(Files.readAllBytes(Paths.get(sourceFilePath)), StandardCharsets.UTF_8);
			sourceData=sourceData.replaceAll("product1_DefaultCallProtocol;product1_ListenPort;product1_DefaultTransportProtocol;product1_Encryption;product1_SipZrtpAttribute;product1_AudioCodec;product1_VideoCodec;product1_Resolution;product1_SipMode;product1_H323Mode;product1_MTU;product1_DefaultCallRate;product1_MaxReceivedCallRate;product1_MaxTransmitedCallRate;product2_DefaultCallProtocol;product2_ListenPort;product2_DefaultTransportProtocol;product2_Encryption;product2_SipZrtpAttribute;product2_AudioCodec;product2_VideoCodec;product2_Resolution;product2_SipMode;product2_H323Mode;product2_MTU;product2_DefaultCallRate;product2_MaxReceivedCallRate;product2_MaxTransmitedCallRate;State", "");
			Files.write(Paths.get(destinationFilePath), sourceData.getBytes(),StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		merge("/Users/Safdar/Google Drive/Simula/MyCodingWorkSpace/WorkspaceForSearchBasedConfigurationGeneration-CiscoCaseStudy/ExperimentExecutorCisco/AllFiles/InitialConfigurationsAndStatuses.csv", "/Users/Safdar/Google Drive/Simula/MyCodingWorkSpace/WorkspaceForSearchBasedConfigurationGeneration-CiscoCaseStudy/ExperimentExecutorCisco/AllFiles/ConfigurationsAndStatusesRun1.csv"); 
	}

}
