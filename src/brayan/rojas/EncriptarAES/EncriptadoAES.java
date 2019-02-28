package brayan.rojas.EncriptarAES;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class EncriptadoAES {
	
	private String textEncrypt;
	private String algoritmo;
	private byte[] cifradoBytes;
	private byte[] descifroHash;

	public byte[] getCifradoBytes() {
		return cifradoBytes;
	}

	public byte[] getDescifroHash() {
		return descifroHash;
	}

	/**
	 * @author brayan.rojas
	 * 
	 * @param text
	 * 
	 * @return "Sysout()"
	 * 
	 * @comment Metodo encargado de encriptar y desencriptar
	 * un String en base al algoritmo de cifrado sim�trico AES.
	 * 
	 */

	public void encriptarAES(String text) {	
		try {
			this.textEncrypt = text;
			this.algoritmo = "AES";
			
			/*
			 *Para utilizar una instancia del algoritmo AES se debe 
			 *utilizar el m�todo est�tico getInstance de la clase Cipher 
			 *especificando el nombre del algoritmo.  
			 * 
			 */
			Cipher cesar = Cipher.getInstance(algoritmo);
			
			/*
			 *Para generar la clave secreta con la cual se va a 
			 *cifrar y descifrar la informaci�n, se debe invocar el
			 *m�todo est�tico getInstance de la clase KeyGenerator con el fin de
			 *obtener una referencia al objeto que implementa
			 *el servicio de generaci�n de clave AES. Posteriormente,
			 *se establece el tama�o y se genera la clave. Por defecto,
			 *el tama�o es de 128 bits. 
			 *
			 */
			
			KeyGenerator generateKey = KeyGenerator.getInstance(algoritmo);
			generateKey.init(256);
			
			SecretKey scKey = generateKey.generateKey();
			
			/*
			 * Para cifrar la informaci�n se debe establecer el modo de operaci�n ENCRYPT_MODE
			 * y pasar como par�metro la clave secreta con la cual se va a cifrar la informaci�n. 
			 *
			 */
			
			cesar.init(Cipher.ENCRYPT_MODE, scKey);
			
			/*
			 * doFinal: Cifra o descifra datos en una operaci�n de una sola parte,
			 * o finaliza una operaci�n de varias partes. Los datos se cifran o descifran,
			 * seg�n c�mo se inicializ� este cifrado.
			 *
			 */
			
			cifradoBytes = cesar.doFinal(textEncrypt.getBytes());
			
			/*
			 *Para descifrar la informaci�n se debe establecer el modo de operaci�n
			 *DECRYPT_MODE y pasar como par�metro la misma clave secreta que se utiliz�
			 *para cifrar el contenido.  
			 *
			 */
			cesar.init(Cipher.DECRYPT_MODE, scKey);
			descifroHash = cesar.doFinal(cifradoBytes);
			
			//System.out.println(encodeNDecodeText());
			//String encodedEncText = javax.xml.bind.DatatypeConverter.printBase64Binary(cifradoBytes);
			System.out.println("Encrypted text: " + new String(cifradoBytes,"UTF-8"));
			//System.out.println("Encrypted and encoded text: " + encodedEncText);
			System.out.println("Decrypted text: " + new String(descifroHash));		  
			
		}catch(NoSuchAlgorithmException e){
			e.getMessage();
		}catch(NoSuchPaddingException e){
			e.getMessage();
		}catch(InvalidKeyException e){
			e.getMessage();
		}catch(BadPaddingException e){
			e.getMessage();
		}catch(IllegalBlockSizeException e){
			e.getMessage();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	}
}
