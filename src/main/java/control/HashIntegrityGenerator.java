package control;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Base64;

public class HashIntegrityGenerator {
	
	private String integrityAttribute = null;
	private String url;
    public HashIntegrityGenerator(String url) {
    	this.url=url;
        String hashAlgorithm = "SHA-256"; // Puoi scegliere tra SHA-512, SHA-384, o SHA-256
        try {
            // Carica la risorsa remota
            InputStream inputStream = new URL(url).openStream();
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] data = outputStream.toByteArray();

            // Calcola l'hash
            MessageDigest digest = MessageDigest.getInstance(hashAlgorithm);
            byte[] hashBytes = digest.digest(data);

            // Codifica l'hash in Base64
            String base64Hash = Base64.getEncoder().encodeToString(hashBytes);
            
            this.integrityAttribute = hashAlgorithm + "-" + base64Hash;
            
            inputStream.close();
            outputStream.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public String getSecure() {
        return integrityAttribute!=null ? "<script src=\"" + url + "\" integrity=\"" + integrityAttribute + "\" crossorigin=\"anonymous\"></script>" : "<script src=\"" + null + "\" integrity=\"" + integrityAttribute + "\" crossorigin=\"anonymous\"></script>";
    }
}