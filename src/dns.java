import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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

        dnsEnum(domain, wordlist.toArray(new String[0]));
    }

    public static void dnsEnum(String domain, String[] wordlist) {
        for (String word : wordlist) {
            String subdomain = word + "." + domain;
            try {
                InetAddress inetAddress = InetAddress.getByName(subdomain);
                System.out.println("Found: " + subdomain + " - " + inetAddress.getHostAddress());
            } catch (UnknownHostException e) {
                System.out.println("Not found: " + subdomain);
            }
        }
    }
}
