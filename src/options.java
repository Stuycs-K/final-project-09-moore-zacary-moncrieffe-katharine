import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Options {
    public static void main (String[] args) {
      parseArgs("enumerate -w thisistheurl");
    }

    public String domain; // -d
    public String wordlistPath; // -w
    public boolean outputRedirects = false; // -r
    public boolean help = false;

    static Options parseArgs(String argString) {
      Options o = new Options();
      // String[] args = argString.split(" ", 0);
      o.outputRedirects = argString.contains("-r");
      o.help = argString.contains("-h");
      // Pattern pattern = Pattern.compile("(?<=-w )[^ ]+", Pattern.CASE_INSENSITIVE);
      // Matcher matcher = pattern.matcher(argString);
      // matcher.find();
      // String s = matcher.group();
      System.out.println(getValueAfterFlag(argString, "-w"));
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