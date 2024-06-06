public class Main{
    public static void main(String[] args){
        
    }
    private static void printHelp() {
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