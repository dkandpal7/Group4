import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.Reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;


public final class FullData {

  /** Change these settings before running this class. */
  private static final String INPUT_FILE_NAME1 = "C:\\Users\\User\\Desktop\\CS310_project_subregion\\";
  public static int row=302;
  public static int col=448;
  public static int time=1404;//for 9 year split do 1404/3 for small it will be 
  public static int yflag=0;//0 for complete 1 for first 9, 2 for second 9, 3 for last 9, rest for small data set
  public static float r=0.9f;  //threshold for 0.9 and 0.95
  public static int shift=3;//time shift for 1,2,3,4
  public static Graph g;
  public static int thread=16;//number of parallel threads
  /** Run the example. */
  public static void main(String... aArgs) {
	  FullData test = new FullData();
	  System.out.println(INPUT_FILE_NAME1);
	  float[][][] fileContents = test.read(INPUT_FILE_NAME1);
	  
  	  float[][] var=new float[row][col];
	  int count =0;
	  for(int i=0;i<row;i++)
	  {
		  for(int j=0;j<col;j++)
		  {
			  
			  var[i][j]=0.0f;
			  for (int k=0;k<time;k++)
			  {
				
				if (!(fileContents[i][j][k]==168.0 || fileContents[i][j][k]==157.0))
				{
					
					var[i][j]+=(fileContents[i][j][k]*fileContents[i][j][k]);
					
				}
				
			  }
			  
			  if(var[i][j]!=0)
			  count++;
			  }
	  }	  
	  
	  int m=0,n=0;
	  int[] ref=new int[row*col];
	  String[] index=new String[count];
	  int[] node=new int[count];
	  
	  for(int i=0;i<row;i++)
	  {
		  for(int j=0;j<col;j++)
		  {
			
			if(var[i][j]!=0)  
			{
			index[m]=i+","+j;
			node[m]=m;
			ref[n++]=m;
			m++;
			}  
			else 
			{ref[n++]=-1;}
			 
		  }
		  
	  }
	  
	  g=new Graph(node);
	  MyThread[] t=new MyThread[FullData.thread];
	  int x=0,y=col/t.length;
	  for(int i=0;i<t.length;i++)
	  {
		  t[i]=new MyThread(x, y, var, index, fileContents, "Thread "+i,ref);
		  t[i].start();
		  x=x+(col/t.length);
		  y=y+(col/t.length);
	  }
	  
	    while(Thread.activeCount()>1);
		
double sum=0.0;		
double sum1=0.0;		
  count=0;
	for(int i=0;i<g.getList().length;i++)
  {  
  if(g.getList()[i].getDegree()!=0)
  {
  sum+=g.getList()[i].getDegree();
  System.out.println("Degree:"+i+" "+g.getList()[i].getDegree());
  count++;
  }
  }
  sum=sum/(count+0.0);
  for(int i=0;i<g.getList().length;i++)
  {  
  if(g.getList()[i].getDegree()!=0)
  {
  sum1+=((g.getList()[i].getDegree()-sum)*(g.getList()[i].getDegree()-sum));
  
  }
  }
  sum1=Math.sqrt(sum1/count);
  
  System.out.println("Standard deviation:"+sum1);
 for(int i=0;i<g.getList().length;i++)
  {  
  if(g.getList()[i].getDegree()>(sum+(5.0*sum1)))
  {
  System.out.println("Node:"+index[i]);
  
  }
  }
    System.out.println("Clustering coeff:"+g.findClusteringcoeff());
 

sum=0.0;
int c=0;
for (int i=0;i<g.getList().length;i++)
  {
		if (g.getList()[i].getDegree()!=0)
		{sum+=g.shortestPath(g.getList()[i]);
		c++;}
	}

//sum/=((g.getList().length*(g.getList().length-1.0)));
sum/=((c*(c-1.0)));
System.out.println("Mean path length:"+sum);
  }
  
float[][][] read(String bInputFileName){
    String aInputFileName="";
   float[][][] data=new float[row][col][time];
   int k=0;
   int a=0,b=0;
   if (yflag==0)
   {
	   a=1979;
	   b=2005;
   }
   else if (yflag==1)
   {
	   a=1979;
	   b=1987;
	   
   }
   else if (yflag==2)
   {
	   a=1988;
	   b=1996;
   }
   else if (yflag==3)
   {
	   a=1997;
	   b=2005;
   }
   else if (yflag==4)
   {a=1990;
	   b=2005;
   }
   else if (yflag==5)
   {a=1990;
	   b=1993;
   }
   else if (yflag==6)
   {a=1994;
	   b=1997;
   }
   else if (yflag==7)
   {a=1998;
	   b=2001;
   }
    else if (yflag==8)
   {a=2002;
	   b=2005;
   }
   for (int y=a;y<=b;y++)
   	{
    	for(int w=1;w<=52;w++)
    	{
    		
    		aInputFileName=bInputFileName;
    		if (w<10)
    		aInputFileName=aInputFileName+y+"\\diffw0"+w+"y"+y+"+landmask";
    		//aInputFileName=aInputFileName+y+"\\Beaufort_Sea_diffw0"+w+"y"+y+"+landmask";

			else
    		aInputFileName=aInputFileName+y+"\\diffw"+w+"y"+y+"+landmask";
			//aInputFileName=aInputFileName+y+"\\Beaufort_Sea_diffw"+w+"y"+y+"+landmask";

			//Beaufort_Sea_diffw0
			File file = new File(aInputFileName);
    	    int i=0;
    	    int j=0;
    	    
    	    try {
    	      InputStream input = null;
    	        int totalBytesRead = 0;
    	        input = new BufferedInputStream(new FileInputStream(file));
    	        DataInputStream r = new DataInputStream(input);
    	        byte[] buffer = new byte[4];
    	        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
    	        	for (i=0;i<row;i++)
    	        		for(j=0;j<col;j++)
    	        			{
    	        			r.read(buffer);
    	        	        byteBuffer = ByteBuffer.wrap(buffer);
    	        			data[i][j][k]=byteBuffer.order(ByteOrder.LITTLE_ENDIAN).getFloat();
							}
    	        	
    	      }
    	    catch (FileNotFoundException ex) {
    	      System.out.println("File not found.");
    	    }
    	    catch (IOException ex) {
    	      System.out.println(ex);
    	    }
    	    finally {}
    	    
    	    k++;
    	}
    }
	  return data;
  }

} 