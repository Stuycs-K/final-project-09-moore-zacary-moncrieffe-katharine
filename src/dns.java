import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.net.HttpURLConnection;
import java.net.IDN;

class Dns {

    // public static void main(String[] args) {
    /*
     * if (args.length != 2) {
     * System.out.println("Usage: java Dns <wordlist file> <domain>");
     * return;
     * }
     * 
     * String wordlistFile = args[0];
     * String domain = args[1];
     * 
     * List<String> wordlist = new ArrayList<>();
     * 
     * try (BufferedReader br = new BufferedReader(new FileReader(wordlistFile))) {
     * String line;
     * while ((line = br.readLine()) != null) {
     * wordlist.add(line.trim());
     * }
     * } catch (IOException e) {
     * System.err.println("Error reading wordlist file: " + e.getMessage());
     * return;
     * }
     * 
     * Options options = Options.
     * parseArgs("enumerate -d example.com -w thisistheurl -r -e --delay 1500 -x php,html,js"
     * );
     * dnsEnum(domain, wordlist.toArray(new String[0]), options);
     */
    // }

    private static final String DOMAIN_REGEX = "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)(\\.[A-Za-z]{2,})+$";

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
        int total = wordlist.length;
        int count = 0;

        if (!isValidDomain(domain)) {
            System.out.println("unable to validate base domain: " + domain);
            return;
        }

        String[] filepathWordlist = null;
        if (options.filepath && !options.filepathList.isEmpty()) {
            filepathWordlist = options.filepathList.split(",");
        }

        for (String word : wordlist) {
            String subdomain = word + "." + domain;
            checkSubdomain(subdomain, filepathWordlist, options);

            count++;
            if (options.showProgress) {
                System.out.println("current progress: " + count + "/" + total);
            }

            if (options.delay) {
                try {
                    Thread.sleep(options.delayTime);
                } catch (InterruptedException e) {
                    System.err.println("error during delay");
                }
            }
        }
    }

    private static void checkSubdomain(String subdomain, String[] filePathWordlist, Options options) {
        try {
            boolean subdomainFound = false;

            if (options.outputRedirects) {
                URL url = new URL("http://" + subdomain);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setInstanceFollowRedirects(true);
                int responseCode = connection.getResponseCode();

                if (responseCode < 400) {
                    subdomainFound = true;
                    System.out.println("found: " + subdomain + " - " + connection.getURL());
                } else {
                    System.out.println("not found: " + subdomain);
                }

                connection.disconnect();
            } else {
                InetAddress inetAddress = InetAddress.getByName(subdomain);
                subdomainFound = true;
                System.out.println("found: " + subdomain + " - " + inetAddress.getHostAddress());
            }

            if (subdomainFound && filePathWordlist != null) {
                enumerateFiles(subdomain, filePathWordlist, options);
            }
        } catch (UnknownHostException e) {
            System.out.println("not found: " + subdomain);
        } catch (IOException e) {
            System.out.println("error checking subdomain: " + subdomain);
        }
    }

    private static void enumerateFiles(String subdomain, String[] filePathWordlist, Options options) {
        List<String> filePathsToCheck = new ArrayList<>();
        
        if (options.extensions) {
            String[] extensions = options.extensionsList.split(",");
            for (String filePath : filePathWordlist) {
                for (String ext : extensions) {
                    filePathsToCheck.add(filePath + "." + ext.trim());
                }
            }
        } else {
            for (String filePath : filePathWordlist) {
                filePathsToCheck.add(filePath);
            }
        }

        for (String filePath : filePathsToCheck) {
            try {
                URL url = new URL("http://" + subdomain + "/" + filePath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setInstanceFollowRedirects(true);
                int responseCode = connection.getResponseCode();

                if (responseCode < 400) {
                    System.out.println("found file: " + url + " [" + responseCode + "]");
                } else {
                    System.out.println("not found file: " + url + " [" + responseCode + "]");
                }

                connection.disconnect();
            } catch (IOException e) {
                System.out.println("error checking file: " + filePath + " on subdomain: " + subdomain);
            }

            if (options.delay) {
                try {
                    Thread.sleep(options.delayTime);
                } catch (InterruptedException e) {
                    System.err.println("error during delay");
                }
            }
        }
    }
}
