[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/ecp4su41)
## Javabusters
Zacary Moore and Katharine Moncrieffe, Cybersecurity Pd 9

## Overview
Javabuster is a **directory and DNS enumeration tool** like Gobuster/Dirbuster. It provides functionalities to **discover subdomains** associated with a target domain and **brute-force directories and file paths** on web servers. The tool offers flexibility in customization, allowing users to **specify options** such as wordlists, delay between requests, and output formats.

## Presentation
https://drive.google.com/file/d/10eRq6be8b2a_EJdc-aN0cHpL4MqoYH4E/view?usp=sharing

## Instructions
### Prerequisites
- Java Development Kit (JDK) installed on your system

### Installation
1. Clone or download the repository to your local machine.
2. Ensure that Java is installed and configured correctly.
3. Compile the source files using the Java compiler: ```javac *.java```

### Usage
#### Directory Enumeration
To perform directory enumeration, use the interactive wizard or the following command:
```java Javabuster -m dir -d example.com -w wordlist.txt```
Replace `example.com` with the target domain and `wordlist.txt` with the path to your wordlist file containing directory and file paths.

#### DNS Enumeration
To perform DNS enumeration, use the interactive wizard or the following command: 
```java Javabuster -m dns -d example.com -w wordlist.txt```
Replace `example.com` with the target domain and `wordlist.txt` with the path to your wordlist file containing subdomain names.

### Options
- `-m, --mode`: Specifies the mode of enumeration (dns/dir).
- `-d, --domain`: Specifies the target domain.
- `-w, --wordlist`: Specifies the path to the wordlist file.
- `-r, --redirect`: Follows redirects during enumeration.
- `-e, --extensions`: Checks for specific file extensions during enumeration.
- `--delay`: Specifies the delay between requests (in milliseconds).
- `-x`: Specifies a list of file extensions to check.
- `-z, --progress`: Displays progress during enumeration.
- `-h, --help`: Displays the help menu.
