package Ataraxy.Ataraxy_QCM;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Representation {
	private int[][] matrix;
	private int size;
	private int[][] matrix1;
	private int size1;
	public Representation() {
		// TODO Auto-generated constructor stub
		this.matrix = new int[0][0];
		this.size = 0;
	}
	
	public Representation(int[][] m,int size) {
		// TODO Auto-generated constructor stub
		this.matrix = m;
		this.size = size;
	}
	
	public void calcule() {
		//int[][] newMatrix = new int[size][size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(i==j) {
					matrix[i][j]=0;
				}
				else if(matrix[i][j] == 1) {
					matrix[j][i] =1;
				}
			}
		}
	}
	public void calcule1() {
		//int[][] newMatrix = new int[size][size];
		for(int i=0;i<size1;i++) {
			for(int j=0;j<size1;j++) {
				if(i==j) {
					matrix1[i][j]=0;
				}
				else if(matrix1[i][j] == 1) {
					matrix1[j][i] =1;
				}
			}
		}
	}
	
	public void multMatrix(int puissance) {
		
		int[][] old_matrix = matrix.clone();
		size1 = size;
		while(puissance >1) {
			matrix1 = new int[size][size]; 
			for(int i=0; i<size;i++) {
				for(int j=0;j<size;j++) {
					for(int k=0; k<size; k++) {
						if(matrix[i][k]==old_matrix[k][j] &&old_matrix[k][j]==1) {
							matrix1[i][j] = 1;
							break;
						}
						
						matrix1[i][j] = 0;
					}
				}
			}
			matrix = matrix1.clone();
			puissance --;
		}
	}
	
	public boolean inList(int n,int[] l) {
		for(int i = 0; i<l.length;i++) {
			if(n==l[i]) {
				return true;
			}
		}
		return false;
	}
	public void dijkstra(int start) {
		int selection = start;
		int min = 0;
		int[] banned = new int[size];
		for(int i = 0;i<size;i++) {
			banned[i]=-1;
		}
		int nBanned =0;
		int valSel = 0;
		banned[nBanned]=start;
		nBanned+=1;
		int[][] newMatrix = new int[size][size];
		for (int i = 0; i<size;i++) {
			if(i!=selection) {
			newMatrix[0][i] = 998;
			}
			else {
				newMatrix[0][i]=0;
			}
		}
		for(int i=1;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(inList(j,banned)) {
					newMatrix[i][j] = 998;
				}
				else if(matrix[selection][j]!=0 && (matrix[selection][j] + valSel)<newMatrix[i-1][j]) {
					
					newMatrix[i][j] = matrix[selection][j] + valSel;
				}
				else {
					
						newMatrix[i][j]=newMatrix[i-1][j];
				}
			}
			min = 0;
			for(int k = 1; k<size;k++) {
				if(newMatrix[i][k]<newMatrix[i][min]){
					min = k;
				}
			}
			selection = min;
			banned[nBanned]=selection;
			nBanned+=1;
			valSel = newMatrix[i][min];
			
		}
		System.out.print("\t");
		for(int a=0; a<size;a++) {
			System.out.print(Character.toUpperCase((char)(a+97))+"\t");
			
		}
		System.out.print("\n");
		System.out.print("Init\t");
		for(int j=0;j<size;j++) {
			if(newMatrix[0][j] != 998) {
			System.out.print(newMatrix[0][j]+"\t");
			}
			else {
				System.out.print("X\t");
			}
		}
		System.out.print("\n");
		for(int i=1;i<size;i++) {
			System.out.print(Character.toUpperCase((char)(banned[i-1]+97))+"\t");
			for(int j=0;j<size;j++) {
				if(newMatrix[i][j] != 998) {
				System.out.print(newMatrix[i][j]+"\t");
				}
				else {
					System.out.print("X\t");
				}
			}
			System.out.print("\n");	
		}
		System.out.print("\n");
	}
	
	public void prim(int start) {
		int selection = start;
		int min = 0;
		int poids = 0;
		int[] banned = new int[size];
		for(int i = 0;i<size;i++) {
			banned[i]=-1;
		}
		int nBanned =0;
		banned[nBanned]=start;
		nBanned+=1;
		int[][] newMatrix = new int[size][size];
		for (int i = 0; i<size;i++) {
			if(i!=selection) {
			newMatrix[0][i] = 998;
			}
			else {
				newMatrix[0][i]=0;
			}
		}
		for(int i=1;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(inList(j,banned)) {
					newMatrix[i][j] = 998;
				}
				else if(matrix[selection][j]!=0 && (matrix[selection][j] <newMatrix[i-1][j])) {
					
					newMatrix[i][j] = matrix[selection][j] ;
				}
				else {
					
						newMatrix[i][j]=newMatrix[i-1][j];
				}
			}
			min = 0;
			for(int k = 1; k<size;k++) {
				if(newMatrix[i][k]<newMatrix[i][min]){
					min = k;
				}
			}
			selection = min;
			poids += newMatrix[i][min];
			banned[nBanned]=selection;
			nBanned+=1;
			
		}
		System.out.print("\t");
		for(int a=0; a<size;a++) {
			System.out.print(Character.toUpperCase((char)(a+97))+"\t");
			
		}
		System.out.print("\n");
		System.out.print("Init\t");
		for(int j=0;j<size;j++) {
			if(newMatrix[0][j] != 998) {
			System.out.print(newMatrix[0][j]+"\t");
			}
			else {
				System.out.print("X\t");
			}
		}
		System.out.print("\n");
		for(int i=1;i<size;i++) {
			System.out.print(Character.toUpperCase((char)(banned[i-1]+97))+"\t");
			for(int j=0;j<size;j++) {
				if(newMatrix[i][j] != 998) {
				System.out.print(newMatrix[i][j]+"\t");
				}
				else {
					System.out.print("X\t");
				}
			}
			System.out.print("\n");	
		}
		System.out.print("\n");
		
		System.out.print("poids = "+poids+"\n");
		for(int i=0;i<size;i++) {
			boolean passed = false;
			for(int j=0;j<size;j++) {
				if(newMatrix[j][i] != 998) {
					passed = true;
					break;
				}
			}
			if(!passed) {
				System.out.println("arbre no exist");
				break;
			}
		}
	}
	
	public void afficher() {
		System.out.print("  ");
		for(int a=0; a<size;a++) {
			System.out.print(Character.toUpperCase((char)(a+97))+"\t");
			
		}
		System.out.print("\n");
		for(int i=0;i<size;i++) {
			System.out.print(Character.toUpperCase((char)(i+97))+" ");
			for(int j=0;j<size;j++) {
				System.out.print(matrix[i][j]+"\t");
				}
			System.out.print("\n");	
		}
		System.out.print("\n");	
	}
	
	
	public void numerotation() {
		int[] num = new int[size];
		int[][] pred = new int[size][size];
		int[] nbPred = new int[size];
		int currentNum = 1;
		ArrayList<Integer> sommet = new ArrayList<Integer>();
		for(int i =0; i<size; i++) {
			nbPred[i] =0;
			sommet.add(i);
		}
		for(int i =0;i<size;i++) {
			for(int j =0;j<size;j++) {
				if(matrix[j][i]==1) {
					pred[i][j]=1;
				}
				else {
					pred[i][j]=0;
				}
			}
		}
		while(currentNum<size) {
			for(int i =0;i<size;i++) {
				for(int j =0;j<size;j++) {
					if(pred[i][j] == 1) {
						nbPred[i]+=1;
					}
				}
				
			}
			ArrayList<Integer> sameCouche = new ArrayList<Integer>();
			int min=sommet.get(0); //�ҵ�ǰ�����ٵ��Ǹ���ĸ
			for(int i =0; i<size; i++) {
				if(nbPred[i] < nbPred[min] && nbPred[i]>=0) {
					min =i;
				}
			}
			
			for(int i =0; i<size; i++) {
				if(nbPred[i] < nbPred[min] && nbPred[i]>=0) {
					min =i;
				}
			}
			
			num[min] = currentNum; 
			
			currentNum+=1;
			nbPred[min] = -1;
			
			for (int i = 0; i < sommet.size(); i++) { 
				 
				if (sommet.get(i) == min) { 
					int tmp = sommet.get(i);
					sommet.remove(i); 
					
					break;
				}
			}
			//ɾ�������ĸ
			for(int i =0; i<size; i++) {
				pred[i][min]=0;
			}
			for(int i =0; i<sommet.size(); i++) {
				nbPred[sommet.get(i)] =0;
			}
		}
		num[sommet.get(0)]=size;
		for(int i =0; i<size; i++) {
			System.out.println(Character.toUpperCase((char)(i+97))+": "+num[i]);
		}
		
		matrix1 = new int[size][size];
		size1 = size;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(matrix[i][j] == 1) {

					matrix1[num[i]-1][num[j]-1]=1;
				}
				else {
					matrix1[num[i]-1][num[j]-1]=0;
				}
			}
		}
		System.out.print("  ");
		for(int a=0; a<size1;a++) {
			System.out.print(a+1+"\t");
			
		}
		System.out.print("\n");
		for(int i=0;i<size1;i++) {
			System.out.print(i+1+" ");
			for(int j=0;j<size1;j++) {
				System.out.print(matrix1[i][j]+"\t");
				}
			System.out.print("\n");	
		}
		System.out.print("\n");	
	}
	
	
	public void afficher1() {
		System.out.print("  ");
		for(int a=0; a<size1;a++) {
			System.out.print(Character.toUpperCase((char)(a+97))+"\t");
			
		}
		System.out.print("\n");
		for(int i=0;i<size1;i++) {
			System.out.print(Character.toUpperCase((char)(i+97))+" ");
			for(int j=0;j<size1;j++) {
				System.out.print(matrix1[i][j]+"\t");
				}
			System.out.print("\n");	
		}
		System.out.print("\n");	
	}
	
	public void inputGraph(String input) {
		StringTokenizer st = new StringTokenizer(input, "&");
		this.size1 = st.countTokens();
		this.matrix1=new int[size1][size1];
		for(int i = 0; i<size1; i++) {
			String line = st.nextToken().replaceAll("[^r\\s\\[\\]]", "");
			StringTokenizer st1 = new StringTokenizer(line, "[");
			st1.nextToken();
			int size2= st1.countTokens();
			for(int j = 0; j<size2; j++) {
				int des = 0;
				String line1 = st1.nextToken();
				while(des < line1.length() && line1.charAt(des)!=']') des++;
				this.matrix1[i][des+i] = 1;
			}
		}
		this.calcule1();
	}
	
	public void inputGraph1(String input) {
		StringTokenizer st = new StringTokenizer(input, "&");
		size1 = st.countTokens();
		matrix1=new int[size1][size1];
		for(int i = 0; i<size1; i++) {
			String line = st.nextToken().replaceAll("[^rl\\s\\[\\]]", "");
			StringTokenizer st1 = new StringTokenizer(line, "[");
			st1.nextToken();
			int size2= st1.countTokens();
			for(int j = 0; j<size2; j++) {
				int des = 0;
				int des1 =0;
				String line1 = st1.nextToken();
				while(des < line1.length() && line1.charAt(des)!=']') {
					if(line1.charAt(des)=='r') {
						des++;
						des1++;
					}
					else {
						des++;
						des1--;
					}
				}
				matrix1[i][des1+i] = 1;
			}
		}
	}
	
	public void inputMatrix(String input) {
		if(input.contains("&")) {
			//input = input.replace("\begin{array}{c|","");
			int i = 16;
			while (i < input.length() && input.charAt(i)=='c') i++;
			size = i-16;
			i=0;
			while (i < input.length() && !Character.isDigit(input.charAt(i))) i++;
			String substring = input.substring(0, i);
			input = input.replace(substring, "");
			input = input.replaceAll("[^\\d ]", "");
			int n=0;
			matrix = new int[size][size];
			
			StringTokenizer st = new StringTokenizer(input, " ");
			for(i=0;i<this.size;i++) {
				for(int j=0;j<this.size;j++) {
					this.matrix[i][j]= Integer.parseInt(st.nextToken());
				}
			}
			
		}
		else {
			int i = 0;
			System.out.println(input);
			while (i < input.length() && !Character.isDigit(input.charAt(i))) i++;
			this.size= i-1;
			System.out.println(size);
			input = input.replaceAll("[^\\d]", "");
			int n=0;
			this.matrix = new int[size][size];
			for(i=0;i<this.size;i++) {
				for(int j=0;j<this.size;j++) {
					this.matrix[i][j]= Character.getNumericValue(input.charAt(n));
					n++;
				}
			}
		}
		
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean compareGraph() {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(this.matrix[i][j] != this.matrix1[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	
	public static void main(String[] args) {
		
		while(true) {
			Ataraxy a = new Ataraxy();
			Representation r = new Representation();
			Scanner inputkb = new Scanner(System.in);
			System.out.println("saisir la matrix ou graph");
			String matrix = inputkb.nextLine();
			if(matrix.contains("array")){
				r.inputMatrix(matrix);
				a.inputMatrix(matrix);
			}
			else if(matrix.contains("{-}")) {
				a.inputGraphNoDirec(matrix);
			}
			else if(matrix.contains("\\xymatrix{")) {
				r.inputGraph1(matrix);
				a.inputGraph(matrix);
			}
			
			
			System.out.println("[1]La matrix non oriente");
			System.out.println("[2]Dijkstra (chemin le plus court)");
			System.out.println("[3]Prim (poids minimal)");
			System.out.println("[4]Produit matricielle");
			System.out.println("[5]Circuit/Chaine eulerien");
			System.out.println("[6]Representation matricielle");
			System.out.println("[7]Numerotation (les numeros peuvent etre inverse!!!)");
			System.out.println("[8]Coloration");
			System.out.println("[9]Composante connexe forte");
			System.out.println("[10]Noyau");
			System.out.println("[11]Les couches");
			
			
			int n = -1;
			while(n<1 || n >11) n = inputkb.nextInt();
			if(n==1) {
				r.calcule();
				r.afficher();
				System.out.println("verification graph[y]/[n]");
				String rep = inputkb.next().toLowerCase();
				while(!(rep.equals("y")||rep.equals("n"))) {
					System.out.println("mal saisir"+rep);
					rep = inputkb.next().toLowerCase();
					}
				if(rep.equals("n")) {
					break;
				}
				else {
					while(true) {
						System.out.println("saisir graph");
						String matrix111 = inputkb.nextLine();
						if(matrix111.contains("\\xymatrix{")) {
							r.inputGraph(matrix111);
						if(r.compareGraph()) {
							System.out.println("good");
							break;
						}
						else {
							System.out.println("wrong");
						}
						
						}
					}
				}
			}
			else if(n==2) {
				System.out.println("Init par?");
				int index = -1;
				while(index <0 || index >r.getSize()) {
					String in = inputkb.next();
					in = in.toLowerCase();
					index = (int)in.charAt(0)-97;
				}
				r.dijkstra(index);
			}
			else if (n==3){
				System.out.println("Init par?");
				int index = -1;
				while(index <0 || index >r.getSize()) {
					String in = inputkb.next();
					in = in.toLowerCase();
					index = (int)in.charAt(0)-97;
				}
				r.prim(index);
			}
			else if (n==4) {
				System.out.println("Puissance?");
				int puissance = inputkb.nextInt();
				r.multMatrix(puissance);
				r.afficher();
			}
			
			else if(n==5) {
				a.circuitEulerien();
			}
			else if(n==6) {
				r.afficher1();
			}
			else if(n==7) {
				r.numerotation();
			}
			
			else if(n==8) {
				a.coloration_v2();
			}
			else if(n==9) {
				a.connexite();
			}
			else if(n==10) {
				a.noyau();
			}
			else if(n==11) {
				System.out.println("[0]Filtration par les Puits");
				System.out.println("[1]Filtration par les Sources");
				n = inputkb.nextInt();
				while(n<0 || n >1) n = inputkb.nextInt();
				if(n==0) {
					a.numerotation_puits();
				}
				else if(n==1){
					a.numerotation_sources();
				}
			}
			
			System.out.println("Continuer? [y]/[n]");
			String rep = inputkb.next().toLowerCase();
			while(!(rep.equals("y")||rep.equals("n"))) {
				System.out.println("wrong"+rep);
				rep = inputkb.next().toLowerCase();
				}
			if(rep.equals("n")) {
				break;
			}
			matrix = "";
			
		}
		}
	//\begin{array}{c|ccccccc}& A& B& C& D& E& F& G\\\hline A& 0& 0& 0& 1& 0& 0& 0\\B& 2& 0& 6& 0& 7& 7& 10\\C& 4& 0& 0& 0& 0& 0& 2\\D& 0& 0& 0& 0& 0& 1& 0\\E& 0& 0& 7& 9& 0& 1& 1\\F& 0& 0& 3& 8& 0& 10& 0\\G& 0& 0& 0& 8& 9& 0& 3\\\end{array}
}
//\begin{array}{c|*{6}{c}} & 0 & 1 \\  \hline A & A, E, F &  \\ B \rightarrow  & A, E & C \\  \rightarrow C & E, F & C \\ D & F & A, B, C, D, E, F \\ E & F & A, B, C \\ F &  &  \\  \end{array}
//\begin{array}{c|*{9}{c}} & \alpha & \beta \\  \hline A \rightarrow  & A, B, D, F, I & D, F, H \\ B & A, C, G, I &  \\ C & A, D, G, H & C, D, E, G \\ D & C, E, F, I & C, H, I \\ E & F, H & F \\ F & E & A, B, E, F, G, H \\ G & D, F &  \\  \rightarrow H & D & A, C, G \\ I & A, C, G, H & F \\  \end{array}
