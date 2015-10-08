//package create;

public class Vertex {

private int name;
private Neighbour list;
Vertex(int name,Neighbour n)
{
	
	this.name=name;
	this.list=n;
}
public int getName() {
	return name;
}
public void setName(int name) {
	this.name = name;
}
public Neighbour getList() {
	return list;
}
public void setList(Neighbour list) {
	this.list = list;
}

public int getDegree()
{
	int count=0;
//	System.out.println("in getdegree");
	Neighbour n=this.list;
	while(n!=null)
	{
		
		n=n.getNext();
		count ++;
		
	}
//	System.out.println("degree:"+count);
	return count;
}
public int[] getcluster()
{
	int i=0;
//	System.out.println("get cluster");
	Neighbour n=this.list;
	int[] lst=new int[this.getDegree()];
	while(n!=null)
	{
		lst[i]=n.getVn();
//		System.out.println("number"+list.getVn());
		n=n.getNext();
		
		i++;
		
	}

return lst;
}
public boolean checkEdge(Vertex v1,Vertex v2)
{
	int[] x=v1.getcluster();
	for(int i=0;i<x.length;i++)
	{
		if(x[i]==v2.name)
		{
			return true;
		}
	}
	return false;
	
}
public int findev(int[] nlist,Vertex[] v) {
	// TODO Auto-generated method stub
	int count=0;
	for(int i=0;i<nlist.length;i++)
	{
		for(int j=i+1;j<nlist.length;j++)
		{
			if(checkEdge(v[nlist[i]], v[nlist[j]]))
			{
				count++;
			}
		}
	}
	return count;
}
}
