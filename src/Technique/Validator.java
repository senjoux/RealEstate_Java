/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

/**
 *
 * @author seif
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Validator {
 
	private Pattern emailpattern;
	private Matcher emailmatcher;
        
        private Pattern passPattern;
        private Matcher passMatcher;
 
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        private static final String PASS_PATTERN ="^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$" ;
 
	public Validator() {
		emailpattern = Pattern.compile(EMAIL_PATTERN);
                passPattern=Pattern.compile(PASS_PATTERN);
	}
 
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateEmail( String hex) {
 
		emailmatcher = emailpattern.matcher(hex);
		return emailmatcher.matches();
 
	}
        public boolean validatePass( String hex){
                passMatcher=passPattern.matcher(hex);
                return passMatcher.matches();
        }
}
