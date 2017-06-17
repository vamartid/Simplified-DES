/*
 * vamartid
 * Vasileios Martidis
 * A.E.M.: 2372
 */
package simplified_des;

public class DES_operator {
    
    /**
     * Method checkInput
     * 
     * @param input input string for check
     * @param lenght length we need the input to be
     * @return true if the Input string contains 
     * only 0 and 1 and it has length equals to the 
     * @param int length
     */
    boolean checkInput(String input,int lenght){
        String set = "01";
        if(input.length()==lenght){
            char[] chars = input.toCharArray();
            for (char c : chars) {
                if(!(c=='1')&&!(c=='0')) {
                    return false;
                }
            }
            return true;
        }
           return false;
    }


    String[][] s0 = new String[][]{
        {"01","00","11","10"},
        {"11","10","01","00"},
        {"00","10","01","11"},
        {"11","01","11","10"}
    };
    
    String[][] s1 = new String[][]{
        {"00","01","10","11"},
        {"10","00","01","11"},
        {"11","00","01","00"},
        {"10","01","00","11"}
    };
    
    private String p10(String key){
        String output=""+
                key.charAt(2)+
                key.charAt(4)+
                key.charAt(1)+
                key.charAt(6)+
                key.charAt(3)+
                key.charAt(9)+
                key.charAt(0)+
                key.charAt(8)+
                key.charAt(7)+
                key.charAt(5);
        return output;
    }
    
    private String cyclicLeftShift(String s, int k){
        k = k%s.length();
        return s.substring(k) + s.substring(0, k);
    }
    
    private String p8(String key){
        String output=""+
                key.charAt(5)+
                key.charAt(2)+
                key.charAt(6)+
                key.charAt(3)+
                key.charAt(7)+
                key.charAt(4)+
                key.charAt(9)+
                key.charAt(8);
        return output;
    }
    
    private String p4(String key){
        String output=""+
                key.charAt(1)+
                key.charAt(3)+
                key.charAt(2)+
                key.charAt(0);
        return output;
    }
    
    private String ep(String key){
        String output=""+
                key.charAt(3)+
                key.charAt(0)+
                key.charAt(1)+
                key.charAt(2)+
                key.charAt(1)+
                key.charAt(2)+
                key.charAt(3)+
                key.charAt(0);
        return output;
    }
    
    String ip(String key){
        String output=""+
                key.charAt(1)+//0
                key.charAt(5)+//1
                key.charAt(2)+//2
                key.charAt(0)+//3
                key.charAt(3)+//4
                key.charAt(7)+//5
                key.charAt(4)+//6
                key.charAt(6);//7
        return output;
    }
    
    String ip_inverse(String key){
        String output=""+
                key.charAt(3)+
                key.charAt(0)+
                key.charAt(2)+
                key.charAt(4)+
                key.charAt(6)+
                key.charAt(1)+
                key.charAt(7)+
                key.charAt(5);
        return output;
    }
    
