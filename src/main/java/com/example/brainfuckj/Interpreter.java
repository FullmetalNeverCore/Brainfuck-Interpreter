package com.example.brainfuckj;

import java.util.Scanner;
import java.util.List;

import java.util.ArrayList;

import java.util.Stack;

public class Interpreter {
    private static final int MEMORY_SIZE = 30000;
    private static byte[] memory = new byte[MEMORY_SIZE]; // Memory array
    private static int pointer = 0; // Pointer to the current cell

    public char[] Inter(String code){
        System.out.println(code);
        resetMemory();
        Scanner scanner = new Scanner(System.in);
        List<Character> inputChars = new ArrayList<>();
        Stack<Integer> loopStack = new Stack<>();

        for (int i = 0; i < code.length(); i++) {
            char command = code.charAt(i); // get current character in bf code.
            switch (command) {
                case '>': // increment pointer
                    pointer++;
                    if (pointer >= MEMORY_SIZE) {
                        pointer = 0; // Wrap around to the start of the array
                    }
                    break;
                case '<': // decrement pointer
                    pointer--;
                    if (pointer < 0) {
                        pointer = MEMORY_SIZE - 1; // Wrap around to the end of the array
                    }
                    break;
                case '+': //increment chosen cell
                    memory[pointer]++;
                    break;
                case '-': //decrement chosen cell
                    memory[pointer]--;
                    break;
                case '.':
                    System.out.print((char) memory[pointer]); // converting ascii value into chars
                    inputChars.add((char) memory[pointer]);
                    break;
                case ',': // putting char into the memory array
                    char inputChar = scanner.next().charAt(0);
                    memory[pointer] = (byte) inputChar;
                    break;
                case '[': //if zero jump to ]
                    if (memory[pointer] == 0) {
                        int loop = 1;
                        while (loop > 0) {
                            char c = code.charAt(++i);
                            if (c == '[') loop++;
                            else if (c == ']') loop--;
                        }
                    } else {
                        loopStack.push(i);
                    }
                    break;
                case ']': //if non zero jump to back to [
                    if (memory[pointer] != 0) {
                        i = loopStack.peek();
                    } else {
                        loopStack.pop();
                    }
                    break;
            } }
        char[] inputArray = new char[inputChars.size()];
        for (int i = 0; i < inputChars.size(); i++) {
            inputArray[i] = inputChars.get(i);
        }
        scanner.close();
        return inputArray;
    }



    public void resetMemory(){
        for (int i = 0; i < MEMORY_SIZE; i++) {
            memory[i] = 0;
        }
        pointer = 0;
    }
}
