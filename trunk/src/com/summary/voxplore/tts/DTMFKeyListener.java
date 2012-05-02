package com.summary.voxplore.tts;

import java.util.Scanner;

public class DTMFKeyListener{
  public static void main(String arg[]){
    String nayme="";  // Declare & initialize a String to hold input.
    Scanner input=new Scanner(System.in); // Decl. & init. a Scanner.

    System.out.print("Whut yur nayme? >");  // Troll asks for name.
    nayme=input.next(); // Get what the user types.
    System.out.println();  // Move down to a fresh line.
    // Then say something trollish and use their name.
    System.out.println("Hur, hur! "+String.valueOf(nayme) + nayme + "!");
  }
}