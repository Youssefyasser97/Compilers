import java.util.*;

public class NFA {
	String start;
	Set<String> ends;
	Map<String, Map<Character,List<String>>> transitions; // state -> (character -> [next states])

	NFA(String[] ss, String[] ts) {
		ends = new TreeSet<String>();
		transitions = new TreeMap<String, Map<Character,List<String>>>();

		// States
		for (String v : ss) {
			String[] pieces = v.split(",");
			if (pieces.length>1) {
				if (pieces[1].equals("S")) start = pieces[0];
				else if (pieces[1].equals("E")) ends.add(pieces[0]);
			}
		}

		// Transitions
		for (String e : ts) {
			String[] pieces = e.split(",");
			String from = pieces[0], to = pieces[1];
			if (!transitions.containsKey(from)) transitions.put(from, new TreeMap<Character,List<String>>());
			for (int i=2; i<pieces.length; i++) {
				char c = pieces[i].charAt(0);
				// difference from DFA: list of next states
				if (!transitions.get(from).containsKey(c)) transitions.get(from).put(c, new ArrayList<String>());
				transitions.get(from).get(c).add(to);				
			}
		}

		System.out.println("start:"+start);
		System.out.println("end:"+ends);
		System.out.println("transitions:"+transitions);
	}
	
	public static String uniqueCharacters(String test){
	      String temp = "";
	         for (int i = 0; i < test.length(); i++){
	            if (temp.indexOf(test.charAt(i)) == - 1){
	               temp = temp + test.charAt(i);
	         }
	      }

	    return temp;

	   }

