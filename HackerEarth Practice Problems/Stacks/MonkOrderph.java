import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
 
class MonkOrderph {

	public static int binarySearch(List<Integer> arr,int x){

		int l=0;
		int result=-1;
		int r=arr.size()-1;
		while(l<=r){
			int m=(l+r)/2;

			if(arr.get(m)<x){
				l=m+1;

			}
			else if(arr.get(m)>x){
				r=m-1;
				result=arr.get(m);
			}
			else{

				l=l+1;//increase l by 1 if element is equal


			}
		}
		return result;

	}
	public static int[] convertIntegers(List<Integer> integers)
{
    int[] ret = new int[integers.size()];
    for (int i=0; i < ret.length; i++)
    {
        ret[i] = integers.get(i).intValue();
    }
    return ret;
}
    public static void main(String args[] ) throws Exception {


    	

      
 
        //Scanner
        Print print = new Print();
        Scan s = new Scan();
        int t=s.scanInt();
        int later_t=t;
        ArrayList<ArrayDeque<Integer>> al=new ArrayList<>();

        TreeSet<Integer> tree=new TreeSet<>();

        int ind=0;
        int n=0;
       
        while(t-->0){

        	ArrayDeque<Integer> stack=new ArrayDeque<>();
        	 n=s.scanInt();
        	for(int i=0;i<n;i++){
        		int ele=s.scanInt();
        		if(ind==0){
        			tree.add(ele);

        		}
        		stack.add(ele);
        	}
        	ind=1;

        	al.add(stack);

        	




         
    }
    StringBuilder sb=new StringBuilder();
   int Q=s.scanInt();
   for(int i=0;i<Q;i++){
   	int type=s.scanInt();
   	if(type==0){
   		int row=s.scanInt();
   		if(row-1==0){
   			tree.remove(al.get(row-1).pollLast());
   		}
   		else
   			al.get(row-1).pollLast();

     }
    else if(type==1){
    	int row=s.scanInt();
    	int height=s.scanInt();
    	if(row-1==0){
    		//Means add that is tree set too
    		tree.add(height);
    	}
    	al.get(row-1).add(height);

    }
    else{
    	
    	int first_ele=tree.first();
    	//System.out.println("Came "+i+" "+al.get(0)+"  "+al.get(1)+" "+first_ele+"  "+al.get(1).getFirst());
    	int indicator=1;
    	int temp_ele=first_ele;
    	ArrayDeque<Integer> temp_stack=al.get(1);
    		//ArrayDeque<Integer> temp_stack=(ArrayDeque<Integer>)temp_stack_2.clone();

    			if(!temp_stack.isEmpty() && temp_stack.getLast()<temp_ele){

    				indicator=0;
    				
    			}
    else{
    	for(int j=1;j<later_t;j++){
    		
    		
    			


    				ArrayList<Integer> ali=new ArrayList<>(temp_stack);
    				//int array[]=convertIntegers(ali);

    				temp_ele=binarySearch(ali,temp_ele);
    				if(temp_ele==-1){
    					indicator=0;
    					break;
    				}
    				

    		

    		
    			}
    		
    		//System.out.println();
    	}
    	//System.out.println(al.get(1));
    	if(indicator==1){
    		sb.append("YES\n");

    	}
    	else{
    		sb.append("NO\n");

    	}
    }
   	}
   	System.out.print(sb.toString());
   }
}
 
class Scan {
    private byte[] buf=new byte[1024];
    private int index;
    private InputStream in;
    private int total;
    public Scan() {
        in=System.in;
    }
 
    public int scan()throws IOException {
        if(total<0) {
            throw new InputMismatchException();
        }
        if(index>=total) {
            index=0;
            total=in.read(buf);
            if(total<=0) {
                return -1;
            }
        }
        return buf[index++];
    }
 
    public int scanInt()throws IOException {
        int integer=0;
        int n=scan();
        while(isWhiteSpace(n)) {
            n=scan();
        }
        int neg=1;
        if(n=='-') {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n)) {
            if(n>='0'&&n<='9') {
                integer*=10;
                integer+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*integer;
    }
 
    public long scanLong()throws IOException {
        long integer=0;
        int n=scan();
        while(isWhiteSpace(n)) {
            n=scan();
        }
        int neg=1;
        if(n=='-') {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n)) {
            if(n>='0'&&n<='9') {
                integer*=10;
                integer+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*integer;
    }
 
    public double scanDouble()throws IOException {
        double doub=0;
        int n=scan();
        while(isWhiteSpace(n))
            n=scan();
        int neg=1;
        if(n=='-') {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n)&&n!='.') {
            if(n>='0'&&n<='9') {
                doub*=10;
                doub+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        if(n=='.') {
            n=scan();
            double temp=1;
            while(!isWhiteSpace(n)) {
                if(n>='0'&&n<='9') {
                    temp/=10;
                    doub+=(n-'0')*temp;
                    n=scan();
                }
                else throw new InputMismatchException();
            }
        }
        return doub*neg;
    }
 
    public String scanString()throws IOException {
        StringBuilder sb=new StringBuilder();
        int n=scan();
        while(isWhiteSpace(n)) {
            n=scan();
        }
        while(!isWhiteSpace(n)) {
            sb.append((char)n);
            n=scan();
        }
        return sb.toString();
    }
 
    public byte[] scanBytes(int N)throws IOException {
        byte[] bytes = new byte[N];
        int diff = total-index;
        int i=index;
        for (;i<total && i-index < N; i++) {
            bytes[i-index] = buf[i];
        }
        index = i;
        if (diff >= N) {
            return bytes;
        }
        in.read(bytes, diff, N-diff);
        return bytes;
    }
 
    public boolean scanBoolean() throws IOException {
        int integer=0;
        int n=scan();
        while(isWhiteSpace(n)) {
            n=scan();
        }
        return n == '1';
    }
 
    private boolean isWhiteSpace(int n) {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1) {
            return true;
        }
        return false;
    }
}
 
class Print {
    private final BufferedWriter bw;
    public Print() {
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public void print(String str)throws IOException {
        bw.append(str);
    }
    public void printLine(String str)throws IOException {
        print(str);
        bw.append("\n");
    }
    public void close()throws IOException {
        bw.close();
    }
}