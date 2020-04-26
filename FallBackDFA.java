package compilersLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*; 
import java.util.*;

public class FDFA {

	public static void main(String[] args) {
		

//		0,0,1,00;1,2,1,01;2,0,3,10;3,3,3,11#0,1,2
//		0,1,0,00;1,1,2,01;2,3,2,10;3,3,3,11#1,2
//		1011100
		Scanner in = new Scanner(System.in);
		String FDFA ="0,1,0,00;1,1,2,01;2,3,2,10;3,3,3,11#1,2";
		
		
		String[] FDFAsplitted = FDFA.split("#");
		List<String> FDFAsplittedList = (List) Arrays.asList(FDFAsplitted);
		ArrayList<String> FDFAsplittedArrayList = new ArrayList<String>(FDFAsplittedList);
		
		
		String goalsString = FDFAsplittedArrayList.get(1);
		String[] goals = goalsString.split(",");
		List<String> goalsList = (List) Arrays.asList(goals);
		ArrayList<String> goalsArrayList = new ArrayList<String>(goalsList);
		
		String DFAvalues = FDFAsplittedArrayList.get(0);
		String[] elements = DFAvalues.split(";");
		List<String> stringlist = (List) Arrays.asList(elements);
		ArrayList<String> listofstrings = new ArrayList<String>(stringlist);
		System.out.println("nodes : " + listofstrings);
		System.out.println("goals : " + goalsArrayList);

		
//		System.out.println("Enter the input string: ");
//		Scanner in2 = new Scanner(System.in);
//		1011100
		String input = "100";
		Stack st = new Stack();
		int rightarrow = input.length();
		String FinalOutput = "";
		

		
		st = FillStack(input, listofstrings, st);


//		leftarrow = 0;
//		rightarrow = input.length()-1;
//		for (int j = 0; j < input.length()+1; j++) {
//			String peek = (String) st.peek();
//			if(isGoal(peek, goalsArrayList)){
//				char stpeekC = ((String) st.peek()).charAt(0);
//				for (int k = 0; k < listofstrings.size(); k++) {
//					if(stpeekC == listofstrings.get(k).charAt(0)){
//						FinalOutput += "" + listofstrings.get(k).charAt(6) + listofstrings.get(k).charAt(7);
//						st.empty();
//						if(rightarrow < input.length())
//							input = input.substring(rightarrow+1,input.length()-1);
//						break;
//					}
//				}
//				if(input.length()<=1){
//					finished = 1;
//				break;
//			}
//				}
//			else{
//				st.pop();
//				rightarrow --;
//			}			
//		}
		
	
		System.out.println("stack : " + st);
		System.out.println("-----------------------------------------------");
//		System.out.println("Final String : " + Finalize(st, input, listofstrings, goalsArrayList, rightarrow, FinalOutput));
		
		Finalize(st, input, listofstrings, goalsArrayList, rightarrow, FinalOutput);
		}
	
	
	public static Stack FillStack(String input, ArrayList<String> listofstrings, Stack st){
		char curState = '0';
		String curStateS = "";
		String nextStateS = "";
		int curStateI;
		char nextState = '0';
		int myIndex = 0;
		int leftarrow = 0;
		int finished = 0;
		for (int i = 0; i < input.length(); i++) {
			char curInputC = input.charAt(i);
			if(curInputC != ',' && curInputC != ';' && curInputC != '#'){
				
				if(curInputC == '0'){
					curStateS = Character.toString(curState);
					curStateI = Integer.parseInt(curStateS);
					nextState = listofstrings.get(curStateI).charAt(2);
					nextStateS = Character.toString(nextState);
				}
				
				else{
					curStateS = Character.toString(curState);
					curStateI = Integer.parseInt(curStateS);
					nextState = listofstrings.get(curStateI).charAt(4);
					nextStateS = Character.toString(nextState);

				}
				
			}
			st.push(curStateS);
			curState = nextState;
			}
		st.push(nextStateS);
		return st;
	}
	
	
	
	
	
	
	public static String Finalize(Stack st, String input, ArrayList<String> listofstrings, ArrayList<String> goalsArrayList, int rightarrow,
			String FinalOutput){
		int dakhalfelelse = 0;
		if(st.size() <= 1 || input.length() == 0){
			return FinalOutput;
		}
		
		
		for (int j = 0; j < input.length(); j++) {
			
			String topOfStack = (String) st.peek();
			char topOfStackC = topOfStack.charAt(0);
		
		if(isGoal(topOfStack, goalsArrayList)){
			for (int i = 0; i < listofstrings.size(); i++) {
				if(listofstrings.get(i).charAt(0) == topOfStackC){
					FinalOutput += "" + listofstrings.get(i).charAt(6) + listofstrings.get(i).charAt(7);
					System.out.println("final output : " + FinalOutput);
					st.clear();
					input = input.substring(rightarrow-1, input.length());
					if(dakhalfelelse==0){
						input = "";
					}
					st = FillStack(input, listofstrings, st);
					Finalize(st, input, listofstrings, goalsArrayList, rightarrow, FinalOutput);
				}
			}
		}
		else{
			dakhalfelelse = 1;
			st.pop();
			rightarrow--;
		}
		
		}
		return FinalOutput;
	}
	
	
	
	
	
	public static boolean isGoal(String state, ArrayList<String> goalsArrayList){
		for (int i = 0; i < goalsArrayList.size(); i++) {
			if(state.equals(goalsArrayList.get(i))){
				return true;				
			}
		}
		return false;
	}
	
}
