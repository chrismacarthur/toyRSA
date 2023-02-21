# toyRSA
toyRSA is a command-line encryption simulator designed to implement the RSA encryption scheme and gain familiarity with algorithms relying on arithmetic.


## Dependencies
toyRSA relies on a few standard, built-in Java libraries, and should be compatible with any version of **Java 1.5+**.


## Running The Simulator
The simulator is housed in a single java class, and requires no additional frameworks. Installing, compiling, and executing the program can be done as follows:
```
git clone https://github.com/chrismacarthur/toyRSA
cd toyRSA
javac toyRSA.java
java toyRSA
```

toyRSA offers 3 command options to the user upon running:
```
1: to generate random plaintext and encrypt   
2: to decrypt ciphertext
3: to terminate the program
```
### Command 1: 
By typing `1`, toyRSA will proceed to generate a random plaintext message in the form of a `BigInteger` object. Upon doing so, the plaintext message will then be encrypted through modular exponentation with the automatically generated public key, resulting in the corresponding ciphertext.

### Command 2: 
By typing `2`, toyRSA will take the encrypted ciphertext and perform modular exponentation once more, this time, with the private key. The result will be the original plaintext message.

### Command 3: 
Typing `3` will terminate the program.


## Why Bother?
toyRSA is created as a learning tool to understand RSA encryption, but more importantly, how public-key encryption algorithms function as a whole. Additionally, performing operations with large exponents can result in costly operations (specifically when decrypting plaintext messages, in which case the private key for RSA can become quite big). Studying several mathematic techniques such as modular exponentation allows the program to function at a level far more efficient than standard computation would permit.


## Disclaimer
PLEASE NOTE: This Program is **NOT** suitable for real-world encryption, and does not meet the required bit-length or other standards put in place for modern RSA. This is strictly designed for interactive/educative purposes.