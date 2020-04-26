import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DFAclass {

public static void main(String[] args) {
// 0,0,1;1,2,1;2,0,3;3,3,3#1,3
//0,3,1;1,2,1;2,2,1;3,3,3#2
int result = 0;
System.out.println("Enter the DFA format: ");
Scanner in = new Scanner(System.in);
String DFA = in.nextLine();
String[] goal = DFA.split("#");
List<String> goallist = (List) Arrays.asList(goal);
ArrayList<String> goallistofstrings = new ArrayList<String>(goallist);
String DFAvalues = goallistofstrings.get(0);
String[] elements = DFAvalues.split(";");
List<String> stringlist = (List) Arrays.asList(elements);
ArrayList<String> listofstrings = new ArrayList<String>(stringlist);

System.out.println("nodes : " + listofstrings);
goallistofstrings.remove(0);
String finalgoalstring = goallistofstrings.get(0);
String[] goalelements = finalgoalstring.split(",");
List<String> goalstringlist = (List) Arrays.asList(goalelements);
ArrayList<String> goalfinallist = new ArrayList<String>(goalstringlist);

System.out.println("goal final list: " + goalfinallist);
ArrayList<String> leadings = new ArrayList<String>();
for (int i = 0; i < elements.length; i++) {
String leading = listofstrings.get(i);
char first = leading.charAt(0);
String firstasstring = Character.toString(first);
leadings.add(firstasstring);
// System.out.println(first);
}
System.out.println("node headers : " + leadings);


Scanner in2 = new Scanner(System.in);
String path = in.nextLine();
char currentnode = DFA.charAt(0);
String currentnodeS = Character.toString(currentnode);

// char currentpath = path.charAt(0);
// String currentpathS = Character.toString(currentpath);

// 010
int x=0;
for (int i = 0; i < path.length(); i++) {
char currentpath = path.charAt(i);
String currentpathS = Character.toString(currentpath);
if(currentpathS.equals("0")){
currentnodeS = listofstrings.get(x);
currentnode = currentnodeS.charAt(2);
currentnodeS = Character.toString(currentnode);
}
else{
currentnodeS = listofstrings.get(x);
currentnode = currentnodeS.charAt(4);
currentnodeS = Character.toString(currentnode);
}
for(int j=0;j<leadings.size();j++){
if(leadings.get(j).equals(currentnodeS)){
x=j;
}
}
}
for (int i = 0; i < goalfinallist.size(); i++) {
String firstgoal = goalfinallist.get(i);
if(currentnodeS.equals(firstgoal)){
result = 1;
}
}
if(result==1){
System.out.println("true");
}
if(result==0){
System.out.println("false");
}
}

}