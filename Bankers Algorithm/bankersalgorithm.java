import java.util.*;   
import java.io.*;   
import java.util.Scanner;  
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
  
// Implementing Bankers Algorithm in java 
class bankersalgorithm
{  
  
        // create  method to calculate the requirenment of each process  
    static void requiredresource(int reqArray[][], int maxArray[][], int allocated_res[][], int total_process, int total_resources)  
    {  
        // use nested for loop to calculate Requirenment for each process  
        for (int i = 0 ; i < total_process ; i++){    // process  
            for (int j = 0 ; j < total_resources ; j++){  // resource  
                reqArray[i][j] = maxArray[i][j] - allocated_res[i][j];  
            }  
        }  
    }  
  
    // create method to determine whether the system is in safe state or not. 
    static boolean safesystem(int processes[], int available_res[], int maxArray[][], int allocated_res[][], int total_process, int total_resources)  
    {  
        int [][]reqArray = new int[total_process][total_resources];  
  
        
        requiredresource(reqArray, maxArray, allocated_res, total_process, total_resources);  
  
         
        boolean []finish_process = new boolean[total_process];  
  
        // initialize sequence that store safe sequenced  
        int []safe_sequence = new int[total_process];  
  
        // initialize temp_avail  as a copy of the available resources  
        int []temp_avail = new int[total_resources];  
          
        for (int i = 0; i < total_resources ; i++)    //copy available resource
            temp_avail[i] = available_res[i];  
  
        // initialize counter if 0 unsafe state/not finished.
        int counter = 0;  
          
        
        while (counter < total_process)  
        {  
              
            boolean foundSafeSystem = false;  
            for (int m = 0; m < total_process; m++)  
            {  
                if (finish_process[m] == false)         
                {  
                    int j;  
                      
                    for (j = 0; j < total_resources; j++)  
                        if (reqArray[m][j] > temp_avail[j])      
                            break;  
  
                    // the value of J and total_resources will be equal when all the needs of current process are satisfied  
                    if (j == total_resources)  
                    {  
                        for (int k = 0 ; k < total_resources ; k++)  
                            temp_avail[k] += allocated_res[m][k];  
  
                        // add current process in the safe_sequence  
                        safe_sequence[counter++] = m;  
  
                        finish_process[m] = true;  
  
                        foundSafeSystem = true;  
                    }  
                }  
            }  
  
            // the system will not be in the safe state when the value is false  
            if (foundSafeSystem == false)  
            {  
                System.out.print("Lack of resources, not in a safe state");  
                return false;  
            }  
        }  
  
        // print the safe sequence  
        System.out.print("The system is in safe state sequence follows: ");  
        for (int i = 0; i < total_process ; i++)  
            System.out.print("P"+safe_sequence[i] + " ");  
  
        return true;  
    }  
  
    // main() method start  
    public static void main(String[] args)  
    {     
        int count_processes, count_resources ; 
          
        //get input from the user  
        Scanner sc = new Scanner(System.in);  
          
        // get total processes from user
        System.out.println("Enter total number of Bank Clinets");  
        count_processes = sc.nextInt();  
          
        // get total resources from the user  
        System.out.println("Enter total requests Available");  
        count_resources = sc.nextInt();  
          
        int processes[] = new int[count_processes];  
        for(int i = 0; i < count_processes; i++){  
            processes[i] = i;  
        }  
          
        int available_res[] = new int[count_resources];  
        for( int i = 0; i < count_resources; i++){  
            System.out.println("Enter the availability of request"+ i +": ");  
            available_res[i] = sc.nextInt();  
        }  
          
        int maxArray[][] = new int[count_processes][count_resources];  
        for( int i = 0; i < count_processes; i++){  
            for( int j = 0; j < count_resources; j++){  
                System.out.println("Enter the max request"+ j +" that can be allocated to client"+ i +": ");  
                maxArray[i][j] = sc.nextInt();  
            }  
        }  
          
        int allocated_res[][] = new int[count_processes][count_resources];  
        for( int i = 0; i < count_processes; i++){  
            for( int j = 0; j < count_resources; j++){  
                System.out.println("How many units of request"+ j +" are allocated to client"+ i +"? ");  
                allocated_res[i][j] = sc.nextInt();  
            }  
        }  
          
        //call method to check whether the system is in safe state or not  
        safesystem(processes, available_res, maxArray, allocated_res, count_processes, count_resources);  
    }  
}  
