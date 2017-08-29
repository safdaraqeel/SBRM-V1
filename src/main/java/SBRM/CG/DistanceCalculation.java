package SBRM.CG;

import java.util.Iterator;
import java.util.List;


public class DistanceCalculation {

	public DistanceCalculation() {
		// TODO Auto-generated constructor stub
	}

	// for normalization
	public static double nor(double x) {
		return x / (x + 1);
	}

	// for x==y
	public static double xEqualsY(double k, double x, double y) {
		// for categorical variables
		if (x < 5) {
			if (x - y == 0) {
				return 0;
			} else {
				return k * nor(Math.abs(x - y));
			}
		}
		// for numeric variables
		else {

			if (x - y == 0) {
				return 0;
			} else {
				return k * nor(Math.abs(x - y));
			}

		}

	}

	// for x!=y
	public static double xNotEqualsY(double k, double x, double y) {

		if (x - y != 0) {
			return 0;
		} else {
			return k * nor(Math.abs(x - y) + 1);
		}
	}

	// for x<y
	public static double xLessThanY(double k, double x, double y) {

		if (x - y < 0) {
			return 0;
		} else {
			return k * nor(Math.abs(x - y) + 1);
		}
	}

	// for x<=y
	public static double xLessEqualsThanY(double k, double x, double y) {

		if (x - y <= 0) {
			return 0;
		} else {
			return k * nor(Math.abs(x - y) + 1);
		}
	}

	// for x>y
	public static double xGreaterThanY(double k, double x, double y) {

		if (y - x < 0) {
			return 0;
		} else {
			return k * nor(Math.abs(x - y) + 1);
		}
	}

	// for x>=y
	public static double xGreaterEqualsThanY(double k, double x, double y) {

		if (y - x <= 0) {
			return 0;
		} else {
			return k * nor(Math.abs(x - y) + 1);
		}
	}


