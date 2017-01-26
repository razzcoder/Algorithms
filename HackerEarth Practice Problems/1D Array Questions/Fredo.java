import java.io.*;
import java.util.*;
class Fredo{
	public static void main(String args[])throws IOException{

		
		Scan scan=new Scan();
		Print print=new Print();
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		//
		int N=scan.scanInt();
		String a[]=new String[N];
		for(int i=0;i<N;i++){
			a[i]=scan.scanString();
		}
		Map<String,Integer> countMap=new LinkedHashMap<>();
		for(String ch:a){

			countMap.compute(ch,(key,val)->{
				if(val==null){
					return 1;
				}
				else{
					return val+1;
				}




			});
		}
		//System.out.println(countMap);
		int Q=scan.scanInt();
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<Q;i++){

			String type=scan.scanString();
			//System.out.println("type="+type);
			long f=scan.scanInt();
			//System.out.println("f="+f);
	//	System.out.println("ok "+f);
		//Iterator it = countMap.entrySet().iterator();
		int indicator=0;
		for (Map.Entry<String,Integer> pair : countMap.entrySet()) {


		    	//while (it.hasNext()) {
				     //   Map.Entry pair = (Map.Entry)it.next();
				       // System.out.println(pair.getKey() + " = " + pair.getValue());
				        if(type.equals("0") && f>0){

				        	// if(i==15){
				        	// 	System.out.println("fuck "+f);
				        	// }
				        	
				        	if((Integer)pair.getValue()>=f && f>0){
				        	
				        	//System.out.println(pair.getKey());
				        	sb.append(pair.getKey()+"\n");
				        	indicator=1;
				        	//it.remove();
				        	break;
				        }
				        	 // avoids a ConcurrentModificationException
    					}
    					else{
    						//System.out.println("what "+(Integer)pair.getValue());
    						if((Integer)pair.getValue()==f && f>0){
				        	
				        	//System.out.println(pair.getKey());
    							sb.append(pair.getKey()+"\n");
				        	indicator=1;
				        	//it.remove();
				        	break;
				        }

    					}
    				}
    	//}

    	if(indicator==0){
    		sb.append("0\n");
    	}
		}
		System.out.println(sb.toString());
		
}//End of main function 
}//End of Class
class Scan
{
	private byte[] buf=new byte[1024];
	private int index;
	private InputStream in;
	private int total;
	public Scan()
	{
		in=System.in;
	}
	public int scan()throws IOException
	{
		if(total<0)
		throw new InputMismatchException();
		if(index>=total)
		{
			index=0;
			total=in.read(buf);
			if(total<=0)
			return -1;
		}
		return buf[index++];
	}
	public int scanInt()throws IOException
	{
		int integer=0;
		int n=scan();
		while(isWhiteSpace(n))
		n=scan();
		int neg=1;
		if(n=='-')
		{
			neg=-1;
			n=scan();
		}
		while(!isWhiteSpace(n))
		{
			if(n>='0'&&n<='9')
			{
				integer*=10;
				integer+=n-'0';
				n=scan();
			}
			else throw new InputMismatchException();
		}
		return neg*integer;
	}
	public double scanDouble()throws IOException
	{
		double doub=0;
		int n=scan();
		while(isWhiteSpace(n))
		n=scan();
		int neg=1;
		if(n=='-')
		{
			neg=-1;
			n=scan();
		}
		while(!isWhiteSpace(n)&&n!='.')
		{
			if(n>='0'&&n<='9')
			{
				doub*=10;
				doub+=n-'0';
				n=scan();
			}
			else throw new InputMismatchException();
		}
		if(n=='.')
		{
			n=scan();
			double temp=1;
			while(!isWhiteSpace(n))
			{
				if(n>='0'&&n<='9')
				{
					temp/=10;
					doub+=(n-'0')*temp;
					n=scan();
				}
				else throw new InputMismatchException();
			}
		}
		return doub*neg;
	}
	public String scanString()throws IOException
	{
		StringBuilder sb=new StringBuilder();
		int n=scan();
		while(isWhiteSpace(n))
		n=scan();
		while(!isWhiteSpace(n))
		{
			sb.append((char)n);
			n=scan();
		}
		return sb.toString();
	}
	private boolean isWhiteSpace(int n)
	{
		if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
		return true;
		return false;
	}
}
 
class Print
{
	private final OutputStream out;
	/*public Print(OutputStream outputStream)
	{
		writer=new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}*/
	public Print()
	{
		this.out=System.out;
	}
	public void print(String str)throws IOException
	{
		//buf=str.getBytes();
		for (int i = 0; i < str.length(); i++)
		{
			/*if (i != 0)
			out.write(' ');*/
			out.write(str.charAt(i));
		}
	}
	public void printLine(String str)throws IOException
	{
		print(str);
		out.write('\n');
	}
	public void close()throws IOException
	{
		out.close();
	}
} 

