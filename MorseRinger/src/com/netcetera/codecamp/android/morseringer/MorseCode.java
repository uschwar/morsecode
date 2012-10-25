package com.netcetera.codecamp.android.morseringer;

import java.util.HashMap;

public class MorseCode {

  private final HashMap<Character, char[]> codes = new HashMap<Character, char[]>();

  public MorseCode() {
    codes.put('A', ".-".toCharArray());
    codes.put('B', "-...".toCharArray());
    codes.put('C', "-.-.".toCharArray());
    codes.put('D', "-..".toCharArray());
    codes.put('E', ".".toCharArray());
    codes.put('F', "..-.".toCharArray());
    codes.put('G', "--.".toCharArray());
    codes.put('H', "....".toCharArray());
    codes.put('I', "..".toCharArray());
    codes.put('J', ".---".toCharArray());
    codes.put('K', "-.-".toCharArray());
    codes.put('L', ".-..".toCharArray());
    codes.put('M', "--".toCharArray());
    codes.put('N', "-.".toCharArray());
    codes.put('O', "---".toCharArray());
    codes.put('P', ".--.".toCharArray());
    codes.put('Q', "--.-".toCharArray());
    codes.put('R', ".-.".toCharArray());
    codes.put('S', "...".toCharArray());
    codes.put('T', "-".toCharArray());
    codes.put('U', "..-".toCharArray());
    codes.put('V', "...-".toCharArray());
    codes.put('W', ".--".toCharArray());
    codes.put('X', "-..-".toCharArray());
    codes.put('Y', "-.--".toCharArray());
    codes.put('Z', "--..".toCharArray());
    codes.put('0', "-----".toCharArray());
    codes.put('1', ".----".toCharArray());
    codes.put('2', "..---".toCharArray());
    codes.put('3', "...--".toCharArray());
    codes.put('4', "....-".toCharArray());
    codes.put('5', ".....".toCharArray());
    codes.put('6', "-....".toCharArray());
    codes.put('7', "--...".toCharArray());
    codes.put('8', "---..".toCharArray());
    codes.put('9', "----.".toCharArray());
    codes.put('À', ".--.-".toCharArray());
    codes.put('Å', ".--.-".toCharArray());
    codes.put('Ä', ".-.-".toCharArray());
    codes.put('È', ".-..-".toCharArray());
    codes.put('É', "..-..".toCharArray());
    codes.put('Ö', "---.".toCharArray());
    codes.put('Ü', "..--".toCharArray());
    codes.put('ß', "...--..".toCharArray());
    codes.put('Ñ', "--.--".toCharArray());
    codes.put('.', ".-.-.-".toCharArray());
    codes.put(',', "--..--".toCharArray());
    codes.put(':', "---...".toCharArray());
    codes.put(';', "-.-.-.".toCharArray());
    codes.put('?', "..--..".toCharArray());
    codes.put('-', "-....-".toCharArray());
    codes.put('_', "..--.-".toCharArray());
    codes.put('(', "-.--.".toCharArray());
    codes.put(')', "-.--.-".toCharArray());
    codes.put('=', "-...-".toCharArray());
    codes.put('+', ".-.-.".toCharArray());
    codes.put('/', "-..-.".toCharArray());
    codes.put('@', ".--.-.".toCharArray());
    codes.put(' ', " ".toCharArray());
  }

  public char[] getMorseCode(char key) {
    return codes.get(key);
  }

}
