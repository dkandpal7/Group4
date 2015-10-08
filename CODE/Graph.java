//package create;

import java.util.ArrayList;





public class Graph {


private Vertex[] list;

public Vertex[] getList() {
	return list;
}

public void setList(Vertex[] list) {
	this.list = list;
}
Graph(int a[])
{
	list =new Vertex[a.length];
	for(int i=0;i<a.length;i++)
	{
		Vertex V=new Vertex(a[i],null);
		list[i]=V;
		
	}
}

public double findClusteringcoeff()
{
	double[] gamma=new double[this.list.length];
	double mean=0.0;
	int x=0;
	double y=0.0;
	int c=0;
	for(int i=0;i<this.list.length;i++)
	{
		x=list[i].getDegree();
		
		int[] nlist=list[i].getcluster();
		if(x!=0)
		{
			//y=(list[i].findev(nlist,list)+x+0.0);
			y=(list[i].findev(nlist,list)+0.0);
			if(y!=0&&x!=1) 
			{//Neighbourhood is not a graph
			gamma[i]=(2.0*y)/((x-1.0)*x);
			mean+=gamma[i];
			c++;
			}
		}
	}
	
	//return (mean/this.list.length);
	return (mean/(c+0.0));
}


public double shortestPath(Vertex v1)
{
	boolean[] flag=new boolean[this.list.length];
	for (int i=0;i<this.list.length;i++)
	{
		flag[i]=false;
		
	}
	flag[v1.getName()]=true;
	
	
	ArrayList<Integer> v=new ArrayList<Integer>();
	v.add(v1.getName());
	ArrayList<Level> l=new ArrayList<Level>();
	Level l1=new Level(0,v);
	l.add(l1);
	
	int i=0;
	while (l.get(i).getV().size()!=0)
	{
		l.add(new Level(i+1,new ArrayList<Integer>()));
		for(int k=0;k<(l.get(i).getV().size());k++)
		{
			int[] n=FullData.g.getList()[l.get(i).getV().get(k)].getcluster();
			for (int h=0;h<n.length;h++)
			{
				if(flag[n[h]]==false)
					{
					flag[n[h]]=true;
					l.get(i+1).getV().add(n[h]);
					
					
					}
			}
		}

		i++;
		
		
	}
	i=0;
	double sum=0.0;
	for (Level d:l)
	{
		sum+=(d.number*d.v.size());
	}
	
		return sum;
	
}

}
