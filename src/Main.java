import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Options options;
        Scanner scanner = new Scanner(System.in);

        // option to use wizard or args
        System.out.println("input method:");
        System.out.println("1. command-line arguments");
        System.out.println("2. interactive wizard");
        System.out.print("choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  

        if (choice == 1) {
            if (args.length == 0) {
                System.out.println("usage: java Main <options>");
                return;
            }
            options = Options.parseArgs(String.join(" ", args));
        } else if (choice == 2) {
            options = Options.wizard();
        } else {
            System.out.println("invalid choice");
            return;
        }

        if (options.help) {
            printHelp();
            return;
        }

        if (options.mode == null || options.mode.isEmpty()) {
            System.out.println("error: mode (-m) must be specified (dns/dir)");
            return;
        }

        if (options.wordlistPath == null || options.wordlistPath.isEmpty()) {
            System.out.println("error: wordlist path (-w) must be specified");
            return;
        }

        List<String> wordlist = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(options.wordlistPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordlist.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("error reading wordlist file: " + e.getMessage());
            return;
        }

        if (options.mode.equalsIgnoreCase("dns")) {
            Dns.dnsEnum(options.domain, wordlist.toArray(new String[0]), options);
        } else if (options.mode.equalsIgnoreCase("dir")) {
            Dir.dir(options.domain, wordlist.toArray(new String[0]));
        } else {
            System.out.println("invalid mode; please use dns or dir");
        }
    }

    public static void printHelp() {
        System.out.println("Usage: java Main <options>");
        System.out.println("Options:");
        System.out.println("  -m, --mode         Mode to use (dns/dir)");
        System.out.println("  -d, --domain       The target domain");
        System.out.println("  -w, --wordlist     Path to the wordlist");
        System.out.println("  -r, --redirect     Follow redirects");
        System.out.println("  -e, --extensions   Check for specific extensions");
        System.out.println("  --delay            Delay between requests (in ms)");
        System.out.println("  -x                 List of extensions to check");
        System.out.println("  -z, --progress     Show progress");
        System.out.println("  -h, --help         Show help");
    }
}