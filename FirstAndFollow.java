package compilersLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class CFG {
String input;
public CFG(String input){
	this.input = input;	
}
public String First() {
	String[] myarray1 = input.split(";");
	List<String> myarray2 = (List<String>) Arrays.asList(myarray1);
	ArrayList<String> myarray = new ArrayList<String>(myarray2);		
	ArrayList<ArrayList<String>> my2darray = new ArrayList<ArrayList<String>>();
	ArrayList<String> temp3 = new ArrayList<String>();
	ArrayList<String> FinalFirst = new ArrayList<String>();
	ArrayList<String> FinalFirst2 = new ArrayList<String>();
	ArrayList<String> FinalFirst3 = new ArrayList<String>();
	ArrayList<String> FinalFirstFinal = new ArrayList<String>();
	
	char curchar;
	char tempchar;
	String curcharS = "";
	char curStateC;
	String curStateS = "";
	int counter = 1;
	char charAfterState;
	String charAfterStateS = "";
	int position = 0;
	char capitalchar;
	int foundepsilon = 0;
	int epsilonflag = 0;
	
	for (int i = 0; i < myarray1.length; i++) {
		String[] temp1 = myarray.get(i).split(",");
		List<String> temp2 = (List<String>) Arrays.asList(temp1);
		temp3 = new ArrayList<String>(temp2);
		my2darray.add(temp3);
	}
//	System.out.println(my2darray);	
	
	
	for (int i = 0; i < my2darray.size(); i++) {
		counter = 1;
		curStateC = my2darray.get(i).get(0).charAt(0);
		curStateS = Character.toString(curStateC);
		FinalFirst.add(curStateS);
		for (int j = 1; j < my2darray.get(i).size(); j++) {
			foundepsilon = 0;
			position = 0;
			counter = 1;
			curchar = my2darray.get(i).get(j).charAt(0);
			if(!Character.isUpperCase(curchar)){ //if the current character is lower case
				curcharS = Character.toString(curchar);
				FinalFirst.add(curcharS);
			}
			else{ //if the current character is upper case (state)
				if(curchar != my2darray.get(i).get(0).charAt(0)){ // if the current character is not the same as the current state
//					this part is wrong
					if(i<1){
						for (int index = 0; index < my2darray.get(i).get(j).length(); index++) {
							foundepsilon = 0;
							if(!Character.isUpperCase(my2darray.get(i).get(j).charAt(index))){
								FinalFirst.add(Character.toString(my2darray.get(i).get(j).charAt(index)));
								break;
							}
							for (int i2 = i; i2 < my2darray.size(); i2++) {
								for (int j2 = 1; j2 < my2darray.get(i2).size(); j2++) {
										curchar = my2darray.get(i).get(j).charAt(index);
										if(my2darray.get(i2).get(0).charAt(0) == curchar){
											if(!Character.isUpperCase(my2darray.get(i2).get(j2).charAt(0))){
												curcharS = Character.toString(my2darray.get(i2).get(j2).charAt(0));
												if(curcharS.equals("e")){
													foundepsilon = 1;
												}
												if(my2darray.get(i).get(j).length()>1){
													if(!curcharS.equals("e") && index <= my2darray.get(i).get(j).length() - 1){
														FinalFirst.add(curcharS);																					
													}
													else if(index > my2darray.get(i).get(j).length() - 1){
														FinalFirst.add(curcharS);
													}													
												}
												else{
													FinalFirst.add(curcharS);																					
												}
											}
											else{
												for (int i3 = i2; i3 < my2darray.size(); i3++) {
													for (int j3 = j2; j3 < my2darray.get(i3).size(); j3++) {
														if(my2darray.get(i3).get(0).equals(Character.toString(curchar))){
															for (int k = 1; k < my2darray.get(i3).size(); k++) {
																if(!Character.isUpperCase(my2darray.get(i3).get(k).charAt(0))){
	//																if(!my2darray.get(i3).get(k).equals("e")){
																	FinalFirst.add(Character.toString(my2darray.get(i3).get(k).charAt(0)));
	//																}
																}
																else{
																	tempchar = my2darray.get(i3).get(k).charAt(0);
																	for (int i4 = i3; i4 < my2darray.size(); i4++) {
																		for (int j4 = k; j4 < my2darray.get(i4).size(); j4++) {
																			if(my2darray.get(i4).get(0).charAt(0) == tempchar){
																				if(!Character.isUpperCase(my2darray.get(i4).get(j4).charAt(0))){
																					FinalFirst.add(Character.toString(my2darray.get(i4).get(j4).charAt(0)));
																				}
																				else{
																					tempchar = my2darray.get(i4).get(j4).charAt(0);
	//																			not sure if these 2 lines below work correct
																					i4 = i3;
																					j4 = j3;
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							if(foundepsilon == 0){
								break;
							}
						}
					}
					else{ //get the first of the curchar and add them to the first of my2darray.get(i).get(0)
						if(FinalFirst.contains(Character.toString(my2darray.get(i).get(j).charAt(0)))){ //if the character you are on existed in the FinalFirst yet
							for (int i2 = 0; i2 < FinalFirst.size(); i2++) {
								curcharS = Character.toString(curchar);
								if(FinalFirst.get(i2).equals(curcharS)){
									position = i2;
									break;
								}
							}
								for (int j2 = position + 1; j2 < FinalFirst.size(); j2++) {
									if (!Character.isUpperCase(FinalFirst.get(j2).charAt(0))) {
										FinalFirst2.add(FinalFirst.get(j2));
									}
									else{
										break;
									}
								}
								if(FinalFirst2.contains("e")){ //if the list of the first of the state contains e then add the small letter after it
									if(my2darray.get(i).get(j).length()>1)
										if(!Character.isUpperCase(my2darray.get(i).get(j).charAt(1)))
											FinalFirst2.add(Character.toString(my2darray.get(i).get(j).charAt(1)));
								}
						}
						else{ // was not in the FinalFirst so you need to get it manually
//							this part is similar to some part below but modified a little
//							-----------------------------------------------------------------------------------------------
							epsilonflag = 0;
							for (int k = 0; k < my2darray.get(i).get(j).length(); k++) {
								capitalchar = my2darray.get(i).get(j).charAt(k);
								if(Character.isUpperCase(capitalchar)){
									for (int i2 = 0; i2 < my2darray.size(); i2++) {
										if(my2darray.get(i2).get(0).equals(Character.toString(capitalchar))){
											for (int j2 = 0; j2 < my2darray.get(i2).size(); j2++) {
												if(!Character.isUpperCase(my2darray.get(i2).get(j2).charAt(0))){
													if(Character.toString(my2darray.get(i2).get(j2).charAt(0)).equals("e")){
														epsilonflag = 1;
													}
													if(!Character.toString(my2darray.get(i2).get(j2).charAt(0)).equals("e") ){
														FinalFirst2.add(Character.toString(my2darray.get(i2).get(j2).charAt(0)));														
													}
												}
											}
										}
									}
								}
								else{
									break;
								}
								if(epsilonflag == 0){
									break;
								}
							}
							if(epsilonflag == 1){
								FinalFirst2.add("e");
							}
//							-----------------------------------------------------------------------------------------------
						}
					}
				}
				else{ //if the current character is upper case and same as the current state
					if(Character.toString(my2darray.get(i).get(j).charAt(0)).equals(my2darray.get(i).get(0))
							&& my2darray.get(i).get(j).length() > 1
							&& my2darray.get(i).contains("e")
							)
					{
						for (int k = 1; k < my2darray.get(i).get(j).length(); k++) {
							capitalchar = my2darray.get(i).get(j).charAt(k);
							if(Character.isUpperCase(capitalchar)){
								for (int i2 = 0; i2 < my2darray.size(); i2++) {
										for (int j2 = 0; j2 < my2darray.get(i2).size(); j2++) {
											if(my2darray.get(i2).get(0).equals(Character.toString(capitalchar))){
												if(!Character.isUpperCase(my2darray.get(i2).get(j2).charAt(0))){
													if(!Character.toString(my2darray.get(i2).get(j2).charAt(0)).equals("e")){
														FinalFirst3.add(Character.toString(my2darray.get(i2).get(j2).charAt(0)));		
													}
												}
											}
										}
									}
								}
							else{
								break;
							}
						}
					}
					
					for (int i2 = 0; i2 < my2darray.size(); i2++) {
						for (int j2 = 0; j2 < my2darray.get(i2).size(); j2++) {
							counter = 1;
							if(i2 != i && my2darray.get(i2).get(0).equals(my2darray.get(i).get(j2))  && my2darray.get(i2).contains("e")){
								for (int k = 0; k < my2darray.get(i).get(j).length(); k++) {
									if(Character.isUpperCase(my2darray.get(i).get(j).charAt(counter))){
										counter++;
									}
								}
								charAfterState = my2darray.get(i).get(j).charAt(counter);
								charAfterStateS = Character.toString(charAfterState);
								FinalFirst.add(charAfterStateS);
								counter = 1;
								break;
							}
						}
					}
				}
			}
		}
//		removing duplicates
		LinkedHashSet<String> hashSet = new LinkedHashSet<>(FinalFirst2);
		ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);
		FinalFirst.addAll(listWithoutDuplicates);
		
		
		LinkedHashSet<String> hashSet2 = new LinkedHashSet<>(FinalFirst3);
		ArrayList<String> listWithoutDuplicates2 = new ArrayList<>(hashSet2);
		FinalFirst.addAll(listWithoutDuplicates2);
		
		FinalFirst2.clear();
		FinalFirst3.clear();
	}
	
//	--------------------------------Formatting to return a string-----------------------------------------------
	for (int i = 0; i < FinalFirst.size(); i++) {
		if(Character.isUpperCase(FinalFirst.get(i).charAt(0)) && i > 0){
			FinalFirstFinal.add(";");
		}
		FinalFirstFinal.add(FinalFirst.get(i));
	}
    StringBuffer sb = new StringBuffer();
    String s;
    for (int i = 0; i < FinalFirstFinal.size(); i++) {
    	s = FinalFirstFinal.get(i);
		sb.append(s);
		if(!s.equals(";") && i < FinalFirstFinal.size()-1)
			if(!FinalFirstFinal.get(i+1).equals(";"))
				sb.append(",");
	}
    String strfirst = sb.toString();

	//convert str to arraylist then to 2d arraylist
    String[] first1 = strfirst.split(";");
	List<String> first2 = (List<String>) Arrays.asList(first1);
	ArrayList<String> first = new ArrayList<String>(first2);		
	ArrayList<ArrayList<String>> first2d = new ArrayList<ArrayList<String>>();
	ArrayList<String> first3 = new ArrayList<String>();

	for (int i = 0; i < myarray1.length; i++) {
		String[] temp1 = first.get(i).split(",");
		List<String> temp2 = (List<String>) Arrays.asList(temp1);
		first3 = new ArrayList<String>(temp2);
		first2d.add(first3);
	}    
    //sorting by alphabetical order
    for (int i = 0; i < first2d.size(); i++) {
		Collections.sort(first2d.get(i));
	}
    
    //removing duplicates
    ArrayList<String> firstWithoutDuplicates = new ArrayList<>();
    for (int i = 0; i < first2d.size(); i++) {
    	LinkedHashSet<String> hashSet = new LinkedHashSet<>(first2d.get(i));
        firstWithoutDuplicates = new ArrayList<>(hashSet);
        first2d.remove(i);
        first2d.add(i, firstWithoutDuplicates); 
	}
    
    //converting 2darray back to string
	String firststringfinal = "";
	for (int i = 0; i < first2d.size(); i++) {
		for (int j = 0; j < first2d.get(i).size(); j++) {
			if(Character.isUpperCase(first2d.get(i).get(j).charAt(0)) && i > 0){
				firststringfinal = firststringfinal + ";";
			}
			if(Character.isUpperCase(first2d.get(i).get(j).charAt(0))){
				firststringfinal = firststringfinal + first2d.get(i).get(j);				
				firststringfinal = firststringfinal + ",";
			}
			else{
				firststringfinal = firststringfinal + first2d.get(i).get(j);				
			}
		}
	}
	
	return firststringfinal;
}

public String Follow() {
	String[] myarray1 = input.split(";");
	List<String> myarray2 = (List<String>) Arrays.asList(myarray1);
	ArrayList<String> myarray = new ArrayList<String>(myarray2);		
	ArrayList<ArrayList<String>> my2darray = new ArrayList<ArrayList<String>>();
	ArrayList<String> temp3 = new ArrayList<String>();
	ArrayList<String> FinalFollow = new ArrayList<String>();
	ArrayList<String> FinalFollow2 = new ArrayList<String>();
	ArrayList<ArrayList<String>> first2d = new ArrayList<ArrayList<String>>();
	ArrayList<String> FinalFollowFinal = new ArrayList<String>();

	char curchar;
	String curstate="";
	String firststate="";
	int foundepsilon = 0;
	int containseps = 0;
	int containseps2 = 0;
	
	//creating 2D arraylist
	for (int i = 0; i < myarray1.length; i++) {
		String[] temp1 = myarray.get(i).split(",");
		List<String> temp2 = (List<String>) Arrays.asList(temp1);
		temp3 = new ArrayList<String>(temp2);
		my2darray.add(temp3);
	}

	for (int i = 0; i < my2darray.size(); i++) {
		curstate = my2darray.get(i).get(0);
		FinalFollow.add(curstate);
		if(i == 0){
			firststate = curstate;
			FinalFollow.add("$");
		}
		for (int i2 = 0; i2 < my2darray.size(); i2++) {
			foundepsilon = 0;
			for (int j2 = 1; j2 < my2darray.get(i2).size(); j2++) {
				for (int k2 = 0; k2 < my2darray.get(i2).get(j2).length(); k2++) {

					curchar = my2darray.get(i2).get(j2).charAt(k2);
					if(Character.toString(my2darray.get(i2).get(j2).charAt(k2)).equals(curstate)){
						if(k2<my2darray.get(i2).get(j2).length() - 1){ //if the character is not the last (there is some char after it)
							if(!Character.isUpperCase(my2darray.get(i2).get(j2).charAt(k2+1))){
								FinalFollow.add(Character.toString(my2darray.get(i2).get(j2).charAt(k2+1)));
							}
							else{ //if next character is also upper case
								//get first of this next character, if contains e, get first of the following one
								for (int k3 = k2+1; k3 < my2darray.get(i2).get(j2).length(); k3++) {
									containseps = 0;
									first2d = first2d();
									for (int i3 = 0; i3 < first2d.size(); i3++) {
										for (int j3 = 1; j3 < first2d.get(i3).size(); j3++) {
											if(first2d.get(i3).get(0).equals(Character.toString(my2darray.get(i2).get(j2).charAt(k3)))){
												if(!first2d.get(i3).get(j3).equals("e")){
													FinalFollow.add(first2d.get(i3).get(j3));													
												}
												else{
													containseps = 1;
												}
											}
										}
									}
									if(containseps == 1){
										for (int i4 = 0; i4 < FinalFollow.size(); i4++) {
											if(FinalFollow.get(i4).equals(my2darray.get(i2).get(0))){
												for (int j4 = i4 + 1; j4 < FinalFollow.size(); j4++) {
													if(Character.isUpperCase(FinalFollow.get(j4).charAt(0))){
														FinalFollow2.add(FinalFollow.get(j4));														
													}
													else{
														break;
													}
												}
											}
										}
										
										
										
										
										
//										System.out.println("contains eps");
//										containseps2 = 1;
//										for (int i4 = 0; i4 < my2darray.size(); i4++) {
//											for (int j4 = 1; j4 < my2darray.get(i4).size(); j4++) {
//												for (int k4 = 0; k4 < my2darray.get(i4).get(j4).length(); k4++) {
//													if(Character.toString(my2darray.get(i4).get(j4).charAt(k4)).equals(my2darray.get(i2).get(0))){
//														if(k4 < my2darray.get(i4).get(j4).length() - 1){
//															if(!Character.isUpperCase(my2darray.get(i4).get(j4).charAt(k4+1))){
//																FinalFollow.add(Character.toString(my2darray.get(i4).get(j4).charAt(k4+1)));
//															}
//														}
//													}
//												}
//												
//											}
//										}
										
										
										
										
										
										
										
									}
									else{
										containseps2 = 0;
									}
									
								}
							}
						}
						else{ //if the character is the last (Nothing comes right after it)
							if(!my2darray.get(i2).get(0).equals(Character.toString(my2darray.get(i2).get(j2).charAt(k2)))){ //if the character is not the same as the state it is in
//								System.out.println("state : " + my2darray.get(i2).get(0));
//								System.out.println("element : " + my2darray.get(i2).get(j2).charAt(k2));
								for (int s = 0; s < FinalFollow.size(); s++) {
									if(FinalFollow.get(s).equals(my2darray.get(i2).get(0))){
										for (int p = s+1; p < FinalFollow.size(); p++) {
											if(!Character.isUpperCase(FinalFollow.get(p).charAt(0))){
												FinalFollow2.add(FinalFollow.get(p));
											}
											else{
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		LinkedHashSet<String> hashSet2 = new LinkedHashSet<>(FinalFollow2);
		ArrayList<String> listWithoutDuplicates2 = new ArrayList<>(hashSet2);
		FinalFollow.addAll(listWithoutDuplicates2);
		
		FinalFollow2.clear();
	}
	
//	System.out.println(FinalFollow);
	

//	--------------------------------Formatting to return a string-----------------------------------------------
	
	
	for (int i = 0; i < FinalFollow.size(); i++) {
		if(Character.isUpperCase(FinalFollow.get(i).charAt(0)) && i > 0){
			FinalFollowFinal.add(";");
		}
		FinalFollowFinal.add(FinalFollow.get(i));
	}
    StringBuffer sb = new StringBuffer();
    String s;
    for (int i = 0; i < FinalFollowFinal.size(); i++) {
    	s = FinalFollowFinal.get(i);
		sb.append(s);
		if(!s.equals(";") && i < FinalFollowFinal.size()-1)
			if(!FinalFollowFinal.get(i+1).equals(";"))
				sb.append(",");
	}
    String strfollow = sb.toString();
	//convert str to arraylist then to 2d arraylist
    String[] follow1 = strfollow.split(";");
	List<String> follow2 = (List<String>) Arrays.asList(follow1);
	ArrayList<String> follow = new ArrayList<String>(follow2);		
	ArrayList<ArrayList<String>> follow2d = new ArrayList<ArrayList<String>>();
	ArrayList<String> follow3 = new ArrayList<String>();

	for (int i = 0; i < myarray1.length; i++) {
		String[] temp1 = follow.get(i).split(",");
		List<String> temp2 = (List<String>) Arrays.asList(temp1);
		follow3 = new ArrayList<String>(temp2);
		follow2d.add(follow3);
	}    
    //sorting by alphabetical order
    for (int i = 0; i < follow2d.size(); i++) {
		Collections.sort(follow2d.get(i));
	}
    
//    swapping dollar sign with state
//    for (int i = 0; i < follow2d.size(); i++) {
//    	if(follow2d.get(i).get(0).equals("$")){
//    		Collections.swap(follow2d.get(i), 0, follow2d.get(i).size()-1);    	    		
//    	}
//    }
    
    for (int i = 0; i < follow2d.size(); i++) {
		if(follow2d.get(i).get(0).equals("$")){
			follow2d.get(i).remove("$");
			follow2d.get(i).add("$");
		}
	}
    //removing duplicates
    ArrayList<String> followWithoutDuplicates = new ArrayList<>();
    for (int i = 0; i < follow2d.size(); i++) {
    	LinkedHashSet<String> hashSet = new LinkedHashSet<>(follow2d.get(i));
    	followWithoutDuplicates = new ArrayList<>(hashSet);
        follow2d.remove(i);
        follow2d.add(i, followWithoutDuplicates); 
	}
        
    //converting 2darray back to string
	String followstringfinal = "";
	for (int i = 0; i < follow2d.size(); i++) {
		for (int j = 0; j < follow2d.get(i).size(); j++) {
			if(Character.isUpperCase(follow2d.get(i).get(j).charAt(0)) && i > 0){
				followstringfinal = followstringfinal + ";";
			}
			if(Character.isUpperCase(follow2d.get(i).get(j).charAt(0))){
				followstringfinal = followstringfinal + follow2d.get(i).get(j);				
				followstringfinal = followstringfinal + ",";
			}
			else{
				followstringfinal = followstringfinal + follow2d.get(i).get(j);				
			}
		}
	}
	
	return followstringfinal;
	
	}








//-------------------------------------------------------------------helper method------------------------------------------------------------

public ArrayList<ArrayList<String>> first2d(){
	String[] myarray1 = input.split(";");
	List<String> myarray2 = (List<String>) Arrays.asList(myarray1);
	ArrayList<String> myarray = new ArrayList<String>(myarray2);		
	ArrayList<ArrayList<String>> my2darray = new ArrayList<ArrayList<String>>();
	ArrayList<String> temp3 = new ArrayList<String>();
	ArrayList<String> FinalFirst = new ArrayList<String>();
	ArrayList<String> FinalFirst2 = new ArrayList<String>();
	ArrayList<String> FinalFirst3 = new ArrayList<String>();
	ArrayList<String> FinalFirstFinal = new ArrayList<String>();
	
	char curchar;
	char tempchar;
	String curcharS = "";
	char curStateC;
	String curStateS = "";
	int counter = 1;
	char charAfterState;
	String charAfterStateS = "";
	int position = 0;
	char capitalchar;
	int foundepsilon = 0;
	int epsilonflag = 0;
	
	for (int i = 0; i < myarray1.length; i++) {
		String[] temp1 = myarray.get(i).split(",");
		List<String> temp2 = (List<String>) Arrays.asList(temp1);
		temp3 = new ArrayList<String>(temp2);
		my2darray.add(temp3);
	}
//	System.out.println(my2darray);	
	
	
	for (int i = 0; i < my2darray.size(); i++) {
		counter = 1;
		curStateC = my2darray.get(i).get(0).charAt(0);
		curStateS = Character.toString(curStateC);
		FinalFirst.add(curStateS);
		for (int j = 1; j < my2darray.get(i).size(); j++) {
			foundepsilon = 0;
			position = 0;
			counter = 1;
			curchar = my2darray.get(i).get(j).charAt(0);
			if(!Character.isUpperCase(curchar)){ //if the current character is lower case
				curcharS = Character.toString(curchar);
				FinalFirst.add(curcharS);
			}
			else{ //if the current character is upper case (state)
				if(curchar != my2darray.get(i).get(0).charAt(0)){ // if the current character is not the same as the current state
//					this part is wrong
					if(i<1){
						for (int index = 0; index < my2darray.get(i).get(j).length(); index++) {
							foundepsilon = 0;
							if(!Character.isUpperCase(my2darray.get(i).get(j).charAt(index))){
								FinalFirst.add(Character.toString(my2darray.get(i).get(j).charAt(index)));
								break;
							}
							for (int i2 = i; i2 < my2darray.size(); i2++) {
								for (int j2 = 1; j2 < my2darray.get(i2).size(); j2++) {
										curchar = my2darray.get(i).get(j).charAt(index);
										if(my2darray.get(i2).get(0).charAt(0) == curchar){
											if(!Character.isUpperCase(my2darray.get(i2).get(j2).charAt(0))){
												curcharS = Character.toString(my2darray.get(i2).get(j2).charAt(0));
												if(curcharS.equals("e")){
													foundepsilon = 1;
												}
												if(my2darray.get(i).get(j).length()>1){
													if(!curcharS.equals("e") && index <= my2darray.get(i).get(j).length() - 1){
														FinalFirst.add(curcharS);																					
													}
													else if(index > my2darray.get(i).get(j).length() - 1){
														FinalFirst.add(curcharS);
													}													
												}
												else{
													FinalFirst.add(curcharS);																					
												}
											}
											else{
												for (int i3 = i2; i3 < my2darray.size(); i3++) {
													for (int j3 = j2; j3 < my2darray.get(i3).size(); j3++) {
														if(my2darray.get(i3).get(0).equals(Character.toString(curchar))){
															for (int k = 1; k < my2darray.get(i3).size(); k++) {
																if(!Character.isUpperCase(my2darray.get(i3).get(k).charAt(0))){
	//																if(!my2darray.get(i3).get(k).equals("e")){
																	FinalFirst.add(Character.toString(my2darray.get(i3).get(k).charAt(0)));
	//																}
																}
																else{
																	tempchar = my2darray.get(i3).get(k).charAt(0);
																	for (int i4 = i3; i4 < my2darray.size(); i4++) {
																		for (int j4 = k; j4 < my2darray.get(i4).size(); j4++) {
																			if(my2darray.get(i4).get(0).charAt(0) == tempchar){
																				if(!Character.isUpperCase(my2darray.get(i4).get(j4).charAt(0))){
																					FinalFirst.add(Character.toString(my2darray.get(i4).get(j4).charAt(0)));
																				}
																				else{
																					tempchar = my2darray.get(i4).get(j4).charAt(0);
	//																			not sure if these 2 lines below work correct
																					i4 = i3;
																					j4 = j3;
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							if(foundepsilon == 0){
								break;
							}
						}
					}
					else{ //get the first of the curchar and add them to the first of my2darray.get(i).get(0)
						if(FinalFirst.contains(Character.toString(my2darray.get(i).get(j).charAt(0)))){ //if the character you are on existed in the FinalFirst yet
							for (int i2 = 0; i2 < FinalFirst.size(); i2++) {
								curcharS = Character.toString(curchar);
								if(FinalFirst.get(i2).equals(curcharS)){
									position = i2;
									break;
								}
							}
								for (int j2 = position + 1; j2 < FinalFirst.size(); j2++) {
									if (!Character.isUpperCase(FinalFirst.get(j2).charAt(0))) {
										FinalFirst2.add(FinalFirst.get(j2));
									}
									else{
										break;
									}
								}
								if(FinalFirst2.contains("e")){ //if the list of the first of the state contains e then add the small letter after it
									if(my2darray.get(i).get(j).length()>1)
										if(!Character.isUpperCase(my2darray.get(i).get(j).charAt(1)))
											FinalFirst2.add(Character.toString(my2darray.get(i).get(j).charAt(1)));
								}
						}
						else{ // was not in the FinalFirst so you need to get it manually
//							this part is similar to some part below but modified a little
//							-----------------------------------------------------------------------------------------------
							epsilonflag = 0;
							for (int k = 0; k < my2darray.get(i).get(j).length(); k++) {
								capitalchar = my2darray.get(i).get(j).charAt(k);
								if(Character.isUpperCase(capitalchar)){
									for (int i2 = 0; i2 < my2darray.size(); i2++) {
										if(my2darray.get(i2).get(0).equals(Character.toString(capitalchar))){
											for (int j2 = 0; j2 < my2darray.get(i2).size(); j2++) {
												if(!Character.isUpperCase(my2darray.get(i2).get(j2).charAt(0))){
													if(Character.toString(my2darray.get(i2).get(j2).charAt(0)).equals("e")){
														epsilonflag = 1;
													}
													if(!Character.toString(my2darray.get(i2).get(j2).charAt(0)).equals("e") ){
														FinalFirst2.add(Character.toString(my2darray.get(i2).get(j2).charAt(0)));														
													}
												}
											}
										}
									}
								}
								else{
									break;
								}
								if(epsilonflag == 0){
									break;
								}
							}
							if(epsilonflag == 1){
								FinalFirst2.add("e");
							}
//							-----------------------------------------------------------------------------------------------
						}
					}
				}
				else{ //if the current character is upper case and same as the current state
					if(Character.toString(my2darray.get(i).get(j).charAt(0)).equals(my2darray.get(i).get(0))
							&& my2darray.get(i).get(j).length() > 1
							&& my2darray.get(i).contains("e")
							)
					{
						for (int k = 1; k < my2darray.get(i).get(j).length(); k++) {
							capitalchar = my2darray.get(i).get(j).charAt(k);
							if(Character.isUpperCase(capitalchar)){
								for (int i2 = 0; i2 < my2darray.size(); i2++) {
										for (int j2 = 0; j2 < my2darray.get(i2).size(); j2++) {
											if(my2darray.get(i2).get(0).equals(Character.toString(capitalchar))){
												if(!Character.isUpperCase(my2darray.get(i2).get(j2).charAt(0))){
													if(!Character.toString(my2darray.get(i2).get(j2).charAt(0)).equals("e")){
														FinalFirst3.add(Character.toString(my2darray.get(i2).get(j2).charAt(0)));		
													}
												}
											}
										}
									}
								}
							else{
								break;
							}
						}
					}
					
					for (int i2 = 0; i2 < my2darray.size(); i2++) {
						for (int j2 = 0; j2 < my2darray.get(i2).size(); j2++) {
							counter = 1;
							if(i2 != i && my2darray.get(i2).get(0).equals(my2darray.get(i).get(j2))  && my2darray.get(i2).contains("e")){
								for (int k = 0; k < my2darray.get(i).get(j).length(); k++) {
									if(Character.isUpperCase(my2darray.get(i).get(j).charAt(counter))){
										counter++;
									}
								}
								charAfterState = my2darray.get(i).get(j).charAt(counter);
								charAfterStateS = Character.toString(charAfterState);
								FinalFirst.add(charAfterStateS);
								counter = 1;
								break;
							}
						}
					}
				}
			}
		}
//		removing duplicates
		LinkedHashSet<String> hashSet = new LinkedHashSet<>(FinalFirst2);
		ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);
		FinalFirst.addAll(listWithoutDuplicates);
		
		
		LinkedHashSet<String> hashSet2 = new LinkedHashSet<>(FinalFirst3);
		ArrayList<String> listWithoutDuplicates2 = new ArrayList<>(hashSet2);
		FinalFirst.addAll(listWithoutDuplicates2);
		
		FinalFirst2.clear();
		FinalFirst3.clear();
	}
	
//	--------------------------------Formatting to return a string-----------------------------------------------
	for (int i = 0; i < FinalFirst.size(); i++) {
		if(Character.isUpperCase(FinalFirst.get(i).charAt(0)) && i > 0){
			FinalFirstFinal.add(";");
		}
		FinalFirstFinal.add(FinalFirst.get(i));
	}
    StringBuffer sb = new StringBuffer();
    String s;
    for (int i = 0; i < FinalFirstFinal.size(); i++) {
    	s = FinalFirstFinal.get(i);
		sb.append(s);
		if(!s.equals(";") && i < FinalFirstFinal.size()-1)
			if(!FinalFirstFinal.get(i+1).equals(";"))
				sb.append(",");
	}
    String strfirst = sb.toString();

	//convert str to arraylist then to 2d arraylist
    String[] first1 = strfirst.split(";");
	List<String> first2 = (List<String>) Arrays.asList(first1);
	ArrayList<String> first = new ArrayList<String>(first2);		
	ArrayList<ArrayList<String>> first2d = new ArrayList<ArrayList<String>>();
	ArrayList<String> first3 = new ArrayList<String>();

	for (int i = 0; i < myarray1.length; i++) {
		String[] temp1 = first.get(i).split(",");
		List<String> temp2 = (List<String>) Arrays.asList(temp1);
		first3 = new ArrayList<String>(temp2);
		first2d.add(first3);
	}    
    //sorting by alphabetical order
    for (int i = 0; i < first2d.size(); i++) {
		Collections.sort(first2d.get(i));
	}
    
    //removing duplicates
    ArrayList<String> firstWithoutDuplicates = new ArrayList<>();
    for (int i = 0; i < first2d.size(); i++) {
    	LinkedHashSet<String> hashSet = new LinkedHashSet<>(first2d.get(i));
        firstWithoutDuplicates = new ArrayList<>(hashSet);
        first2d.remove(i);
        first2d.add(i, firstWithoutDuplicates); 
	}
    
    return first2d;
}



}
