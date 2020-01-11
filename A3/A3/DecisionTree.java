import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf



		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}



		// this method takes in a datalist (ArrayList of type datum) and a minSizeInClassification (int) and returns
		// the calling DTNode object as the root of a decision tree trained using the datapoints present in the
		// datalist variable
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {
			if (datalist == null) {
                throw new IllegalArgumentException("Null datalist input!");
            }
			
			double dataEntropy = calcEntropy(datalist); 
			if(datalist.size() < minSizeDatalist || dataEntropy == 0){ // Early stop for overfitting or a pure list 			
				this.left = null;
                this.right = null;
                this.label = findMajority(datalist);
                this.leaf = true;
                return this;
				}
			else{
				double bestAvgH = Double.MAX_VALUE;
				int bestAttribute = -1;
				double bestThreshold = -1;
				int[] attributeArr ={0,1};
				for (int i = 0; i<attributeArr.length; i++){
					
					for(Datum cur: datalist){
						double currentAvgH;
						ArrayList<Datum> d1 = new ArrayList<Datum>();
						ArrayList<Datum> d2 = new ArrayList<Datum>();				
						for(Datum secondCur: datalist){
							if (secondCur.x[i]<cur.x[i]){
								d1.add(secondCur);
							}
							else{
								d2.add(secondCur);
							}
						}

						currentAvgH = ( (double)d1.size() / (double)(d1.size()+d2.size()) ) * calcEntropy(d1) + 
								( (double)d2.size() / (double)(d1.size()+d2.size()) ) * calcEntropy(d2);
						if (bestAvgH > currentAvgH){
							bestAvgH = currentAvgH;
							bestAttribute = attributeArr[i];
							bestThreshold = cur.x[attributeArr[i]];
							}
						}
					}
				
				if (bestAttribute != -1){ // there is a possible split and create internal questions
					this.leaf = false;
					this.attribute = bestAttribute;
					this.threshold = bestThreshold;
				}else{  // for no further split create leaf node like base case
					 this.left = null;
	                 this.right = null;
	                 this.label = findMajority(datalist);
	                 this.leaf = true;
	                 return this;
				}

				
				ArrayList<Datum> smallerHalf = new ArrayList<Datum>();
				ArrayList<Datum> largerHalf = new ArrayList<Datum>();
				
				for(Datum a: datalist){		
					if (a.x[bestAttribute]< bestThreshold){
						smallerHalf.add(a);
					}
					else{
						largerHalf.add(a);
					}
				}
			    
				this.left = (new DTNode()).fillDTNode(smallerHalf);
				this.right = (new DTNode()).fillDTNode(largerHalf);
				return this;			

			}
		}
			
		//This is a helper method. Given a datalist, this method returns the label that has the most
		// occurences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist)
		{
			int l = datalist.get(0).x.length;
			int [] votes = new int[l];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			int max = -1;
			int max_index = -1;
			//find the label with the max occurrences
			for (int i = 0 ; i < l ;i++)
			{
				if (max<votes[i])
				{
					max = votes[i];
					max_index = i;
				}
			}
			return max_index;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {
			if(this.leaf){
				return this.label;
			}else{
				int attriChosen = this.attribute;
				if(xQuery[attriChosen]<this.threshold){
					return this.left.classifyAtNode(xQuery);
				}else{
					return this.right.classifyAtNode(xQuery);
				}
			}
		}


		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter
		public boolean equals(Object dt2)
		{
			
			if(!(dt2 instanceof DTNode)){
				return false;
			}
				DTNode query = (DTNode)dt2;

				
				if (this.leaf && query.leaf){
					
					if(this.label == query.label){
						return true;
					}
					else
						return false;
				}
				else if ((!this.leaf && query.leaf) ||(this.leaf && !query.leaf)){
					return false;
				}
				else{
					if((this.attribute != query.attribute) || (this.threshold != query.threshold)){
						return false;
					}
				}		
	
			
			if (this.left.equals(query.left) && this.right.equals(query.right)){
				return true;
			}
			


			return false;
		}
	}



	//Given a dataset, this retuns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist)
	{
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
	int classify(double[] xQuery ) {
		DTNode node = this.rootDTNode;
		return node.classifyAtNode( xQuery );
	}

    // Checks the performance of a DecisionTree on a dataset
    //  This method is provided in case you would like to compare your
    //results with the reference values provided in the PDF in the Data
    //section of the PDF

    String checkPerformance( ArrayList<Datum> datalist)
	{
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}

}
