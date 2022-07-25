package org.sid.catservice.service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CommunicationCMF {


	  static Integer sequence = 38;
	  static String amb = "05";
	  static String soh = "01";
	  static String etx = "03";

//	  public String CommunicationCmf() {
//		  
//		  String test = prepareCommande("c3", "1234567890123,ALI BABA,NIAMEY,01010101,toto@toto.com");
//		    System.out.println(test);
//			return test;
//
//		    
//		  }
		 // ---------------------------------------------------------------------
		 // convert to hex 
		  public static String toHex(int decimal) {
		    int rem;
		    String hex = "";
		    char hexchars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		    while (decimal > 0) {
		      rem = decimal % 16;
		      hex = hexchars[rem] + hex;
		      decimal = decimal / 16;
		    }
		    return hex;
		  }
		   // ---------------------------------------------------------------------
		  // method convert BCC TO 300300300300
		  private static String convertBCC30(String BCC) {
		    // Creating array of string length
		    char[] ch = new char[BCC.length()];
		    // Copy character by character into array
		    for (int i = 0; i < BCC.length(); i++) {
		      ch[i] = BCC.charAt(i);
		    }
		    // Printing content of array
		    String strg = "";
		    for (char c : ch) {
		      String str = ("3" + c).toString();

		      strg = strg + str;
		    }
		    return BCC = strg;
		  }

		  // ---------------------------------------------------------------------

		  // method String Data TO Hexadicimal
		  private static String stringToHex(String chaine) {
		    char[] charArray = chaine.toCharArray();
		    StringBuilder stringBuilder = new StringBuilder();
		    for (char c : charArray) {
		      String charToHex = Integer.toHexString(c);
		      stringBuilder.append(charToHex);
		    }
		    return stringBuilder.toString();
		  }

		  public static String intergerToHex(int decimal) {
		    int rem;
		    String hex = "";
		    char hexchars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		    while (decimal > 0) {
		      rem = decimal % 16;
		      hex = hexchars[rem] + hex;
		      decimal = decimal / 16;
		    }
		    return hex;
		  }

		  public String prepareCommande(String cmd, String data) {

		    // convertir data to hexa
		    String dataHex = stringToHex(data);
		    // ---------------------------------------/

		    // sequence
		    sequence++;
		    String seqHex = StringUtils.leftPad(toHex(sequence), 2, "0");
		    // ---------------------------------------/

		    // len  --> 32 decimal = 20 hex // 
		    int lengthData = data.length();
		    int lengthAllData = lengthData + 4 + 32;
		    String lenHex = intergerToHex(lengthAllData);
		    // ---------------------------------------------/

		    // BCC
		    int cmdInt = Integer.parseInt(cmd, 16);
		    char[] listChar = data.toCharArray();
		    Integer dataSum = 0;
		    for (char c : listChar) {
		      Integer charInt = Integer.valueOf(c);
		      dataSum = dataSum + charInt;
		    }

		    Integer allSum = lengthAllData + sequence + cmdInt + dataSum + 5;

		    String bccLeftPad = intergerToHex(allSum);
		    String bcc = StringUtils.leftPad(bccLeftPad, 4, "0");
		    bcc = convertBCC30(bcc).toLowerCase();
		    String subCommande = lenHex + seqHex + cmd + dataHex + amb + bcc;
		    // ----------------------------------------------/

		    String commande = soh + subCommande + etx;

		    return commande;

		  }

		

	  }
	
	

