import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * toyRSA implements a simplified version of the RSA Encryption Scheme Algorithm.
 * PLEASE NOTE: This Program is NOT suitable for real-world encryption, and does not meet the required bit-length or other standards
 * put in place for modern RSA. This is designed for interactive/educative purposes.
 */
public class toyRSA {

  /** the supplied first prime number used to calculate the composite modulus. */
  private static BigInteger p = new BigInteger("491");

  /** the supplied second prime number used to calculate the composite modulus. */
  private static BigInteger q = new BigInteger("269");
  
  /** the ciphertext to be decrypted. */
  private static BigInteger c;

  /** the plaintext to be encrypted. */
  private static BigInteger m;

  /** the composite modulus resulting from p and q. */
  private static BigInteger n = p.multiply(q);

  /** the encryption exponent (public key) to be calculated. */
  private static BigInteger e = new BigInteger("2");

  /** the decryption exponent (private key) to be calculated. */
  private static BigInteger d = new BigInteger("0");

  /** helper variable in calculating the private key. */
  private static BigInteger b;

  /** helper-loop variable in calculating the private key. */
  private static int i = 0;

  /** tracks the current session message. */
  private static int sessionCount = 1;

  /**
   * Driver/main method that collects input, analyzes commands, 
   * and calls the assigned functions.
   * @param args stores command-line arguments.
   */
  public static void main(String[] args) {

    boolean somethingIsEncrypted = false;
    int choice;
    keyGen(p, q);
    Scanner scan = new Scanner(System.in);
    Random random = new Random();
    System.out.print("\nWelcome to toyRSA, an RSA Encryption Scheme simulator.\n--------------------\nSUPPLIED VALUES:\nThe first prime is p = " + p +"\nThe second prime is q = " + q + 
    "\nGENERATED VALUES:\nThe composite modulus n = " + n + "\nThe encryption exponent e = " + e + "\nThe decryption exponent d = " + d + 
    "\n--------------------\nCommands:\n\t1: to generate random plaintext and encrypt\n\t2: to decrypt ciphertext\n\t3: to terminate the program\n");

    while(true) {
      choice = scan.nextInt();
      switch(choice) {
        case 1:
          if(somethingIsEncrypted == false) {
            m = new BigInteger((n.bitLength() - 1), random);
            enc(e, m);
            somethingIsEncrypted = true;
            break;
          }
          else {
            System.out.println("--------------------\nLooks like you've already encrypted a message! Try decrypting before encrypting a new message.\n--------------------\n");
            break;
          }
        case 2:
          if(somethingIsEncrypted == true) {
            dec(d, c);
            somethingIsEncrypted = false;
            break;
          }
          else {
            System.out.println("--------------------\nLooks like you don't have an active encrypted message! Try encrypting a new message first.\n--------------------\n");
            break;
          }
        case 3:
          scan.close();
          return;
      }
    }
  }


  /**
   * Encrypts the passed plaintext message with the corresponding public key.
   * the modExponentation method is then forwarded these variables while feedback of 
   * the generated actions is provided.
   * @param e the encryption exponent (public key).
   * @param m the plaintext message.
   */
  public static void enc(BigInteger e, BigInteger m) {
    System.out.println("\n-------------------------\nEncrypting Session Message " + sessionCount + ":\nPlaintext to be encrypted is m = " + m);
    System.out.println("performing modular exponentation on " + m + "^" + e + " % " + n + "...");
    c = modExponentation(m, e.intValue(), n);
    System.out.println("Done! The resulting ciphertext is c = " + c);
    System.out.println("-------------------------\nCommands:\n\t1: to generate random plaintext and encrypt\n\t2: to decrypt ciphertext\n\t3: to terminate the program\n--------------------\n");
  }


  /**
   * Decrypts the passed ciphertext with the corresponding private key.
   * the modExponentation method is then forwarded these variables while feedback of 
   * the generated actions is provided.
   * @param d the decryption exponent (private key).
   * @param c the encrypted ciphertext.
   */
  public static void dec(BigInteger d, BigInteger c) {
    System.out.println("\n-------------------------\nDecrypting Session Message " + sessionCount + ":\nCiphertext to be decrypted is c = " + c);
    BigInteger decM;
    System.out.println("performing modular exponentation on " + c + "^" + d + " % " + n + "...");
    decM = modExponentation(c, d.intValue(), n);
    System.out.println("Done! The decrypted plaintext is m = " + decM);
    sessionCount++;
    System.out.println("-------------------------\nCommands:\n\t1: to generate random plaintext and encrypt\n\t2: to decrypt ciphertext\n\t3: to terminate the program\n--------------------\n");
  }


  /**
   * Generates the public and private keys to be used for encryption and decryption.
   * @param p first prime number used to calculate the composite modulus.
   * @param q second prime number used to calculate the composite modulus.
   */
  public static void keyGen(BigInteger p, BigInteger q) {

    /** Public key generation. */
    BigInteger phi = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
    while(e.compareTo(phi) < 0) {
      if(!phi.gcd(e).equals(BigInteger.ONE)) {
        e = e.add(BigInteger.ONE);
      }
      else {
        break;
      }
    }

    /** Private key generation. */
    while(i < n.intValue()) {
      b = (BigInteger.ONE).add((phi.multiply(BigInteger.valueOf(i))));
      if(b.mod(e).equals(BigInteger.ZERO)) {
        d = b.divide(e);
        break;
      }
      i++;
    }
  }


  /**
   * Implements modular exponentation, which is used to encrypt and decrypt
   * depending on the provided arguments
   * @param num the base number we are performing the modulus operation on.
   * @param exp the exponent applied to the base number.
   * @param n the modulus number.
   * @return the resulting BigInteger object with the value of the operation.
   */
  public static BigInteger modExponentation(BigInteger num, int exp, BigInteger n) {
    BigInteger result = BigInteger.ONE;
    if(n.signum() == 0) {
      return BigInteger.ZERO;
    }
    for(int i = 0; i < exp; i++) {
      result = (result.multiply(num)).mod(n);
    }
    return result;
  }
}