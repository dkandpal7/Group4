//package create;

class MyThread extends Thread
{
	public MyThread( int x, int y, float[][] var, String[] index,
			float[][][] fileContents, String name,int[] ref) {
		super();
		
		this.x = x;
		this.y = y;
		this.var = var;
		this.index = index;
		this.fileContents = fileContents;
		this.name = name;
		this.ref=ref;
	}

	int x;
	int y;
	float[][] var;
	String[] index;
	float[][][] fileContents;
	String name;
	int[] ref;
	static long m=0;
   public void run ()
   {
		 
			 int a,b,c=0,d=0,f=0;
			 float sxy=0.0f;
			int v1=0;
			int v2=0;
			int m=0;
		  for(int i=0;i<FullData.row;i++)
		  {
			  for(int j=x;j<y;j++)
				  
			  {
				  
				  a=i;
				  b=j;
				  m=ref[FullData.col*i+j];
				  //System.out.println(a+b);
				  if (var[a][b]!=0)
				  {
					  
					  for(int h=m+1;h<index.length;h++)
					  { 
						  sxy=0f;
						  c=Integer.parseInt(index[h].split(",")[0]);
						  d=Integer.parseInt(index[h].split(",")[1]);
						  if(var[c][d]!=0)
						  {
							  for (int k=0;k<(FullData.time-FullData.shift);k++)
							  {
								  sxy+=(fileContents[a][b][k])*(fileContents[c][d][k+FullData.shift]);
							  }
							  if (sxy/(Math.sqrt(var[a][b]*var[c][d]))>FullData.r)
							  {
								  v1=FullData.g.getList()[m].getName();
								  v2=FullData.g.getList()[h].getName();
								  FullData.g.getList()[m].setList(new Neighbour(v2,FullData.g.getList()[m].getList()));
								  FullData.g.getList()[h].setList(new Neighbour(v1,FullData.g.getList()[h].getList()));
								  //System.out.println(this.name+" "+a+","+b+","+c+","+d);
								  
							  }
						  }	  
						  
						  
						  
					  }
					
				  }
				  
			  }
		  }
   }


public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}

public float[][] getVar() {
	return var;
}

public void setVar(float[][] var) {
	this.var = var;
}

public String[] getIndex() {
	return index;
}

public void setIndex(String[] index) {
	this.index = index;
}

public float[][][] getFileContents() {
	return fileContents;
}

public void setFileContents(float[][][] fileContents) {
	this.fileContents = fileContents;
}

public String gettName() {
	return name;
}

public void settName(String name) {
	this.name = name;
}
}
