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
	 * un String en base al algoritmo de cifrado simétrico AES.
	 * 
	 */

	public void encriptarAES(String text) {	
		try {
			this.textEncrypt = text;
			this.algoritmo = "AES";
			
			/*
			 *Para utilizar una instancia del algoritmo AES se debe 
			 *utilizar el método estático getInstance de la clase Cipher 
			 *especificando el nombre del algoritmo.  
			 * 
			 */
			Cipher cesar = Cipher.getInstance(algoritmo);
			
			/*
			 *Para generar la clave secreta con la cual se va a 
			 *cifrar y descifrar la información, se debe invocar el
			 *método estático getInstance de la clase KeyGenerator con el fin de
			 *obtener una referencia al objeto que implementa
			 *el servicio de generación de clave AES. Posteriormente,
			 *se establece el tamaño y se genera la clave. Por defecto,
			 *el tamaño es de 128 bits. 
			 *
			 */
			
			KeyGenerator generateKey = KeyGenerator.getInstance(algoritmo);
			generateKey.init(256);
			
			SecretKey scKey = generateKey.generateKey();
			
			/*
			 * Para cifrar la información se debe establecer el modo de operación ENCRYPT_MODE
			 * y pasar como parámetro la clave secreta con la cual se va a cifrar la información. 
			 *
			 */
			
			cesar.init(Cipher.ENCRYPT_MODE, scKey);
			
			/*
			 * doFinal: Cifra o descifra datos en una operación de una sola parte,
			 * o finaliza una operación de varias partes. Los datos se cifran o descifran,
			 * según cómo se inicializó este cifrado.
			 *
			 */
			
			cifradoBytes = cesar.doFinal(textEncrypt.getBytes());
			
			/*
			 *Para descifrar la información se debe establecer el modo de operación
			 *DECRYPT_MODE y pasar como parámetro la misma clave secreta que se utilizó
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