	public boolean match(String s) {
		// difference from DFA: multiple prev states
		Set<String> prevStates = new TreeSet<String>();
		prevStates.add(start);
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			Set<String> nextStates = new TreeSet<String>();
			// transition from each prev state to each of its next states
			for (String state : prevStates)
				if (transitions.get(state).containsKey(c))
					nextStates.addAll(transitions.get(state).get(c));
			if (nextStates.isEmpty()) return false;
			prevStates = nextStates;
		}
		// end up in multiple states -- accept if any is an end state
		for (String state : prevStates) {
			if (ends.contains(state)) return true;
		}
		return false;
	}

	public void test(String name, String[] inputs) {
		System.out.println("*" + name);
		for (String s : inputs)
			System.out.println(s + ":" + match(s));
	}
	
	 public static String[] GetStringArray(ArrayList<String> arr) 
	    { 
	  
	        String str[] = new String[arr.size()]; 
	  
	        for (int j = 0; j < arr.size(); j++) { 
	  
	            // Assign each value to String array 
	            str[j] = arr.get(j); 
	        } 
	  
	        return str; 
	    } 
	

	public static void main(String[] args) {
//		0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3
//		0,0;0,1;2,2#0,0;1,2;2,1#1,2#1
//		0,0;0,1#0,0;1,2#1,2#2
		String NFA = "0,0;0,1#0,0;1,2#1,2#2";
		String[] nfasplit = NFA.split("#");
		List<String> nfasplit_1 = (List<String>) Arrays.asList(nfasplit);
		ArrayList<String> nfasplit_2 = new ArrayList<String>(nfasplit_1);
		
		String zeros = nfasplit_2.get(0);
		String ones = nfasplit_2.get(1);
		String epsilon = nfasplit_2.get(2);
		String goalstate = nfasplit_2.get(3);
		
		String[] NFAzeros = zeros.split(";");
		List<String> NFAzeros1 = (List<String>) Arrays.asList(NFAzeros);
		ArrayList<String> NFAzeros2 = new ArrayList<String>(NFAzeros1);
		
		String[] NFAones = ones.split(";");
		List<String> NFAones1 = (List<String>) Arrays.asList(NFAones);
		ArrayList<String> NFAones2 = new ArrayList<String>(NFAones1);
		
		String[] NFAepsilon = epsilon.split(";");
		List<String> NFAepsilon1 = (List<String>) Arrays.asList(NFAepsilon);
		ArrayList<String> NFAepsilon2 = new ArrayList<String>(NFAepsilon1);
		
		String[] NFAgoalstate = goalstate.split(";");
		List<String> NFAgoalstate1 = (List<String>) Arrays.asList(NFAgoalstate);
		ArrayList<String> NFAgoalstate2 = new ArrayList<String>(NFAgoalstate1);

//		System.out.println("zeros: " + NFAzeros2);
//		System.out.println("ones: " + NFAones2);
//		System.out.println("epsilon: " + NFAepsilon2);
//		System.out.println("goalstate: " + NFAgoalstate2);
		
		String ourSgoal = "";
		
		for (int i = 0; i < NFAgoalstate2.size(); i++) {
			ourSgoal += NFAgoalstate2.get(i);
		}
		
		ArrayList<String> allstates = new ArrayList<String>();
		int found = 0;
		for (int i = 0; i < NFA.length(); i++) {
			char currentchar = NFA.charAt(i);
			if(currentchar != ',' && currentchar != ';' && currentchar != '#'){
				String currentcharasstring = Character.toString(currentchar);
				allstates.add(currentcharasstring);
			}
		}
		
		String unique = uniqueCharacters(NFA);
		String newUnique = "";
		
		for (int i = 0; i < unique.length(); i++) {
			if(unique.charAt(i)!= ',' && unique.charAt(i)!= ';' && unique.charAt(i)!= '#'){
				newUnique += unique.charAt(i);
			}
		}
		
		ArrayList<String> finalS = new ArrayList<String>();
		String firststring= newUnique.charAt(0) + ",S";
		finalS.add(firststring);
		for (int i = 1; i < newUnique.length()-NFAgoalstate2.size(); i++) {
			char c = newUnique.charAt(i);
			String remainingstrings = Character.toString(c);
			finalS.add(remainingstrings);
		}
		for (int stoppedat = newUnique.length()-NFAgoalstate2.size(); stoppedat < newUnique.length(); stoppedat++) {
			char c = newUnique.charAt(stoppedat);
			String remainingstrings = Character.toString(c);
			String remainingstringsforgoal = remainingstrings + ",E";
			finalS.add(remainingstringsforgoal);
		}
		System.out.println("final String hateb2a:  " + finalS);
		String[] finalSlist = GetStringArray(finalS);
		for (int i= 0; i< NFAzeros1.size(); i++ ) {
			String A = NFAzeros2.get(i);
			A = A + ",0";
			NFAzeros2.set(i, A);
		}
		String[] NFAzeros3 = GetStringArray(NFAzeros2); 
//		System.out.println("NFAzeros2: " + NFAzeros2);		
		for (int i= 0; i< NFAones1.size(); i++ ) {
			String A = NFAones2.get(i);
			A = A + ",1";
			NFAones2.set(i, A);
		
		}
		String[] NFAones3 = GetStringArray(NFAones2); 
//		System.out.println("NFAones2: " + NFAones2);
			
			 int N1 = NFAzeros3.length;
			 int N2 = NFAones3.length;
	
		        String[] result = new String[N1 + N2];
		       System.arraycopy(NFAzeros3, 0, result, 0, N1);
		       System.arraycopy(NFAones3, 0, result, N1, N2);
//		       System.out.println("Result:" + Arrays.toString(result));
		       

		       
				String[] NFAepsilon3 = GetStringArray(NFAepsilon2); 
//				System.out.println("NFAepsilon2: " + NFAepsilon2);
				
				
				List<String> result1 = (List<String>) Arrays.asList(result);
				ArrayList<String> result2 = new ArrayList<String>(result1);				
				
				for (int j=0; j<NFAepsilon1.size(); j++) {
					for( int i=0; i<result.length; i++) {
						char currentepsilon = NFAepsilon2.get(j).charAt(0);
						if(result2.get(i).charAt(2) == currentepsilon){
							char first = result2.get(i).charAt(0);
							char third = result2.get(i).charAt(4);
							String total = first + "," + NFAepsilon2.get(j).charAt(2) + "," + third; 
							result2.add(total);
						}
					}
				}
								
				String[] result3List = GetStringArray(result2); 			
		       	String[] s = { "0,S", "1", "2,E"};
				String[] t = { "0,0,0","0,1,0", "0,0,1","1,2,1"};
				NFA nfa_test = new NFA(finalSlist, result);				
				String[] test = { "01" };
				nfa_test.test("tests: ", test);
	}
	

}