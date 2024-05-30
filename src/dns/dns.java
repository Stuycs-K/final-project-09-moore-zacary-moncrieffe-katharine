import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

class dns{

    public static void main(String[] args) {
        String[] wordlist = {"www", "mail", "forum", "test"}; 
        dnsEnum("example.com", wordlist);
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
