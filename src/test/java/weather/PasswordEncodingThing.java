package weather;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodingThing {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("No command line argument specified");System.exit(-1);
		}
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println("Password: " + args[0]);
		System.out.println("Encoded.: " + encoder.encode(args[0]));
	}

}
