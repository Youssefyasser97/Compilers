package compilersLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Stack;

import org.hamcrest.core.SubstringMatcher;

//T09_37_4081_youssef_yasser
//Author: Youssef Yasser
//First and Follow methods: Khadija Khaled
public class T09_37_4081_youssef_yasser {

	/*
	 * Please update the file/class name, and the following comment
	 */

	// Tutorial_ID_Name

	static class CFG {
		static String grammar;

		/**
		 * Creates an instance of the CFG class. This should parse a string
		 * representation of the grammar and set your internal CFG attributes
		 * 
		 * @param grammar A string representation of a CFG
		 */
		static ArrayList<String []> rules;
		static ArrayList<ArrayList<String>>  terminals_firsts;
		static ArrayList<ArrayList<String>> variables_firsts;
		
		static ArrayList<String> variables;
		static ArrayList<String> terminals;
		
		static ArrayList<ArrayList<String>> terminals_follows;
		static ArrayList<ArrayList<String>> variables_follows;
		
		public CFG(String grammar) {
			this.grammar = grammar;
		}
		
		public static ArrayList<ArrayList<String>> myRules2D(){
			String[] myarray1 = CFG.grammar.split(";");
			List<String> myarray2 = (List<String>) Arrays.asList(myarray1);
			ArrayList<String> myarray = new ArrayList<String>(myarray2);		
			ArrayList<ArrayList<String>> my2darray = new ArrayList<ArrayList<String>>();
			ArrayList<String> temp3 = new ArrayList<String>();
			for (int i = 0; i < myarray1.length; i++) {
				String[] temp1 = myarray.get(i).split(",");
				List<String> temp2 = (List<String>) Arrays.asList(temp1);
				temp3 = new ArrayList<String>(temp2);
				my2darray.add(temp3);
			}
			return my2darray;
		}
		
		public static void createRules(String cfg) {
			
			String[] cfg_rules = cfg.split(";");
			
			rules = new ArrayList<String []>();
			
			for(int i = 0 ; i < cfg_rules.length ; i++) {
				rules.add(cfg_rules[i].split(","));
			}
			
			variables = new ArrayList<String>();
			terminals = new ArrayList<String>();
			
			for(int i = 0 ; i < rules.size() ; i++) {		
				for(int j = 0 ; j < rules.get(i).length ; j++) {
					for(int k = 0 ; k < rules.get(i)[j].length() ; k++) {
						if(Character.isLowerCase(rules.get(i)[j].charAt(k))) {
							if(!terminals.contains("" + rules.get(i)[j].charAt(k)) && rules.get(i)[j].charAt(k) != 'e') {
								terminals.add("" + rules.get(i)[j].charAt(k));
							}
						} else {
							if(!variables.contains("" + rules.get(i)[j].charAt(k))) {
								variables.add("" + rules.get(i)[j].charAt(k));
							}
						}
					}
				}
			}

		}
		
		public static ArrayList<String> initialize_firsts(String symbol) {
			
			ArrayList<String> f = new ArrayList<String>();
			
			if(terminals.contains(symbol)) {
				
				f.add(symbol);
				return f;
			}
			else {
				
				return f;
			}
			
		}
		
		public static ArrayList<String> first(String symbol) {
			
			if(terminals.contains(symbol)) {
				
				for(int i = 0 ; i < terminals.size() ; i++) {
					
					if(terminals.get(i).equals(symbol)) {
						
						return terminals_firsts.get(i);
					}
				}
			} 
			else if(variables.contains(symbol)){
				
				for(int i = 0 ; i < variables.size() ; i++) {
					
					if(variables.get(i).equals(symbol)) {
						
						return variables_firsts.get(i);
					}
				}
			} 
			else if(symbol.equals("e")){
				
				ArrayList<String> t = new ArrayList<String>();
				
				t.add("e");
				
				return t;
			}
			
			return new ArrayList<String>();
		}
		
