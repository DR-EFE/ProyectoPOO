package Model;


import java.util.Arrays;
import java.util.Objects;

public class UserCredentials {
    private int userID;
    
	private byte[] passwordHash; // Guardar el hash de la contraseña
    private byte[] salt; // Guardar el salt utilizado para el hash

    // Getters y setters
    public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public byte[] getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(passwordHash);
		result = prime * result + Arrays.hashCode(salt);
		result = prime * result + Objects.hash(userID);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCredentials other = (UserCredentials) obj;
		return Arrays.equals(passwordHash, other.passwordHash) && Arrays.equals(salt, other.salt)
				&& userID == other.userID;
	}
	
	
	
	
	}

	
	
	
	
	
	
	
	

