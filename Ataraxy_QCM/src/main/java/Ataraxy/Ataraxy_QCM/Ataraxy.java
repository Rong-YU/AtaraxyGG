package Ataraxy.Ataraxy_QCM;

import org.jgrapht.*;
import org.jgrapht.Graphs;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.color.LargestDegreeFirstColoring;
import org.jgrapht.alg.color.SaturationDegreeColoring;
import org.jgrapht.alg.color.SmallestDegreeLastColoring;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.experimental.BrownBacktrackColoring;
import org.jgrapht.graph.*;

import java.util.*;
import java.util.Map.Entry;

import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.SupplierUtil;

public class Ataraxy {
	private Graph<String, DefaultEdge> g;
	private Graph<String, DefaultEdge> g1;
	public void inputMatrix(String input) {
		
			//input = input.replace("\begin{array}{c|","");
			g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
			int i = 16;
			while (i < input.length() && input.charAt(i)=='c') i++;
			int size = i-16;
			i=0;
			while (i < input.length() && !Character.isDigit(input.charAt(i))) i++;
			String substring = input.substring(0, i);
			input = input.replace(substring, "");
			input = input.replaceAll("[^\\d ]", "");
			int n=0;
			for(i = 0; i<size; i++) {
				char lettre = Character.toUpperCase((char)(i+97));
				String strLettre = Character.toString(lettre);
				g.addVertex(strLettre);
			}
			
			StringTokenizer st = new StringTokenizer(input, " ");
			for(i=0;i<size;i++) {
				char lettre = Character.toUpperCase((char)(i+97));
				String strLettre = Character.toString(lettre);
				for(int j=0;j<size;j++) {
					char lettreDes = Character.toUpperCase((char)(j+97));
					String strLettreDes = Character.toString(lettreDes);
					if(Integer.parseInt(st.nextToken())==1) {
						g.addEdge(strLettre, strLettreDes);
					}
				}
			}
			
	}
	