		public static void add_first(String symbol, String first) {
			
			if(terminals.contains(symbol)) {
				
				for(int i = 0 ; i < terminals.size() ; i++) {
					
					if(terminals.get(i).equals(symbol) && !terminals_firsts.get(i).contains(first)) {
						
						terminals_firsts.get(i).add(first);
					}
				}
			}
			else {
				
				for(int i = 0 ; i < variables.size() ; i++) {
					
					if(variables.get(i).equals(symbol) && !variables_firsts.get(i).contains(first)) {
						
						variables_firsts.get(i).add(first);
					}
				}
			}
		}
		
		public static void add_first(String symbol, ArrayList<String> first) {
			
			if(terminals.contains(symbol)) {
				
				for(int i = 0 ; i < terminals.size() ; i++) {
					
					if(terminals.get(i).equals(symbol)) {
						
						for(int j = 0 ; j < first.size() ; j++) {
							
							if(!first.get(j).equals("e") && !terminals_firsts.get(i).contains(first.get(j))) {
								
								terminals_firsts.get(i).add(first.get(j));
							}
						}
					}
				}
			}
			else if(variables.contains(symbol)) {
				
				for(int i = 0 ; i < variables.size() ; i++) {
					
					if(variables.get(i).equals(symbol)) {
						
						for(int j = 0 ; j < first.size() ; j++) {
							
							if(!first.get(j).equals("e") && !variables_firsts.get(i).contains(first.get(j))) {
								
								variables_firsts.get(i).add(first.get(j));
							}
						}
					}
				}
			}
		}
		
		public static boolean isSubset(ArrayList<String> set1, ArrayList<String> set2) {
			
			if(set2.isEmpty())
				return false;
			
			for(int i = 0 ; i < set1.size() ; i++) {
				
				if(!set1.get(i).equals("e") && !set2.contains(set1.get(i))) {
					
					return false;
				}
			}
		
			
			return true;
		}

		public String First() {
			
			createRules(grammar);
			
			terminals_firsts = new ArrayList<ArrayList<String>>();
			
			for(int i = 0 ; i < terminals.size() ; i ++) {
				
				terminals_firsts.add(initialize_firsts(terminals.get(i)));
			}
			
			variables_firsts = new ArrayList<ArrayList<String>>();
			
			for(int i = 0 ; i < variables.size() ; i ++) {
				
				variables_firsts.add(initialize_firsts(variables.get(i)));
			}
			
			boolean change = true;
			
			while(change) {
				
				change = false;
				
				for(int i = 0 ; i < rules.size() ; i++) {
					
					for(int j = 1 ; j < rules.get(i).length ; j++) {
						
						boolean flag = true;
						for(int k = 0 ; k < rules.get(i)[j].length() ; k++) {
							
							if(!first("" + rules.get(i)[j].charAt(k)).contains("e") || first(rules.get(i)[0]).contains("e"))
								flag = false;
						}
						
						if(flag) {
							
							if(!first(rules.get(i)[0]).contains("e")) {
								
								add_first(rules.get(i)[0], "e");
								change = true;
							}
						}
						else {
							
							for(int k = 0 ; k < rules.get(i)[j].length() ; k++) {
								
								boolean flag2 = true;
								boolean enteredLoop = false;					
								
								for(int m = 0 ; m <= k-1 ; m++) {
									
									enteredLoop = true;
									if(!first("" + rules.get(i)[j].charAt(m)).contains("e"))
										flag2 = false;
								}
									
								if((flag2 && enteredLoop) || k == 0) {
									
									if(!isSubset(first("" + rules.get(i)[j].charAt(k)), first(rules.get(i)[0]))) {
										
										add_first(rules.get(i)[0], first("" + rules.get(i)[j].charAt(k)));
										change = true;
									}
								}
								
							}
						}
					}
				}
			}
			
			for(int i = 0 ; i < variables_firsts.size() ; i++)
				Collections.sort(variables_firsts.get(i));
			
			String result = "";
			
			for(int i = 0 ; i < variables.size() ; i++) {
				
				result += variables.get(i) + ",";
				
				for(int j = 0 ; j < variables_firsts.get(i).size() ; j++) {
					
					result += variables_firsts.get(i).get(j);
				}
				
				if(i != variables.size()-1)
					result += ";";
			}
			
			return result;
		}


