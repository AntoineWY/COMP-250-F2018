

import java.math.BigInteger;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	public int size()
	{	
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
		int index = 0;
		if(t.getCoefficient().intValue()!=0 && t.getExponent()>=0){
			
		
		// Hint: Notice that the function SLinkedList.get(index) method is O(n), 
		// so if this method were to call the get(index) 
		// method n times then the method would be O(n^2).
		// Instead, use a Java enhanced for loop to iterate through 
		// the terms of an SLinkedList.
	
			for (Term currentTerm: polynomial)
			{
				if(t.getExponent() < currentTerm.getExponent()){
					index++;
				}
			// The for loop iterates over each term in the polynomial!!
			// Example: System.out.println(currentTerm.getExponent()) should print the exponents of each term in the polynomial when it is not empty.  
			}
			if(index == polynomial.size()){
				polynomial.addLast(t);
			}
			else if( t.getExponent()!= polynomial.get(index).getExponent()){
				polynomial.add(index, t);
			}
			else{
				BigInteger newCo = polynomial.get(index).getCoefficient().add(t.getCoefficient());
				if(newCo.intValue() !=0){
					polynomial.get(index).setCoefficient(newCo);
				}
				else{
					polynomial.remove(index);
				}
			}
		}
	
	}
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
		int maxEx =0;
		if(p1.polynomial.size()!=0 && p2.polynomial.size()!=0){
			if(p1.getTerm(0).getExponent() >= p2.getTerm(0).getExponent()){
				maxEx = p1.getTerm(0).getExponent();
			}else{
				maxEx = p2.getTerm(0).getExponent();
			}
		}
		else if(p1.polynomial.size() == 0 &&p2.polynomial.size()!=0){
			maxEx = p2.getTerm(0).getExponent();
		}
		else if(p1.polynomial.size() != 0 &&p2.polynomial.size()==0){
			maxEx = p1.getTerm(0).getExponent();
		}
		else{
			return new Polynomial();
		}
		
		BigInteger[] coeArray = new BigInteger[maxEx+1];
		for(int i = 0; i<coeArray.length;i++){
			coeArray[i] = new BigInteger("0");
		}
		for(Term currentP1: p1.polynomial){
			coeArray[maxEx - currentP1.getExponent()] = currentP1.getCoefficient();
		}
		for(Term currentP2: p2.polynomial){
			coeArray[maxEx - currentP2.getExponent()] = coeArray[maxEx - currentP2.getExponent()].add(currentP2.getCoefficient());
		}
		
		Polynomial result = new Polynomial();
		for (int i=0; i< coeArray.length;i++){
			BigInteger coefficient = coeArray[i];
			Term curTerm = new Term(maxEx-i,coefficient );
			
			if(curTerm.getCoefficient().intValue()!=0){
				result.addTermLast(curTerm);
			}
			
		}
		return result;
	}
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		if (polynomial.size() == 0){
			return;
		}
		int maxEx = this.polynomial.get(0).getExponent();
		BigInteger[] coeArray = new BigInteger[maxEx+1];
		for(int i = 0; i<coeArray.length;i++){
			coeArray[i] = new BigInteger("0");
		}
		
		for(Term currentP1: polynomial){
			coeArray[maxEx - currentP1.getExponent()] = currentP1.getCoefficient();
		}
		Polynomial newPoly = new Polynomial();
		for(int i = 0; i<coeArray.length;i++){
			coeArray[i] = coeArray[i].multiply(t.getCoefficient());
			
			if(coeArray[i].intValue()!=0){
				BigInteger newCoe = coeArray[i];
				Term newTerm = new Term(maxEx-i+t.getExponent(),newCoe);
				newPoly.addTermLast(newTerm);
			}
		}
		
		this.polynomial = newPoly.polynomial;
	}
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		Polynomial result = new Polynomial();
	
		for(Term current: p2.polynomial){
			Polynomial temp = p1.deepClone();
			p1.multiplyTerm(current);
			
			result = Polynomial.add(p1, result);

			p1 = temp;
		}
		return result;
	}
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
		if(this.polynomial.size() == 0){
			return new BigInteger("0");
		}
		else{
			int maxEx = this.polynomial.get(0).getExponent();
			
			if (maxEx == 0){
				return this.polynomial.get(0).getCoefficient();
			}
			BigInteger[] coeArray = new BigInteger[maxEx+1];
			for(int i = 0; i<coeArray.length;i++){
				coeArray[i] = new BigInteger("0");
			}
			for(Term currentP1: polynomial){
				coeArray[maxEx - currentP1.getExponent()] = currentP1.getCoefficient();
			}
		
			BigInteger result = coeArray[0];
			for(int j=1; j<coeArray.length;j++){
				result = result.multiply(x).add( new BigInteger(coeArray[j]+""));
			}
			return result;
		}
	}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}
