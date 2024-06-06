import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.net.IDN;

class Dns{

   //public static void main(String[] args) {
       /* if (args.length != 2) {
            System.out.println("Usage: java Dns <wordlist file> <domain>");
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
        dnsEnum(domain, wordlist.toArray(new String[0]), options);*/
   // }

   private static final String DOMAIN_REGEX =
   "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)(\\.[A-Za-z]{2,})+$";

   private static final Pattern DOMAIN_PATTERN = Pattern.compile(DOMAIN_REGEX);

   public static boolean isValidDomain(String domain) {
    if (domain == null || domain.length() > 253) {
        return false;
    }

    try {
        // idn to ascii
        String asciiDomain = IDN.toASCII(domain);
        if (asciiDomain.length() > 253) {
            return false;
        }

        Matcher matcher = DOMAIN_PATTERN.matcher(asciiDomain);
        return matcher.matches();
    } catch (IllegalArgumentException e) {
        return false;
    }
}

    public static void dnsEnum(String domain, String[] wordlist, Options options) {
        if(isValidDomain(domain)){
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
