function crypt(event){
	
	event.PreventDefault();
	
	var passwordField = document.getElementById('password');
    var password = passwordField.value;

            // Crittografa la password usando SHA-256
            var hashedBytes = CryptoJS.SHA256(password);

            // Converti in stringa esadecimale
            var hashedPassword = hashedBytes.toString(CryptoJS.enc.Hex);

            // Sostituisci il valore del campo password con l'hash
            passwordField.value = hashedPassword;

            // Invia il form
            event.target.submit();
}