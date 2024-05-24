import java.io.*;
import java.net.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class dns {
    private final ExecutorService executor = Executors.newFixedThreadPool(10); 
    private final Options globalopts; //tbd
    private final OptionsDNS options;
    private final boolean isWildcard = false;
    private final Set<InetAddress> wildcardIps = new HashSet<>();
    private final String resolver;
   
   public dns(Options globalopts, OptionsDNS options) throws Exception {
        if (globalopts == null) {
            throw new IllegalArgumentException("please provide valid global options");
        }

        if (options == null) {
            throw new IllegalArgumentException("please provide valid plugin options");
        }

        this.globalopts = globalopts;
        this.options = options;
        this.resolver = options.resolver.isEmpty() ? "8.8.8.8" : options.resolver;
    }
}

