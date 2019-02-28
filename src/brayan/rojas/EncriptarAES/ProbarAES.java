package brayan.rojas.EncriptarAES;


public class ProbarAES {

	public static void main(String[] args) {
		
		String pww = "yourText";
		
		EncriptadoAES instancia = new EncriptadoAES();
		instancia.encriptarAES(pww);
	}

}
