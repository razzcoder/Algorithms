import java.io.*;
import java.util.*;
class JumptoReachEnd{
	public static void main(String args[]){
		 JumptoReachEnd mj = new JumptoReachEnd();
        int arr[] = {1,3,5,3,2,2,6,1,6,8,9};
        int r[] = new int[arr.length];
		int result = mj.minJump(arr,r);
		System.out.println(result);

	}
	public int minJump(int arr[],int result[]){
		int jump[]=new int[arr.length];
		jump[0]=0;
		for(int i=1;i<arr.length;i++){
			jump[i]=Integer.MAX_VALUE-1;
		}
		for(int i=1;i<arr.length;i++){
			for(int j=0;j<i;j++){
				if(arr[j]+j>=i){
					if(jump[i]>jump[j]+1){
						result[i]=j;
						jump[i]=jump[j]+1;
					}
				}
			}
		}
		return jump[jump.length-1];
	}
}