	public static List<CPLRules> CalculateDistanceForAllRules(List<CPLRules> rulesList,EncodedConfigurableParameters configuration){
		int maximumpredicatesInAnyConstraint=0;
		for (Iterator iterator = rulesList.iterator(); iterator.hasNext();) {
			CPLRules rule = (CPLRules) iterator.next();
			String [] predicates =rule.getExpression().split("AND");
			if (predicates.length>maximumpredicatesInAnyConstraint){
				maximumpredicatesInAnyConstraint=predicates.length;
			}
		}
		
		
		
		//System.out.println("This is parsing constraints and calculating distance");
		DistanceCalculation disObj= new DistanceCalculation();		
		for (Iterator iterator = rulesList.iterator(); iterator.hasNext();) {
			CPLRules rule = (CPLRules) iterator.next();
			double distance = 0;
			String [] predicates =rule.getExpression().split("AND");		
			for (int j=0;j<predicates.length;j++){
							
				//System.out.println("Rule#"+i+" and clause#"+j+" "+predicates[j]);
				//***********Replacing values and variables in constraintst with numeric number from the configuration data generated ****************

				if (predicates[j].contains("product1_DefaultCallProtocol")||predicates[j].contains("product2_DefaultCallProtocol")){
					predicates[j]=predicates[j].replace("product1_DefaultCallProtocol", Double.toString(configuration.getProduct1_DefaultCallProtocol()));
					predicates[j]=predicates[j].replace("product2_DefaultCallProtocol", Double.toString(configuration.getProduct2_DefaultCallProtocol()));
					predicates[j]=predicates[j].replace("sip", "0");
					predicates[j]=predicates[j].replace("aim", "1");
					predicates[j]=predicates[j].replace("googletalk", "2");
					predicates[j]=predicates[j].replace("icq", "3");
					predicates[j]=predicates[j].replace("ippi", "4");
					predicates[j]=predicates[j].replace("iptel.org", "5");
					predicates[j]=predicates[j].replace("irc", "6");
					predicates[j]=predicates[j].replace("h323", "7");
					
				}else if (predicates[j].contains("product1_ListenPort")||predicates[j].contains("product2_ListenPort")){
					predicates[j]=predicates[j].replace("product1_ListenPort", Double.toString(configuration.getProduct1_ListenPort()));
					predicates[j]=predicates[j].replace("product2_ListenPort", Double.toString(configuration.getProduct2_ListenPort()));
					predicates[j]=predicates[j].replace("off", "0");
					predicates[j]=predicates[j].replace("on", "1");
					
				}else if (predicates[j].contains("product1_DefaultTransportProtocol")||predicates[j].contains("product2_DefaultTransportProtocol")){
					predicates[j]=predicates[j].replace("product1_DefaultTransportProtocol", Double.toString(configuration.getProduct1_DefaultTransportProtocol()));
					predicates[j]=predicates[j].replace("product2_DefaultTransportProtocol", Double.toString(configuration.getProduct2_DefaultTransportProtocol()));	
					predicates[j]=predicates[j].replace("auto", "0");
					predicates[j]=predicates[j].replace("udp", "1");
					predicates[j]=predicates[j].replace("tcp", "2");
					predicates[j]=predicates[j].replace("tls", "3");
					
				}else if (predicates[j].contains("product1_Encryption")||predicates[j].contains("product2_Encryption")){
					predicates[j]=predicates[j].replace("product1_Encryption", Double.toString(configuration.getProduct1_Encryption()));
					predicates[j]=predicates[j].replace("product2_Encryption", Double.toString(configuration.getProduct2_Encryption()));
					predicates[j]=predicates[j].replace("off", "0");
					predicates[j]=predicates[j].replace("on", "1");
					predicates[j]=predicates[j].replace("besteffort", "2");
					
				}else if (predicates[j].contains("product1_SipZrtpAttribute")||predicates[j].contains("product2_SipZrtpAttribute")){
					predicates[j]=predicates[j].replace("product1_SipZrtpAttribute", Double.toString(configuration.getProduct1_SipZrtpAttribute()));
					predicates[j]=predicates[j].replace("product2_SipZrtpAttribute", Double.toString(configuration.getProduct2_SipZrtpAttribute()));
					predicates[j]=predicates[j].replace("auto", "0");
					predicates[j]=predicates[j].replace("true", "1");
					predicates[j]=predicates[j].replace("false", "2");
					
				}else if (predicates[j].contains("product1_AudioCodec")||predicates[j].contains("product2_AudioCodec")){
					predicates[j]=predicates[j].replace("product1_AudioCodec", Double.toString(configuration.getProduct1_AudioCodec()));
					predicates[j]=predicates[j].replace("product2_AudioCodec", Double.toString(configuration.getProduct2_AudioCodec()));
					predicates[j]=predicates[j].replace("gsm-8000", "10");
					predicates[j]=predicates[j].replace("speex-8000", "11");
					predicates[j]=predicates[j].replace("amr-wb-16000", "12");
					predicates[j]=predicates[j].replace("silk-12000", "13");
					predicates[j]=predicates[j].replace("silk-8000", "14");
					predicates[j]=predicates[j].replace("telephone-event-80000", "15");
					predicates[j]=predicates[j].replace("auto", "0");
					predicates[j]=predicates[j].replace("opus-48000", "1");
					predicates[j]=predicates[j].replace("silk-24000", "2");
					predicates[j]=predicates[j].replace("silk-16000", "3");
					predicates[j]=predicates[j].replace("g722-16000", "4");
					predicates[j]=predicates[j].replace("speex-32000", "5");
					predicates[j]=predicates[j].replace("speex-16000", "6");
					predicates[j]=predicates[j].replace("pcmu-8000", "7");
					predicates[j]=predicates[j].replace("pcma-8000", "8");
					predicates[j]=predicates[j].replace("ilbc-8000", "9");
					
				}else if (predicates[j].contains("product1_VideoCodec")||predicates[j].contains("product2_VideoCodec")){
					predicates[j]=predicates[j].replace("product1_VideoCodec", Double.toString(configuration.getProduct1_VideoCodec()));
					predicates[j]=predicates[j].replace("product2_VideoCodec", Double.toString(configuration.getProduct2_VideoCodec()));
					predicates[j]=predicates[j].replace("auto", "0");
					predicates[j]=predicates[j].replace("h264", "1");
					predicates[j]=predicates[j].replace("red", "2");
					predicates[j]=predicates[j].replace("rtx", "3");
					predicates[j]=predicates[j].replace("ulpfec", "4");
					predicates[j]=predicates[j].replace("vp8", "5");
					
				}else if (predicates[j].contains("product1_Resolution")||predicates[j].contains("product2_Resolution")){
					predicates[j]=predicates[j].replace("product1_Resolution", Double.toString(configuration.getProduct1_Resolution()));
					predicates[j]=predicates[j].replace("product2_Resolution", Double.toString(configuration.getProduct2_Resolution()));
					predicates[j]=predicates[j].replace("1080", "0");
					predicates[j]=predicates[j].replace("720", "1");
					predicates[j]=predicates[j].replace("480", "2");
					predicates[j]=predicates[j].replace("360", "3");
					predicates[j]=predicates[j].replace("240", "4");
				}else if (predicates[j].contains("product1_SipMode")||predicates[j].contains("product2_SipMode")){
					predicates[j]=predicates[j].replace("product1_SipMode", Double.toString(configuration.getProduct1_SipMode()));
					predicates[j]=predicates[j].replace("product2_SipMode", Double.toString(configuration.getProduct2_SipMode()));
					predicates[j]=predicates[j].replace("off", "0");
					predicates[j]=predicates[j].replace("on", "1");
				}else if (predicates[j].contains("product1_H323Mode")||predicates[j].contains("product2_H323Mode")){
					predicates[j]=predicates[j].replace("product1_H323Mode", Double.toString(configuration.getProduct1_H323Mode()));
					predicates[j]=predicates[j].replace("product2_H323Mode", Double.toString(configuration.getProduct2_H323Mode()));
					predicates[j]=predicates[j].replace("off", "0");
					predicates[j]=predicates[j].replace("on", "1");
					
				}else if (predicates[j].contains("product1_MTU")||predicates[j].contains("product2_MTU")){
					predicates[j]=predicates[j].replace("product1_MTU", Double.toString(configuration.getProduct1_MTU()));
					predicates[j]=predicates[j].replace("product2_MTU", Double.toString(configuration.getProduct2_MTU()));
					
				}else if (predicates[j].contains("product1_DefaultCallRate")||predicates[j].contains("product2_DefaultCallRate")){
					predicates[j]=predicates[j].replace("product1_DefaultCallRate", Double.toString(configuration.getProduct1_DefaultCallRate()));
					predicates[j]=predicates[j].replace("product2_DefaultCallRate", Double.toString(configuration.getProduct2_DefaultCallRate()));
					
				}else if (predicates[j].contains("product1_MaxReceivedCallRate")||predicates[j].contains("product2_MaxReceivedCallRate")){
					predicates[j]=predicates[j].replace("product1_MaxReceivedCallRate", Double.toString(configuration.getProduct1_MaxReceivedCallRate()));
					predicates[j]=predicates[j].replace("product2_MaxReceivedCallRate", Double.toString(configuration.getProduct2_MaxReceivedCallRate()));
					
				}else if (predicates[j].contains("product1_MaxTransmitedCallRate")||predicates[j].contains("product2_MaxTransmitedCallRate")){
					predicates[j]=predicates[j].replace("product1_MaxTransmitedCallRate", Double.toString(configuration.getProduct1_MaxTransmitedCallRate()));
					predicates[j]=predicates[j].replace("product2_MaxTransmitedCallRate", Double.toString(configuration.getProduct2_MaxTransmitedCallRate()));
				}
			

				//calculating branch distance
			if (predicates[j].contains("<=")){
			//	System.out.println("<= operator found");
				String [] oprands = predicates[j].split("<=");
			//	System.out.println(oprands[0]+" <= " + oprands[1]);
				
				distance+= disObj.xLessEqualsThanY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1]));
			//	System.out.println("distance is:- "+disObj.xLessEqualsThanY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1])));
			}
			else if (predicates[j].contains(">=")){
				//System.out.println(">= operator found");
				String [] oprands = predicates[j].split(">=");
				//System.out.println(oprands[0]+" >= " + oprands[1]);
				
				distance+= disObj.xGreaterEqualsThanY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1]));
			//	System.out.println("distance is:- "+disObj.xGreaterEqualsThanY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1])));
			}
			else if (predicates[j].contains("<")){
				//System.out.println("< operator found");
				String [] oprands = predicates[j].split("<");
				//System.out.println(oprands[0]+" < " + oprands[1]);
				
				distance+= disObj.xLessThanY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1]));
			//	System.out.println("distance is:- "+disObj.xLessThanY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1])));
			}
			else if (predicates[j].contains(">")){
				//System.out.println("> operator found");
				String [] oprands = predicates[j].split(">");
				//System.out.println(oprands[0]+" > " + oprands[1]);
				distance+= disObj.xGreaterThanY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1]));
			//	System.out.println("distance is:- "+disObj.xGreaterThanY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1])));
			}
			
			else if (predicates[j].contains("=")){
				//System.out.println("= operator found");
				String [] oprands = predicates[j].split("=");
				//System.out.println(oprands[0]+" = " + oprands[1]);
					distance+= disObj.xEqualsY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1]));
			//		System.out.println("distance is:- "+disObj.xEqualsY(1, Double.parseDouble(oprands[0]), Double.parseDouble(oprands[1])));
			}
			else {
				System.out.println("No operator found");
			}
		}
		//constraints[i].setDistance((distance/predicates.length));
		rule.setDistance((distance/maximumpredicatesInAnyConstraint));
		
		//System.out.println("The total distance for a constraint"+constraints[i].getConstraintExpression()+" is:- "+constraints[i].getDistance());
		//System.out.println();
		}
//		for (int j=0;j<rulesList.length;j++){
//			rulesList[j].printConstraint();
//		}
	return rulesList;
	

	
	}
	
		
	
}
