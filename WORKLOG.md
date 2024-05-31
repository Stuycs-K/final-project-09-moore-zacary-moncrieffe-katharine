# Work Log

## Zac Moore

### 5/22

- Created source code folder & class files
- Looked at gobuster dir mode source code to try and understand how it works

### 5/23

- Started dir code, made for loop of sending requests to url/path where path is a word from the wordlist

### 5/24
- Finished for loop to make requests
- Fixed code to send requests and made a `get(url` function
- Output if url is valid or invalid

### 5/28
- created function readWordlistIntoArray that reads wordlist into string array

### 5/29
- dir mode outputs url that request redirects to

### 5/30
- created options.java and parseArgs method; looked at gobuster options and picked out ones that would be applicable

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