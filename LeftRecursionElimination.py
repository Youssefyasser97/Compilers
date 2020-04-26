def substitution(i,eleminatedleft,currentValue):
    for before in range(0,i):
                for current in range(1,len(eleminatedleft[i])):
                    if(currentValue[0]==eleminatedleft[before][0]):
                        currentValue=eleminatedleft[i][current]
                        if(len(currentValue)==1):
                            eleminatedleft[i].remove(currentValue)
                            for z in range(1,len(eleminatedleft[before])):
                                eleminatedleft[i].insert(z,eleminatedleft[before][z])
                        else:
                            eleminatedleft[i].remove(currentValue)
                            for z in range(1,len(eleminatedleft[before])):
                                eleminatedleft[i].insert(z,eleminatedleft[before][z]+currentValue[1:])
def eleminateleftrecursion(eleminatedleft,i,j,leftrecursion1,leftrecursion2):
    currentValue = eleminatedleft[i][j]
    if(currentValue[0]==eleminatedleft[i][0]):
        counter1 = 1
        counter2 = 1
        for index in range(1,len(eleminatedleft[i])):
            stringtoeleminate=eleminatedleft[i][index]
            if(stringtoeleminate[0]!=currentValue[0]):
                if(counter1 == 1):
                    leftrecursion1.append(currentValue[0])
                    counter1 = 0

                leftrecursion1.append(eleminatedleft[i][index]+currentValue[0]+'`')
            else:
                stringtoeleminate1=eleminatedleft[i][index]
                if(counter2 == 1):
                    leftrecursion2.append(currentValue[0]+'`')
                    counter2 = 0
                leftrecursion2.append(stringtoeleminate1[1:]+currentValue[0]+'`')
                leftrecursion2.append(',')



def LRE(CFG):
    eleminatedleft=[]
    leftrecursion1=[]
    leftrecursion2=[]
    youssef=CFG.split(';')
    for i in range(0,len(youssef)):
        eleminatedleft.append(youssef[i].split(','))


    i=0
    while(i<(len(eleminatedleft))):

        leftrecursion1=[]
        leftrecursion2=[]
        for j in range(1,len(eleminatedleft[i])):
            currentValue=eleminatedleft[i][j]
            substitution(i,eleminatedleft,currentValue)
            eleminateleftrecursion(eleminatedleft,i,j,leftrecursion1,leftrecursion2)


        if(len(leftrecursion1)==0):
            if(len(leftrecursion2)==0):

               i+=1
        else:
            eleminatedleft.pop(i)
            eleminatedleft.insert(i,leftrecursion1)
            eleminatedleft.insert(i+1,leftrecursion2)
            i+=2

    eleminatedleftarray = []
    for index1 in range(0,len(eleminatedleft)):
        if(eleminatedleft[index1] not in eleminatedleftarray):
            eleminatedleftarray.append(eleminatedleft[index1])




    finalstring = ''
    print(eleminatedleftarray)
    for i in range(0,len(eleminatedleftarray)):
        for j in range(0,len(eleminatedleftarray[i])):
            finalstring = finalstring + eleminatedleftarray[i][j]
            if(j + 2 <= len(eleminatedleftarray)):
                finalstring = finalstring + ','
        if(i < len(eleminatedleftarray) - 1):
            finalstring = finalstring + ';'
    print(finalstring)




# ----------------------------TESTING------------------------------
LRE('S,lLr,a;L,LbS,S')
LRE('S,ScT,T;T,aSb,iaLb,i;L,SdL,S')
LRE('S,SuS,SS,Ss,lSr,a')
LRE('S,SuT,T;T,TF,F;F,Fs,P;P,a,b')
LRE('S,BC,C;B,Bb,b;C,SC,a')
LRE('S,z,To;T,o,Sz')
