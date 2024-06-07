import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Options {
    

    /*public static void main (String[] args) {
    //  parseArgs("enumerate -w thisistheurl");
    //"enumerate -d example.com -w thisistheurl -r -e --delay 1500 -x php,html,js"
    Options options = wizard();
        System.out.println("domain: " + options.domain);
        System.out.println("wordlist: " + options.wordlistPath);
        System.out.println("redirect: " + options.outputRedirects);
        System.out.println("extensions: " + options.extensions);
        System.out.println("delay: " + options.delayTime);
        System.out.println("progress: " + options.showProgress);
        System.out.println("extensions list: " + options.extensionsList);
    }*/

    public String domain; // -d
    public String wordlistPath; // -w
    public String mode; // -m
    public boolean outputRedirects = false; // -r
    public boolean help = false;
    public boolean extensions = false;
    public boolean delay = false; 
    public int delayTime = 0; 
    public boolean showProgress = false; 
    public String extensionsList = "";
    public boolean filepath = false;
    public String filepathList = "";

    public static Options parseArgs(String argString) {
      Options o = new Options();
      o.outputRedirects = argString.contains("-r");
      o.help = argString.contains("-h");
      o.extensions = argString.contains("-x");
      o.showProgress = argString.contains("-z");
      o.filepath = argString.contains("-f");

      if(argString.contains("-m")){
        o.mode = getValueAfterFlag(argString, "-m");
      }
      if (argString.contains("-d")) {
          o.domain = getValueAfterFlag(argString, "-d");
      }
      if (argString.contains("-w")) {
          o.wordlistPath = getValueAfterFlag(argString, "-w");
      }
      if (argString.contains("--delay")) {
          o.delay = true;
          String delayString = getValueAfterFlag(argString, "--delay");
          o.delayTime = Integer.parseInt(delayString); 
      }
      if (argString.contains("-x")) {
        o.extensionsList = getValueAfterFlag(argString, "-x");
      }
      if(argString.contains("-f")){
        o.filepathList = getValueAfterFlag(argString, "-f");
      }
      return o;
  }

    static String getValueAfterFlag(String text, String flag) {
      System.out.println("(?<="+flag+" )[^ ]+");
      Pattern pattern = Pattern.compile("(?<="+flag+" )[^ ]+", Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(text);
      matcher.find();
      return matcher.group();
      // return "";
    }

    static Options wizard() {
      Options o = new Options();
      Scanner in = new Scanner(System.in);

      System.out.println("Show help menu? (yes/no):");
      String response = in.nextLine().trim();
      if ("yes".equalsIgnoreCase(response)) {
          Javabuster.printHelp();
          System.exit(0);
      }

      System.out.println("Mode (dns/dir):");
        o.mode = in.nextLine();

      System.out.println("Website URL:");
      o.domain = in.nextLine();

      System.out.println("Wordlist path:");
      o.wordlistPath = in.nextLine();

      System.out.println("Delay between requests:");
      o.delayTime = in.nextInt();
      if (o.delayTime > 0) o.delay = true;
      
      in.nextLine(); // consumes the dangling newline character

      System.out.println("File extensions to search:");
      o.extensionsList = in.nextLine();
      if (!o.extensionsList.isEmpty()) o.extensions = true;

      System.out.println("File paths to search:");
      o.filepathList = in.nextLine();
      if (!o.filepathList.isEmpty()) o.filepath = true;

      System.out.println("Output redirects? (true/false)");
      o.outputRedirects = in.nextBoolean();

      System.out.println("Show progress? (true/false)");
      o.showProgress = in.nextBoolean();

      in.close();
      return o;
    }
}


/*
 * gobuster dns modes
 * Uses DNS subdomain enumeration mode

Usage:
  gobuster dns [flags]

Flags:
  -d, --domain string      The target domain
  -h, --help               help for dns
  -r, --resolver string    Use custom DNS server (format server.com or server.com:port)
  -c, --show-cname         Show CNAME records (cannot be used with '-i' option)
  -i, --show-ips           Show IP addresses
      --timeout duration   DNS resolver timeout (default 1s)
      --wildcard           Force continued operation when wildcard found

Global Flags:
      --delay duration    Time each thread waits between requests (e.g. 1500ms)
      --no-color          Disable color output
      --no-error          Don't display errors
  -z, --no-progress       Don't display progress
  -o, --output string     Output file to write results to (defaults to stdout)
  -p, --pattern string    File containing replacement patterns
  -q, --quiet             Don't print the banner and other noise
  -t, --threads int       Number of concurrent threads (default 10)
  -v, --verbose           Verbose output (errors)
  -w, --wordlist string   Path to the wordlist

 */

 /*
  * dir mode flags

Flags:
  -f, --add-slash                       Append / to each request
  -c, --cookies string                  Cookies to use for the requests
  -d, --discover-backup                 Also search for backup files by appending multiple backup extensions
      --exclude-length ints             exclude the following content length (completely ignores the status). Supply multiple times to exclude multiple sizes.
  -e, --expanded                        Expanded mode, print full URLs
  -x, --extensions string               File extension(s) to search for
  -r, --follow-redirect                 Follow redirects
  -H, --headers stringArray             Specify HTTP headers, -H 'Header1: val1' -H 'Header2: val2'
  -h, --help                            help for dir
      --hide-length                     Hide the length of the body in the output
  -m, --method string                   Use the following HTTP method (default "GET")
  -n, --no-status                       Don't print status codes
  -k, --no-tls-validation               Skip TLS certificate verification
  -P, --password string                 Password for Basic Auth
      --proxy string                    Proxy to use for requests [http(s)://host:port]
      --random-agent                    Use a random User-Agent string
      --retry                           Should retry on request timeout
      --retry-attempts int              Times to retry on request timeout (default 3)
  -s, --status-codes string             Positive status codes (will be overwritten with status-codes-blacklist if set)
  -b, --status-codes-blacklist string   Negative status codes (will override status-codes if set) (default "404")
      --timeout duration                HTTP Timeout (default 10s)
  -u, --url string                      The target URL
  -a, --useragent string                Set the User-Agent string (default "gobuster/3.2.0")
  -U, --username string                 Username for Basic Auth

Global Flags:
      --delay duration    Time each thread waits between requests (e.g. 1500ms)
      --no-color          Disable color output
      --no-error          Don't display errors
  -z, --no-progress       Don't display progress
  -o, --output string     Output file to write results to (defaults to stdout)
  -p, --pattern string    File containing replacement patterns
  -q, --quiet             Don't print the banner and other noise
  -t, --threads int       Number of concurrent threads (default 10)
  -v, --verbose           Verbose output (errors)
  -w, --wordlist string   Path to the wordlist
  */