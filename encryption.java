import​ java.awt.GridLayout;
import​ java.awt.event.ActionEvent;
import​ java.awt.event.ActionListener;
import​ java.util.Arrays;
import​ javax.crypto.*;
import​ javax.swing.*;

public class AESimplementation extends JFrame implements ActionListener {

  JButton encryptBtn, decryptBtn;
  JTextArea output, input;
  SecretKey key;
  byte​[] ciphertext;

  public​ ​static​ ​void​ ​main​(String[] args)​ {
    new​ AESimplementation();
  }

  public AESimplementation() {
    JPanel upanel = ​new​ JPanel(​new​ GridLayout(​1​, ​2​, ​2​, ​2​));
    JPanel dpanel = ​new​ JPanel(​new​ GridLayout(​2​, ​1​));
    encryptBtn = ​new​ JButton(​"Encrypt text"​);
    decryptBtn = ​new​ JButton(​"Decrypt text"​);
    output = ​new​ JTextArea();
    input = ​new​ JTextArea(​"Introduction to Computer Security."​);
    encryptBtn.addActionListener(​this​);
    decryptBtn.addActionListener(​this​);
    input.setLineWrap(​true​);
    output.setLineWrap(​true​);
    this​.setLayout(​new​ GridLayout(​2​, ​1​, ​2​, ​2​));
    upanel.add(encryptBtn);
    upanel.add(decryptBtn);
    dpanel.add(upanel);
    dpanel.add(input);
    this​.add(dpanel);
    this​.add(output);
    this​.setSize(​400​, ​400​);
    this​.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this​.setVisible(​true​);
    try​ { key = KeyGenerator.getInstance(​"AES"​).generateKey();
    } catch​ (Exception e) {output.setText(​"Could not generate key!"​);}
  }

  public void actionPerformer(ActionEvent e) {
    try {
      Cipher cipher = Cipher.getInstance(​"AES/ECB/PKCS5Padding"​);
      if​ (e.getSource() == encryptBtn && ciphertext == ​null​) {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        ciphertext = cipher.doFinal(input.getText().getBytes());
        output.setText(​"Ciphertext:"​ +​"\n"​+ Arrays.toString(ciphertext));
      }
      else​ ​if​ (e.getSource() == decryptBtn && ciphertext != ​null​) {
        cipher.init(Cipher.DECRYPT_MODE, key);
        output.setText(​"Plaintext:"​ + ​"\n" + ​new​ String(cipher.doFinal(ciphertext)));
        ciphertext = ​null​;
      }
    } catch​ (Exception e1){output.setText(​"Could not perform encryption/decryption!"​);}
  }

}