	public void inputGraph(String input) {
		g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		StringTokenizer st = new StringTokenizer(input, "&");
		int size = st.countTokens();
		for(int i = 0; i<size; i++) {
			char lettre = Character.toUpperCase((char)(i+97));
			String strLettre = Character.toString(lettre);
			g.addVertex(strLettre);
		}
		for(int i = 0; i<size; i++) {
			String line = st.nextToken().replaceAll("[^rl\\s\\[\\]]", "");
			StringTokenizer st1 = new StringTokenizer(line, "[");
			char lettre = Character.toUpperCase((char)(i+97));
			String strLettre = Character.toString(lettre);
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
				char lettreDes = Character.toUpperCase((char)(i+97+des1));
				String strLettreDes = Character.toString(lettreDes);
				g.addEdge(strLettre, strLettreDes);
			}
		}
	    
	}
	
	public void inputGraphNoDirec(String input) {
		g = new DefaultUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		StringTokenizer st = new StringTokenizer(input, "&");
		int size = st.countTokens();
		for(int i = 0; i<size; i++) {
			char lettre = Character.toUpperCase((char)(i+97));
			String strLettre = Character.toString(lettre);
			g.addVertex(strLettre);
		}
		for(int i = 0; i<size; i++) {
			String line = st.nextToken().replaceAll("[^rl\\s\\[\\]]", "");
			StringTokenizer st1 = new StringTokenizer(line, "[");
			char lettre = Character.toUpperCase((char)(i+97));
			String strLettre = Character.toString(lettre);
			st1.nextToken();
			int size2= st1.countTokens();
			for(int j = 0; j<size2; j++) {
				int des = 0;
				String line1 = st1.nextToken();
				while(des < line1.length() && line1.charAt(des)!=']') {
					des++;
				}
				char lettreDes = Character.toUpperCase((char)(i+97+des));
				String strLettreDes = Character.toString(lettreDes);
				g.addEdge(strLettre, strLettreDes);
			}
		}
		
	}
	
	
	public void coloration() {
		
		LargestDegreeFirstColoring<String, DefaultEdge> coloration =
	            new LargestDegreeFirstColoring<>(g);
		System.out.println(coloration.getColoring());
		Map<String, Integer> colors = coloration.getColoring().getColors();
		int size = colors.size();
		String[] keys = new String[size];
		int i =  0;
		for ( String key : colors.keySet() ) {
		    keys[i] = key;
		    i++;
		}
		
		String s = "";
		for(i=0;i<size;i++) {
			int max=i;
			System.out.println("max"+max);
			for(int j =i+1;j<size;j++) {
				System.out.println("j"+j);
				if(g.degreeOf(keys[j]) > g.degreeOf(keys[max]) || (g.degreeOf(keys[j]) >= g.degreeOf(keys[max]) && (int)keys[j].charAt(0)<(int)keys[max].charAt(0))) {
					max = j;
				}
			}
			String tmp = keys[i];
			keys[i] = keys[max];
			keys[max]= tmp;
			
		}
		i=0;
		for(String key : keys ) {
			int n = colors.get(keys[i]) + 1;
			s+=keys[i]+"="+n+" ";
			i++;
		}
		System.out.println(s);
		/*
		//verifier si la reponse est juste
		while(true) {
		Scanner input = new Scanner(System.in);
		String in = input.nextLine();
		int loc = in.indexOf("Coul");
		int loc1 = in.indexOf("Som");
		int loc2 = in.indexOf("DSAT_{1}");
		String sommet = in.substring(loc1+4,loc2);
		System.out.println(sommet);
		StringTokenizer st = new StringTokenizer(sommet,"&");
		int size1 = st.countTokens();
		String[] somList = new String[size1];
		int i=0;
		while(st.hasMoreTokens()) {
			somList[i] = Character.toString(st.nextToken().charAt(1));
			System.out.println(somList[i]);
			i++;
		}
		
		
		String coul = in.substring(loc+5,in.length()-1);
		System.out.println(coul);
		StringTokenizer st1 = new StringTokenizer(coul,"&");
		int size = st1.countTokens();
		int[] coulList = new int[size];
		i=0;
		while(st1.hasMoreTokens()) {
			coulList[i] = Character.getNumericValue(st1.nextToken().charAt(1));
			System.out.println(coulList[i]);
			i++;
		}

		int numCol =1;
		boolean wrong = false;
		while(numCol<nbColor && !wrong) {
			System.out.println("while");
			ArrayList<Integer> sameColor = new ArrayList<Integer>();
			for(int j=0;j<coulList.length;j++) {
				if(coulList[j]==numCol) {
				
					sameColor.add(j);
					System.out.println("added"+j);
				}
			}
			for(i = 0; i<sameColor.size();i++) {
				for(int j = 0; j<sameColor.size();j++) {
					System.out.println(somList[sameColor.get(i)]+" ----> "+somList[sameColor.get(j)]);
					if(g.containsEdge(somList[sameColor.get(i)], somList[sameColor.get(j)])) {
						wrong = true;
						System.out.println("wrong answer!");
						break;
					}
				}
				if(wrong) {
					break;
				}
			}
			numCol++;
		}
		if(wrong) {
			System.out.println("wrong answer!");
		}
		else {
			System.out.println("good answer!");
			break;
		}
		System.out.println(numCol);
		}
		*/
		//verifier les deux sommets qui ont le meme couleur a une arc
		// si oui return false
		
	}
	
	public void numerotation_sources() {
		int n =1 ;
		Iterator<String> iter = new DepthFirstIterator<>(g);
		ArrayList<String> numerotation = new ArrayList<String>();
		ArrayList<String> sources = new ArrayList<String>();
		ArrayList<String> couches = new ArrayList<String>();
		while(true) {
			while (iter.hasNext()) {
				String vertex = iter.next();
				if(g.inDegreeOf(vertex) == 0) {
					sources.add(vertex);
				}
			}
			String s = "{ ";
			for(int i = 0; i<sources.size();i++) {
				numerotation.add(sources.get(i)+"="+n);
				s+=sources.get(i)+" ";
				g.removeVertex(sources.get(i));
				n+=1;
				iter = new DepthFirstIterator<>(g);
			}
			s+="}";
			couches.add(s);
			sources.clear();
			if(!iter.hasNext()) {
				break;
			}
		}
		System.out.println("Numbre de couches: "+ couches.size());
		for(int i = 0;i<couches.size();i++) {
			n = i;
			n= i+1;
			System.out.println("src"+n+" : "+couches.get(i));
		}
		
	}
	
	public void numerotation_puits() {
		int n =1 ;
		Iterator<String> iter = new DepthFirstIterator<>(g);
		ArrayList<String> numerotation = new ArrayList<String>();
		ArrayList<String> sources = new ArrayList<String>();
		ArrayList<String> couches = new ArrayList<String>();
		while(true) {
			while (iter.hasNext()) {
				String vertex = iter.next();
				if(g.outDegreeOf(vertex) == 0) {
					sources.add(vertex);
				}
			}
			String s = "{ ";
			for(int i = 0; i<sources.size();i++) {
				numerotation.add(sources.get(i)+"="+n);
				s+=sources.get(i)+" ";
				g.removeVertex(sources.get(i));
				n+=1;
				iter = new DepthFirstIterator<>(g);
			}
			s+="}";
			couches.add(s);
			sources.clear();
			if(!iter.hasNext()) {
				break;
			}
		}
		System.out.println("Numbre de couches: "+ couches.size());
		for(int i = 0;i<couches.size();i++) {
			n = i;
			n= i+1;
			System.out.println("pts"+n+" : "+couches.get(i));
		}
	}
	
	
	public void connexite() {
		// computes all the strongly connected components of the directed graph
        StrongConnectivityAlgorithm<String, DefaultEdge> scAlg =
            new KosarajuStrongConnectivityInspector<>(g);
        List<Graph<String, DefaultEdge>> stronglyConnectedSubgraphs =
            scAlg.getStronglyConnectedComponents();

        // prints the strongly connected components
        System.out.println("composant connexe:");
        for (int i = 0; i < stronglyConnectedSubgraphs.size(); i++) {
            System.out.println(stronglyConnectedSubgraphs.get(i));
        }
        System.out.println();
	}
	
	public void noyau() {
		ArrayList<String> noyau = new ArrayList<String>();
		
		//if(g.outDegreeOf("A")==0) {
		//	g.removeVertex("A");
		//}
		Iterator<String> iter = new DepthFirstIterator<>(g);
        while (iter.hasNext()) {
            String vertex = iter.next();
            
            if(g.outDegreeOf(vertex)==0) {
            	
            	Iterator<String> iter1 = new DepthFirstIterator<>(g);
            	while(iter1.hasNext()) {
            		String vertexSource = iter1.next();
            		if(g.containsEdge(vertexSource, vertex)) {
            			g.removeVertex(vertexSource);
            			
            			iter1 = new DepthFirstIterator<>(g);
            		}
            	}
            	
        		noyau.add(vertex);
        		g.removeVertex(vertex);
        		iter = new DepthFirstIterator<>(g);
            }
            
        }
        String print = "Noyau = { ";
        for(int i = 0; i<noyau.size();i++) {
        	print += noyau.get(i) + " ";
        }
        System.out.println(print + "}");
	}
	
	public void circuitEuler() {
		HierholzerEulerianCycle eu = new HierholzerEulerianCycle();
		if(eu.isEulerian(g)) {
			System.out.println("good");
		}
	}
	
	public void circuitEulerien() {
		int nbImpair =0;
		Iterator<String> iter = new DepthFirstIterator<>(g);
        while (iter.hasNext()) {
        	String vertex = iter.next();
        	if(g.degreeOf(vertex)%2 !=0) {
        		nbImpair += 1;
        	}
		}
		if(nbImpair ==2) {
			System.out.println("Il exist une chaine eulerien");
		}
		else if(nbImpair ==0) {
			System.out.println("Il exist un circuit eulerien");
		}
		else {
			System.out.println("pas de circuit, pas de chaine");
		}
	}
	
	public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
				oriMap.entrySet());
		Collections.sort(entryList, new MapValueComparator());
 
		Iterator<Map.Entry<String, String>> iter = entryList.iterator();
		Map.Entry<String, String> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}

	
	public void coloration_v2() {
		Iterator<String> iter = new DepthFirstIterator<>(g);
		//ArrayList<Integer> dast = new ArrayList<Integer>();
		Map<String, String> dast = new HashMap<String, String>();
		Map<String, Integer> colored = new HashMap<String, Integer>();
        while (iter.hasNext()) {
        	String vertex = iter.next();
        	dast.put(vertex,String.valueOf(g.outDegreeOf(vertex)));
        	}
        dast = sortMapByValue(dast);

        //brelaz start
        for (Map.Entry<String, String> entry : dast.entrySet()) { 
        	String vertex = entry.getKey();
        	Map.Entry<String, String> maxEntry = null;

        	for (Map.Entry<String, String> testentry : dast.entrySet())
        	{
        	    if (!colored.containsKey(testentry.getKey()) && (maxEntry == null || Integer.valueOf(testentry.getValue()) >Integer.valueOf(maxEntry.getValue()) ))
        	    {
        	        maxEntry = testentry;
        	    }
        	}
        	vertex = maxEntry.getKey();
        	
        	if(!colored.containsKey(vertex)) {
        		Iterator<String> voisin = new DepthFirstIterator<>(g);
        		int numCol = 1;
        		boolean end = false;
        		while(!end) {
        			end = true;
	        		for(Map.Entry<String, Integer> coloredList : colored.entrySet()) {
	            		if(g.containsEdge(vertex, coloredList.getKey()) && coloredList.getValue() == numCol) {
	                		numCol ++;
	                		end=false;
	                	}
	            	}
        		}
        		colored.put(vertex, numCol);
        		
        		while (voisin.hasNext()) {
                	String vertexVoisin = voisin.next();
                	int nbVoisinColore = 0;
                	for(Map.Entry<String, Integer> coloredList : colored.entrySet()) {
                		if(g.containsEdge(vertexVoisin, coloredList.getKey())) {
                    		nbVoisinColore ++;
                    	}
                	}
                	if(nbVoisinColore>0) {
            			dast.put(vertexVoisin,String.valueOf(nbVoisinColore));
            		}
                }
        		
        	}
   
        	
        }
        for (Map.Entry<String, Integer> entry : colored.entrySet()) {
			dast.put(entry.getKey(),String.valueOf(entry.getValue()));
		}
        System.out.print("Som	");
        for (Map.Entry<String, String> entry : dast.entrySet()) {
			System.out.print(entry.getKey() + " ");
		}
        System.out.println();
        System.out.print("Coul	");
        for (Map.Entry<String, String> entry : dast.entrySet()) {
			System.out.print(entry.getValue() + " ");
		}
        System.out.println();
	}
	
	
	public static void main(String[] args) {
		Ataraxy a = new Ataraxy();
		a.inputMatrix("\\begin{array}{c|cccccccccccc}& A& B& C& D& E& F& G& H& I& J& K& L\\\\\\hline A& 0& 1& 0& 1& 1& 1& 0& 0& 0& 0& 0& 0\\\\B& 1& 0& 1& 1& 0& 0& 0& 1& 0& 0& 0& 1\\\\C& 0& 1& 0& 0& 1& 1& 0& 0& 0& 0& 1& 0\\\\D& 1& 1& 0& 0& 0& 1& 1& 0& 0& 1& 1& 0\\\\E& 1& 0& 1& 0& 0& 0& 0& 0& 1& 0& 1& 0\\\\F& 1& 0& 1& 1& 0& 0& 1& 0& 1& 1& 0& 0\\\\G& 0& 0& 0& 1& 0& 1& 0& 0& 0& 0& 1& 1\\\\H& 0& 1& 0& 0& 0& 0& 0& 0& 1& 0& 0& 0\\\\I& 0& 0& 0& 0& 1& 1& 0& 1& 0& 1& 1& 1\\\\J& 0& 0& 0& 1& 0& 1& 0& 0& 1& 0& 1& 0\\\\K& 0& 0& 1& 1& 1& 0& 1& 0& 1& 1& 0& 1\\\\L& 0& 1& 0& 0& 0& 0& 1& 0& 1& 0& 1& 0\\\\\\end{array}");
		a.coloration_v2();
	}
}
	
	class MapValueComparator implements Comparator<Map.Entry<String, String>> {
		 
		@Override
		public int compare(Entry<String, String> me1, Entry<String, String> me2) {
	 
			return me2.getValue().compareTo(me1.getValue());
		}


	}
