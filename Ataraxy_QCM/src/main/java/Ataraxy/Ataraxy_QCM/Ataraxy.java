package Ataraxy.Ataraxy_QCM;

import org.jgrapht.*;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.color.SaturationDegreeColoring;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;

import java.util.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.SupplierUtil;

public class Ataraxy {
	private Graph<String, DefaultEdge> g;
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
		SaturationDegreeColoring<String, DefaultEdge> coloration =
	            new SaturationDegreeColoring<>(g);
		System.out.println(coloration.getColoring());
		
		//verifier si la reponse est juste
		Scanner input = new Scanner(System.in);
		String in = input.nextLine();
		int loc = in.indexOf("Coul");
		String coul = in.substring(loc+5,in.length()-1);
		System.out.println(coul);
		StringTokenizer st1 = new StringTokenizer(coul,"&");
		int size = st1.countTokens();
		int[] coulList = new int[size];
		int i=0;
		while(st1.hasMoreTokens()) {
			coulList[i] = Character.getNumericValue(st1.nextToken().charAt(1));
			System.out.println(coulList[i]);
			i++;
		}
		
		//verifier les deux sommets qui ont le meme couleur a une arc
		// si oui return false
	}
	
	public void test() {
		Scanner input = new Scanner(System.in);
		String in = input.nextLine();
		int loc = in.indexOf("Coul");
		String coul = in.substring(loc+5,in.length()-1);
		System.out.println(coul);
		StringTokenizer st1 = new StringTokenizer(coul,"&");
		int size = st1.countTokens();
		int[] coulList = new int[size];
		int i=0;
		while(st1.hasMoreTokens()) {
			coulList[i] = Character.getNumericValue(st1.nextToken().charAt(1));
			System.out.println(coulList[i]);
			i++;
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
	
	
	public static void main(String[] args) {
		Ataraxy a = new Ataraxy();
		a.test();
	}
}