		public static ArrayList<String> initialize_follows(String symbol) {
			
			ArrayList<String> f = new ArrayList<String>();
			
			if(symbol.equals("S")) {
				
				f.add("$");
				return f;
			}
			else {
				
				return f;
			}
			
		}
		
		public static ArrayList<String> follow(String symbol) {
			
			if(variables.contains(symbol)) {
				
				for(int i = 0 ; i < variables.size() ; i++) {
					
					if(variables.get(i).equals(symbol)) {
						
						return variables_follows.get(i);
					}
				}	
			}
			else if(terminals.contains(symbol)) {
				
				for(int i = 0 ; i < terminals.size() ; i++) {
					
					if(terminals.get(i).equals(symbol)) {
						
						return terminals_follows.get(i);
					}
				}	
			}
			
			return new ArrayList<String>();
		}
		
		public static void add_follow(String symbol, String follow) {
			
			if(variables.contains(symbol)) {
				
				for(int i = 0 ; i < variables.size() ; i++) {
					
					if(variables.get(i).equals(symbol) && !variables_follows.get(i).contains(follow)) {
						
						variables_follows.get(i).add(follow);
					}
				}
			}
			else if(terminals.contains(symbol)) {
				
				for(int i = 0 ; i < terminals.size() ; i++) {
					
					if(terminals.get(i).equals(symbol) && !terminals_follows.get(i).contains(follow)) {
						
						terminals_follows.get(i).add(follow);
					}
				}
			}
		}
		
		public static void add_follow(String symbol, ArrayList<String> follow) {
			
			if(variables.contains(symbol)) {
				
				for(int i = 0 ; i < variables.size() ; i++) {
					
					if(variables.get(i).equals(symbol)) {
						
						for(int j = 0 ; j < follow.size() ; j++) {
							
							if(!follow.get(j).equals("e") && !variables_follows.get(i).contains(follow.get(j))) {
								
								variables_follows.get(i).add(follow.get(j));
							}
						}
					}
				}
			}
			else if(terminals.contains(symbol)) {
				
				for(int i = 0 ; i < terminals.size() ; i++) {
					
					if(terminals.get(i).equals(symbol)) {
						
						for(int j = 0 ; j < follow.size() ; j++) {
							
							if(!follow.get(j).equals("e") && !terminals_follows.get(i).contains(follow.get(j))) {
								
								terminals_follows.get(i).add(follow.get(j));
							}
						}
					}
				}
			}
		}
		
