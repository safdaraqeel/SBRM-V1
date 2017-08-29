package SBRM.RM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtractRuleFromDescisionTree {
	
	
    public static void write(String filePath, String content, boolean append)
    {
        try
        {
            if (filePath == null)
            {
                filePath = "J48Rules.txt";
            }
            
            File file = new File(filePath);

            PrintWriter out = null;
            if ( file.exists() ) {
                out = new PrintWriter(new FileOutputStream(new File(filePath), append));
            }
            else {
                out = new PrintWriter(filePath);
            }
            out.println(content);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

	public static void extractRules(String inputFilePath,String outputFilePath) throws IOException {
		write(outputFilePath, "", false);	// to clear the file
	    String tree = new String(Files.readAllBytes(Paths.get(inputFilePath)), StandardCharsets.UTF_8);
		String[] lines = tree.split(System.getProperty("line.separator"));

		
		Node root = new Node();
		root.childern = readTree(Arrays.asList(lines), 0);
		getRules (outputFilePath, root) ;

	}

	public static void getRules (String outputFilePath, Node node) {
		//root.path=root.description;
		if (node.childern.size()>0){
			for (int i = 0; i <node.childern.size(); i++) {
				if (node.path.equals("")){
					node.childern.get(i).path=node.childern.get(i).description;
				}
				else{
					node.childern.get(i).path=node.path + " AND "+node.childern.get(i).description;
				}
				getRules(outputFilePath, node.childern.get(i)) ;
			}
		}
		else {
			System.out.println(node.path);
			write(outputFilePath, node.path, true);
		}
	}
	


	public static List<Node> readTree(List<String> lines, Integer depth) {
		List<Node> nodes = new ArrayList<Node>();
		if (lines.isEmpty()) {
			return null;
		}
		List<Integer> depthIndexList = new ArrayList<Integer>();
		Integer index = 0;
		for (String line : lines) {
			if (depth(line).equals(depth)) {
				depthIndexList.add(index);
			}
			index++;
		}

		Integer count = 0;
		for (Integer location : depthIndexList) {
			Node node = new Node();
			String line = lines.get(location);
			String tokens[] = line.split("\\|");
			node.description = tokens[tokens.length - 1].trim();
			if (count + 1 < depthIndexList.size()) {
				List<String> subList = lines.subList(location + 1, depthIndexList.get(count + 1));
				List<Node> childern = readTree(subList, depth + 1);
				if (childern != null) {
					node.childern = childern;
				}
			} else {
				List<String> subList = lines.subList(location + 1, lines.size());
				List<Node> childern = readTree(subList, depth + 1);
				if (childern != null) {
					node.childern = childern;
				}
			}
			nodes.add(node);
			count++;
		}
		return nodes;
	}

	private static Integer depth(String line) {
		return line.split("\\|").length - 1;
	}

	private static class Node {
		public String description;
		public List<Node> childern = new ArrayList<Node>();
		public String path="";
	}

	public static void main(String args[]) throws IOException {

		
		extractRules("tree.txt","J48Rules.txt");
	}

}
