## Javabuster
Zacary Moore, Katharine Moncrieffe
Cybersecurity Pd 9

### 2 main modes: Directory and DNS

#### Directory Mode
Enumerate directories and files on a webserver. 
To perform directory enumeration, use the interactive wizard or the following command:
```java Javabuster -m dir -d example.com -w wordlist.txt```

#### DNS Mode
Discover subdomains and enumerate files. 
To perform DNS enumeration, use the interactive wizard or the following command:
```java Javabuster -m dns -d example.com -w wordlist.txt```

### Usage:
1. Clone or download the repository to your local machine.
2. Ensure that Java is installed and configured correctly.
3. Compile the source files using the Java compiler: ```javac *.java```

#### Interactive Wizard
Allows users who are unfamiliar with using the command line to use Javabuster effectively. 
Prompts user to specify mode, domain, wordlist, other options. 

#### Options
- `-m, --mode`: Specifies the mode of enumeration (dns/dir).
- `-d, --domain`: Specifies the target domain.
- `-w, --wordlist`: Specifies the path to the wordlist file.
- `-r, --redirect`: Follows redirects during enumeration.
- `-e, --extensions`: Checks for specific file extensions during enumeration.
- `--delay`: Specifies the delay between requests (in milliseconds).
- `-x`: Specifies a list of file extensions to check.
- `-z, --progress`: Displays progress during enumeration.
- `-h, --help`: Displays the help menu.

