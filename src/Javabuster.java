import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Javabuster {

    public static void main(String[] args) {
        Options options;
        Scanner scanner = new Scanner(System.in);

        // option to use wizard or args
        System.out.println(
    "    \\ \\  __ ___   ____ _| |__  _   _ ___| |_ ___ _ __    by \n" +
    "     \\ \\/ _` \\ \\ / / _` | '_ \\| | | / __| __/ _ \\ '__|   Katharine Moncrieffe\n" +
    "  /\\_/ / (_| |\\ V / (_| | |_) | |_| \\__ \\ ||  __/ |      and\n" +
    "  \\___/ \\__,_| \\_/ \\__,_|_.__/ \\__,_|___/\\__\\___|_|      Zacary Moore\n");
        System.out.println("To use the wizard, run without any arguments.\n");
        
        if (args.length > 0)
            options = Options.parseArgs(String.join(" ", args));
        else
            options = Options.wizard();

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
            Dir.dir(options.domain, wordlist.toArray(new String[0]), options);
        } else {
            System.out.println("invalid mode; please use dns or dir");
        }
    }

    public static void printHelp() {
        System.out.println("========================================");
        System.out.println("Usage: java Javabuster <options>");
        System.out.println("Options:");
        System.out.println("  -m        Mode to use (dns/dir)");
        System.out.println("  -d        The target domain");
        System.out.println("  -w        Path to the wordlist");
        System.out.println("  -r        Follow redirects");
        System.out.println("  -e        Check for specific extensions");
        System.out.println("  --delay   Delay between requests (in ms)");
        System.out.println("  -x        List of extensions to check");
        System.out.println("  -f        List of filepaths to check");
        System.out.println("  -z        Show progress");
        System.out.println("  -h        Show help");
        System.out.println("========================================");
    }
}