		public String Follow() {
			
			this.First();
			
			terminals_follows = new ArrayList<ArrayList<String>>();
			for(int i = 0 ; i < terminals.size() ; i ++) {				
				terminals_follows.add(initialize_follows(terminals.get(i)));
			}
			variables_follows = new ArrayList<ArrayList<String>>();			
			for(int i = 0 ; i < variables.size() ; i ++) {				
				variables_follows.add(initialize_follows(variables.get(i)));
			}
			boolean change = true;			
			while(change) {
				
				change = false;
				
				for(int i = 0 ; i < rules.size() ; i++) {
					
					for(int j = 1 ; j < rules.get(i).length ; j++) {
						
						for(int k = 0 ; k < rules.get(i)[j].length() ; k++) {
							
							if(k == rules.get(i)[j].length()-1) {
								
								if(rules.get(i)[j].charAt(k) != 'e') {
									
									if(!isSubset(follow(rules.get(i)[0]), follow("" + rules.get(i)[j].charAt(k)))) {
										
										add_follow("" + rules.get(i)[j].charAt(k), follow(rules.get(i)[0]));
										change = true;
									}
								}
							}
							else {
								
								if(!isSubset(first("" + rules.get(i)[j].charAt(k+1)), follow("" + rules.get(i)[j].charAt(k)))) {
									
									add_follow("" + rules.get(i)[j].charAt(k), first("" + rules.get(i)[j].charAt(k+1)));
									change = true;
								}
								
								if(first("" + rules.get(i)[j].charAt(k+1)).contains("e") && (k+1) != rules.get(i)[j].length()-1) {
									
									boolean flag = true;
									int count = 1;
									
									do {
										
										if(!isSubset(first("" + rules.get(i)[j].charAt(k + 1 + count)), follow("" + rules.get(i)[j].charAt(k)))) {
											
											add_follow("" + rules.get(i)[j].charAt(k), first("" + rules.get(i)[j].charAt(k + 1 + count)));
											change = true;
										}
										
										if((k + 1 + count) < rules.get(i)[j].length()-1 && first("" + rules.get(i)[j].charAt(k + 1 + count)).contains("e")) {
											
											flag = true;
											count++;
										}
										else if((k + 1 + count) == rules.get(i)[j].length()-1 && first("" + rules.get(i)[j].charAt(k + 1 + count)).contains("e")) {
											
											if(!isSubset(follow(rules.get(i)[0]), follow("" + rules.get(i)[j].charAt(k)))) {
												
												add_follow("" + rules.get(i)[j].charAt(k), follow("" + rules.get(i)[0]));
												change = true;
											}
											
											flag = false;
										}
										else {
											
											flag = false;
										}
									}
									while(flag);
								}
								else if(first("" + rules.get(i)[j].charAt(k+1)).contains("e") && (k+1) == rules.get(i)[j].length()-1) {
									
									if(!isSubset(follow(rules.get(i)[0]), follow("" + rules.get(i)[j].charAt(k)))) {
									
										add_follow("" + rules.get(i)[j].charAt(k), follow("" + rules.get(i)[0]));
										change = true;
									}
								}
							}
						}				
					}
				}
			}
			
			for(int i = 0 ; i < variables_follows.size() ; i++)
				Collections.sort(variables_follows.get(i));
			
			for(int i = 0 ; i < variables_follows.size() ; i++) {
				
				if(variables_follows.get(i).get(0).equals("$")) {
					
					variables_follows.get(i).remove(0);
					variables_follows.get(i).add("$");
				}
			}
			
			String result = "";
			
			for(int i = 0 ; i < variables.size() ; i++) {
				
				result += variables.get(i) + ",";
				
				for(int j = 0 ; j < variables_follows.get(i).size() ; j++) {
					
					result += variables_follows.get(i).get(j);
				}
				
				if(i != variables.size()-1)
					result += ";";
			}
			
			return result;
		}	
		
		public static void displayArray(String[] array) {
			
			for(int i = 0 ; i < array.length ; i++) {
				System.out.print(array[i] + " ");
			}
		}
		
	
		public String table() {
			String firstResult = First();
			String followResult = Follow();
			String tableoutput = "";
			int firsttime = 1;
			int firsttimedollar = 1;
			ArrayList<ArrayList<String>> myRules2D = myRules2D();
			ArrayList<String> firstof = new ArrayList<String>();
			ArrayList<String> followof = new ArrayList<String>();
			ArrayList<String> table1d = new ArrayList<String>();			
			ArrayList<ArrayList<String>> table2d = new ArrayList<ArrayList<String>>();
			
			for (int i = 0; i < variables.size(); i++) {
				firsttimedollar = 1;
				for (int j = 0; j < terminals.size(); j++) {
					for (int i2 = 0; i2 < myRules2D.size(); i2++) {
						firsttime = 1;
						if(variables.get(i).equals(myRules2D.get(i2).get(0))){
							for (int j2 = 1; j2 < myRules2D.get(i2).size(); j2++) {
								firstof = first(Character.toString(myRules2D.get(i2).get(j2).charAt(0)));
								followof = follow(myRules2D.get(i2).get(0));
								if(Character.toString(myRules2D.get(i2).get(j2).charAt(0)).equals(terminals.get(j)) //// if first character is small and like the terminal i am standing on
								|| firstof.contains(terminals.get(j))	){		//or that the first is variable and its first contains the terminal im standing on
									if(firsttime == 1){
										tableoutput = tableoutput + variables.get(i) + ",";
										table1d.add(variables.get(i));
										tableoutput = tableoutput + terminals.get(j) + ",";
										table1d.add(terminals.get(j));
										firsttime = 0;
									}
									tableoutput = tableoutput + myRules2D.get(i2).get(j2) + ";";
									table1d.add(myRules2D.get(i2).get(j2));
								}
								else if(myRules2D.get(i2).contains("e")){ //else if the rules contain epsilon
									if(followof.contains(terminals.get(j))){
										if(firsttime == 1){
											tableoutput = tableoutput + variables.get(i) + ",";
											table1d.add(variables.get(i));
											tableoutput = tableoutput + terminals.get(j) + ",";
											table1d.add(terminals.get(j));
											tableoutput = tableoutput + "e" + ";";
											table1d.add("e");
											firsttime = 0;
										}										
									}

										if(followof.contains("$") && firsttimedollar == 1){
											tableoutput = tableoutput + variables.get(i) + ",";
											table1d.add(variables.get(i));
											tableoutput = tableoutput + "$" + ",";
											table1d.add("$");
											tableoutput = tableoutput + "e" + ";";
											table1d.add("e");
											firsttimedollar = 0;
										}
									
								}
							}
						}
					}
				}
				}
			ArrayList<String> helper = new ArrayList<String>();
			for (int i = 0; i < table1d.size(); i = i + 3) {
				if(i+2 < table1d.size()){
					helper.add(table1d.get(i));
					helper.add(table1d.get(i+1));
					helper.add(table1d.get(i+2));									
				}
				table2d.add((ArrayList<String>) helper.clone());
				helper.clear();
			}
			tableoutput = tableoutput.substring(0,tableoutput.length()-1);
			return tableoutput;
		}

		
		
