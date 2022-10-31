package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Hash {
    public static String hash(String content, int rounds) {
        return BCrypt.withDefaults().hashToString(rounds, content.toCharArray());
    }

    public static BCrypt.Result verify(String hashString, String toMatch) {
        return BCrypt.verifyer().verify(toMatch.toCharArray(), hashString);
    }
}
