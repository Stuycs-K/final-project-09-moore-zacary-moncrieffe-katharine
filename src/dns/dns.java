import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class dns {

    private Options globalopts;
    private OptionsDNS options;
    private boolean isWildcard;
    private List<InetAddress> wildcardIps;

    public dns(Options globalopts, OptionsDNS options) throws Exception {
        if (globalopts == null || options == null) {
            throw new IllegalArgumentException("Please provide valid options");
        }

        this.globalopts = globalopts;
        this.options = options;
        this.wildcardIps = new ArrayList<>();
    }

    public String name() {
        return "DNS enumeration";
    }

    public void preRun() throws Exception {
        String guid = UUID.randomUUID().toString();
        List<InetAddress> wildcardIps = dnsLookup(guid + "." + options.domain);
        if (!wildcardIps.isEmpty()) {
            isWildcard = true;
            this.wildcardIps.addAll(wildcardIps);
            if (!options.wildcardForced) {
                throw new ErrWildcard(wildcardIps);
            }
        }

        if (!globalopts.quiet) {
            List<InetAddress> baseDomainIps = dnsLookup(options.domain);
            if (baseDomainIps.isEmpty()) {
                System.out.println("[-] Unable to validate base domain: " + options.domain);
            }
        }
    }
}