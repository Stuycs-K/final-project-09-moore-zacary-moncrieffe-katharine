import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;
import org.apache.commons.validator.routines.DomainValidator;

class Dns{

   public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java dns <wordlist file> <domain>");
            return;
        }

        String wordlistFile = args[0];
        String domain = args[1];

        List<String> wordlist = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(wordlistFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordlist.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading wordlist file: " + e.getMessage());
            return;
        }

        Options options = Options.parseArgs("enumerate -d example.com -w thisistheurl -r -e --delay 1500 -x php,html,js");
        dnsEnum(domain, wordlist.toArray(new String[0]), options);
    }

    public static void dnsEnum(String domain, String[] wordlist, Options options) {
        if(DomainValidator.isValid(domain)){
        for (String word : wordlist) {
            String subdomain = word + "." + domain;
            try {
                if(options.delay){
                    try {
                        Thread.sleep(options.delayTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                InetAddress inetAddress = InetAddress.getByName(subdomain);
                System.out.println("Found: " + subdomain + " - " + inetAddress.getHostAddress());
            } catch (UnknownHostException e) { 
                System.out.println("Not found: " + subdomain);
            }
        }
    }
    else{
        System.out.println("unable to validate base domain:" + domain);
    }
    }
}
