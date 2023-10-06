package metier;

public class Coup
{
	private int colDep;
	private int ligDep;

	private int colArr;
	private int ligArr;

	public Coup(int colDep, int ligDep, int colArr, int ligArr)
	{
		this.colDep = colDep;
		this.ligDep = ligDep;

		this.colArr = colArr;
		this.ligArr = ligArr;
	}

	public int getColDep() {return this.colDep;}
	public int getLigDep() {return this.ligDep;}
	public int getColArr() {return this.colArr;}
	public int getLigArr() {return this.ligArr;}
}