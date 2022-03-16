package contactmgr;

import java.util.Scanner;

public class Input {

    private Scanner promptInput;

    public static void main(String[] args) {
    }

    public Input() {
        this.promptInput = new Scanner(System.in);
    }

    public int getInt() {
        int userNum = promptInput.nextInt();
        promptInput.nextLine();
        System.out.println("You entered: " + userNum);
        return userNum;
    }

    public boolean yesNo(String anserToYN) {
        System.out.print(anserToYN);
        String bufferYesOrNo = promptInput.nextLine();
        return (bufferYesOrNo.equalsIgnoreCase("y") || bufferYesOrNo.equalsIgnoreCase("yes"));
    }

    public String getString() {
        //        System.out.println("you entered: " + sentenceInput);
        return promptInput.nextLine();
    }


    public Scanner getPromptIput() {
        return promptInput;
    }

    public void setPromptIput(Scanner promptIput) {
        this.promptInput = promptIput;
    }


}/// END OF CLASS
