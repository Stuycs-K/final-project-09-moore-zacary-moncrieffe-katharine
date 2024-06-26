# Work Log

## Zac Moore

### 5/22

- Created source code folder & class files
- Looked at gobuster dir mode source code to try and understand how it works

### 5/23

- Started dir code, made for loop of sending requests to url/path where path is a word from the wordlist

### 5/24
- Finished for loop to make requests
- Fixed code to send requests and made a `get(url\)` function
- Output if url is valid or invalid

### 5/28
- created function readWordlistIntoArray that reads wordlist into string array

### 5/29 
- dir mode outputs url that request redirects to

### 5/30
- created options.java and parseArgs method; looked at gobuster options and picked out ones that would be applicable

### 5/31 
- merged dir with main, removed main method from dir class

### 6/3 
- created regex to get value after flag, (e.g. -w VALUE), created wizard

### 6/4
- added extension searching to dir mode

###
- added ascii art, made it so wizard only opens when no args are present

## Katharine Moncrieffe

### 5/22
research on original gobuster

### 5/23
fixed some problems with wsl jdk in class; continuing to try to understand how original gobuster's dns mode works

started on our dns functionality

### 5/24
continuing work on dns based on gobuster source; made dns subdirectory for organization

### weekend, 5/28
added more methods from the original gobuster mode, but realized their mode is more than what we need

### 5/29
rewrote my earlier code for a simpler implementation; plan to flesh out more (multithreading, fixing wordlist, etc)

### 5/30
edited main() to allow command line input of wordlist file, domain name

### 5/31
figuring out which of the gobuster extensions are feasible/relevant to our work

### weekend, 6/3
added sources, determined what's left for us to finish
continued work on options

### 6/4, 6/5
implementing options in dns; making 1 main main to process options

### 6/6
base domain validation, extensions, progress flags in dns
added main class, searching for specific files in dns mode

### 6/7 
finished presentation.md, readme, slides

### sources
Dirbuster: https://github.com/KajanM/DirBuster

Gobuster: https://github.com/OJ/gobuster

Java docs: https://docs.oracle.com/en/java/

DomainValidator: https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/DomainValidator.html 
        
https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2F/java.net.http/java/net/http/HttpClient.Redirect.html

https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html
          
Regex lookaround: https://www.regular-expressions.info/lookaround.html

Scanner skipping `nextLine` call: https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/
