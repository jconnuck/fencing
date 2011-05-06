package fencing_temp;
//================================================ Java code sample ================================================//

import java.net.*;
import java.io.*;

public class SendSms {

    static public void main(String[] args) {
        try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  http://usa.bulksms.com/docs/eapi/submission/character_encoding/
             */
            data += "username=" + URLEncoder.encode("cs032fencing", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("F3ncing!", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode("Test test test", "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=12123007360";

            // Send data
	    //URL url = new URL ("http://usa.bulksms.com:5567/eapi/status_reports/get_report/2/2.0");
            URL url = new URL("http://usa.bulksms.com:5567/eapi/reception/get_inbox/1/1.0");
            //URL url = new URL("http://usa.bulksms.com:5567/eapi/submission/send_sms/2/2.0");  
	    //URL url = new URL("");	    

	    String get_inbox = "username=cs032fencing&password=F3ncing!&last_retrieved_id=0";

	   /*
            * If your firewall blocks access to port 5567, you can fall back to port 80:
            * URL url = new URL("http://usa.bulksms.com/eapi/submission/send_sms/2/2.0");
            * (See FAQ for more details.)
            */

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(get_inbox);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
