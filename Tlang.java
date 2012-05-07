import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tuenti Programming Contest 
 * Challenge 2: TLang 
 * @author alefhidalgo [at] gmail [dot] com
 */
public class TLang {
	/* ADD OPERATION TOKEN */
	private String OP_ADD = "^=";
	/* MULTIPLICATION OPERATION TOKEN */
	private String OP_MULT = "^#";
	/* SUBTRACTION OPERATION TOKEN */
	private String OP_SUB = "^@";

	/**
	 * Solve expression
	 * @param expression
	 * @return
	 */
	public String solveExpression(String expression) {	
		/* Pattern to recognise simple expressions */
		Pattern p = Pattern.compile("[[\\^#][\\^=][\\^@]]+(\\s+[-]?\\d+)?\\s+[-]?\\d+\\$+"); 
		Matcher m = p.matcher(expression);
		//recursive until expression will be fully simplified
		if (m.find()) {
			String simpleExpression = m.group();
			String simpleExpressionSolved = solveSimpleExpression(simpleExpression);
			String simplyfiedExpression = expression.replace(simpleExpression, simpleExpressionSolved);			
			return solveExpression(simplyfiedExpression);
		} else {			
			//at this point the expression is solved
			return expression;
		}
	}
	
	/**
	 * Solve Simple Expression 
	 * @param simpleExpression
	 *            ^# xxx yyy$..$ or ^@ yyy$..$
	 * @return
	 */
	private String solveSimpleExpression(String simpleExpression) {
		BigDecimal number1 = null;
		BigDecimal number2 = null;
		BigDecimal result = null;
		String mod = "";
		Scanner exprScn = new Scanner(simpleExpression);		
		List<String> arrExpr = new ArrayList<String>();
		while (exprScn.hasNext()) {
			arrExpr.add(exprScn.next());
		}		
		//Type1 ^@ yyy$..$
		if (arrExpr.size() == 2) {
			mod = arrExpr.get(1).replaceFirst("[-]?\\d+\\$", "");
			number1 = new BigDecimal(arrExpr.get(1).replaceAll("\\$", ""));
			if (arrExpr.get(0).equals(OP_SUB)) {
				result = number1.negate();
			}else {
				result = number1;
			}
		//Type2 ^# xxx yyy$..$
		} else {
			number1 = new BigDecimal(arrExpr.get(1));
			number2 = new BigDecimal(arrExpr.get(2).replaceAll("\\$", ""));
			mod = arrExpr.get(2).replaceFirst("[-]?\\d+\\$", "");
			if (arrExpr.get(0).equals(OP_ADD)) {
				result = number1.add(number2);
			} else if (arrExpr.get(0).equals(OP_MULT)) {
				result = number1.multiply(number2);
			} else if (arrExpr.get(0).equals(OP_SUB)) {
				result = number1.subtract(number2);
			}
		}
		return result.toPlainString() + mod;
	}
	
	public static void main(String args[]) {
		TLang tLang = new TLang();
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			System.out.println(tLang.solveExpression(in.nextLine()));
		}
	}
}