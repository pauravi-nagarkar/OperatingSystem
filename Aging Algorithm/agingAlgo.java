import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class agingAlgo {
    public static void main(String[] args) throws IOException {
        System.out.println("Please Enter the memory size or maximum frame size");
        Scanner sc = new Scanner(System.in);
        int i1=0,highbit=128;
        int framenumber = sc.nextInt();
        sc.close();
        File file = new File("/Users/Akshay Bidwai/OneDrive/Desktop/a.txt");
        if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }
        Scanner scan = new Scanner(file);
        int [] page = new int[10000];
        int [][] frame_faults = new int[framenumber][2];

        /**size of memory is 8 as given*/
        int [][] memory = new int[8][2];


        @SuppressWarnings("resource")
        PrintWriter writer = new PrintWriter("/Users/Akshay Bidwai/OneDrive/Desktop/a.txt");

        /**code for random generator of frame size*/

        for(int i=0;i<10000;i++){
            int k=(int)(Math.floor(Math.random()*framenumber));
         	/**numbers will be from frame to frame number*/
            writer.println(k+1);
        }
        writer.close();

        while(scan.hasNextLine()){
            String s = scan.nextLine().trim();
            page[i1] = Integer.parseInt(s);
            i1++;
        }

        scan.close();
        for(int i :page){
            boolean notfound = false,dobreak =false;
            /**
             * we are checking page in memory reference and increasing its value if found or else we are reducing it.
             * and input is One Clock
             * */
            while(!notfound){
                for(int []m:memory){
                    if(m[0]==i){
		    			/**We have page found reference, and we increase the count and break.*/
                        m[1]=m[1]|highbit;
                        notfound = false;
                        dobreak=true;
                        break;
                    }
                }
                notfound=true;
            }
            if(notfound == true && !dobreak){
                /**found the memory does not have it and shifting all the memory values by one position right*/
                for(int []m:memory){
                    m[1]= m[1]>>1;
                }
               /** after shifting find the least value and replace with the value in memory*/
                int lowest = 0;
                for(int i11 = 0;i11<7;i11++){
                    if(memory[i11][1] > memory[i11+1][1]){
                        lowest = i11+1;
                    }
                }
                /** Assigning the value in to memory*/
                memory[lowest][0]=i;
                memory[lowest][1]=128;
		    	 /**increasing the page fault*/
                frame_faults[i-1][0]=i;
                frame_faults[i-1][1]=frame_faults[i-1][1]+1;
            }

        }

        System.out.println("Memory");
        for(int []values:memory){
            System.out.println(values[0]+"    "+values[1]);
        }

        System.out.println("Page Faults");
        for(int []values:frame_faults){
            if(values[0] >0){
                System.out.println(values[0]+"    "+values[1]);
                System.out.println(values[0]);
            }
        }
    }
}