    private String xor(String a, String b){
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < a.length(); k++)
           sb.append((a.charAt(k) ^ b.charAt(k + (Math.abs(a.length() - b.length()))))) ;
        return sb.toString();
    }
    
    private String findRow(String str){
        String bit1bit4=""+str.charAt(0)+str.charAt(3);
        return bit1bit4;
    }
    
    private String fidnCollumn(String str ){
        String bit2bit3=""+str.charAt(1)+str.charAt(2);
        return bit2bit3;
    }
    
    private int stringToDecim(String str){
        
        int number=0;
        int power=0;
        for (int i = str.length()-1; i >=0 ; i--) {
            if (str.charAt(i) == '1') {
                number=number+(int)Math.pow(2,power);
            }
            power++;
        }
        return number;
    }
    
    String[] keyGen(String key){
        //create a DES_operator object in order
        //we can use the functions on the key.
        //DES_operator des=new DES_operator();
        
        //perform p10
        String p10_output=p10(key);
        //print p10 output
        System.out.print("After p10 : ");
        System.out.print(p10_output+"\n");
        //get the middle of the String
        int mid = p10_output.length() / 2;
        //split the p10 in two
        String[] parts = {p10_output.substring(0, mid),p10_output.substring(mid)};
        //first part
        System.out.print("First half of p10 : ");
        System.out.print(parts[0]+"\n");
        //second part
        System.out.print("Second half of p10 : ");
        System.out.print(parts[1]+"\n");
        String[] parts_ls1= new String[2];
        //left shift both strings on 1 possition
        parts_ls1[0]=cyclicLeftShift(parts[0], 1);
        parts_ls1[1]=cyclicLeftShift(parts[1], 1);
        System.out.print("First half of p10 leftShifted 1 possition or LS1_l : ");
        System.out.print(parts_ls1[0]+"\n");
        //second part
        System.out.print("Second half of p10 leftShifted 1 possition or LS1_r : ");
        System.out.print(parts_ls1[1]+"\n");
        
        //merge the two parts of p10
        //name is p10 after Splits Shifts and Merge
        String ls1=parts_ls1[0]+parts_ls1[1];
        //print p10ssm
        System.out.print("Both LeftShifted for 1 possition halfs merged or LS1 : ");
        System.out.print(ls1+"\n");
        //perform p10
        System.out.print("After p8(Key 1) : ");
        String key1=p8(ls1);
        System.out.print(key1+"\n");
        
        //left shift for 2 possition the parts_ls1 we created before
        //to make the second key the same way
        String[] parts_ls2= new String[2];
        //left shift both strings on 2 possition
        parts_ls2[0]=cyclicLeftShift(parts_ls1[0], 2);
        parts_ls2[1]=cyclicLeftShift(parts_ls1[1], 2);
        System.out.print("First half of LS1 leftShifted 2 possitions or LS2_l : ");
        System.out.print(parts_ls2[0]+"\n");
        //second part
        System.out.print("Second half of LS1 leftShifted 2 possitions or LS2_r : ");
        System.out.print(parts_ls2[1]+"\n");
        
        //merge the two parts of p10
        //name is p10 after Splits Shifts and Merge
        String ls2=parts_ls2[0]+parts_ls2[1];
        //print p10ssm
        System.out.print("Both LeftShifted for 1 possition halfs merged or LS2 : ");
        System.out.print(ls2+"\n");
        //perform p10
        System.out.print("After p8(Key 2) : ");
        String key2=p8(ls2);
        System.out.print(key2+"\n");
        String[] keys= {key1,key2};
        return keys;
    }
    String fKmethod(String plaintext,String key){
        //plaintext can also be ip_output so
        //perform ip
        String ip_output=plaintext;
        //ep right most half of ip
        int mid = ip_output.length() / 2;
        //split the p10 in two
        String[] ip_parts = {ip_output.substring(0, mid),ip_output.substring(mid)};
        //first part
        System.out.print("First half of ip : ");
        System.out.print(ip_parts[0]+"\n");
        //second part
        System.out.print("Second half of ip : ");
        System.out.print(ip_parts[1]+"\n");
        //ep on the second part of ip
        String ep=ep(ip_parts[1]);
        //print ep
        System.out.print("Ep on Second half of ip : ");
        System.out.print(ep+"\n");
        //xor key and ep
        String xor=xor(ep, key);
        System.out.print("XOR ep and key "+key+" : ");
        System.out.print(xor+"\n");
        mid = xor.length() / 2;
        //split the p10 in two
        String[] xor_parts = {xor.substring(0, mid),xor.substring(mid)};
        //first part
        System.out.print("First half of xor : ");
        System.out.print(xor_parts[0]+"\n");
        //second part
        System.out.print("Second half of xor : ");
        System.out.print(xor_parts[1]+"\n");
        //row of first part
        System.out.print("Row of first half of xor : ");
        String xorLr=findRow(xor_parts[0]);
        System.out.print(xorLr+"-to-decimal->"+stringToDecim(xorLr)+"\n");
        //collumn of first part
        System.out.print("Collumn of first half of xor : ");
        String xorLc=fidnCollumn(xor_parts[0]);
        System.out.print(xorLc+"-to-decimal->"+stringToDecim(xorLc)+"\n");
        //row of second part
        System.out.print("Row of second half of xor : ");
        String xorRr=findRow(xor_parts[1]);
        System.out.print(xorRr+"-to-decimal->"+stringToDecim(xorRr)+"\n");
        //collumn of second part
        System.out.print("Collumn of second half of xor : ");
        String xorRc=fidnCollumn(xor_parts[1]);
        System.out.print(xorRc+"-to-decimal->"+stringToDecim(xorRc)+"\n");
        System.out.print("S0 cell : ");
        String s_0=s0[stringToDecim(xorLr)][stringToDecim(xorLc)];
        System.out.print(s_0+"\n");
        System.out.print("S1 cell : ");
        String s_1=s1[stringToDecim(xorRr)][stringToDecim(xorRc)];
        System.out.print(s_1+"\n");
        System.out.print("S0 and S1 : ");
        String s0_s1=s_0+s_1;
        System.out.print(s0_s1+"\n");
        System.out.print("p4 on S0 and S1 : ");
        String p4=p4(s0_s1);
        System.out.print(p4+"\n");
        System.out.print("p4 xor ipL : ");
        String xor_p4_ipL=xor(p4, ip_parts[0]);
        System.out.print(xor_p4_ipL+"\n");
        String output=xor_p4_ipL+ip_parts[1];
        
        return output;
    }
    
    String swapHalfs(String str){
        System.out.print("Swap the last xor with the ipR and merge them : ");
        int mid = str.length() / 2;
        //split the p10 in two
        String[] str_parts = {str.substring(0, mid),str.substring(mid)};
        str=str_parts[1]+str_parts[0];
        System.out.print(str+"\n");
        return str;
    }
    
    String encrypt(String[] keys,String plaintext){
        //perform ip
        String ip_output=ip(plaintext);
        //print ip output
        System.out.print("After ip : ");
        System.out.print(ip_output+"\n");
        System.out.println("----------------------------------");
        System.out.println("      Starting fK first time");
        System.out.println("----------------------------------");
        String first_fK=fKmethod(ip_output,keys[0]);
        System.out.print("First Fk output is : ");
        System.out.print(first_fK+"\n");
        //swap halfs before you perform fk second time but with key 2
//        System.out.println("Swap 1fK output on half");
        String sw=swapHalfs(first_fK);
//        System.out.println(sw);
        System.out.println("----------------------------------");
        System.out.println("      Starting fK second time");
        System.out.println("----------------------------------");
        String second_fK=fKmethod(sw,keys[1]);
        System.out.print("Second Fk output is : ");
        System.out.print(second_fK+"\n");
        //perform ip^(-1) on the last output
        String cyphertext=ip_inverse(second_fK);
//        System.out.println("Cyphertext is");
//        System.out.println(cyphertext);
        return cyphertext;
    }
    
        String decrypt(String[] keys,String plaintext){
            //change the possitions of the keys
            String temp= keys[0];
            keys[0]=keys[1];
            keys[1]=temp;
        return encrypt(keys,plaintext);
    }
}   