		public ArrayList<ArrayList<String>> table2d() {
			String firstResult = First();
			String followResult = Follow();
			String tableoutput = "";
			int firsttime = 1;
			int firsttimedollar = 1;
			ArrayList<ArrayList<String>> myRules2D = myRules2D();
			ArrayList<String> firstof = new ArrayList<String>();
			ArrayList<String> followof = new ArrayList<String>();
			ArrayList<String> table1d = new ArrayList<String>();			
			ArrayList<ArrayList<String>> table2d = new ArrayList<ArrayList<String>>();
			
			for (int i = 0; i < variables.size(); i++) {
				firsttimedollar = 1;
				for (int j = 0; j < terminals.size(); j++) {
					for (int i2 = 0; i2 < myRules2D.size(); i2++) {
						firsttime = 1;
						if(variables.get(i).equals(myRules2D.get(i2).get(0))){
							for (int j2 = 1; j2 < myRules2D.get(i2).size(); j2++) {
								firstof = first(Character.toString(myRules2D.get(i2).get(j2).charAt(0)));
								followof = follow(myRules2D.get(i2).get(0));
								if(Character.toString(myRules2D.get(i2).get(j2).charAt(0)).equals(terminals.get(j)) //// if first character is small and like the terminal i am standing on
								|| firstof.contains(terminals.get(j))	){		//or that the first is variable and its first contains the terminal im standing on
									if(firsttime == 1){
										tableoutput = tableoutput + variables.get(i) + ",";
										table1d.add(variables.get(i));
										tableoutput = tableoutput + terminals.get(j) + ",";
										table1d.add(terminals.get(j));
										firsttime = 0;
									}
									tableoutput = tableoutput + myRules2D.get(i2).get(j2) + ";";
									table1d.add(myRules2D.get(i2).get(j2));
								}
								else if(myRules2D.get(i2).contains("e")){ //else if the rules contain epsilon
									if(followof.contains(terminals.get(j))){
										if(firsttime == 1){
											tableoutput = tableoutput + variables.get(i) + ",";
											table1d.add(variables.get(i));
											tableoutput = tableoutput + terminals.get(j) + ",";
											table1d.add(terminals.get(j));
											tableoutput = tableoutput + "e" + ";";
											table1d.add("e");
											firsttime = 0;
										}										
									}

										if(followof.contains("$") && firsttimedollar == 1){
											tableoutput = tableoutput + variables.get(i) + ",";
											table1d.add(variables.get(i));
											tableoutput = tableoutput + "$" + ",";
											table1d.add("$");
											tableoutput = tableoutput + "e" + ";";
											table1d.add("e");
											firsttimedollar = 0;
										}
									
								}
							}
						}
					}
				}
				}
			ArrayList<String> helper = new ArrayList<String>();
			for (int i = 0; i < table1d.size(); i = i + 3) {
				if(i+2 < table1d.size()){
					helper.add(table1d.get(i));
					helper.add(table1d.get(i+1));
					helper.add(table1d.get(i+2));									
				}
				table2d.add((ArrayList<String>) helper.clone());
				helper.clear();
			}
			tableoutput = tableoutput.substring(0,tableoutput.length()-1);
			return table2d;
		}
		
		
		/**
		 * Parses the input string using the parsing table
		 * 
		 * @param s The string to parse using the parsing table
		 * @return A string representation of a left most derivation
		 */
		public String parse(String s) {
			ArrayList<ArrayList<String>> table2d = table2d();
			String table = table();
			Stack<String> stack = new Stack<String>();
			s = s + "$";
			String result = "";
			stack.push("$");
			stack.push(Character.toString(table.charAt(0)));
			result = result + Character.toString(table.charAt(0)) + ",";
			int pointer = 0;
			int rulefound = 0;
			String currentRule = "";
			String currentresult = "";
			String[] results;
			String temprule = "";
			while(stack.peek()!="$"){
				rulefound = 0;
				currentRule = "";
				for (int i = 0; i < table2d.size(); i++) {
					if(table2d.get(i).get(0).equals(stack.peek()) && table2d.get(i).get(1).equals(Character.toString(s.charAt(pointer)))){
						rulefound = 1;
						currentRule = table2d.get(i).get(2);
						break;
					}
				}
				if(Character.toString(s.charAt(pointer)).equals(stack.peek())){ //top of the stack equal current character
					stack.pop();
					pointer ++;
				}
				else if(!Character.isUpperCase(stack.peek().charAt(0)) && !stack.peek().equals(Character.toString(s.charAt(pointer)))){ //stack.peek terminal return error
					result += "ERROR";
					break;
				}
				else if(rulefound == 0){
					result += "ERROR";
					break;
				}
				else{ //found rule
					if(currentRule == "e"){		//rule == e
						results = result.split(",");
						temprule = results[results.length - 1];
						result = result + temprule.replaceFirst(stack.pop(), "") + ",";

					}
					else{
//						results = result.split(",");
//						currentresult = results[results.length-1];
//						result = result + currentresult.replaceFirst(stack.peek(), currentRule) + ",";
//						stack.pop();

						results = result.split(",");
						temprule = results[results.length - 1];
						result = result + temprule.replaceFirst(stack.pop(), currentRule) + ",";
						
						for (int i = currentRule.length()-1; i >= 0; i--) {
							stack.push(Character.toString(currentRule.charAt(i)));
						}
					}
				}
			}
			if(result.charAt(result.length()-1) == ','){
				result = result.substring(0,result.length()-1);				
			}
			return result;
		}
	}

	public static void main(String[] args) {

		/*
		 * Please make sure that this EXACT code works. This means that the method
		 * and class names are case sensitive
		 */
		
		String grammar = "S,iST,e;T,cS,a";
		String input1 = "iiac";
		String input2 = "iia";
		
//		String grammar = "S,zToS,e;T,zTo,e";
//		String input1 = "zzoozo";
//		String input2 = "zoz";
		
//		String grammar = "S,AB;A,iA,n;B,CA;C,zC,o";
//		String input1 = "inzon";
//		String input2 = "nzin";
//		
//		String grammar = "S,lLr,a;L,lLrD,aD;D,cSD,e";
//		String input1 = "laclacarr";
//		String input2 = "laclacarl";
//		
//		String grammar = "S,aA;A,SB,e;B,pA,mA";
//		String input1 = "aamaamp";
//		String input2 = "aapaap";
		
//		String grammar = "S,TA;A,pTA,e;T,FB;B,mFB,e;F,lSr,i";
//		String input1 = "imlipir";
//		String input2 = "imlipirl";		
		
		
		CFG g = new CFG(grammar);
		System.out.println(g.table());
		System.out.println(g.parse(input1));
		System.out.println(g.parse(input2));
		
	}

}

